package jpadb.demo.service;

import jakarta.transaction.Transactional;
import jpadb.demo.model.Comment;
//import jpadb.demo.model.User;
import jpadb.demo.repository.CommentRepository;
import jpadb.demo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    // comment 저장
    private CommentRepository commentRepository;

//    @Autowired
//    public CommentService(CommentRepository commentRepository) {
//        this.commentRepository = commentRepository;
//    }

    public Comment saveComment(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    public List<Comment> findCommentsByTitle(String title) {
        // 여기서는 'title'을 사용하여 관련 코멘트를 검색하는 로직을 구현합니다.
        // 예를 들어, 다음과 같이 구현할 수 있습니다:

        return commentRepository.findCommentsByMovieTitle(title);
    }


}
