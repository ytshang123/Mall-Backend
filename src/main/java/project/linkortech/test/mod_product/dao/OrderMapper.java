package project.linkortech.test.mod_product.dao;

import mizuki.project.core.restserver.config.mybatis.provider.PGBaseSqlProvider;
import org.apache.ibatis.annotations.*;
import project.linkortech.test.mod_product.bean.OrderMain;
import project.linkortech.test.mod_product.bean.OrderProduct;

import java.util.List;

@Mapper
public interface OrderMapper {

    @InsertProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_INSERT)
    @Options(useGeneratedKeys = true,keyProperty = "orderid")
    void save(OrderMain orderMain);

    @UpdateProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_UPDATEALL)
    void update(OrderMain orderMain);

    @InsertProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_INSERT)
    void saveProduct(OrderProduct product);

    @Select("select * from order_main where orderid=#{param1}")
    @Results({
            @Result(property = "orderid",column = "orderid"),
            @Result(property = "products",column = "orderid",many = @Many(select = "listOrderProduct"))
    })
    OrderMain find(int id);

    @Select("select * from order_main where userid=#{param1} order by orderid desc")
    @Results({
            @Result(property = "orderid",column = "orderid"),
            @Result(property = "products",column = "orderid",many = @Many(select = "listOrderProduct"))
    })
    List<OrderMain> listByUser(int uid);

    @Select("update order_main set status=#{param2} where orderid=#{param1}")
    void updateStatus(int id,int status);

    @Select("select * from order_product where orderid=#{param1}")
    List<OrderProduct> listOrderProduct(int oid);
}
