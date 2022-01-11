package test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Porudzbina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Proizvod proizvod;
	
	@Column
	private int kolicina;
	
	public Porudzbina () {
		
	}
	
	public Porudzbina (Porudzbina porudzbina) {
		
		this.id = porudzbina.id;
		this.kolicina = porudzbina.kolicina;
		this.proizvod = porudzbina.proizvod;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}
	
	
	
	
	
	
	
	
	
}
