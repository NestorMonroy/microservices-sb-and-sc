package mx.nestor.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.nestor.app.item.clientes.ProductoClienteRest;
import mx.nestor.app.item.models.Item;
import mx.nestor.app.commons.models.entity.Producto;

@Service("serviceFeign")
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ProductoClienteRest clienteFeign;

	@Override
	public List<Item> findAll() {
		return clienteFeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clienteFeign.detalle(id), cantidad);
	}

	// CLASE69
	@Override
	public Producto save(Producto producto) {
		// CLASE72
		return clienteFeign.crear(producto);
	}

	// CLASE69
	@Override
	public Producto update(Producto producto, Long id) {
		// CLASE72
		return clienteFeign.editar(producto, id);
	}

	// CLASE69
	@Override
	public void delete(Long id) {
		// CLASE72
		clienteFeign.eliminar(id);
	}
}
