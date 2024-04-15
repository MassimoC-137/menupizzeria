package it.prova.menupizzeria.dao;

import java.util.List;

import jakarta.persistence.EntityManager;

public interface IBaseDAO<T> {

    public List<T> getAll() throws Exception;

    public   T   get(Long id) throws Exception;

    public void update(T input) throws Exception;

    public void insert(T input) throws Exception;

    public void delete(T input) throws Exception;

 // questo mi serve per l'injection
 	public void setEntityManager(EntityManager entityManager);
 	
}