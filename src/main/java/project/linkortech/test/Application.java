package project.linkortech.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {
		RedisAutoConfiguration.class,
})
@EnableAsync
public class Application{

	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
