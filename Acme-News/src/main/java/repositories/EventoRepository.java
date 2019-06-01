package repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
	
	@Query("select n from Evento n where n.agencia.manager.id=?1")
	List<Evento> searchByManager(int managerId);

	@Query("select e from Evento e  where e.fecha > NOW() order by e.agencia.importancia DESC")
	List<Evento> listActualEvents();
}

