package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Agencia;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Integer> {

	@Query("select a from Agencia a where a.capacidadDisponible > 0")
	Collection<Agencia> findAllNotFull();

}
