package it.prova.menupizzeria.dao;

import java.util.List;
import java.util.Set;

import it.prova.menupizzeria.model.Ingrediente;
import it.prova.menupizzeria.model.Pizza;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class PizzaDAOImpl implements PizzaDAO{

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}
	
	@Override
	public List<Pizza> getAll() throws Exception {
		return entityManager.createQuery("from Pizza",Pizza.class).getResultList();
	}

	@Override
	public Pizza get(String nome) throws Exception {
		return entityManager.find(Pizza.class, nome);
	}

	@Override
	public void update(Pizza pizzaInstance) throws Exception {
		if (pizzaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		pizzaInstance = entityManager.merge(pizzaInstance);
	}

	@Override
	public void insert(Pizza pizzaInstance) throws Exception {
		if (pizzaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(pizzaInstance);
	}

	@Override
	public void delete(Pizza pizzaInstance) throws Exception {
		if (pizzaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(pizzaInstance));
	}

	@Override
	public List<Pizza> cercaPerIngrediente(Set<Ingrediente> ingredienti) {
		TypedQuery<Pizza> query = entityManager.createQuery("select p FROM Pizza p join u.Ingrediente i where i = :ingrediente",Pizza.class);
		query.setParameter("ingrediente", ingredienti);
		return query.getResultList();
	}

	@Override
	public Pizza get(Long id) throws Exception {
		return entityManager.find(Pizza.class, id);
	}

	


}