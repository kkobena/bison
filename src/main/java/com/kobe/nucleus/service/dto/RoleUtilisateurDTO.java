package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.RoleUtilisateur} entity.
 */
public class RoleUtilisateurDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    private String desc;

    private Boolean enable;


    private Long utilisateurId;
    private Set<MenuDTO> menus = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean isEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Set<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(Set<MenuDTO> menus) {
        this.menus = menus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleUtilisateurDTO)) {
            return false;
        }

        return id != null && id.equals(((RoleUtilisateurDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleUtilisateurDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", desc='" + getDesc() + "'" +
            ", enable='" + isEnable() + "'" +
            ", utilisateurId=" + getUtilisateurId() +
            ", menus='" + getMenus() + "'" +
            "}";
    }
}
