package test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import test.model.Proizvod;
import test.web.dto.ProizvodDTO;

public interface ProizvodService {

	
	List<Proizvod> getAll();
	Optional<Proizvod> one(Long id);
	Proizvod save (ProizvodDTO proizvod);
	Proizvod save (Proizvod proizvod);
	Proizvod delete(Long id);
	Optional<Proizvod> getByName (String proizvodIme);
	Proizvod update (ProizvodDTO proizvod);
	Page<Proizvod> getAll(int page);
	Page<Proizvod> search (int minCena, int maxCena, Long kategorija, int pageNum);
	
	
}
