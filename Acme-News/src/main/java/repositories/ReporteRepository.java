package repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



import domain.Reporte;


@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {


}
