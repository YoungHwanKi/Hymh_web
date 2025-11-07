package home.parns.Hymh_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {		DataSourceAutoConfiguration.class})

@SpringBootApplication
public class HymhWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HymhWebApplication.class, args);
	}

}
