package project.linkortech.test.mod_user.dao;

import mizuki.project.core.restserver.config.mybatis.provider.PGBaseSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import project.linkortech.test.mod_user.bean.Admin;
import project.linkortech.test.mod_user.bean.User;

import java.util.List;
import java.util.Map;

public interface AdminMapper {
    @InsertProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_INSERT)
    void save(Admin admin);

    @UpdateProvider(type = PGBaseSqlProvider.class,method = PGBaseSqlProvider.METHOD_UPDATEALL)
    void update(Admin admin);

    @Select("select * from admin where name=#{param1} and passwd=#{param2}")
    Admin login(String username, String passwd);

    @Select("select * from admin where name=#{param1}")
    Admin findByName(String username);

    @Update("update admin set state=1 where adminid=#{param1}")
    void ban(int id);

    @Update("update admin set state=0 where adminid=#{param1}")
    void live(int id);

    @Select("select * from admin order by type,adminid")
    List<Admin> list();

}
