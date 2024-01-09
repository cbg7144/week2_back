package jpadb.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Game {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String gameid;
    private String question;
    private String answer;
    private String poster_url;

    public Game() {
    }

    public Game(String gameid, String question, String answer, String poster_url) {
        this.gameid = gameid;
        this.question = question;
        this.answer = answer;
        this.poster_url = poster_url;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPosterUrl() {
        return poster_url;
    }

    public void setPosterUrl(String poster_url) {
        this.poster_url = poster_url;
    }
}
