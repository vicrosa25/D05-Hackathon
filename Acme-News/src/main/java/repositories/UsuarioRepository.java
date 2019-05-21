package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Informacion;
import domain.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("select c from Usuario c where userAccount.id = ?1")
	Usuario findByUserAccountId(int id);
	
	
	
	
	//devuelve toda la informacion compartida por la gente a los que sigue un usuario
	@Query("select informacionDeQuienSigues from Usuario usuario join usuario.siguiendo losQueSigues join losQueSigues.informacionCompartida informacionDeQuienSigues where usuario.id=?1")
	Collection<Informacion> findInformacionDeQuienSigues(int id);
	
	@Query(" select a from Usuario a join a.userAccount c where c.username=?1")
	Usuario findOneByName(String user);
	
	@Query("select c from Usuario c where c.banned=(0)")
	List<Usuario> getUsersNotBanned();
	@Query("select c from Usuario c where c.banned=(1)")
	List<Usuario> getUsersBanned();
	@Query("select count(u.usernameCopyForBan) from Usuario u where u.usernameCopyForBan=?1")
	Integer userAccountExist(String username);

}
