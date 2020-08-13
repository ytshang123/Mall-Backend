package project.linkortech.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by ycj on 16/5/19.
 * 初始化 配置
 */
@Component
public class InitRunner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(String... args) throws Exception {
        logger.info("init");
        // todo 在此运行初始化逻辑
    }
}
