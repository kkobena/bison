package com.kobe.nucleus.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Peremption;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Lot} entity.
 */
public class LotDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

    @NotNull
    private Integer qte;

    @NotNull
    private Integer qtUg;

    private String num;

    private LocalDate dateFabrication;

    private LocalDate peremption;

    private Peremption peremptionstatus;


    private Long commandeItemId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public Integer getQtUg() {
        return qtUg;
    }

    public void setQtUg(Integer qtUg) {
        this.qtUg = qtUg;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public LocalDate getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(LocalDate dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public LocalDate getPeremption() {
        return peremption;
    }

    public void setPeremption(LocalDate peremption) {
        this.peremption = peremption;
    }

    public Peremption getPeremptionstatus() {
        return peremptionstatus;
    }

    public void setPeremptionstatus(Peremption peremptionstatus) {
        this.peremptionstatus = peremptionstatus;
    }

    public Long getCommandeItemId() {
        return commandeItemId;
    }

    public void setCommandeItemId(Long commandeItemId) {
        this.commandeItemId = commandeItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LotDTO)) {
            return false;
        }

        return id != null && id.equals(((LotDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LotDTO{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", qte=" + getQte() +
            ", qtUg=" + getQtUg() +
            ", num='" + getNum() + "'" +
            ", dateFabrication='" + getDateFabrication() + "'" +
            ", peremption='" + getPeremption() + "'" +
            ", peremptionstatus='" + getPeremptionstatus() + "'" +
            ", commandeItemId=" + getCommandeItemId() +
            "}";
    }
}
