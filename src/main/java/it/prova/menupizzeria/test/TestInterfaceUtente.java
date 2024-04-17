package it.prova.menupizzeria.test;

import java.util.HashSet;
import java.util.List;
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

		while (!exit) {
			System.out.println("\n\n Benvenuto nella tua interfaccia utente! Scegli un'opzione: ");
			System.out.println("1. Dammi tutte le pizze del menù. ");
			System.out.println("2. Dammi una pizza. ");
			System.out.println("3. Cambia disponibilità ingredienti. ");
			System.out.println("4. Crea una nuova pizza. ");
			System.out.println("5. Aggiorna una pizza già esistente. ");
			System.out.println("6. Elimina una pizza dal menu. ");

			int choice;
			try {
				choice = scanner.nextInt();
			} catch (Exception e) {
				choice = 0;
			}

			scanner.useDelimiter("\n");
			scanner.nextLine();

			PizzaService pizzaServiceInstance = MyServiceFactory.getPizza_service_instance();
			IngredienteService ingredienteServiceInstance = MyServiceFactory.getIngrediente_service_instance();

			switch (choice) {
			case 1:
				System.out.println("Ecco il menù delle pizze di oggi: ");
				List<Pizza> tutteLePizze;
				try {
					tutteLePizze = pizzaServiceInstance.getAll();
					if (tutteLePizze.isEmpty()) {
						System.out.println("Non ci sono pizze sul menù. ");
					} else {
						for (Pizza pizza : tutteLePizze) {
							System.out.println(pizza.toStrings());
						}
					}
				} catch (Exception e) {
					System.out.println("Errore durante il caricamento del menù delle pizze. ");
				}
				break;

			case 2:
			    System.out.println("Di quale pizza vuoi i dettagli? ");
			    System.out.println("Inserisci il nome: ");
			    String nomePizza = scanner.nextLine();

			    try {
			        Pizza pizza = pizzaServiceInstance.get(nomePizza);
			        if (pizza != null) {
			            System.out.println(pizza);
			        } else {
			            System.out.println("Non è stata trovata alcuna pizza con il nome: " + nomePizza);
			        }
			    } catch (Exception e) {
			        System.out.println("Non è possibile trovare una pizza con il nome: " + nomePizza);
			    }
			    break;
			case 3:
				System.out.println("Vuoi cambiare la disponibilità di un ingrediente. \nEcco la lista degli ingredienti");
				ingredienteServiceInstance.getAll();
				System.out.println("Inserisci l'ID dell'ingrediente per modificare la disponibilità. ");
				Long idIngrediente = 0l;
				try {
					idIngrediente = scanner.nextLong();

				} catch (Exception e) {
					System.out.println("ID inserito non valido. ");
					scanner.nextLine();
					break;
				}
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
				System.out.println("Crea una nuova pizza!");
				System.out.println("Inserisci il nome della pizza: \n");
				String nome = scanner.nextLine();
				System.out.println("Inserisci il prezzo della pizza: \n");
				float prezzo = 0f;
				try {
					prezzo = scanner.nextFloat();
				} catch (Exception e) {
					System.out.println("Prezzo inserito non valido. ");
					scanner.nextLine();
					break;
				}
				scanner.nextLine();
				System.out.println("E' una pizza gourmet? Inserisci true o false: \n");
				boolean gourmet = false;
				try {
					gourmet = scanner.nextBoolean();
				} catch (Exception e) {
					System.out.println("Errore. Inserisci solo true o false. ");
					scanner.nextLine();
					break;
				}
				scanner.nextLine();

				Set<String> nomiIngredienti = new HashSet<>();
				System.out.println("Inserisci gli ingredienti (digita 'fine' per terminare):");
				String input = scanner.nextLine();
				while (!input.equalsIgnoreCase("fine")) {
					nomiIngredienti.add(input);
					input = scanner.nextLine();
				}

				Pizza nuovaPizza = new Pizza(null, nome, prezzo, gourmet, null);
				pizzaServiceInstance.insert(nuovaPizza, nomiIngredienti);
				System.out.println("Pizza creata con successo!");

				break;

			case 5:
				System.out.println("Ha scelto il comando per aggiornare una pizza");
				System.out.println("Inserisci l'id della pizza da aggiornare:");
				Long idP = 0l;
				try {
					idP = scanner.nextLong();
				} catch (Exception e) {
					System.out.println("ID inserito non valido. ");
					scanner.nextLine();
					break;
				}
				scanner.nextLine();

				System.out.println("Inserisci il prezzo della pizza da aggiornare:");
				float prezzoP = 0f;
				try {
					prezzoP = scanner.nextFloat();
				} catch (Exception e) {
					System.out.println("Prezzo inserito non valido. ");
					scanner.nextLine();
					break;
				}
				scanner.nextLine();

				System.out.println("Inserisci il nome della pizza da aggiornare:");
				String nomeP = scanner.nextLine();

				System.out.println("Inserisci true se la pizza è gourmet, false se non lo è:");
				boolean gourmetP = false;
				try {
					gourmetP = scanner.nextBoolean();
				} catch (Exception e) {
					System.out.println("Errore. Inserisci solo true o false. ");
					scanner.nextLine();
					break;
				}
				scanner.nextLine();

				Set<String> ingredientiP = new HashSet<>();
				System.out.println("Inserisci gli ingredienti (digita 'fine' per terminare):");
				String inputIngrediente = scanner.nextLine();
				while (!inputIngrediente.equalsIgnoreCase("fine")) {
					ingredientiP.add(inputIngrediente);
					inputIngrediente = scanner.nextLine();
				}

				Pizza pizzaP = new Pizza(idP, nomeP, prezzoP, gourmetP, null);
				pizzaServiceInstance.update(pizzaP, ingredientiP);

				System.out.println("Pizza aggiornata con successo!");
				break;

			case 6:
				System.out.println("Ha scelto il quarto comando per eliminare una pizza");
				System.out.println("Inserisci L'id  della pizza da eliminare:");
				Long idp = scanner.nextLong();
				scanner.nextLine();
				pizzaServiceInstance.delete(idp);
				System.out.println("Pizza eliminata con successo! ");

				break;

			default:
				System.out.println("Comando non valido. ");
			}
		}
		System.out.println("Grazie per aver usato l'interfaccia utente. Arrivederci!");
		scanner.close();
	}
}
