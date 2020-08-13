package project.linkortech.test.mod_product.dao;

import mizuki.project.core.restserver.config.mybatis.provider.PGBaseSqlProvider;
import org.apache.ibatis.annotations.*;
import project.linkortech.test.mod_product.bean.ShopCart;

import java.util.List;

@Mapper
public interface ShopCartMapper {

    @InsertProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_INSERT)
    void save(ShopCart shopCart);

    @UpdateProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_UPDATEALL)
    void update(ShopCart shopCart);

    @DeleteProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_DELETE)
    void del(ShopCart shopCart);

    @Select("select * from shop_cart where userid=#{param1} order by createdt desc")
    @Results({
            @Result(property = "product",column = "productid", one = @One(select = "project.linkortech.test.mod_product.dao.ProductMapper.findById")),
            @Result(property = "productid",column = "productid")
    })
    List<ShopCart> listByUser(int uid);

    @Select("select * from shop_cart where userid=#{param1} and productid=#{param2}")
    ShopCart find(int uid,int pid);

}
