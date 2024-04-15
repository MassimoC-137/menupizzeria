package it.prova.menupizzeria.utils;

import java.util.List;

import it.prova.menupizzeria.model.Ingrediente;
import it.prova.menupizzeria.model.Pizza;

public class Utils {
	
	public static void stampaListaDiPizze(List<Pizza> pizze) {
		
		for (Pizza pizza:pizze) {
			System.out.println(pizza.toString());
		}
	}
	
	public static void stampaListaDiIngredienti(List<Ingrediente> ingredienti) {
		
		for (Ingrediente ingrediente:ingredienti) {
			System.out.println(ingrediente.toString());
		}
	}
	
	public static void togliPizzeDalMenuSeIngredienteMancante(List<Pizza> pizze, Ingrediente ingrediente) {
	    pizze.removeIf(pizza -> !pizza.getIngredienti().contains(ingrediente));
	}
	
	
}
