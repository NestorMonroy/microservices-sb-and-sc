package mx.nestor.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mx.nestor.app.commons.usuarios.models.entity.Usuario;

@FeignClient(name="servicio-usuarios")
public interface IUsuarioFeignClient {
	@GetMapping("/usuarios/search/buscar-username")
	public Usuario findByUsername(@RequestParam("username")String username);
}
