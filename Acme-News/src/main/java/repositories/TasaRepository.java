package repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import domain.Tasa;


@Repository
public interface TasaRepository extends JpaRepository<Tasa, Integer> {

	@Query("select t.tasaVisita from Tasa t")
	Double findTasaVisita();
	
	@Query("select t.tasaModerador from Tasa t")
	Double findTasaModerador();
	
	@Query("select t.puntosPrincipiante from Tasa t")
	Double findpuntosPrincipiante();
	
	@Query("select t.puntosVeterano from Tasa t")
	Double findpuntosModerador();
	
	@Query("select t.puntosMaestro from Tasa t")
	Double findpuntosMaestro();
	
	
	@Query("select t.costeVeterano from Tasa t")
	Double findcosteVeterano();
	
	@Query("select t.costeMaestro from Tasa t")
	Double findcosteMaestro();
}
