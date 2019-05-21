package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Moderador;


@Repository
public interface ModeradorRepository extends JpaRepository<Moderador, Integer> {
	@Query("select m from Moderador m where userAccount.id= ?1")
	Moderador findByUserAccountId(int userAccountId);
	
	@Query("select m from Moderador m where m.email=?1")
	Moderador findOneByEmail(String email);
}
