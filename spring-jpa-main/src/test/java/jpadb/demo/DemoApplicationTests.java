package jpadb.demo;

import jpadb.demo.model.MovieDao;
import jpadb.demo.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests { // 여기서 data 잘 들어가나 test 할 수 있음

    @Autowired
    private MovieDao movieDao;

    @Test
    void addMovieTest() {
        Movie movie = new Movie();
        movie.setDocid(1000000L);
        movie.setTitle("해리포터");
        movie.setActorNm("abc");
        movie.setRuntime(100);
        movie.setRating("12세 관람가");
        movie.setGenre("판타지");
        movie.setPosterUrl("https//");

        movieDao.save(movie);
    }

    /*
    void getAllMoviesAndDeleteThem() {
        List <Movie> movies = movieDao.getAllMovies();
        for (Movie movie: movies) {
            MovieDao.delete(movie);
        }
    }
    */
     

}