package mx.nestor.app.item.models.service;

import mx.nestor.app.item.clientes.ProductoClienteRestFeign;
import mx.nestor.app.item.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
//@Primary
public class ItemServiceFeign implements  ItemService{

    @Autowired
    private ProductoClienteRestFeign clienteFeign;

    @Override
    public List<Item> findAll() {
        System.out.println("");
        //como regresa una lista de productos, se tiene que regresar una lista de items
        return clienteFeign.listar().stream().map(p-> new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return  new Item(clienteFeign.detalle(id),cantidad);
    }
}
