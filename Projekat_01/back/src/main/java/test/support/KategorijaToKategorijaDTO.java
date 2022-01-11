package test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.model.Kategorija;
import test.web.dto.KategorijaDTO;

@Component
public class KategorijaToKategorijaDTO implements Converter<Kategorija, KategorijaDTO> {

	@Override
	public KategorijaDTO convert(Kategorija kategorija) {
		
		KategorijaDTO dto = new KategorijaDTO();
		
		dto.setId(kategorija.getId());
		dto.setNaziv(kategorija.getNaziv());
		
		return dto;
	}
	
	public List<KategorijaDTO> convert(List<Kategorija> kategorije) {
		
		List<KategorijaDTO> dto = new ArrayList<>();
		
		for(Kategorija k : kategorije ) {
			
			KategorijaDTO DTO = convert(k);
			dto.add(DTO);
		}
		return dto;
		
	}

}
