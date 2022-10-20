package mx.nestor.app.productos.models.dao;

import mx.nestor.app.productos.models.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDao extends CrudRepository<Producto, Long> {
}
