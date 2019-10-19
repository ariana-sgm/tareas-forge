import java.util.Scanner;

class Pelicula {
  public static void main(String[] args) {
    System.out.println("\n¿Te cuesta elegir que película ver?"
                      +"\n¡No te preocupes!"
                      +"\nEste programa te ayudará a elegir una película aleatoriamente entre un máximo de 10 películas."
                      +"\nIngresa el nombre de cada película cuando el programa te lo indique. Para finalizar antes escriba fin.\n");

    Scanner in = new Scanner(System.in);

    double numMayor = 0;
    String peliculaElegida = "";

    int contadorPeliculas = 0;

    while (contadorPeliculas < 10){
      System.out.println("Nombre de pelicula: ");
      String nombre = in.nextLine();

      if (nombre.equals("fin")){
        break;
      }

      double numAsignado = Math.random();

      if (numAsignado > numMayor){
        numMayor = numAsignado;
        peliculaElegida = nombre;
      }

      contadorPeliculas++;
    }

    System.out.println("\n La pelicula elegida es: " + peliculaElegida + "\n ¡Disfrutala!");
  }
}