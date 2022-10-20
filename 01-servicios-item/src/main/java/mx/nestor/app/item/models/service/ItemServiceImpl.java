package mx.nestor.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import mx.nestor.app.item.models.Item;
import mx.nestor.app.item.models.Producto;

@Service
public class ItemServiceImpl implements IItemService{

	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public List<Item> findAll() {

									// de un arreglo, lo convertimos a un Array
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", Producto[].class));
				//java8 (programacion funcional) con stream convertimos la lista en un flujo, por cada elemento se tiene el producto, al final se convierte en una lista
		return productos.stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		
		//pasar el id se utiliza un pathVariables
		Map<String,String> pathVariables = new HashMap <String,String>();
		pathVariables.put("id",id.toString());
		
		Producto producto = clienteRest.getForObject("http://localhost:8001/ver/{id}", Producto.class,pathVariables);
		
		return new Item(producto, cantidad);
	}

}
