package mx.nestor.app.item.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import mx.nestor.app.item.models.Item;
import mx.nestor.app.commons.models.entity.Producto;
import mx.nestor.app.item.models.service.ItemService;

/*
 * CLASE39: /@RequestParam(name="nombre")String nombre : SE RELACIONA CON EL .YML DE GATEWAY. EL CONTROLADOR DE ITEMS, CAPTURA LA INFORMACIÓN ANEXADA AL REQUEST EN LOS FILTROS DE FÁBRICA, A TRAVÉS DE LOS MÉTODOS HANDLER.
 * ...@RequestHeader(name="token-request")String token : TAMBIEN SE RECIBE LA CABECERA.
 * required=false : PARA QUE NO SEA OBLIGATORIO MANDAR LA INFORMACIÓN EN EL REQUEST.
 * cbFactory.create("items") : ... "items" ES EL NOMBRE DEL CIRCUITBREAKER. Y SE CONFIGURA EN LA CLASE AppCircuitBreakerConfig.
 * 
 * CLASE51: SE USAN ANOTACIONES
 * CLASE52: SE ENVUELVE LA LLAMADA EN UNA REPRESENTACIÓN FUTURA CON LA CLASE CompletableFuture., 
 * CLASE61: VA A BUSCAR LA CONFIGURACIÓN EXTERNA DEL APPLICATION.PROPERTIES A TRAVÉS DEL SERVICIO-CONFIG-SERVER.
 * ResponseEntity<Map<String,String>> : ResponseEntity ALMACENA UN TIPO GENERICO <?> Y EN ESTE CASO SE USA UN MAP.
 * new ResponseEntity<Map<String,String>>(json,HttpStatus.OK); : EN EL CUERPO DE LA RESPUESTA SE DEVUELVE UN OBJ MAP, MÁS UN STATUS HTTP OK.
 * 
 * CLASE63:/SE PREGUNTA SI HAY ALGÚN PERFIL ACTIVO Y TAMBIÉN SI EL 1ER PERFIL ES DE DESARROLLO.
 * CLASE64:@RefreshScope : PARA ACTUALIZAR LA APLICACION SIN TENER Q REINICIAR EL SERVICIO.
 * */

@RefreshScope // CLASE64
@RestController
public class ItemController {
	// CLASE43
	@Autowired
	private CircuitBreakerFactory cbFactory;
	// CLASE44
	private final Logger logger = LoggerFactory.getLogger(ItemController.class);

	// CLASE61
	@Value("${configuracion.texto}")
	private String texto;
	// CLASE61
	private static Logger log = LoggerFactory.getLogger(ItemController.class);

	// CLASE63
	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;

	@GetMapping("listar") // CLASE39
	public List<Item> listar(@RequestParam(name = "nombre", required = false) String nombre,
			@RequestHeader(name = "token-request", required = false) String token) {
		System.out.println(nombre);
		System.out.println(token);
		return itemService.findAll();
	}

	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return cbFactory.create("items").run(() -> itemService.findById(id, cantidad),
				e -> metodoAlternativo(id, cantidad, e));
	}

	// CLASE51
	@CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver2/{id}/cantidad/{cantidad}")
	public Item detalle2(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}

	// CLASE52
	@CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo2")
	@TimeLimiter(name = "items")
	@GetMapping("/ver3/{id}/cantidad/{cantidad}")
	public CompletableFuture<Item> detalle3(@PathVariable Long id, @PathVariable Integer cantidad) {
		return CompletableFuture.supplyAsync(() -> itemService.findById(id, cantidad));
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
		return CompletableFuture.supplyAsync(() -> item);
	}

	// CLASE61
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfiguracion(@Value("${server.port}") String puerto) {
		log.info(texto);
		Map<String, String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("puerto", puerto);

		// CLASE63
		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("desarrollo")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}

		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}

	// CLASE70
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return itemService.save(producto);
	}

	// CLASE70
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		return itemService.update(producto, id);
	}

	// CLASE70
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		itemService.delete(id);
	}
}
