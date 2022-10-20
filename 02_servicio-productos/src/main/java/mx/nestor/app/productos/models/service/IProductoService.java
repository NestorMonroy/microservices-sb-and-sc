package mx.nestor.app.productos.models.service;

import mx.nestor.app.productos.models.entity.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> findAll();
    public Producto findById(Long id);
}
