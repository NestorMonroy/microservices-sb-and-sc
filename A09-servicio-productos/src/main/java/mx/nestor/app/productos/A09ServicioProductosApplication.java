package mx.nestor.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"mx.nestor.app.commons.models.entity"})
public class A09ServicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(A09ServicioProductosApplication.class, args);
	}

}
