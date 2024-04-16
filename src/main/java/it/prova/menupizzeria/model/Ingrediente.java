package it.prova.menupizzeria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingrediente")

public class Ingrediente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id; 
	@Column(name="nome")
	private String nome; 
	@Column(name="disponibilita")
	private boolean disponibilita; 
	
	public Ingrediente() {
		
	}
	
	public Ingrediente(Long id, String nome, boolean disponibilita) {
		this.setId(id);
		this.setNome(nome); 
		this.setDisponibilita(disponibilita);
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}

	@Override
	public String toString() {
		return "L'ingrediente " + nome + " numero " + id +  " è oggi disponibile? " + disponibilita;
	}
	
	
	
}
