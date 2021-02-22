package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.kobe.nucleus.domain.enumeration.TypeMagasin;
import com.kobe.nucleus.domain.Magasin;
import com.kobe.nucleus.domain.User;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Magasin} entity.
 */
public class MagasinDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull
	private TypeMagasin typeMagasin;
	private String typeMagasinValue;

	private String nomCourt;

	@NotNull
	private String nomLong;

	private String addressePostal;

	private Status status=Status.ACTIVE;

	private String phone;

	private String mobile;

	private String commentaire;

	private String autreCommentaire;

	private String entete;

	private String compteContribuable;

	private String registreCommerce;

	private String registreImposition;

	private String centreImposition;

	private String numComptable;

	private Long magasinId;

	private String magasinNomCourt;

	private Long managerId;
   private boolean autonome;
	private String managerFirstName;
	private Set<RayonDTO> rayons = new HashSet<>();
	private Set<MagasinDTO> magasins = new HashSet<>();
	private Set<UserDTO> utilisateurs = new HashSet<>();
	private MagasinInfos magasinInfo;
	private UserDTO manager;

	
	public boolean isAutonome() {
		return autonome;
	}

	public void setAutonome(boolean autonome) {
		this.autonome = autonome;
	}

	public UserDTO getManager() {
		return manager;
	}

	public void setManager(UserDTO manager) {
		this.manager = manager;
	}

	public String getTypeMagasinValue() {
		return typeMagasinValue;
	}

	public void setTypeMagasinValue(String typeMagasinValue) {
		this.typeMagasinValue = typeMagasinValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypeMagasin getTypeMagasin() {
		return typeMagasin;
	}

	public void setTypeMagasin(TypeMagasin typeMagasin) {
		this.typeMagasin = typeMagasin;
	}

	public String getNomCourt() {
		return nomCourt;
	}

	public void setNomCourt(String nomCourt) {
		this.nomCourt = nomCourt;
	}

	public String getNomLong() {
		return nomLong;
	}

	public void setNomLong(String nomLong) {
		this.nomLong = nomLong;
	}

	public String getAddressePostal() {
		return addressePostal;
	}

	public void setAddressePostal(String addressePostal) {
		this.addressePostal = addressePostal;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getAutreCommentaire() {
		return autreCommentaire;
	}

	public void setAutreCommentaire(String autreCommentaire) {
		this.autreCommentaire = autreCommentaire;
	}

	public String getEntete() {
		return entete;
	}

	public void setEntete(String entete) {
		this.entete = entete;
	}

	public String getCompteContribuable() {
		return compteContribuable;
	}

	public void setCompteContribuable(String compteContribuable) {
		this.compteContribuable = compteContribuable;
	}

	public String getRegistreCommerce() {
		return registreCommerce;
	}

	public void setRegistreCommerce(String registreCommerce) {
		this.registreCommerce = registreCommerce;
	}

	public String getRegistreImposition() {
		return registreImposition;
	}

	public void setRegistreImposition(String registreImposition) {
		this.registreImposition = registreImposition;
	}

	public String getCentreImposition() {
		return centreImposition;
	}

	public void setCentreImposition(String centreImposition) {
		this.centreImposition = centreImposition;
	}

	public String getNumComptable() {
		return numComptable;
	}

	public void setNumComptable(String numComptable) {
		this.numComptable = numComptable;
	}

	public Long getMagasinId() {
		return magasinId;
	}

	public void setMagasinId(Long magasinId) {
		this.magasinId = magasinId;
	}

	public String getMagasinNomCourt() {
		return magasinNomCourt;
	}

	public void setMagasinNomCourt(String magasinNomCourt) {
		this.magasinNomCourt = magasinNomCourt;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long utilisateurId) {
		this.managerId = utilisateurId;
	}

	public String getManagerFirstName() {
		return managerFirstName;
	}

	public void setManagerFirstName(String utilisateurFirstName) {
		this.managerFirstName = utilisateurFirstName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MagasinDTO)) {
			return false;
		}

		return id != null && id.equals(((MagasinDTO) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	public MagasinDTO() {

	}

	public Set<RayonDTO> getRayons() {
		return rayons;
	}

	public void setRayons(Set<RayonDTO> rayons) {
		this.rayons = rayons;
	}

	public Set<MagasinDTO> getMagasins() {
		return magasins;
	}

	public void setMagasins(Set<MagasinDTO> magasins) {
		this.magasins = magasins;
	}

	public Set<UserDTO> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(Set<UserDTO> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public MagasinInfos getMagasinInfo() {
		return magasinInfo;
	}

	public void setMagasinInfo(MagasinInfos magasinInfo) {
		this.magasinInfo = magasinInfo;
	}

	public MagasinDTO(Magasin magasin) {

		this.id = magasin.getId();
		this.typeMagasin = magasin.getTypeMagasin();
		this.nomCourt = magasin.getNomCourt();
		this.nomLong = magasin.getNomLong();
		this.addressePostal = magasin.getAddressePostal();
		this.phone = magasin.getPhone();
		this.mobile = magasin.getMobile();
		this.commentaire = magasin.getCommentaire();
		this.autreCommentaire = magasin.getAutreCommentaire();
		this.entete = magasin.getEntete();
		this.compteContribuable = magasin.getCompteContribuable();
		this.registreCommerce = magasin.getRegistreCommerce();
		this.registreImposition = magasin.getRegistreImposition();
		this.centreImposition = magasin.getCentreImposition();
		this.numComptable = magasin.getNumComptable();
		this.autonome=magasin.isAutonome();
		Magasin parent = magasin.getMagasin();
		if (parent != null) {
			this.magasinId = parent.getId();
			this.magasinNomCourt = parent.getNomCourt();
		}
		User manager = magasin.getManager();
		if (manager != null) {
			this.managerId = manager.getId();
			this.managerFirstName = manager.getFirstName() + " " + manager.getLastName();
			this.manager = new UserDTO(manager);
		}
		this.magasinInfo = new MagasinInfos(this.commentaire, this.autreCommentaire, this.entete,this.id);
		this.magasins = magasin.getMagasins().stream().map(MagasinDTO::new).collect(Collectors.toSet());
		this.utilisateurs = magasin.getUtilisateurs().stream().map(UserDTO::new).collect(Collectors.toSet());
		this.typeMagasinValue = magasin.getTypeMagasin().getValue();

	}

	@Override
	public String toString() {
		return "MagasinDTO{" + "id=" + getId() + ", typeMagasin='" + getTypeMagasin() + "'" + ", nomCourt='"
				+ getNomCourt() + "'" + ", nomLong='" + getNomLong() + "'" + ", addressePostal='" + getAddressePostal()
				+ "'" + ", status='" + getStatus() + "'" + ", phone='" + getPhone() + "'" + ", mobile='" + getMobile()
				+ "'" + ", commentaire='" + getCommentaire() + "'" + ", autreCommentaire='" + getAutreCommentaire()
				+ "'" + ", entete='" + getEntete() + "'" + ", compteContribuable='" + getCompteContribuable() + "'"
				+ ", registreCommerce='" + getRegistreCommerce() + "'" + ", registreImposition='"
				+ getRegistreImposition() + "'" + ", centreImposition='" + getCentreImposition() + "'"
				+ ", numComptable='" + getNumComptable() + "'" + ", magasinId=" + getMagasinId() + ", magasinNomCourt='"
				+ getMagasinNomCourt() + "'" + ", managerId=" + getManagerId() + ", managerFirstName='"
				+ getManagerFirstName() + "'" + "}";
	}
}
