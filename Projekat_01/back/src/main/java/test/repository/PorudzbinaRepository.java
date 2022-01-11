package test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.model.Porudzbina;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina , Long>{

}
