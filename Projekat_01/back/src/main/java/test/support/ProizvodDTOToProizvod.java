package test.support;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.model.Kategorija;
import test.model.Proizvod;
import test.service.KategorijaService;
import test.service.ProizvodService;
import test.web.dto.ProizvodDTO;

@Component
public class ProizvodDTOToProizvod implements Converter<ProizvodDTO, Proizvod>{

	@Autowired
	private KategorijaService kS;
	@Autowired
	private ProizvodService ps;
	
	@Override
	public Proizvod convert(ProizvodDTO dto) {
		
		Long id = dto.getId();
		Proizvod proizvod = id == null ? new Proizvod() : new Proizvod(ps.one(id).get());
		
		
		if(proizvod != null) {
			proizvod.setId(dto.getId());
			proizvod.setCena(dto.getCena());
			proizvod.setNaziv(dto.getNaziv());
			proizvod.setStanje(dto.getStanje());
		};
		Optional<Kategorija> kategorija = kS.getOne(dto.getKategorijaID());
		if(kategorija.isPresent()) {
			Kategorija k = kategorija.get();
			proizvod.setKategorija(k);
			return proizvod;
		}else {
			return null;
		}
		
	}

}
