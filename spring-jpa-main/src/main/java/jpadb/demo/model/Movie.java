package jpadb.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class  Movie {
    @Id
    private String docid;
    private String title;
    private String directorNm;
    private String actor1;
    private String actor2;
    private int runtime;
    private String rating;
    private String genre;
    private String reRlsDate;
    private String posterUrl;
    private String stillUrl;
    private String vodUrl;

//    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE)
//    private List<Comment> comments = new ArrayList<>();
//
//    // 기타 getter와 setter 메소드들
//
//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirectorNm() {
        return directorNm;
    }

    public void setDirectorNm(String directorNm) {
        this.directorNm = directorNm;
    }

    public String getActor1() {
        return actor1;
    }

    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    public String getActor2() {
        return actor2;
    }

    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReRlsDate() {
        return reRlsDate;
    }

    public void setReRlsDate(String reRlsDate) {
        this.reRlsDate = reRlsDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getStillUrl() {
        return stillUrl;
    }

    public void setStillUrl(String stillUrl) {
        this.stillUrl = stillUrl;
    }

    public String getVodUrl() {
        return vodUrl;
    }

    public void setVodUrl(String vodUrl) {
        this.vodUrl = vodUrl;
    }
}

/*cumulativeResponse.append("DOCID: ").append(docId)
                        .append("\nTitle: ").append(title)
                        .append("\nDirector: ").append(directorNm)
                        .append("\nActor 1: ").append(actor1)
                        .append("\nActor 2: ").append(actor2)
                        .append("\nRuntime: ").append(runtime)
                        .append("\nRating: ").append(rating)
                        .append("\nGenre: ").append(genre)
                        .append("\nRelease Date: ").append(repRlsDate)
                        .append("\nPoster URL: ").append(posterUrl)
                        .append("\nStill URL: ").append(stillUrl)
                        .append("\nVOD URL: ").append(vodUrl)
                        .append("\n\n");*/
