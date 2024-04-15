package it.prova.menupizzeria.service;

import it.prova.menupizzeria.dao.MyDAOFactory;

public class MyServiceFactory {

	private static PizzaService pizza_service_instance; 
	private static IngredienteService ingrediente_service_instance;
	
	
	public static PizzaService getPizza_service_instance() {
		if (pizza_service_instance == null) 
			pizza_service_instance = new PizzaServiceImpl();
		
		pizza_service_instance.setPizzaDAO(MyDAOFactory.getPizzaDAOInstance()); 
		pizza_service_instance.setIngredienteDAO(MyDAOFactory.getIngredienteDAOInstance()); 
		return pizza_service_instance;
	}
	public static IngredienteService getIngrediente_service_instance() {
		if (ingrediente_service_instance == null)
			ingrediente_service_instance = new IngredienteServiceImpl(); 
		
		ingrediente_service_instance.setIngredienteDAO(MyDAOFactory.getIngredienteDAOInstance()); 

		return ingrediente_service_instance;
	}
	
}
