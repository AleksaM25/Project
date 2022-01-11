package test.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.model.Kategorija;
import test.repository.KategorijaRepository;
import test.service.KategorijaService;

@Service
public class JPAKategorijaService implements KategorijaService {
	
	@Autowired
	private KategorijaRepository kategorijaRepository;

	@Override
	public List<Kategorija> all() {
		return kategorijaRepository.findAll();
	}

	@Override
	public Optional<Kategorija> getOne(Long id) {
		return kategorijaRepository.findById(id);
	}

}
