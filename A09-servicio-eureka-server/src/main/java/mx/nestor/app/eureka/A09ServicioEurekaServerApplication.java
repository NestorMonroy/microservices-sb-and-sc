package mx.nestor.app.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class A09ServicioEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(A09ServicioEurekaServerApplication.class, args);
	}

}
