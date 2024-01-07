package jpadb.demo.controller;

import jpadb.demo.model.Movie;
import jpadb.demo.model.MovieDao;
import jpadb.demo.repository.MovieRepository;
import jpadb.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    @Autowired
    private final MovieService movieService;

//    public MovieController(MovieService movieService) {
//        this.movieService = movieService;
//    }

    @PostMapping ("/movie/search")
    public List<Movie> searchMovie(@RequestBody String searchString) {
        return movieService.searchMovies(searchString);
        ////// movie repository에서 검색기능하는 함수 쓰기
    }

    @GetMapping("/movie/list")
    public List<Movie> getAllMovies(){return movieService.getAllMovies();}

//    @PostMapping("/movie/searchid")
//    public List<Movie> searchMovieById(@RequestBody String searchid) {
//        return movieService.searchMoviesById(searchid);
//    }
}
