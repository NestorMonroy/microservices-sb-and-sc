package mx.nestor.app.item.models.service;

import java.util.List;

import mx.nestor.app.item.models.Item;
import mx.nestor.app.item.models.Producto;

public interface ItemService {
	public List<Item> findAll();
	public Item findById(Long id, Integer cantidad);
	//CLASE69
	public Producto save(Producto producto); 
	
	//CLASE69
	public Producto update(Producto producto, Long id);
	
	//CLASE69
	public void delete(Long id);
}
