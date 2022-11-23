package mx.nestor.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
@EnableConfigServer
@SpringBootApplication
public class A09ServicioConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(A09ServicioConfigServerApplication.class, args);
	}

}
