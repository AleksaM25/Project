package test.service;

import java.util.List;

import test.model.Porudzbina;


public interface PorudzbinaService {
	
	
	List<Porudzbina> getAll();
	Porudzbina findOne(Long id);
	Porudzbina save (Porudzbina porudzbina);

}
