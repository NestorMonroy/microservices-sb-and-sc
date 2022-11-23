package mx.nestor.app.productos.models.service;

import java.util.List;

import mx.nestor.app.productos.models.entity.Producto;

public interface IProductoService {
	public List<Producto> findAll();
	public Producto findById(Long id);
	//CLASE 67
	public Producto save(Producto producto);
	//CLASE 67
	public void deleteById(Long id);
}
