package it.prova.menupizzeria.service;

import java.util.List;

import it.prova.menupizzeria.dao.IngredienteDAO;
import it.prova.menupizzeria.dao.PizzaDAO;
import it.prova.menupizzeria.model.Ingrediente;

public interface IngredienteService {

	public List<Ingrediente> getAll() throws Exception; 

	public Ingrediente get(Long idIngrediente) throws Exception; 

	public void update(Ingrediente ingredienteInstance) throws Exception; 

	public void insert(Ingrediente ingredienteInstance) throws Exception; 

	public void delete(Long id) throws Exception; 
	
	
	public void setPizzaDAO(PizzaDAO pizzaDAO);

	public void setIngredienteDAO(IngredienteDAO ingredienteDAO);
	
	public boolean updateDisponibilita(Ingrediente ingredienteInstance) throws Exception; 
	

	public Ingrediente cercaPerNome(String nomeIngrediente); 
}
