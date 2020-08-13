package project.linkortech.test.mod_product.dao;

import mizuki.project.core.restserver.config.mybatis.provider.PGBaseSqlProvider;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import project.linkortech.test.mod_product.bean.Product;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    @InsertProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_INSERT)
    void save(Product product);

    @UpdateProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_UPDATEALL)
    void update(Product product);

    @Select("select * from product where productid=#{param1}")
    Product findById(int id);

    @Select("select * from product where off=0")
    List<Product> listProduct();

    @Select("select * from product order by productid desc")
    List<Product> listAllProduct();

    @Select("select * from product where type=#{param1} and off=0")
    List<Product> listProductByType(String type);

    @Delete("delete from product where productid=#{param1}")
    void del(int productid);

    @Update("update product set off=1 where productid=#{param1}")
    void ban(int id);

    @Update("update product set off=0 where productid=#{param1}")
    void active(int id);

    @Select("select * from product where name like \"%${param1}%\"")
    List<Product> search(@Param("key")String key);

}
