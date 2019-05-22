package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

	@Query("select m from Mensaje m where m.receptor.id = ?1")
	Collection<Mensaje> mensajesRecibidos(int receptorId);
}
