package it.prova.menupizzeria.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.prova.menupizzeria.dao.EntityManagerUtil;
import it.prova.menupizzeria.model.Ingrediente;
import it.prova.menupizzeria.model.Pizza;
import it.prova.menupizzeria.service.IngredienteService;
import it.prova.menupizzeria.service.MyServiceFactory;
import it.prova.menupizzeria.service.PizzaService;
import it.prova.menupizzeria.utils.Utils;

public class TestMenuPizzeria {

	public static void main(String[] args) {
		
		System.out.println("Inizio programma pizza. Buon appetito! ");
		
		PizzaService pizzaServiceInstance = MyServiceFactory.getPizza_service_instance();
		IngredienteService ingredienteServiceInstance = MyServiceFactory.getIngrediente_service_instance(); 
		

		try { 
			
			getUnaPizza(pizzaServiceInstance, "Funghi"); 
//			
//			getTuttePizze(pizzaServiceInstance); 
//			
//			insertPizza(pizzaServiceInstance);
//			
//			updatePizza(pizzaServiceInstance); 
//			
//			deletePizza(pizzaServiceInstance);
			
			
			
			
		} catch (Throwable e) {
			
			e.printStackTrace();
			
		} finally {
			
			EntityManagerUtil.shutdown();
		}
		
		System.out.println("Fine programma pizza. Spero che la pizza vi sia piaciuta! ");

	}

	static void getUnaPizza(PizzaService pizzaServiceInstance, String nomePizza) {

		System.out.println("Ecco la pizza che hai scelto: "); 

		try {
	        Pizza pizza = pizzaServiceInstance.get(nomePizza);
	        System.out.println(pizza.toString());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	static void getTuttePizze(PizzaService pizzaServiceInstance) {
		System.out.println("Le pizze di oggi sul menù sono: ");
		try {
			List<Pizza> daStampare = pizzaServiceInstance.getAll(); 
			Utils.stampaListaDiPizze(daStampare);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static void insertPizza(PizzaService pizzaServiceInstance) throws Exception {
		
		System.out.println("Lo chef vuole aggiungere una nuova pizza al menu"); 
		
		Set<Ingrediente> ingredienti = new HashSet<>();
//	    ingredienti.add(new Ingrediente("Pomodoro", true));
//	    ingredienti.add(new Ingrediente("Mozzarella", true));
//	    ingredienti.add(new Ingrediente("Prosciutto Cotto", true));
//	    ingredienti.add(new Ingrediente("Mango", true));
	    
		Pizza nuovaPizza = new Pizza (null, "Hawaiana", 9.00f, false, ingredienti); 
//		pizzaServiceInstance.insert(nuovaPizza, ingredienti); 
	}
	
	static void updatePizza(PizzaService pizzaServiceInstance) throws Exception {
		
		System.out.println("Oops, abbiamo fatto un errore negli ingredienti dell'ultima pizza aggiunta"); 
		
//		Ingrediente ingredienteDaRimuovere = new Ingrediente("Mango", false);
//	    Ingrediente ingredienteDaAggiungere = new Ingrediente("Ananas", true);
	    
		Pizza pizzaDaAggiornare = pizzaServiceInstance.get("Hawaiana"); 
		
		if (pizzaDaAggiornare != null) {
//			pizzaDaAggiornare.getIngredienti().removeIf(ingrediente -> ingrediente.getNome().equalsIgnoreCase(ingredienteDaRimuovere.getNome()));
	        
//	        pizzaDaAggiornare.getIngredienti().add(ingredienteDaAggiungere);

	        pizzaServiceInstance.update(pizzaDaAggiornare);
	    } else {
	        System.out.println("Pizza non trovata con il nome fornito.");
	    }
		
	}
	
	static void deletePizza(PizzaService pizzaServiceInstance) throws Exception {
		
		System.out.println("La pizza con l'ananas è un crimine. La cancelliamo dal menu. ");
		Pizza pizzaDaEliminare = pizzaServiceInstance.get("Hawaiana"); 
		
		if (pizzaDaEliminare != null) {
			try {
				pizzaServiceInstance.delete("Hawaiana");
	            System.out.println("Pizza eliminata con successo.");
	        } catch (Exception e) {
	            System.out.println("Errore durante l'eliminazione della pizza.");
	            e.printStackTrace();
	        }	
		}
		
	}
	
	public static void getTuttiIngredienti(IngredienteService ingredienteServiceInstance) throws Exception {
		
		System.out.println("Gli ingredienti sono: ");
		try {
			List<Ingrediente> daStampare = ingredienteServiceInstance.getAll(); 
			Utils.stampaListaDiIngredienti(daStampare);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}

