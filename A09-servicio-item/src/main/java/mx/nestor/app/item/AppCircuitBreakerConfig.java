package mx.nestor.app.item;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

//1.-ID : HACE REFERENCIA AL CIRCUIT BREAKER QUE ESTÁ EN LA CLASE ITEMCONTROLLER.


@Configuration
public class AppCircuitBreakerConfig {


	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> defaultCuztomizer(){
		//1
		return factory -> factory.configureDefault(id -> {
			return new Resilience4JConfigBuilder(id)
					.circuitBreakerConfig(CircuitBreakerConfig.custom()
							.slidingWindowSize(10) //(muestreo estadistico) Número de peticiones a fallar
							.failureRateThreshold(50) //umbral de porcentaje a falla (contabilizar las fallas, cambio de estados)
							.waitDurationInOpenState(Duration.ofSeconds(10L))//Tiempo donde se pertenece el estado abierto (cortocircuito)
							.permittedNumberOfCallsInHalfOpenState(5) //cambio del estado abierto a semi abierto (peticiones de prueba)
							.slowCallRateThreshold(50) // umbral cuando las peticiones son lentas   
							.slowCallDurationThreshold(Duration.ofSeconds(2L))//aumento de llamadas lentas 
							.build())
					.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(6L)).build())
					.build();
		});
	}

}
