package project.linkortech.test.mod_product.bean;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "shop_cart")
public class ShopCart {
    @Id
    private int userid;
    @Id
    private int productid;
    private int count;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdt;
    @Transient
    private Product product;

    public Product getProduct() {
        return product;
    }

    public ShopCart setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Timestamp getCreatedt() {
        return createdt;
    }

    public ShopCart setCreatedt(Timestamp createdt) {
        this.createdt = createdt;
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public ShopCart setUserid(int userid) {
        this.userid = userid;
        return this;
    }

    public int getProductid() {
        return productid;
    }

    public ShopCart setProductid(int productid) {
        this.productid = productid;
        return this;
    }

    public int getCount() {
        return count;
    }

    public ShopCart setCount(int count) {
        this.count = count;
        return this;
    }
}
