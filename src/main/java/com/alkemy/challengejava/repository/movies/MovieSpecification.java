package com.alkemy.challengejava.repository.movies;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alkemy.challengejava.dto.movies.MovieFiltersDTO;
import com.alkemy.challengejava.entity.MovieEntity;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getSpecsByFilters(MovieFiltersDTO filts) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new LinkedList<>();

            if (StringUtils.hasLength(filts.getTitle())) {
                predicates.add(
                    criteriaBuilder.like(
                        root.get("title"),
                        "%" + root.get("title") + "%"
                    )
                );
            }

            if (StringUtils.hasLength(filts.getImage())) {
                predicates.add(
                    criteriaBuilder.like(
                        root.get("image"),
                        "%" + root.get("image") + "%"
                    )
                );
            }

            if(filts.getCreationDateLess() != null){
                predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(
                        root.get("creationDate"), filts.getCreationDateLess()
                    )
                );
            }

            if(filts.getCreationDateMore() != null){
                predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                        root.get("creationDate"), filts.getCreationDateMore()
                    )
                );
            } 

            query.distinct(true);

            if(filts.isASC()){
                query.orderBy(criteriaBuilder.asc(root.get("title")));
            }else{
                query.orderBy(criteriaBuilder.desc(root.get("title")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
