package repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Premio;

@Repository
public interface PremioRepository extends JpaRepository<Premio, Integer> {



}

