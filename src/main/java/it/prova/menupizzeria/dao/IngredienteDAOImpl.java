package it.prova.menupizzeria.dao;

import java.util.List;

import it.prova.menupizzeria.model.Ingrediente;
import jakarta.persistence.EntityManager;

public class IngredienteDAOImpl implements IngredienteDAO{

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Ingrediente> getAll() throws Exception {
		return entityManager.createQuery("from Ingrediente",Ingrediente.class).getResultList();
	}

	@Override
	public Ingrediente get(Long id) throws Exception {
		return entityManager.find(Ingrediente.class, id);
	}

	@Override
	public void update(Ingrediente ingredienteInstance) throws Exception {
		if (ingredienteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		ingredienteInstance = entityManager.merge(ingredienteInstance);
	}

	@Override
	public void insert(Ingrediente ingredienteInstance) throws Exception {
		if (ingredienteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(ingredienteInstance);
	}

	@Override
	public void delete(Ingrediente ingredienteInstance) throws Exception {
		if (ingredienteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(ingredienteInstance));
	}


}
