package project.linkortech.test.mod_product.bean;

import com.alibaba.fastjson.annotation.JSONField;
import project.linkortech.test.mod_user.bean.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "order_main")
public class OrderMain {
    @Id @GeneratedValue
    private int orderid;
    private String deliver;
    private String deName;
    private String dePhone;
    private BigDecimal price;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createtime;
    private int userid;
    @Transient
    private User user;
    private int status;
    @Transient
    private List<OrderProduct> products;

    public int getUserid() {
        return userid;
    }

    public OrderMain setUserid(int userid) {
        this.userid = userid;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public OrderMain setStatus(int status) {
        this.status = status;
        return this;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public OrderMain setProducts(List<OrderProduct> products) {
        this.products = products;
        return this;
    }

    public int getOrderid() {
        return orderid;
    }

    public OrderMain setOrderid(int orderid) {
        this.orderid = orderid;
        return this;
    }

    public String getDeliver() {
        return deliver;
    }

    public OrderMain setDeliver(String deliver) {
        this.deliver = deliver;
        return this;
    }

    public String getDeName() {
        return deName;
    }

    public OrderMain setDeName(String deName) {
        this.deName = deName;
        return this;
    }

    public String getDePhone() {
        return dePhone;
    }

    public OrderMain setDePhone(String dePhone) {
        this.dePhone = dePhone;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderMain setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public OrderMain setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
        return this;
    }

    public User getUser() {
        return user;
    }

    public OrderMain setUser(User user) {
        this.user = user;
        return this;
    }
}
