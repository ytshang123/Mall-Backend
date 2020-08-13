package project.linkortech.test.mod_user.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "load_address")
public class LoadAddress {
    @Id @GeneratedValue
    private int addressid;
    private int userid;
    private String name;
    private String province;
    private String city;
    private String area;
    private String address;
    private String phone;
    private String postalcode;

    public String getAddress() {
        return address;
    }

    public LoadAddress setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getAddressid() {
        return addressid;
    }

    public LoadAddress setAddressid(int addressid) {
        this.addressid = addressid;
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public LoadAddress setUserid(int userid) {
        this.userid = userid;
        return this;
    }

    public String getName() {
        return name;
    }

    public LoadAddress setName(String name) {
        this.name = name;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public LoadAddress setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public LoadAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getArea() {
        return area;
    }

    public LoadAddress setArea(String area) {
        this.area = area;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public LoadAddress setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public LoadAddress setPostalcode(String postalcode) {
        this.postalcode = postalcode;
        return this;
    }
}
