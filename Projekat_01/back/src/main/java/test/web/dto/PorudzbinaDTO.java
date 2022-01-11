package test.web.dto;

public class PorudzbinaDTO {

	private Long id;
	
	private int kolicina;
	
	private Long proizvodID;
	
	public Long getProizvodID() {
		return proizvodID;
	}

	public void setProizvodID(Long proizvodID) {
		this.proizvodID = proizvodID;
	}

	private String proizvodIme;

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

	public String getProizvodIme() {
		return proizvodIme;
	}

	public void setProizvodIme(String proizvodIme) {
		this.proizvodIme = proizvodIme;
	}
	
	
	
	
	
}
