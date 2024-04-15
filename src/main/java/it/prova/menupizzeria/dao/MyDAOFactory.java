package it.prova.menupizzeria.dao;

public class MyDAOFactory {

	// rendiamo questo factory SINGLETON
	private static PizzaDAO pizza_dao_instance = null;
	private static IngredienteDAO ingrediente_dao_instance = null;

	public static PizzaDAO getPizzaDAOInstance() {
		if (pizza_dao_instance == null)
			pizza_dao_instance = new PizzaDAOImpl();
		return pizza_dao_instance;
	}

	public static IngredienteDAO getIngredienteDAOInstance() {
		if (ingrediente_dao_instance == null)
			ingrediente_dao_instance = new IngredienteDAOImpl();
		return ingrediente_dao_instance;
	}

}