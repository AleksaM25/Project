package test.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;

@Entity
public class Proizvod {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String naziv;
	@Column(nullable = false)
	private int cena;
	@Column(nullable = false)
	private int stanje;
	
	@ManyToOne
	private Kategorija kategorija;
	@OneToMany(mappedBy = "proizvod", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Porudzbina> porudzbine;
	
	
	public Proizvod () {
		
	}
	
	public Proizvod (Proizvod proizvod) {
		
		this.id = proizvod.id;
		this.naziv = proizvod.naziv;
		this.cena = proizvod.cena;
		this.stanje = proizvod.stanje;
		this.kategorija = proizvod.kategorija;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public int getStanje() {
		return stanje;
	}

	public void setStanje(int stanje) {
		this.stanje = stanje;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public List<Porudzbina> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(List<Porudzbina> porudzbine) {
		this.porudzbine = porudzbine;
	}
	
	

	
	
	
	
	
}
