package opgg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value="classpath:static/common/apikeys.properties")
public class TwoJobLessApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwoJobLessApplication.class, args);
	}

}
