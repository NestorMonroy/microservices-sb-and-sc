package mx.nestor.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ServiciosItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiciosItemApplication.class, args);
	}

}
