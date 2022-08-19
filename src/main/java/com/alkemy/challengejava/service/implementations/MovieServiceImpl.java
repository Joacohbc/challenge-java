package com.alkemy.challengejava.service.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alkemy.challengejava.dto.ErrorDTO;
import com.alkemy.challengejava.dto.GenreDTO;
import com.alkemy.challengejava.dto.characters.CharacterDTO;
import com.alkemy.challengejava.dto.movies.MovieDTO;
import com.alkemy.challengejava.dto.movies.MovieFiltersDTO;
import com.alkemy.challengejava.entity.MovieEntity;
import com.alkemy.challengejava.entity.Rating;
import com.alkemy.challengejava.mapper.MovieMapper;
import com.alkemy.challengejava.repository.movies.MovieRepository;
import com.alkemy.challengejava.repository.movies.MovieSpecification;
import com.alkemy.challengejava.service.CharacterService;
import com.alkemy.challengejava.service.GenreService;
import com.alkemy.challengejava.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private MovieSpecification specification;

    @Autowired
    private MovieMapper mapper;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private GenreService genreService;

    @Override
    public boolean existMovie(Long id) {
        return repository.existsById(id);
    }

    private void throwErrorIfNotExits(Long id) {
        if (!existMovie(id)) {
            throw new ErrorDTO("No existe una pelicula con el ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public MovieDTO saveMovie(MovieDTO dto) throws ErrorDTO {

        if (dto.getId() != null) {
            throw new ErrorDTO("No puede asignar un ID a una Pelicula en su creacion", HttpStatus.BAD_REQUEST);
        }

        // Por si no ingresa ningun Personaje no tire error
        if (dto.getCharacters() == null) {
            dto.setCharacters(new HashSet<>());
        } else {

            // Aqui guardare los Personajes con todos sus atributos (sacados de la BD)
            // Para evitar que el CascadeType.MERGE actualice campos en Personajes que ya
            // existen. Y evitar que cree personajes nuevos (solo se pueden crear desde el
            // POST - /characters)
            Set<CharacterDTO> charactersFulls = new HashSet<>();

            // Listo los Personajes de la Pelicula
            for (CharacterDTO c : dto.getCharacters()) {
                try {
                    // Verifico que existan (getCharacter()) y los guardo en mi nuevo Set
                    charactersFulls.add(characterService.getCharacter(c.getId()));
                } catch (ErrorDTO e) {
                    throw new ErrorDTO("El personaje que intenta agregar (con ID " + c.getId() + ") no existe",
                            HttpStatus.BAD_REQUEST);
                }
            }

            // Vuelvo a setter los Personajes con toda su informacion (atributos), para
            // retornar los Personajes con toda su informacion
            dto.setCharacters(charactersFulls);
        }

        // Por si no ingresa ningun Genero que no tire error
        if (dto.getGenres() == null) {
            dto.setGenres(new HashSet<>());
        } else {
            // Aqui guardate los Generos con todos sus atributos (sacados de la BD)
            // Para evitar que el CascadeType.MERGE actualice campos en Generos que ya
            // existen. Y evitar que cree generos nuevos (solo se pueden crear desde el
            // POST - /genres)
            Set<GenreDTO> genresFulls = new HashSet<>();
            for (GenreDTO g : dto.getGenres()) {
                try {
                    genresFulls.add(genreService.getGenre(g.getId()));
                } catch (ErrorDTO e) {
                    throw new ErrorDTO("El genero que intenta agregar (con ID " + g.getId() + ") no existe",
                            HttpStatus.BAD_REQUEST);
                }
            }

            // Vuelvo a setter los Generos con toda su informacion (atributos), para
            // retornar los Generos con toda su informacion
            dto.setGenres(genresFulls);
        }

        // Valido por si pone 0 en el Rating
        if (dto.getRating() == Rating.INVALID_OPTION) {
            dto.setRating(Rating.UNA_ESTRELLA);
        }

        MovieEntity entity = mapper.DTO2Entity(dto, true);
        return mapper.Entity2DTO(repository.save(entity), true);
    }

    @Override
    public void updateMovie(Long id, MovieDTO dto) throws ErrorDTO {

        MovieDTO dtoOld = getMovie(id);

        if (Strings.isBlank(dto.getTitle())) {
            dto.setTitle(dtoOld.getTitle());
        }

        if (Strings.isBlank(dto.getImage())) {
            dto.setImage(dtoOld.getImage());
        }

        if (dto.getRating() == null) {
            dto.setRating(dtoOld.getRating());
        }

        if (dto.getCreationDate() == null) {
            dto.setCreationDate(dto.getCreationDate());
        }

        repository.update(dto.getTitle(), dto.getImage(), dto.getRating(), id);
    }

    @Override
    public void deleteMovie(Long id) throws ErrorDTO {
        throwErrorIfNotExits(id);
        repository.deleteById(id);
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        Set<MovieEntity> movies = new HashSet<>(repository.findAll());
        return mapper.ListEntity2ListDTO(movies, true);
    }

    @Override
    public MovieDTO getMovie(Long id) throws ErrorDTO {
        throwErrorIfNotExits(id);
        return mapper.Entity2DTO(repository.findById(id).get(), true);
    }

    @Override
    public void addCharacterToMovie(Long idMovie, Long idCharacter) throws ErrorDTO {
        CharacterDTO c = characterService.getCharacter(idCharacter);
        MovieDTO m = getMovie(idMovie);
        
        if(!m.getCharacters().add(c)){
            throw new ErrorDTO(
                "La pelicula con el ID " + idMovie + " ya contiene al personaje con el ID " + idCharacter,
                HttpStatus.BAD_REQUEST);
        }

        repository.save(mapper.DTO2Entity(m, true));
    }

    @Override
    public void removeCharacterFromMovie(Long idMovie, Long idCharacter) throws ErrorDTO {
        CharacterDTO c = characterService.getCharacter(idCharacter);
        MovieDTO m = getMovie(idMovie);

        if(!m.getCharacters().remove(c)) {
            throw new ErrorDTO(
                "La pelicula con el ID " + idMovie + " no contiene al personaje con el ID " + idCharacter,
                HttpStatus.BAD_REQUEST);
        }
        
        repository.save(mapper.DTO2Entity(m, true));
    }

    @Override
    public List<MovieDTO> getByFilters(MovieFiltersDTO filts) {
        Set<MovieEntity> dtos = new HashSet<>(repository.findAll(specification.getSpecsByFilters(filts)));
        return mapper.ListEntity2ListDTO(dtos, false);
    }
}
