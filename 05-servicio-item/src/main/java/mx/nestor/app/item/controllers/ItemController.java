package mx.nestor.app.item.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import mx.nestor.app.item.models.Item;
import mx.nestor.app.item.models.Producto;
import mx.nestor.app.item.models.service.ItemService;

/*
 * CLASE39: /@RequestParam(name="nombre")String nombre : SE RELACIONA CON EL .YML DE GATEWAY. EL CONTROLADOR DE ITEMS, CAPTURA LA INFORMACIÓN ANEXADA AL REQUEST EN LOS FILTROS DE FÁBRICA, A TRAVÉS DE LOS MÉTODOS HANDLER.
 * ...@RequestHeader(name="token-request")String token : TAMBIEN SE RECIBE LA CABECERA.
 * required=false : PARA QUE NO SEA OBLIGATORIO MANDAR LA INFORMACIÓN EN EL REQUEST.
 * cbFactory.create("items") : ... "items" ES EL NOMBRE DEL CIRCUITBREAKER. Y SE CONFIGURA EN LA CLASE AppCircuitBreakerConfig.
 * 
 * CLASE51: SE USAN ANOTACIONES
 * CLASE52: SE ENVUELVE LA LLAMADA EN UNA REPRESENTACIÓN FUTURA CON LA CLASE CompletableFuture., 
 * 
 * */


@RestController
public class ItemController {
	
	
	//CLASE43
	@Autowired
	private CircuitBreakerFactory cbFactory; 
	private final Logger logger = LoggerFactory.getLogger(ItemController.class);

	
	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;
	
	@GetMapping("/listar")
	public List<Item>listar(@RequestParam(name="nombre", required=false)String nombre, @RequestHeader(name="token-request",required=false)String token){
		System.out.println(nombre);
		System.out.println(token);
		return itemService.findAll();
	}
	
	//CLASE39
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return cbFactory.create("items")
				.run(()-> itemService.findById(id, cantidad), e -> metodoAlternativo(id, cantidad, e));
	}
	
	//CLASE51
	@CircuitBreaker(name="items", fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver2/{id}/cantidad/{cantidad}")
	public Item detalle2(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}

	//CLASE52
	@CircuitBreaker(name="items",fallbackMethod = "metodoAlternativo2")
	@TimeLimiter(name="items")
	@GetMapping("/ver3/{id}/cantidad/{cantidad}")
	public CompletableFuture<Item>detalle3(@PathVariable Long id, @PathVariable Integer cantidad) {
		return CompletableFuture.supplyAsync(() -> itemService.findById(id, cantidad)) ;
	}

	public Item metodoAlternativo(Long id, Integer cantidad, Throwable e) {
		
		logger.info(e.getMessage());
		Item item = new Item();
		Producto producto = new Producto();
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Sin nombre. no encontrado!");
		producto.setPrecio(0.00);
		item.setProducto(producto);
		return item;
	}

	public CompletableFuture<Item> metodoAlternativo2(Long id, Integer cantidad, Throwable e) {
		
		logger.info(e.getMessage());
		Item item = new Item();
		Producto producto = new Producto();
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Sin nombre. no encontrado!");
		producto.setPrecio(0.00);
		item.setProducto(producto);
		return CompletableFuture.supplyAsync(() -> item );
	}
	
}
