package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the je_prijatelj database table.
 * 
 */
@Entity
@Table(name="je_prijatelj")
@NamedQuery(name="JePrijatelj.findAll", query="SELECT j FROM JePrijatelj j")
public class JePrijatelj implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int je_prijateljID;

	private String status;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="korisnik_korisnikID")
	private Korisnik korisnik1;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="korisnik_korisnikID1")
	private Korisnik korisnik2;

	public JePrijatelj() {
	}

	public int getJe_prijateljID() {
		return this.je_prijateljID;
	}

	public void setJe_prijateljID(int je_prijateljID) {
		this.je_prijateljID = je_prijateljID;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Korisnik getKorisnik1() {
		return this.korisnik1;
	}

	public void setKorisnik1(Korisnik korisnik1) {
		this.korisnik1 = korisnik1;
	}

	public Korisnik getKorisnik2() {
		return this.korisnik2;
	}

	public void setKorisnik2(Korisnik korisnik2) {
		this.korisnik2 = korisnik2;
	}

}