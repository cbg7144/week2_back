package jpadb.demo.model;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentid;
    private int score;
    private String linecomment;
    private String longcomment;

    @ManyToOne
    @JoinColumn(name = "docid") // 여기서 "movie_id"는 Movie 테이블의 기본 키 컬럼 이름입니다.
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Long getCommentid() {
        return commentid;
    }

    public void setCommentid(Long commentid) {
        this.commentid = commentid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLinecomment() {
        return linecomment;
    }

    public void setLinecomment(String linecomment) {
        this.linecomment = linecomment;
    }

    public String getLongcomment() {
        return longcomment;
    }

    public void setLongcomment(String longcomment) {
        this.longcomment = longcomment;
    }

}
