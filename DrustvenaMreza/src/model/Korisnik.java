package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Calendar;
import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int korisnikID;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar datumRodjenja;

	private String email;

	private String korisnickoIme;

	private String password;

	private String punoIme;

	private String punoPrezime;

	private String putanjaSlike;

	//bi-directional many-to-one association to JePrijatelj
	@OneToMany(mappedBy="korisnik1", fetch=FetchType.LAZY)
	private List<JePrijatelj> jePrijateljs1;

	//bi-directional many-to-one association to JePrijatelj
	@OneToMany(mappedBy="korisnik2", fetch=FetchType.LAZY)
	private List<JePrijatelj> jePrijateljs2;

	//bi-directional many-to-one association to Status
	@OneToMany(mappedBy="korisnik", fetch=FetchType.LAZY)
	private List<Status> statuses;

	public Korisnik() {
	}

	public int getKorisnikID() {
		return this.korisnikID;
	}

//	public void setKorisnikID(int korisnikID) {
//		this.korisnikID = korisnikID;
//	}

	public Calendar getDatumRodjenja() {
		return this.datumRodjenja;
	}

	public void setDatumRodjenja(Calendar datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKorisnickoIme() {
		return this.korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPunoIme() {
		return this.punoIme;
	}

	public void setPunoIme(String punoIme) {
		this.punoIme = punoIme;
	}

	public String getPunoPrezime() {
		return this.punoPrezime;
	}

	public void setPunoPrezime(String punoPrezime) {
		this.punoPrezime = punoPrezime;
	}

	public String getPutanjaSlike() {
		return this.putanjaSlike;
	}

	public void setPutanjaSlike(String putanjaSlike) {
		this.putanjaSlike = putanjaSlike;
	}

	public List<JePrijatelj> getJePrijateljs1() {
		return this.jePrijateljs1;
	}

	public void setJePrijateljs1(List<JePrijatelj> jePrijateljs1) {
		this.jePrijateljs1 = jePrijateljs1;
	}

	public JePrijatelj addJePrijateljs1(JePrijatelj jePrijateljs1) {
		getJePrijateljs1().add(jePrijateljs1);
		jePrijateljs1.setKorisnik1(this);

		return jePrijateljs1;
	}

	public JePrijatelj removeJePrijateljs1(JePrijatelj jePrijateljs1) {
		getJePrijateljs1().remove(jePrijateljs1);
		jePrijateljs1.setKorisnik1(null);

		return jePrijateljs1;
	}

	public List<JePrijatelj> getJePrijateljs2() {
		return this.jePrijateljs2;
	}

	public void setJePrijateljs2(List<JePrijatelj> jePrijateljs2) {
		this.jePrijateljs2 = jePrijateljs2;
	}

	public JePrijatelj addJePrijateljs2(JePrijatelj jePrijateljs2) {
		getJePrijateljs2().add(jePrijateljs2);
		jePrijateljs2.setKorisnik2(this);

		return jePrijateljs2;
	}

	public JePrijatelj removeJePrijateljs2(JePrijatelj jePrijateljs2) {
		getJePrijateljs2().remove(jePrijateljs2);
		jePrijateljs2.setKorisnik2(null);

		return jePrijateljs2;
	}

	public List<Status> getStatuses() {
		return this.statuses;
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}

	public Status addStatus(Status status) {
		getStatuses().add(status);
		status.setKorisnik(this);

		return status;
	}

	public Status removeStatus(Status status) {
		getStatuses().remove(status);
		status.setKorisnik(null);

		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((korisnickoIme == null) ? 0 : korisnickoIme.hashCode());
		result = prime * result + korisnikID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (korisnickoIme == null) {
			if (other.korisnickoIme != null)
				return false;
		} else if (!korisnickoIme.equals(other.korisnickoIme))
			return false;
		if (korisnikID != other.korisnikID)
			return false;
		return true;
	}

}