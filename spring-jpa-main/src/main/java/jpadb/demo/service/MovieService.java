package jpadb.demo.service;

import jakarta.transaction.Transactional;
import jpadb.demo.model.Movie;
import jpadb.demo.model.User;
import jpadb.demo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// search하는 string으로 movie찾는 함수
@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

//    public List<Movie> searchMoviesById(String docid) {
//        return movieRepository.findByDocidContaining(docid);
//    }

    public List<Movie> searchMovies(String title) {
        //System.out.println(movieRepository.findByTitleContaining((title))); -> []
        return movieRepository.findByTitleContaining(title);
    }

    public List<Movie> getAllMovies(){
        List<Movie> movies =new ArrayList<>();
        Streamable.of(movieRepository.findAll()).
                forEach(movies::add);

        return movies;
    }

}

