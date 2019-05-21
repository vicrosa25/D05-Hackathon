package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Categoria;
import domain.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
	@Query("select n from Noticia n where n.categoria=?1 and n.estado=2 order by n.fecha desc")
	List<Noticia> findByCategoria(Categoria categoria);
	
	@Query("select n from Noticia n where n.titulo like %:keyword% or "+
		"n.descripcion like %:keyword% or " +
		"n.fecha like %:keyword% and n.estado=2 order by n.fecha desc")
	List<Noticia> searchByKeyword(@Param("keyword") String keyword);

	@Query("select n from Noticia n where n.periodista.id=?1 order by n.fecha desc")
	List<Noticia> searchByJournalist(Integer id);
	
	
	@Query("select n from Noticia n where n.reportes.size>2 and n.estado=2 order by n.fecha asc")
	List<Noticia> noticiasParaBanear();
	
	@Query("select n from Noticia n where n.estado=1 order by n.fecha asc")
	List<Noticia> noticiasPendientes();
	
	@Query(" select a from Noticia a  where a.titulo=?1")
	Noticia findOneByName(String titulo);

}
