package com.kobe.nucleus.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A RoleUtilisateur.
 */
@Entity
@Table(name = "role_utilisateur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleUtilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "jhi_desc")
    private String desc;

    @Column(name = "enable")
    private Boolean enable;

    @OneToOne
    @JoinColumn(unique = true)
    private Utilisateur utilisateur;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "role_utilisateur_menu",
               joinColumns = @JoinColumn(name = "role_utilisateur_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"))
    private Set<Menu> menus = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public RoleUtilisateur name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public RoleUtilisateur desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean isEnable() {
        return enable;
    }

    public RoleUtilisateur enable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public RoleUtilisateur utilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        return this;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public RoleUtilisateur menus(Set<Menu> menus) {
        this.menus = menus;
        return this;
    }

    public RoleUtilisateur addMenu(Menu menu) {
        this.menus.add(menu);
        menu.getRoleUtilisateurs().add(this);
        return this;
    }

    public RoleUtilisateur removeMenu(Menu menu) {
        this.menus.remove(menu);
        menu.getRoleUtilisateurs().remove(this);
        return this;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleUtilisateur)) {
            return false;
        }
        return id != null && id.equals(((RoleUtilisateur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleUtilisateur{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", desc='" + getDesc() + "'" +
            ", enable='" + isEnable() + "'" +
            "}";
    }
}
