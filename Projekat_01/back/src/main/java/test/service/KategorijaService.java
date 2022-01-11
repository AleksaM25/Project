package test.service;

import java.util.List;
import java.util.Optional;

import test.model.Kategorija;

public interface KategorijaService {

	
	List<Kategorija> all();
	Optional<Kategorija> getOne(Long id);
	
}
