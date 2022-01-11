package test.web.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ProizvodDTO {

	private Long id;
	@NotEmpty
	@Size(max = 15)
	private String naziv;
	@Positive
	private int cena;
	@Positive
	private int stanje;
	
	private Long kategorijaID;
	
	
	private String kategorijaIme;
	
	
	
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
	public Long getKategorijaID() {
		return kategorijaID;
	}
	public void setKategorijaID(Long kategorijaID) {
		this.kategorijaID = kategorijaID;
	}
	
	public String getKategorijaIme() {
		return kategorijaIme;
	}
	public void setKategorijaIme(String kategorijaIme) {
		this.kategorijaIme = kategorijaIme;
	}
	
	
	
	
}
