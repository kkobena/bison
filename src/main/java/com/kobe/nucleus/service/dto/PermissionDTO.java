package com.kobe.nucleus.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Permission} entity.
 */
public class PermissionDTO implements Serializable {
    
    private Long id;

    private String description;

    private String name;


    private Long createdById;

    private String createdByFirstName;

    private Long menuId;

    private String menuLibelle;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long utilisateurId) {
        this.createdById = utilisateurId;
    }

    public String getCreatedByFirstName() {
        return createdByFirstName;
    }

    public void setCreatedByFirstName(String utilisateurFirstName) {
        this.createdByFirstName = utilisateurFirstName;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuLibelle() {
        return menuLibelle;
    }

    public void setMenuLibelle(String menuLibelle) {
        this.menuLibelle = menuLibelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PermissionDTO)) {
            return false;
        }

        return id != null && id.equals(((PermissionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PermissionDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", name='" + getName() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByFirstName='" + getCreatedByFirstName() + "'" +
            ", menuId=" + getMenuId() +
            ", menuLibelle='" + getMenuLibelle() + "'" +
            "}";
    }
}
