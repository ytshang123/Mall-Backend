package project.linkortech.test.mod_user.bean;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user")
public class User {
    @Id @GeneratedValue
    private int userid;
    private String username;
    @JSONField(serialize = false)
    private String passwd;
    private int state;
    private String contact;
    private String email;

    public int getUserid() {
        return userid;
    }

    public User setUserid(int userid) {
        this.userid = userid;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPasswd() {
        return passwd;
    }

    public User setPasswd(String passwd) {
        this.passwd = passwd;
        return this;
    }

    public int getState() {
        return state;
    }

    public User setState(int state) {
        this.state = state;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public User setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
}
