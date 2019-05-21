package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where userAccount.id= ?1")
	Actor findByUserAccountId(int userAccountId);
	
	@Query(" select a from Actor a join a.userAccount c where c.username=?1")
	Actor findOneByName(String user);

	@Query("select a from Actor a where a.email=?1")
	Actor findOneByEmail(String email);

}
