package com.kobe.nucleus.service.dto;

import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.TypeIcon;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Menu} entity.
 */
public class MenuDTO implements Serializable {
    
    private Long id;

    private String menuName;

    private String description;

    private String libelle;

    private String icon;

    private TypeIcon type;


    private Long parentId;

    private String parentLibelle;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public TypeIcon getType() {
        return type;
    }

    public void setType(TypeIcon type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long menuId) {
        this.parentId = menuId;
    }

    public String getParentLibelle() {
        return parentLibelle;
    }

    public void setParentLibelle(String menuLibelle) {
        this.parentLibelle = menuLibelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MenuDTO)) {
            return false;
        }

        return id != null && id.equals(((MenuDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MenuDTO{" +
            "id=" + getId() +
            ", menuName='" + getMenuName() + "'" +
            ", description='" + getDescription() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", icon='" + getIcon() + "'" +
            ", type='" + getType() + "'" +
            ", parentId=" + getParentId() +
            ", parentLibelle='" + getParentLibelle() + "'" +
            "}";
    }
}
