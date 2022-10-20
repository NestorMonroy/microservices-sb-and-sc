package mx.nestor.app.item.models.service;

import java.util.List;

import mx.nestor.app.item.models.Item;

public interface IItemService {
	public List<Item>findAll();
	public Item findById(Long id, Integer cantidad);
}
