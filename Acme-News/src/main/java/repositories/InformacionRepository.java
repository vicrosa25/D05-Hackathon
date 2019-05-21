package repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Informacion;



@Repository
public interface InformacionRepository extends JpaRepository<Informacion, Integer> {
	
	@Query(" select a from Informacion a  where a.titulo=?1")
	Informacion findOneByName(String titulo);


}
