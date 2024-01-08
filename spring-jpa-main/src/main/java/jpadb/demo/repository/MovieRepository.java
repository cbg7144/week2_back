package jpadb.demo.repository;

import jpadb.demo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Jpa: sql query를 작성할 필요없이 함수로 sql 처리를 하게 해줌
@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    //List<Movie> findByDocidContaining (String id);

    // SELECT * FROM movie WHERE title LIKE '%title%'
    List<Movie> findByTitleContaining (String title);


    Movie findByTitle(String title);
}
