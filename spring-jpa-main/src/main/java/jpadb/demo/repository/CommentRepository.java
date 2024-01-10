package jpadb.demo.repository;

import jpadb.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findCommentsByMovieTitle(String title);
    List<Comment> findCommentsByUserInfoUserid(String id);

}