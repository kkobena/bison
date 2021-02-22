package com.kobe.nucleus.service.dto;

public class MagasinInfos {

	private String commentaire;

	private String autreCommentaire;

	private String entete;
	private long id;

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

	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MagasinInfos(String commentaire, String autreCommentaire, String entete,long magasinId) {
		this.commentaire = commentaire;
		this.autreCommentaire = autreCommentaire;
		this.entete = entete;
		this.id=magasinId;
	}

	public MagasinInfos() {

	}

}
