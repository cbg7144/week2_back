package jpadb.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 여기서는 increasing key로 primary key를 자동생성해주지만, 나는 id를 primary key로 생성해줬기 때문에 필요없다
    private String id; // 이게 id
    private String nickname;
    private String profileimage;
    //private int age;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", profileimage='" + profileimage  +
                '}';
    }
}