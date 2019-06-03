package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrador;

@Repository
public interface AdminRepository extends JpaRepository<Administrador, Integer> {
	
	
	@Query("select admin from Administrador admin where admin.userAccount.id = ?1")
	Administrador findByUserAccountId(int id);
	
	
	// Dashboard Queries
	@Query("select min(p.noticias.size),max(p.noticias.size),avg(p.noticias.size) from Periodista p")
	Object[] query1();

	@Query("select p.nombre from Periodista p ORDER BY (p.cartera.saldoAcumulado + p.cartera.saldoAcumuladoTotal) DESC")
	List<String> query2();

	@Query("select count(u) from Usuario u where u.estatus = (0)")
	Integer query3_1();

	@Query("select count(u) from Usuario u where u.estatus = (1)")
	Integer query3_2();

	@Query("select count(u) from Usuario u where u.estatus = (2)")
	Integer query3_3();

	@Query("select min(p.puntosNecesarios),max(p.puntosNecesarios),avg(p.puntosNecesarios) from Sorteo p")
	Object[] query4();

	@Query("select SUM(u.puntos) from Usuario u")
	Integer query5();

	@Query("select p.nombre from Usuario p ORDER BY (p.puntos) DESC")
	List<String> query6();
	
	@Query("select p.nombre from Periodista p join p.noticias i ORDER BY i.numeroVisitas DESC")
	List<String> query7();
	
	@Query("select count(c)  from Noticia c where c.estado=(0)")
	Integer query8();
	
	@Query("select count(c)  from Noticia c where c.estado=(0)")
	Integer query9();

	@Query("select count(c)  from Sorteo c")
	Integer query10();
}

