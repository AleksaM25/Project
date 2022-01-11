package test.support;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.model.Porudzbina;
import test.model.Proizvod;
import test.service.PorudzbinaService;
import test.service.ProizvodService;
import test.web.dto.PorudzbinaDTO;

@Component
public class PorudzbinaDTOToPorudzbina implements Converter<PorudzbinaDTO, Porudzbina>{
	
	@Autowired
	private PorudzbinaService ps;
	@Autowired
	private ProizvodService pS;

	@Override
	public Porudzbina convert(PorudzbinaDTO dto) {
		
		Porudzbina porudzbina = new Porudzbina();
		
		porudzbina.setId(dto.getId());
		porudzbina.setKolicina(dto.getKolicina());
		Optional<Proizvod> proizvod = pS.one(dto.getProizvodID());
		
		if (proizvod.isPresent()) {
			Proizvod p = proizvod.get();
			porudzbina.setProizvod(p);	
			return porudzbina;
		} else {
			return null;
		}
	}
	
	
	

}
