package test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.model.Proizvod;
import test.web.dto.ProizvodDTO;

@Component
public class ProizvodToProizvodDTO implements Converter<Proizvod, ProizvodDTO>{

	@Override
	public ProizvodDTO convert(Proizvod proizvod) {
		
		ProizvodDTO dto = new ProizvodDTO();
		dto.setId(proizvod.getId());
		dto.setCena(proizvod.getCena());
		dto.setNaziv(proizvod.getNaziv());
		dto.setStanje(proizvod.getStanje());
		dto.setKategorijaID(proizvod.getKategorija().getId());
		dto.setKategorijaIme(proizvod.getKategorija().getNaziv());
		
		return dto;
	}
	
	public List<ProizvodDTO> convert (List<Proizvod> proizvodi) {
		
		List<ProizvodDTO> dto = new ArrayList<>();
		
		for(Proizvod p : proizvodi) {
			ProizvodDTO DTO = convert(p);
			dto.add(DTO);
		}
		return dto;
		
	}

}
