import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Ingredient{
  private String name;
  private int timesChosen;

  public Ingredient (String name, int timesChosen){
    this.name = name;
    this.timesChosen = timesChosen;
  }

  public String getName (){
    return this.name;
  }

  public int getTimesChosen(){
    return this.timesChosen;
  }
}

class TopIngredients {
	// ingredientChooser: String[], String[] 
	// identifica e imprime los tres elementos de una lista (availableIngredients) con un mayor número de ocurrencias en otra lista (chosenIngredients).
	/* ejemplo: lista 1: availableIngredients = {"maíz", "peperoni", "anchoas", "champiñones", "tocino", "jamón", "queso"}
	            lista 2: chosenIngredients = {"maíz", "maíz", "maíz", "jamón", "anchoas", "anchoas", "anchoas", "anchoas", "anchoas", "queso", "queso", "tocino"}
	Main.ingredientChooser (availableIngredients, chosenIngredients) es Los tres ingredientes preferidos son: anchoas, maíz y queso.
	*/
  public static void ingredientChooser (String[] availableIngredients, String[] chosenIngredients){

    List<Ingredient> ingredientsRepetition = new ArrayList<Ingredient>();

    for (int i = 0; i < availableIngredients.length; i++){
      int counter = 0;

      for (int j = 0; j < chosenIngredients.length; j++){
        if (chosenIngredients[j].equals(availableIngredients[i])){
          counter++;
        }
      }

      ingredientsRepetition.add(new Ingredient(availableIngredients[i], counter));
    }

    int first = 0;
    int second = 0;
    int third = 0;

    String ingredient1 = "";
    String ingredient2 = "";
    String ingredient3 = "";

    int i = 0;
    while (i < ingredientsRepetition.size()){
      Ingredient currentIngredient = ingredientsRepetition.get(i);
      int timesRepeated = currentIngredient.getTimesChosen();
      String name = currentIngredient.getName();

      if (timesRepeated > first){
        third = second;
        second = first;
        first = timesRepeated;

        ingredient3 = ingredient2;
        ingredient2 = ingredient1;
        ingredient1 = name;

      } else if (timesRepeated > second){
        third = second;
        second = timesRepeated;

        ingredient3 = ingredient2;
        ingredient2 = name;

      } else if (timesRepeated > third){
        third = timesRepeated;

        ingredient3 = name;
      }
      i++;
    }
    if (third == 0){
      System.out.println(" \n Solo se han ingresado dos ingredientes distintos: " + ingredient1 + " y " + ingredient2 + ".");
    } else { 
      System.out.println(" \n Los tres ingredientes preferidos son: " + ingredient1 + ", " + ingredient2 + " y " + ingredient3 + ".");
    }
    
  }


  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("\nDescubre los tres ingredientes de pizza preferidos en el grupo.");

    int numberOfConsumers = 0;
    do {
      System.out.println("\nIndique el número de consumidores: ");
      try{
        numberOfConsumers = in.nextInt();
      } catch (Exception e){
        System.out.println("Respuesta Invalida: Intente de nuevo.");
        in.nextLine();
      } 
    } while (numberOfConsumers <= 0);
    

    String[] availableIngredients = {"maíz", "peperoni", "anchoas", "champiñones", "tocino", "jamón", "queso"};

    System.out.println("\nIngredientes disponibles:\n\n 1. Maíz \n 2. Peperoni \n 3. Anchoas \n 4. Champiñones \n 5. Tocino  \n 6. Jamón \n 7. Queso \n\nIndique el NUMERO de un ingrediente por persona.");

    String[] chosenIngredients2 = new String[numberOfConsumers];
    for (int i = 0; i < numberOfConsumers; i++){
      System.out.println("\nIngrediente " + String.valueOf(i + 1) + ":");
      try {
        int ingredientNumber = in.nextInt();
        chosenIngredients2[i] = availableIngredients[ingredientNumber-1];
      } catch (Exception e){
        System.out.println ("Respuesta Invalida: Intente de nuevo.");
        i--;
      }
      in.nextLine();
    }

    TopIngredients.ingredientChooser(availableIngredients, chosenIngredients2);

    System.out.println("\nIngredientes preferidos a partir de lista ya elaborada: ");

    
    String[] chosenIngredients = {"maíz", "maíz", "maíz", "jamón", "anchoas", "anchoas", "anchoas", "anchoas", "anchoas", "queso", "queso", "tocino"};
    TopIngredients.ingredientChooser(availableIngredients, chosenIngredients);
  }
}