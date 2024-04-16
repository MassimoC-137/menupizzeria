package it.prova.menupizzeria.model;

import java.util.HashSet;
import java.util.Set;

import it.prova.menupizzeria.service.PizzaService;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="pizza")

public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id; 
	@Column(name = "nome")
	private String nome;
	@Column(name = "prezzo")
	private float prezzo; 
	@Column(name = "gourmet")
	private boolean gourmet; 
	

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="ingredienti_pizza", joinColumns = @JoinColumn(name = "id_pizza", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_ingrediente", referencedColumnName = "id"))
			
	private Set<Ingrediente> ingredienti = new HashSet<>(0);
	
	
	public Pizza () {
		
	}

	public Pizza(Long idp) {
		this.setId(idp);
	}
	
	public Pizza(Long id, String nome, float prezzo, boolean gourmet, Set<Ingrediente> ingredienti) {
		this.setId(id);
		this.setNome(nome); 
		this.setPrezzo(prezzo); 
		this.setGourmet(gourmet);
		this.setIngredienti(ingredienti); 
	}

	public void setId(Long id) {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public boolean isGourmet() {
		return gourmet;
	}

	public void setGourmet(boolean gourmet) {
		this.gourmet = gourmet;
	}
	
	public Set<Ingrediente> getIngredienti() {
		return ingredienti;
	}
	
	public void setIngredienti(Set<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}

	
	public String toStrings() {
		return "La pizza " + nome + " è la numero " + id + " e costa " + prezzo;
	}
	
	@Override
	public String toString() {
		return "La pizza " + nome + " è la numero " + id + " e costa " + prezzo + " ed i suoi ingredienti sono: " + ingredienti;
	}
	
	
}
