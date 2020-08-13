package project.linkortech.test.mod_product.bean;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "order_product")
public class OrderProduct {
    @Id
    private int orderid;
    @Id
    private int productid;
    private int amount;
    private BigDecimal price;
    private String name;
    private String img;

    public int getOrderid() {
        return orderid;
    }

    public OrderProduct setOrderid(int orderid) {
        this.orderid = orderid;
        return this;
    }

    public int getProductid() {
        return productid;
    }

    public OrderProduct setProductid(int productid) {
        this.productid = productid;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public OrderProduct setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderProduct setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderProduct setName(String name) {
        this.name = name;
        return this;
    }

    public String getImg() {
        return img;
    }

    public OrderProduct setImg(String img) {
        this.img = img;
        return this;
    }
}
