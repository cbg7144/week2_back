package jpadb.demo.controller;

import jpadb.demo.model.Movie;
import jpadb.demo.model.MovieDao;
import jpadb.demo.repository.MovieRepository;
import jpadb.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MovieController {
    @Autowired
    private final MovieService movieService;

//    public MovieController(MovieService movieService) {
//        this.movieService = movieService;
//    }

    @GetMapping ("/movie/search")
    public List<Movie> searchMovie(@RequestParam Map<String, String> body) { // Map<String, String> 빼먹어서 5시간 날림
        return movieService.searchMovies(body.get("searchString"));
        ////// movie repository에서 검색기능하는 함수 쓰기
    }

    @GetMapping("/movie/list")
    public List<Movie> getAllMovies(){return movieService.getAllMovies();}

    @GetMapping("/movie/view")
    public Movie getMovieView(@RequestParam Map<String, String> body) {
        return movieService.getMovieByDocid(body.get("docid"));
    }

//    @PostMapping("/movie/searchid")
//    public List<Movie> searchMovieById(@RequestBody String searchid) {
//        return movieService.searchMoviesById(searchid);
//    }
}
