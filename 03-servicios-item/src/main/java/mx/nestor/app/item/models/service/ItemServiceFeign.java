package mx.nestor.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.nestor.app.item.clientes.ProductoClienteRestFeign;
import mx.nestor.app.item.models.Item;

@Service("serviceFeign")
public class ItemServiceFeign implements IItemService{

	@Autowired
	private ProductoClienteRestFeign clienteFeign;

	@Override
	public List<Item> findAll() {
		return clienteFeign.listar().stream().map(p-> new Item(p,1)).collect(Collectors.toList());

	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clienteFeign.detalle(id),cantidad);
	}

}