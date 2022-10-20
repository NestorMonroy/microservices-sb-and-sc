package mx.nestor.app.item.clientes;

import mx.nestor.app.item.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="servicio-productos", url="localhost:8001") //Consume
public interface ProductoClienteRestFeign {
    @GetMapping("/listar")
    public List<Producto>listar();

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable Long id);
}
