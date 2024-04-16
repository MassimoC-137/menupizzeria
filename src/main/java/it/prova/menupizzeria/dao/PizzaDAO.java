package it.prova.menupizzeria.dao;

import java.util.List;
import java.util.Set;

import it.prova.menupizzeria.model.Ingrediente;
import it.prova.menupizzeria.model.Pizza;

public interface PizzaDAO extends IBaseDAO<Pizza>{
	
	public List<Pizza> cercaPerIngrediente(Set<Ingrediente> ingredienti); 
	
	public Pizza getByName(String nome) throws Exception;
	
}
