package it.prova.menupizzeria.service;

import java.util.List;
import java.util.Set;

import it.prova.menupizzeria.dao.EntityManagerUtil;
import it.prova.menupizzeria.dao.IngredienteDAO;
import it.prova.menupizzeria.dao.PizzaDAO;
import it.prova.menupizzeria.model.Ingrediente;
import it.prova.menupizzeria.model.Pizza;
import jakarta.persistence.EntityManager;

public class PizzaServiceImpl implements PizzaService{

	private PizzaDAO pizzaDAO; 

	@Override
	public void setPizzaDAO(PizzaDAO pizzaDAO) {
		this.pizzaDAO = pizzaDAO; 
		
	}

	@Override
	public void setIngredienteDAO(IngredienteDAO ingredienteDAO) {
	}
	
	@Override
	public List<Pizza> getAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			pizzaDAO.setEntityManager(entityManager);
			return pizzaDAO.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw e; 
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Pizza get(String nome) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager(); 
		
		try {
			pizzaDAO.setEntityManager(entityManager);
			return pizzaDAO.get(nome); 
		} catch (Exception e){ 
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void update(Pizza pizzaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			pizzaDAO.setEntityManager(entityManager); 
			
			pizzaDAO.update(pizzaInstance); 
			
			entityManager.getTransaction().commit();
		} catch (Exception e) { 
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e; 
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}
	

	@Override
	public void insert(Pizza pizzaInstance, Set<Ingrediente> ingredienti) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			
	        pizzaInstance.setIngredienti(ingredienti);

			pizzaDAO.setEntityManager(entityManager);

			pizzaDAO.insert(pizzaInstance);
			
			for (Ingrediente ingrediente : ingredienti) {
	            if (ingrediente.setId() == null || entityManager.find(Ingrediente.class, ingrediente.setId()) == null) {
	                entityManager.persist(ingrediente);
	            } else {
	                ingrediente = entityManager.merge(ingrediente);
	            }
	        }
	        pizzaInstance.setIngredienti(ingredienti);

	        entityManager.persist(pizzaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void delete(String nome) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			pizzaDAO.setEntityManager(entityManager);

			pizzaDAO.delete(pizzaDAO.get(nome));

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public List<Pizza> cercaPerIngrediente(Set<Ingrediente> ingredienti) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			pizzaDAO.setEntityManager(entityManager);

			return pizzaDAO.cercaPerIngrediente(ingredienti);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}
	

}
