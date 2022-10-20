package mx.nestor.app.item.models.service;

import mx.nestor.app.item.models.Item;

import java.util.List;

public interface ItemService {
    public List<Item> findAll();
    public Item findById(Long id, Integer cantidad);
}
