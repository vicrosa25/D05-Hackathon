package repositories;



import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Sorteo;


@Repository
public interface SorteoRepository extends JpaRepository<Sorteo, Integer> {

	@Query("select e from Sorteo e  where e.fechaInicio <= now() and e.fechaVencimiento >= now() order by  e.fechaVencimiento ASC")
	Collection<Sorteo> findSorteosProximos();

	@Query(" select a from Sorteo a  where a.titulo=?1")
	Sorteo findOneByName(String titulo);

}
