package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Periodista;
import domain.Usuario;


@Repository
public interface PeriodistaRepository extends JpaRepository<Periodista, Integer> {

	@Query("select p from Periodista p where userAccount.id= ?1")
	Periodista findByUserAccountId(int userAccountId);

	@Query("select p from Periodista p where p.email=?1")
	Periodista findOneByEmail(String email);

	@Query("select p from Periodista p join p.noticias n where n.isBanned = true")
	Collection<Periodista> findWithBannedNew();

	@Query("select p from Periodista p where p.isBanned = true")
	Collection<Periodista> findBanned();

	@Query("select u from Usuario u join u.periodistas p where p.id = ?1")
	Collection<Usuario> findFollowers(int periodistaId);
}
