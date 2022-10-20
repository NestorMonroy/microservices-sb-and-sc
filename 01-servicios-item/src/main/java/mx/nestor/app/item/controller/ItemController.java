package mx.nestor.app.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import mx.nestor.app.item.models.Item;
import mx.nestor.app.item.models.service.IItemService;

@RestController
public class ItemController {

	@Autowired
	private IItemService itemService;
	
	@GetMapping("listar")
	public List<Item>listar(){
		return itemService.findAll();
	}
	
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
}
