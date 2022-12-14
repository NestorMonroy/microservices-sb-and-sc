package mx.nestor.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import mx.nestor.app.item.models.Producto;

@FeignClient(name="servicio-productos", url="localhost:8001")
public interface ProductoClienteRestFeign {
	
	@GetMapping("/listar") //tienen que ser la misma ruta del controlador
	public List<Producto>listar();
	
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);

}
