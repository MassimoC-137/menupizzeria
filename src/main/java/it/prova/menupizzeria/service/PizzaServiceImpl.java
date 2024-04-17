package it.prova.menupizzeria.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.prova.menupizzeria.dao.EntityManagerUtil;
import it.prova.menupizzeria.dao.IngredienteDAO;
import it.prova.menupizzeria.dao.PizzaDAO;
import it.prova.menupizzeria.model.Ingrediente;
import it.prova.menupizzeria.model.Pizza;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

public class PizzaServiceImpl implements PizzaService {

	private PizzaDAO pizzaDAO;
	private IngredienteDAO ingredienteDAO;

	@Override
	public void setPizzaDAO(PizzaDAO pizzaDAO) {
		this.pizzaDAO = pizzaDAO;
	}

	@Override
	public void setIngredienteDAO(IngredienteDAO ingredienteDAO) {
		this.ingredienteDAO = ingredienteDAO;
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
			return pizzaDAO.getByName(nome);
		} catch (NoResultException e) {
			System.out.println("Non esiste alcuna pizza con il nome : " + nome);
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void update(Pizza pizzaInstance, Set<String> ingredienti) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			pizzaDAO.setEntityManager(entityManager); 
			ingredienteDAO.setEntityManager(entityManager);
			
			Set<Ingrediente> ingred = new HashSet<>();
			
			for (String ingrediente : ingredienti) {
				Ingrediente ing = ingredienteDAO.getByNome(ingrediente);
	            if (ing == null) {
	                ingredienteDAO.insert(new Ingrediente(null, ingrediente, true));
	            }
	            ingred.add(ing); 
			}
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
	@Transactional
	public void insert(Pizza pizzaInstance, Set<String> ingredienti) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			pizzaDAO.setEntityManager(entityManager);
			ingredienteDAO.setEntityManager(entityManager);

			Set<Ingrediente> ingred = new HashSet<Ingrediente>();

			for (String ingrediente : ingredienti) {
				Ingrediente ing = ingredienteDAO.getByNome(ingrediente);
				if (ing == null) {
					ingredienteDAO.insert(new Ingrediente(null, ingrediente, true));
					ing = ingredienteDAO.getByNome(ingrediente);
				} else {
					ingredienteDAO.update(ing);
				}
				ingred.add(ing);

			}
			pizzaInstance.setIngredienti(ingred);

			pizzaDAO.insert(pizzaInstance);

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
	public void delete(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			pizzaDAO.setEntityManager(entityManager);

			pizzaDAO.delete(pizzaDAO.get(id));

			entityManager.getTransaction().commit();
		} catch (NoResultException e) {
			System.out.println("Non esiste alcuna pizza con ID : " + id);
			entityManager.getTransaction().rollback();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
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
