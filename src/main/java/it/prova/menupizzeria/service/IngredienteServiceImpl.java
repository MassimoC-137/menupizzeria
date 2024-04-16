package it.prova.menupizzeria.service;

import java.util.List;

import it.prova.menupizzeria.dao.EntityManagerUtil;
import it.prova.menupizzeria.dao.IngredienteDAO;
import it.prova.menupizzeria.dao.PizzaDAO;
import it.prova.menupizzeria.model.Ingrediente;
import jakarta.persistence.EntityManager;

public class IngredienteServiceImpl implements IngredienteService{

	private IngredienteDAO ingredienteDAO; 
	
	@Override
	public void setPizzaDAO(PizzaDAO pizzaDAO) { 
		
	}

	@Override
	public void setIngredienteDAO(IngredienteDAO ingredienteDAO) {
		this.ingredienteDAO = ingredienteDAO;
	}
	
	@Override
	public List<Ingrediente> getAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			ingredienteDAO.setEntityManager(entityManager);
			return ingredienteDAO.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw e; 
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ingrediente get(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager(); 
		
		try {
			ingredienteDAO.setEntityManager(entityManager);
			return ingredienteDAO.get(id); 
		} catch (Exception e){ 
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void update(Ingrediente ingredienteInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			ingredienteDAO.setEntityManager(entityManager); 
			
			ingredienteDAO.update(ingredienteInstance); 
			
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
	public void insert(Ingrediente ingredienteInstance) throws Exception {
	    EntityManager entityManager = EntityManagerUtil.getEntityManager();

	    try {
	        entityManager.getTransaction().begin();

	        ingredienteInstance.setDisponibilita(true);

	        ingredienteDAO.setEntityManager(entityManager);
	        ingredienteDAO.insert(ingredienteInstance);

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

			ingredienteDAO.setEntityManager(entityManager);

			ingredienteDAO.delete(ingredienteDAO.get(id));

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
	public boolean updateDisponibilita(Ingrediente ingredienteInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
        
		try { 
			entityManager.getTransaction().begin();
			
			ingredienteInstance.setDisponibilita(!ingredienteInstance.isDisponibilita());
			
	        entityManager.merge(ingredienteInstance);
	        
	        entityManager.getTransaction().commit();
	        
	        return ingredienteInstance.isDisponibilita();
		} catch (Exception e) {
	        entityManager.getTransaction().rollback(); 
	        throw e;
	    } finally {
	        entityManager.close(); 
	    }

	}

	@Override
	public Ingrediente cercaPerNome(String nomeIngrediente) {
		return ingredienteDAO.getByNome(nomeIngrediente);
	}

	

}
