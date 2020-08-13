package project.linkortech.test.mod_user.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "admin")
public class Admin {
    @Id @GeneratedValue
    private int adminid;
    private String name;
    private String passwd;
    private int type;
    private int state;

    public int getAdminid() {
        return adminid;
    }

    public Admin setAdminid(int adminid) {
        this.adminid = adminid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Admin setName(String name) {
        this.name = name;
        return this;
    }

    public int getState() {
        return state;
    }

    public Admin setState(int state) {
        this.state = state;
        return this;
    }

    public String getPasswd() {
        return passwd;
    }

    public Admin setPasswd(String passwd) {
        this.passwd = passwd;
        return this;
    }

    public int getType() {
        return type;
    }

    public Admin setType(int type) {
        this.type = type;
        return this;
    }
}
