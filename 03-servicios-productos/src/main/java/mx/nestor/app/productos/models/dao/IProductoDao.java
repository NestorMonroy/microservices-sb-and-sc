package mx.nestor.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import mx.nestor.app.productos.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{

}
