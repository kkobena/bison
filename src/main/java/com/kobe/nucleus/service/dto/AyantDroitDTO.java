package com.kobe.nucleus.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import com.kobe.nucleus.domain.AyantDroit;
import com.kobe.nucleus.domain.Client;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.AyantDroit} entity.
 */
public class AyantDroitDTO implements Serializable {
    
    private Long id;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

    @NotNull
    private Status status;

    private String num;

    @NotNull
    private String firstName;

    private String lastName;

    private String sexe;

    private LocalDate datNaiss;

    private String mobile;


    private Long assureId;

    private String assureFirstName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDatNaiss() {
        return datNaiss;
    }

    public void setDatNaiss(LocalDate datNaiss) {
        this.datNaiss = datNaiss;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getAssureId() {
        return assureId;
    }

    public void setAssureId(Long clientId) {
        this.assureId = clientId;
    }

    public String getAssureFirstName() {
        return assureFirstName;
    }

    public void setAssureFirstName(String clientFirstName) {
        this.assureFirstName = clientFirstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AyantDroitDTO ayantDroitDTO = (AyantDroitDTO) o;
        if (ayantDroitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ayantDroitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
    public AyantDroitDTO() {
    	
    }
    public AyantDroitDTO(AyantDroit a) {
		super();
		this.id = a.getId();
		this.createdAt = a.getCreatedAt();
		this.updatedAt = a.getUpdatedAt();
		this.status = a.getStatus();
		this.num = a.getNum();
		this.firstName = a.getFirstName();
		this.lastName = a.getLastName();
		this.sexe = a.getSexe();
		this.datNaiss = a.getDatNaiss();
		Client c = a.getAssure();
		this.assureId = c.getId();
		this.assureFirstName = c.getFirstName() + " " + c.getLastName();
		

	}
    @Override
    public String toString() {
        return "AyantDroitDTO{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", status='" + getStatus() + "'" +
            ", num='" + getNum() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", datNaiss='" + getDatNaiss() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", assureId=" + getAssureId() +
            ", assureFirstName='" + getAssureFirstName() + "'" +
            "}";
    }
}
