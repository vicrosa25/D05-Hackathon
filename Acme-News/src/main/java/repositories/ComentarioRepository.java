package repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



import domain.Comentario;


@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {


}
