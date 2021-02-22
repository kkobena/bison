package com.kobe.nucleus.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kobe.nucleus.domain.Authority;

public class AuthorityDTO implements Serializable {

	private static final long serialVersionUID = -4508401300358756645L;
	private List<PermissionDTO> permissions=new ArrayList<>();
	private List<MenuDTO> menus=new ArrayList<>();
    private String name;
    private String libelle;
	public List<PermissionDTO> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<PermissionDTO> permissions) {
		this.permissions = permissions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public List<MenuDTO> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuDTO> menus) {
		this.menus = menus;
	}
	public AuthorityDTO() {
		
	}
	public AuthorityDTO(Authority authority) {
        this.name=authority.getName();
        this.libelle=authority.getLibelle();
	
	}
    
	
}
