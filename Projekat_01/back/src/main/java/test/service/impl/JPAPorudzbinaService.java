package test.service.impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.model.Porudzbina;
import test.model.Proizvod;
import test.repository.PorudzbinaRepository;
import test.service.PorudzbinaService;
import test.service.ProizvodService;

@Service
public class JPAPorudzbinaService implements PorudzbinaService {
	
	@Autowired
	private PorudzbinaRepository porudzbinaRepository;
	
	@Autowired
	private ProizvodService proizvodService;

	@Override
	public List<Porudzbina> getAll() {
		return porudzbinaRepository.findAll();
	}

	@Override
	public Porudzbina save(Porudzbina porudzbina) {
		Porudzbina saved = porudzbinaRepository.save(porudzbina);
		Proizvod proizvod = saved.getProizvod();
		proizvod.setStanje(proizvod.getStanje() - saved.getKolicina());
		proizvodService.save(proizvod);
		return saved;
	}

	@Override
	public Porudzbina findOne(Long id) {
		return porudzbinaRepository.getOne(id);
	}


	
	
	
	
}
