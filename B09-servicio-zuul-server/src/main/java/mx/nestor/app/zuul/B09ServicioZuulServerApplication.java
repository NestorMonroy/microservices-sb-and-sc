package mx.nestor.app.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
@EnableEurekaClient
@EnableZuulProxy  //SE HABILITA ZUUL EN EL PROYECTO
@SpringBootApplication
public class B09ServicioZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(B09ServicioZuulServerApplication.class, args);
	}

}
