package mx.nestor.app.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class B09ServicioEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(B09ServicioEurekaServerApplication.class, args);
	}

}
