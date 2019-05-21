
package repositories;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Buscador;
import domain.Categoria;
import domain.Noticia;

@Repository
public interface BuscadorRepository extends JpaRepository<Buscador, Integer> {

	@Query("select n from Noticia n where (n.titulo LIKE ?1 or n.descripcion like ?1 or n.periodista.nombre LIKE " +
		"?1) and n.estado=2 order by n.fecha desc")
	Collection<Noticia> filterByKeyword(String keyword);

	@Query("select n from Noticia n where n.fecha <= ?1 and n.estado=2 order by n.fecha desc")
	Collection<Noticia> filterByFechaFin(Date fechaFin);

	@Query("select n from Noticia n where n.fecha >= ?1 and n.estado=2 order by n.fecha desc")
	Collection<Noticia> filterByFechaInicio(Date fechaInicio);

	@Query("select n from Noticia n where n.categoria=?1 and n.estado=2 order by n.fecha desc")
	List<Noticia> filterByCategoria(Categoria categoria);

}
