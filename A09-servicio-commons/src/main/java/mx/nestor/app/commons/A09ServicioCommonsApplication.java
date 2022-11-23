package mx.nestor.app.commons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class A09ServicioCommonsApplication {

	public static void main(String[] args) {
		SpringApplication.run(A09ServicioCommonsApplication.class, args);
	}

}
