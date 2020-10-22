package com.kobe.nucleus.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Medecin.
 */
@Entity
@Table(name = "medecin")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Medecin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "mobile")
	private String mobile;

	@NotNull
	@Column(name = "num", nullable = false, unique = true)
	private String num;

	@OneToMany(mappedBy = "medecin")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Vente> ventes = new HashSet<>();

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public Medecin firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Medecin lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public Medecin mobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNum() {
		return num;
	}
	public Medecin() {
		
	}
	public Medecin num(String num) {
		this.num = num;
		return this;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Set<Vente> getVentes() {
		return ventes;
	}

	public void setVentes(Set<Vente> ventes) {
		this.ventes = ventes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Medecin)) {
			return false;
		}
		return id != null && id.equals(((Medecin) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	// prettier-ignore
	@Override
	public String toString() {
		return "Medecin{" + "id=" + getId() + ", firstName='" + getFirstName() + "'" + ", lastName='" + getLastName()
				+ "'" + ", mobile='" + getMobile() + "'" + ", num='" + getNum() + "'" + "}";
	}
}
