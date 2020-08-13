package project.linkortech.test.config;

import mizuki.project.core.restserver.config.BasicConfig;
import mizuki.project.core.restserver.config.SpringConfBean;
import mizuki.project.core.restserver.config.mybatis.DataSourceMysql1Conf;
import mizuki.project.core.restserver.config.mybatis.DataSourcePgsql1Conf;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        BasicConfig.class,
        SpringConfBean.class,
        DataSourceMysql1Conf.class
})
@MapperScan(basePackages = "project.linkortech.test")
public class MainConfig {

}
