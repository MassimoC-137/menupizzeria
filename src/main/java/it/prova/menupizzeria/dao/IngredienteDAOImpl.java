package it.prova.menupizzeria.dao;

import java.util.List;

import it.prova.menupizzeria.model.Ingrediente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

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
	public Ingrediente getByNome(String nome) {
		try {
			return entityManager.createQuery("from Ingrediente i where i.nome = '" + nome + "'",Ingrediente.class).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Ingrediente update(Ingrediente ingredienteInstance) throws Exception {
		if (ingredienteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		return entityManager.merge(ingredienteInstance);
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
