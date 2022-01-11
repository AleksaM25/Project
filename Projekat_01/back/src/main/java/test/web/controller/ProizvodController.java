package test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.model.Proizvod;
import test.service.ProizvodService;
import test.support.ProizvodDTOToProizvod;
import test.support.ProizvodToProizvodDTO;
import test.web.dto.ProizvodDTO;

@RestController
@RequestMapping(value = "api/proizvodi")
public class ProizvodController {
	@Autowired
	private ProizvodService proizvodService;
	@Autowired
	private ProizvodToProizvodDTO toDTO;
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping
	public ResponseEntity<List<ProizvodDTO>> getAll(
			@RequestParam (value = "minCena", defaultValue = "0") Integer minCena,
			@RequestParam (value = "maxCena", required = false) Integer maxCena,
			@RequestParam (value = "kategorija", required = false) Long kategorija,
			@RequestParam (value = "pageNo", defaultValue = "0") int pageNo
			) {
		
		Page<Proizvod> page = null;
		
		if(minCena != null && maxCena != null && kategorija != null) {
			page = proizvodService.search(minCena, maxCena, kategorija, pageNo);			
		}else {
			page = proizvodService.getAll(pageNo);
		}			
		HttpHeaders header = new HttpHeaders();
		
		header.add("Total-Pages", Integer.toString(page.getTotalPages()));
		
		return new ResponseEntity<>(toDTO.convert(page.getContent()), header, HttpStatus.OK);		
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<ProizvodDTO> create (@Valid @RequestBody ProizvodDTO proizvodDTO) {
		
		if(proizvodDTO != null && proizvodDTO.getId() == null) {
			 Proizvod proizvod = proizvodService.save(proizvodDTO);
			return new ResponseEntity<>(toDTO.convert(proizvod),HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<ProizvodDTO> delete (@PathVariable Long id) {
		
		Proizvod obrisan = proizvodService.delete(id);
		if(obrisan == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(toDTO.convert(obrisan), HttpStatus.OK);
		}
		
		
	}
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<ProizvodDTO> getOne (@PathVariable Long id) {
		try {
			Proizvod proizvod = proizvodService.one(id).get();
			return new ResponseEntity<>(toDTO.convert(proizvod),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<ProizvodDTO> update (@RequestBody ProizvodDTO proizvodDTO , @PathVariable Long id){		
		try {
			if(proizvodDTO.getId() != null && proizvodDTO.getId().equals(id)) {
				Proizvod proizvod = proizvodService.update(proizvodDTO);
				return new ResponseEntity<>(toDTO.convert(proizvod), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
				
			}catch (Exception e) {
				e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
		
	}

}
