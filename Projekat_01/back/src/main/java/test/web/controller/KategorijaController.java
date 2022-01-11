package test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.model.Kategorija;
import test.service.KategorijaService;
import test.support.KategorijaToKategorijaDTO;
import test.web.dto.KategorijaDTO;

@RestController
@RequestMapping(value = "api/kategorije")
public class KategorijaController {
	@Autowired
	private KategorijaToKategorijaDTO toDTO;
	@Autowired
	private KategorijaService kategorijaService;

	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping
	public ResponseEntity<List<KategorijaDTO>> getAll() {
		
		List<Kategorija> kategorija = kategorijaService.all();
		return new ResponseEntity<> (toDTO.convert(kategorija), HttpStatus.OK);
		
	}
	
	
}
