package jpadb.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserInfo {
    @Id
    private String userid;
    private String userpwd;

    public String getId() {
        return userid;
    }

    public void setId(String id) {
        this.userid = id;
    }

    public String getPwd() {
        return userpwd;
    }

    public void setPwd(String pwd) {
        this.userpwd = pwd;
    }
}
