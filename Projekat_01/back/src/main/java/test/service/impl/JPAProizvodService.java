package test.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import test.model.Porudzbina;
import test.model.Proizvod;
import test.repository.ProizvodRepository;
import test.service.ProizvodService;
import test.support.ProizvodDTOToProizvod;
import test.web.dto.ProizvodDTO;

@Service
public class JPAProizvodService implements ProizvodService {
	@Autowired
	private ProizvodRepository proizvodRepository;
	@Autowired
	private ProizvodDTOToProizvod toJO;
	
	

	@Override
	public List<Proizvod> getAll() {
		return proizvodRepository.findAll();
	}
	
	@Override
	public Proizvod save(Proizvod proizvod) {
		return proizvodRepository.save(proizvod);
	}
	
	@Override
	public Proizvod save(ProizvodDTO proizvodDTO) {
		
		Proizvod proizvod = toJO.convert(proizvodDTO);
		
		return proizvodRepository.save(proizvod);
	}

	@Override
	public Proizvod delete(Long id) {
		Optional<Proizvod> proizvodOptional = proizvodRepository.findById(id);
		if(proizvodOptional.isPresent()) {
			Proizvod proizvod = proizvodOptional.get();
			proizvodRepository.deleteById(id);
			return proizvod;
		}
		
		return null;
	}


	

	@Override
	public Optional<Proizvod> getByName(String proizvodIme) {
		return proizvodRepository.getByName(proizvodIme);
	}

	@Override
	public Proizvod update(ProizvodDTO proizvod) {
		
		Proizvod proizvod1 = toJO.convert(proizvod);
		Optional<Proizvod> stariOptional = one(proizvod.getId());
		if(stariOptional.isPresent()) {
			Proizvod stari = stariOptional.get();
			List<Porudzbina> staraLista = stari.getPorudzbine();
			proizvod1.setPorudzbine(staraLista);
			proizvod1 = proizvodRepository.save(proizvod1);
			return proizvod1;
		}
		
		
		return null;
	}

	@Override
	public Optional<Proizvod> one(Long id) {
		return proizvodRepository.findById(id);
	}

	@Override
	public Page<Proizvod> getAll(int page) {
		return proizvodRepository.findAll(PageRequest.of(page,  3));
	}

	@Override
	public Page<Proizvod> search(int minCena, int maxCena, Long kategorija, int page) {
	
		return proizvodRepository.search(minCena, maxCena, kategorija, PageRequest.of(page, 3));
	}

	

	

	

}
