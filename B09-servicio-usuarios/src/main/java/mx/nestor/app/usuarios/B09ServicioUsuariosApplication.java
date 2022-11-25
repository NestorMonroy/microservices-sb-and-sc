package mx.nestor.app.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"mx.nestor.app.commons.usuarios.models.entity"})
@SpringBootApplication
public class B09ServicioUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(B09ServicioUsuariosApplication.class, args);
	}

}
