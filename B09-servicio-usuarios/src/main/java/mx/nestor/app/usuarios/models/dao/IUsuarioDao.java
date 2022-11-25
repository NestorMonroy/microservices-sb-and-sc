package mx.nestor.app.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import mx.nestor.app.commons.usuarios.models.entity.Usuario;

//CLASE85: @RepositoryRestResource Rest Respositories

@RepositoryRestResource(path="usuarios")
public interface IUsuarioDao extends PagingAndSortingRepository<Usuario, Long> {
	// CLASE84   //CLASE86 localhost:8090/api/usuarios/usuarios/search/findByUsername?username=nestor 
	//localhost:8090/api/usuarios/usuarios/search/buscar-username?nombre=nestor
	@RestResource(path="buscar-username")
	//@RestResource(path="buscar-nombre")
	public Usuario findByUsername(@Param("username") String username);
	//public Usuario findByUsername(@Param("nombre") String nombre);

	// CLASE84  //localhost:8090/api/usuarios/usuarios/search/obtenerPorUsername?username=nestor
	@Query("select u from Usuario u where u.username=?1")
	public Usuario obtenerPorUsername(String username);
}
