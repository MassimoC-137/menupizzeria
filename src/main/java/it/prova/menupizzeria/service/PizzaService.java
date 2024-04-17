package it.prova.menupizzeria.service;

import java.util.List;
import java.util.Set;

import it.prova.menupizzeria.dao.IngredienteDAO;
import it.prova.menupizzeria.dao.PizzaDAO;
import it.prova.menupizzeria.model.Ingrediente;
import it.prova.menupizzeria.model.Pizza;

public interface PizzaService {

	public List<Pizza> getAll() throws Exception; 
	
	public Pizza get(String nome) throws Exception; 
	
	public void update(Pizza pizzaInstance, Set<String> ingredienti) throws Exception; 
	
	public void insert(Pizza pizzaInstance, Set<String> ingredienti) throws Exception; 
	
	public void delete(Long id) throws Exception; 
	
	public List<Pizza> cercaPerIngrediente(Set <Ingrediente> ingredienti) throws Exception; 
	
	public void togliPizzeDalMenuSeIngredienteMancante(List<Pizza> pizze, Ingrediente ingrediente) throws Exception;  
	
	
	public void setPizzaDAO(PizzaDAO pizzaDAO);

	public void setIngredienteDAO(IngredienteDAO ingredienteDAO);


	
}
