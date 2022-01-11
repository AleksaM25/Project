package test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.model.Porudzbina;
import test.web.dto.PorudzbinaDTO;

@Component
public class PorudzbinaToPorudzbinaDTO implements Converter<Porudzbina, PorudzbinaDTO> {

	@Override
	public PorudzbinaDTO convert(Porudzbina source) {

		PorudzbinaDTO dto = new PorudzbinaDTO();
		
		dto.setId(source.getId());
		dto.setKolicina(source.getKolicina());
		dto.setProizvodID(source.getProizvod().getId());
		dto.setProizvodIme(source.getProizvod().getNaziv());
		
		return dto;
	}

	public List<PorudzbinaDTO> convert (List<Porudzbina> porudzbine) {
		
		List<PorudzbinaDTO> dto = new ArrayList<>();
		for(Porudzbina p: porudzbine) {
			PorudzbinaDTO DTO = convert(p);
			dto.add(DTO);
		}
		return dto;
	}
	
	
}
