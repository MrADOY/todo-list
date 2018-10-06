package com.adoy.todo.modeles;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author aurelienpietrzak Cette classe permettra le mapping avec le document
 *         mongoDB
 */

@Document(collection = "todos")
@JsonIgnoreProperties(value = { "dateCreation" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Todo {
	
	@Id
	private String id;
    @NotBlank
    @Size(max=100)
    @Indexed(unique=true)
    protected String titre;
  	protected String commentaire;
    protected Boolean terminee;
    protected Date dateFin;
    protected Date dateCreation;
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Boolean getTerminee() {
		return terminee;
	}
	public void setTerminee(Boolean terminee) {
		this.terminee = terminee;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	
}
