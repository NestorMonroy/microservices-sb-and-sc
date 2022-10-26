package mx.nestor.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import mx.nestor.app.productos.models.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long>{

}
