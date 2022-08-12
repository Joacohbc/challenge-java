package com.alkemy.challengejava.repository.characters;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alkemy.challengejava.dto.characters.CharacterFiltersDTO;
import com.alkemy.challengejava.entity.CharacterEntity;
import com.alkemy.challengejava.entity.MovieEntity;

@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getSpecsByFilters(CharacterFiltersDTO filts) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();

            if (StringUtils.hasLength(filts.getName())) {
                // * WHERE WHERE charactert.name LIKE '%{name}%'
                predicates.add(
                        criteriaBuilder.like(root.get("name"),
                                "%" + filts.getName().toLowerCase() + "%"));
            }

            if (filts.getAge() != null) {
                // * WHERE charactert.age = {age}
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), filts.getAge()));
            }

            if (filts.getWeight() != null) {
                // WHERE charactert.weight = {weight}
                predicates.add(
                        criteriaBuilder.equal(root.get("weight"), filts.getWeight()));
            }

            Set<Long> movies = filts.getMovies();
            if (!CollectionUtils.isEmpty(movies)) {

                // Se genera una setencia algo asi:
                /*
                 * 1 - SELECT charactert.id_character FROM charactert
                 * 2 - INNER JOIN movie_character mc ON mc.id_character = charactert.id_character
                 * 3 - INNER JOIN movie ON movie.id_movie = mc.id_movie
                 * 4 - WHERE movie.id_movie IN (10);
                 */

                // Aca se realiza el INNER JOIN entre Movies y Character (2 y 3)
                Join<MovieEntity,CharacterEntity> join = root.join("movies", JoinType.INNER);
                
                // Aca obtengo del JOIN los ID de los Movies que participaron en peliculas (1)
                Expression<String> moviesId = join.get("id");

                // Y aca hago un IN con todos los IDs de las peliculas donde participaron (4)
                predicates.add(moviesId.in(movies));
            }

            // * Que aplique un DISTINC al resultado
            query.distinct(true);

            // * Aplico un ORDER BY name ASC/DESC
            if(filts.isASC()){
                query.orderBy(criteriaBuilder.asc(root.get("name")));
            }else{
                query.orderBy(criteriaBuilder.desc(root.get("name")));
            }

            // Y uno todos las condiciones (predicados) de manera que se deben cumplir todos (con un AND)
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
