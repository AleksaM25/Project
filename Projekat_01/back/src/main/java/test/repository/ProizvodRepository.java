package test.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import test.model.Proizvod;

@Repository
public interface ProizvodRepository extends JpaRepository<Proizvod, Long> {

	@Query("SELECT p FROM Proizvod p WHERE p.naziv = :naziv")
	Optional<Proizvod> getByName(@Param("naziv") String naziv);
	@Query("SELECT p FROM Proizvod p WHERE "
			+ " p.cena BETWEEN :minCena AND :maxCena"
			+ " AND p.kategorija.id = :kategorija")
	Page<Proizvod> search (@Param("minCena") int minCena, @Param("maxCena") int maxCena, @Param("kategorija") Long kategorija, Pageable pageable);
	
	
	
}