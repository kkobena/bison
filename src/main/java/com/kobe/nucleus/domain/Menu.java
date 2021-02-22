package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.TypeIcon;

/**
 * A Menu.
 */
@Entity
@Table(name = "menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "description")
    private String description;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "icon")
    private String icon;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeIcon type;

    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Menu> menus = new HashSet<>();

    @OneToMany(mappedBy = "menu")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Permission> permissions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "menus", allowSetters = true)
    private Menu parent;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public Menu menuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public Menu description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLibelle() {
        return libelle;
    }

    public Menu libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getIcon() {
        return icon;
    }

    public Menu icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public TypeIcon getType() {
        return type;
    }

    public Menu type(TypeIcon type) {
        this.type = type;
        return this;
    }

    public void setType(TypeIcon type) {
        this.type = type;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public Menu menus(Set<Menu> menus) {
        this.menus = menus;
        return this;
    }

    public Menu addMenu(Menu menu) {
        this.menus.add(menu);
        menu.setParent(this);
        return this;
    }

    public Menu removeMenu(Menu menu) {
        this.menus.remove(menu);
        menu.setParent(null);
        return this;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Menu permissions(Set<Permission> permissions) {
        this.permissions = permissions;
        return this;
    }

    public Menu addPermission(Permission permission) {
        this.permissions.add(permission);
        permission.setMenu(this);
        return this;
    }

    public Menu removePermission(Permission permission) {
        this.permissions.remove(permission);
        permission.setMenu(null);
        return this;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Menu getParent() {
        return parent;
    }

    public Menu parent(Menu menu) {
        this.parent = menu;
        return this;
    }

    public void setParent(Menu menu) {
        this.parent = menu;
    }

   

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Menu)) {
            return false;
        }
        return id != null && id.equals(((Menu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Menu{" +
            "id=" + getId() +
            ", menuName='" + getMenuName() + "'" +
            ", description='" + getDescription() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", icon='" + getIcon() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
