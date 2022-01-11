package test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.model.Porudzbina;
import test.service.PorudzbinaService;
import test.support.PorudzbinaDTOToPorudzbina;
import test.support.PorudzbinaToPorudzbinaDTO;
import test.web.dto.PorudzbinaDTO;

@RestController
@RequestMapping(value = "api/porudzbine")
public class PorudzbinaController {
	@Autowired
	private PorudzbinaService porudzbinaService;
	@Autowired
	private PorudzbinaToPorudzbinaDTO toDTO;
	@Autowired
	private PorudzbinaDTOToPorudzbina toJO;

	
	@GetMapping
	public ResponseEntity<List<PorudzbinaDTO>> getAll() {
		
		List<Porudzbina> porudzbina = porudzbinaService.getAll();
		
		return new ResponseEntity<>(toDTO.convert(porudzbina), HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PorudzbinaDTO> create ( @RequestBody PorudzbinaDTO porudzbinaDTO) {
		
		Porudzbina porudzbina = toJO.convert(porudzbinaDTO);
		System.out.println(porudzbina.getId());
		if (porudzbina != null && porudzbina.getId() == null) {
			Porudzbina sacuvana = porudzbinaService.save(porudzbina);
			return new ResponseEntity<>(toDTO.convert(sacuvana), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
	
}
