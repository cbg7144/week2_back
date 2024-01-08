package jpadb.demo.controller;

import jpadb.demo.model.Comment;
import jpadb.demo.model.Movie;
import jpadb.demo.repository.CommentRepository;
import jpadb.demo.service.CommentService;
import jpadb.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommentController {

    //@Autowired
    private final CommentService commentService;

    private final CommentRepository commentRepository;

    //@Autowired
    private final MovieService movieService;
    // comment 저장
    @PostMapping("/comment/save")
    public Comment saveComment(@RequestBody Map<String, String> body){

        Comment comment = new Comment();
        comment.setCommentid(body.get("commentid"));
        comment.setScore(Integer.parseInt(body.get("score"))); // 문자열을 정수로 변환
        comment.setLinecomment(body.get("linecomment"));
        comment.setLongcomment(body.get("longcomment"));

        // 만약 Movie 엔티티와 연관된 정보가 있다면, 여기에서 처리합니다.
        // 예: comment.setMovie(movieService.findMovieById(body.get("movieId")));

        // 로그 출력
        System.out.println("Comment ID: " + comment.getCommentid());
        System.out.println("Score: " + comment.getScore());
        System.out.println("Line Comment: " + comment.getLinecomment());
        System.out.println("Long Comment: " + comment.getLongcomment());

        String movieTitle = body.get("movieTitle");
        Movie movie = movieService.findByTitle(movieTitle);
        if (movie != null) {
            comment.setMovie(movie);
            System.out.println("Associated Movie Title: " + movie.getTitle());
            System.out.println("Associated Movie DocID: " + movie.getDocid());
        }
        commentRepository.save(comment);

        return comment;
    }

    // comment 띄우기
    @GetMapping("/comment/load")
    public List<Comment> loadComment(@RequestParam Map<String,String> body){
        System.out.println("title: " + body.get("searchTitle"));
        return commentRepository.findCommentsByMovieTitle(body.get("searchTitle"));

    }

}

