package project.linkortech.test.mod_user.dao;

import mizuki.project.core.restserver.config.mybatis.provider.PGBaseSqlProvider;
import org.apache.ibatis.annotations.*;
import project.linkortech.test.mod_user.bean.LoadAddress;
import project.linkortech.test.mod_user.bean.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @InsertProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_INSERT)
    void save(User user);

    @UpdateProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_UPDATEALL)
    void update(User user);

    @Select("select * from user where userid=#{param1}")
    User findById(int id);

    @Select("select * from user where username=#{param1}")
    User findByName(String name);

    @Select("select * from user where username=#{param1} and passwd=#{param2}")
    User login(String username, String passwd);

    /**
     * 收货地址
     */

    @InsertProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_INSERT)
    void saveAddress(LoadAddress address);

    @UpdateProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_UPDATEALL)
    void updateAddress(LoadAddress address);

    @DeleteProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_DELETE)
    void delAddress(LoadAddress address);

    @Select("select * from load_address where addressid=#{param1}")
    LoadAddress findAddress(int id);

    @Select("select * from load_address where userid=#{param1} order by addressid")
    List<LoadAddress> listByUser(int uid);
}
