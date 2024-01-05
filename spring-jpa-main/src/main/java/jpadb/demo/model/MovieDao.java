package jpadb.demo.model;

import jpadb.demo.model.Movie;
import jpadb.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieDao { // auto-writing / Data Access Object
    @Autowired
    private MovieRepository repository;


    public Movie save(Movie movie){
        repository.save(movie); // repository 함수가 다 자동으로 해줌
        return movie;
    }

    public List<Movie> getAllMovies(){
        List<Movie> movies=new ArrayList<>();
        Streamable.of(repository.findAll()).
                forEach(movies::add);

        return movies;
    }

    public void delete(Movie movie){
        repository.delete(movie);
    }

    public void deleteById(Long docid){
        repository.deleteById(docid);
    }
}
