package it.prova.menupizzeria.test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import it.prova.menupizzeria.service.MyServiceFactory;
import it.prova.menupizzeria.service.PizzaService;
import it.prova.menupizzeria.model.Ingrediente;
import it.prova.menupizzeria.model.Pizza;
import it.prova.menupizzeria.service.IngredienteService;

public interface TestInterfaceUtente {

	public static void main(String[] args) throws Exception {
		
		Scanner scanner = new Scanner(System.in); 
		boolean exit = false; 
		
		while(!exit) {
			System.out.println("Benvenuto nella tua interfaccia utente! Scegli un'opzione: ");
			System.out.println("1. Dammi tutte le pizza del menù. ");
			System.out.println("2. Dammi una pizza. "); 
			System.out.println("3. Cambia disponibilità ingredienti. ");
			System.out.println("4. Crea una nuova pizza. ");
			System.out.println("5. Aggiorna una pizza già esistente. ");
			System.out.println("6. Elimina una pizza dal menu. "); 
			
			int choice = scanner.nextInt(); 
			scanner.nextLine(); 
			
			PizzaService pizzaServiceInstance = MyServiceFactory.getPizza_service_instance(); 
			IngredienteService ingredienteServiceInstance = MyServiceFactory.getIngrediente_service_instance(); 
			
			switch (choice) {
			case 1: 
				System.out.println("Ecco il menù delle pizze di oggi: "); 
				TestMenuPizzeria.getTuttePizze(pizzaServiceInstance); 
				
			case 2: 
				System.out.println("Di quale pizza vuoi i dettagli? ");
				System.out.println("Inserisci il nome: ");
				String nomePizza = scanner.next(); 
				scanner.nextLine(); 
				TestMenuPizzeria.getUnaPizza(pizzaServiceInstance, nomePizza);
				
			case 3: 
				System.out.println("Vuoi cambiare la disponibilità di un ingrediente. \nEcco la lista degli ingredienti"); 
				TestMenuPizzeria.getTuttiIngredienti(ingredienteServiceInstance);
				System.out.println("Inserisci l'ID dell'ingrediente per modificare la disponibilità. ");
				Long idIngrediente = scanner.nextLong();
			    scanner.nextLine(); 
			    Ingrediente ingredienteDaModificare = ingredienteServiceInstance.get(idIngrediente);
			    if (ingredienteDaModificare != null) {
			        ingredienteDaModificare.setDisponibilita(!ingredienteDaModificare.isDisponibilita());
			        ingredienteServiceInstance.update(ingredienteDaModificare);
			        System.out.println("La disponibilità dell'ingrediente è stata modificata.");
			    } else {
			        System.out.println("Ingrediente non trovato.");
			    }
			    break;
			    
				case 4: 
					System.out.println("Crea una nuova pizza! ");
					System.out.println("Inserisci il nome della pizza: ");
					String nome = scanner.nextLine(); 
					System.out.println("Inserisci il prezzo della pizza: ");
					double prezzo = scanner.nextDouble(); 
					System.out.println("E' una pizza gourmet? Inserisci true o false: "); 
					boolean gourmet = scanner.nextBoolean(); 
					scanner.nextLine();
					
					Set<String> nomiIngredienti = new HashSet<>();
					System.out.println("Inserisci gli ingredienti (digita 'fine' per terminare):");
					String input = scanner.nextLine();
					while(!input.equalsIgnoreCase("fine")) {
					    nomiIngredienti.add(input);
					    input = scanner.nextLine();
					}
					
					Set<Ingrediente> ingredientiPerLaPizza = new HashSet<>();
					for (String nomeIngrediente : nomiIngredienti) {
					    Ingrediente ingrediente = ingredienteServiceInstance.cercaPerNome(nomeIngrediente);
					    if (ingrediente == null) {
					        ingrediente = new Ingrediente();
					        ingrediente.setNome(nomeIngrediente);
					        ingrediente.setDisponibilita(true);
					        ingredienteServiceInstance.insert(ingrediente);
					    }
					    ingredientiPerLaPizza.add(ingrediente);
					}
					Pizza nuovaPizza = new Pizza(pizzaServiceInstance, ingredientiPerLaPizza);
				    pizzaServiceInstance.insert(nuovaPizza, ingredientiPerLaPizza);
				    System.out.println("Pizza creata con successo!");
				    
				    break; 
					

				case 5: 
				    System.out.println("Ha scelto il comando per aggiornare una pizza");
				    System.out.println("Inserisci l'id della pizza da aggiornare:");
				    Long idP = scanner.nextLong();
				    scanner.nextLine();

				    System.out.println("Inserisci il prezzo della pizza da aggiornare:");
				    double prezzoP = scanner.nextDouble();
				    scanner.nextLine();

				    System.out.println("Inserisci il nome della pizza da aggiornare:");
				    String nomeP = scanner.nextLine();

				    System.out.println("Inserisci true se la pizza è gourmet, false se non lo è:");
				    boolean gourmetP = scanner.nextBoolean();
				    scanner.nextLine();

				    Set<Ingrediente> ingredientiP = new HashSet<>();
				    System.out.println("Inserisci gli ingredienti (digita 'fine' per terminare):");
				    String inputIngrediente = scanner.nextLine();
				    while(!inputIngrediente.equalsIgnoreCase("fine")) {
				        Ingrediente ingrediente = ingredienteServiceInstance.cercaPerNome(inputIngrediente);
				        if (ingrediente == null) {
				            ingrediente = new Ingrediente();
				            ingrediente.setNome(inputIngrediente);
				            ingrediente.setDisponibilita(true);  
				            ingredienteServiceInstance.insert(ingrediente);
				        }
				        ingredientiP.add(ingrediente);
				        inputIngrediente = scanner.nextLine();
				    }

				    Pizza pizzaP = new Pizza(idP, nomeP, prezzoP, gourmetP, ingredientiP);
				    pizzaServiceInstance.update(pizzaP);

				    System.out.println("Pizza aggiornata con successo!");
				    break;

					
				case 6: 
					System.out.println("Ha scelto il quarto comando per eliminare una pizza");
					System.out.println("Inserisci L'id  della pizza da eliminare:");
					Long idp = scanner.nextLong();
					scanner.nextLine();
					Pizza pizzap = new Pizza(idp);
					TestMenuPizzeria.deletePizza(pizzaServiceInstance);

					break;
			}
		}
		System.out.println("Grazie per aver usato l'interfaccia utente. Arrivederci!");
		scanner.close();
	}
}
