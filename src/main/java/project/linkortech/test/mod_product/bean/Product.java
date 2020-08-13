package project.linkortech.test.mod_product.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "product")
public class Product {

    @Id @GeneratedValue
    private int productid;
    private String name;
    private BigDecimal price;
    private String type;
    private String img;
    private String tag;
    private int amount;
    private int off;

    public int getOff() {
        return off;
    }

    public Product setOff(int off) {
        this.off = off;
        return this;
    }

    public int getProductid() {
        return productid;
    }

    public Product setProductid(int productid) {
        this.productid = productid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getType() {
        return type;
    }

    public Product setType(String type) {
        this.type = type;
        return this;
    }

    public String getImg() {
        return img;
    }

    public Product setImg(String img) {
        this.img = img;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public Product setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public Product setAmount(int amount) {
        this.amount = amount;
        return this;
    }
}
