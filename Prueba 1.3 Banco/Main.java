import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class Person {
  private String name;
  private int age;
  private boolean client;

  public Person (String name, int age, boolean client){
    this.name = name;
    this.age = age;
    this.client = client;
  }

  public String getName(){
    return this.name;
  }

  public int getAge(){
    return this.age;
  }

  public boolean isClient(){
    return this.client;
  }

  // isSenior: vacio -> boolean
  // determina si es verdadero o falso que la edad (this.age) de la persona (Person) es mayor a 65.
  /*ejemplo: Person persona("Ariana", 45, false);
             persona.isSenior() es false 
  */
  public boolean isSenior(){
    boolean senior = false;
    if (this.age >= 65){
        senior = true;
    }
    return senior;
  }

  public void printName(){
    System.out.println(this.name);
  }
}

class ListOfCustomers {
  private List <Person> persons;
  private int maxElements;

  public ListOfCustomers (int maxElements){
    this.maxElements = maxElements;
    this.persons = new ArrayList<Person>();
  }
  
  //isFull: vacio -> boolean
  //Determina si lista persons en ListOfCustomers esta llena. Si el tamaño de la lista es igual a el maximo de elementos entonces true, sino false.
  /*Ejemplo: ListOfCustomers: ListOfCustomers lista (5)
             Lista de ListOfCustomers: persons = {persona1, persona2, persona3, persona4, persona5}
			 -> lista.isFull() es true
   */
  public boolean isFull (){
    boolean statement = false;

    if (this.persons.size() == this.maxElements){
      statement = true;
    }
    return statement;
  }

  //hasCustomers: vacio -> boolean
  //Determina si la lista persons de ListOfCustomers tiene clientes (true) o esta vacia (false). 
  /*Ejemplo: ListOfCustomers: ListOfCustomers lista (5)
             Lista de ListOfCustomers: persons = {}
			 -> lista.hasCustomers() es false
   */
  public boolean hasCustomers(){
    boolean statement = true;
    if (this.persons.isEmpty()){
      statement = false;
    }
    return statement;
  }

  //Mensaje para imprimir a conveniencia
  public void fullListMessage (){
    System.out.println("\nFila completa. Persona no puede ser ingresada.");
  }

  //addPerson: person -> vacio
  //Agrega persona Person a la lista persons de ListOfCustomers.
  /*Ejemplo: Lista de ListOfCustomers: persons = {}
             Persona: Person persona1("Ariana", 20, false) 
             -> lista.addPerson(persona1) es persons = {persona1}
  */
  public void addPerson (Person person){
      this.persons.add(person);
  }

  //removePerson: vacio
  //Elimina la persona de indice 0 de la lista persons de ListOfCustomers.
  /*Ejemplo: Lista de ListOfCustomers: persons = {persona1, persona2}
             -> lista.removePerson() es persons = {persona2}
  */
  public void removePerson (){
      this.persons.remove(0);
  }

  //printNames: vacio
  //Imprime los nombres actuales en la lista persons de ListOfCustomers.
  //Ejemplo: persons = {person1, person2}
  //         lista.printNames() es Ariana, Pedro
  public void printNames(){
      for (int i = 0; i < this.persons.size(); i++){
          this.persons.get(i).printName();
      }
  }


}

class Main {
	//addPersonToLine: Person, ListOfCustomers, ListOfCustomers, ListOfCustomers -> none
	/*Agrega una persona de tipo Person a la lista indicada segun sus atributos entre tres listas de tipo ListOfCustomers. 
	Si Person > 65, Person se agregara a primera lista (3era edad). 
	Sino si Person es cliente, se agregará a la segunda lista (clientes), sino se agregará a la tercera lista (publico general).
	En caso de que la lista indicada esté llena, no agrega a la persona y ejecuta la función fullListMessage (vease funcion).
	Ejemplo: Persona = Person a("Ariana", 67, true)
	         Lista 1 = ListOfCustomers 1(5)
			 Lista 2 = ListOfCustomers 2(20)
			 Lista 3 = ListOfCustomers 3(10)
	Main.addPersonToLine(a, 1, 2, 3) agrega a la persona "a" a la lista 1.
	*/
  public static void addPersonToLine (Person person, ListOfCustomers seniorPersons, ListOfCustomers clients, ListOfCustomers nonClients ){
    if (person.isSenior()){
      if (seniorPersons.isFull()){
          seniorPersons.fullListMessage();
      } else { 
          seniorPersons.addPerson(person);
      }
    } else {
        if (person.isClient()){
            if (clients.isFull()){
                clients.fullListMessage();
            } else {
                clients.addPerson(person);
            }
        } else {
            if (nonClients.isFull()){
                nonClients.fullListMessage();
            } else {
                nonClients.addPerson(person);
            }
        }
    }
  }

  //attendCustomer: ListOfCustomers, ListOfCustomers, ListOfCustomers
  //"Atiende" al cliente con prioridad de los clientes en tres listas de ListOfCustomers removiendolos de la lista.
  // Da prioridad a la lista 1. Si la lista 1 esta vacia atiende a la lista 2. Si la lista 2 esta vacia atiende a la lista 3.
  // Si la lista 3 esta vacia imprime que todos estan atendidos.
  /*Ejemplo: Listas de ListOfCustomers: lista1 = {}
                                        lista2 = {personaX}
										lista3 = {personaY}
	         -> Main.attendCustomer(lista1, lista2, lista3) es lista2 = {} 
  */
  public static void attendCustomer (ListOfCustomers seniorPersons, ListOfCustomers clients, ListOfCustomers nonClients){
        if (seniorPersons.hasCustomers()){
          seniorPersons.removePerson();
        } else {
            if (clients.hasCustomers()){
              clients.removePerson();
            } else {
                if (nonClients.hasCustomers()){
                  nonClients.removePerson();
                } else { 
                  System.out.println("\nTodos los clientes han sido atendidos.");
                }
            }
        }

    }

  public static void main(String[] args) {
      Scanner in = new Scanner (System.in);

      ListOfCustomers seniorCustomers = new ListOfCustomers(5);
      ListOfCustomers clientCustomers = new ListOfCustomers(20);
      ListOfCustomers nonClientCustomers = new ListOfCustomers(10);

      boolean continueProgram = true;

      while (continueProgram){
         System.out.println("\nMenu:\n 1 - Ingresar cliente a fila.\n 2 - Atender cliente.\n 3 - cerrar. \n\nIndique el numero de la acción que desea realizar: " );
         int selectedOption = in.nextInt();
         switch (selectedOption){
                case 1: 
                    System.out.println("\nIndique el nombre: ");
                    String name = in.next();
                    System.out.println("\nIndique la edad: ");
                    int age = in.nextInt();
                    System.out.println("\nPresione 1 si es cliente o 2 si es publico general: ");
                    int client = in.nextInt();
                    boolean needsNewInput = true;
                    boolean isClient = false;
                    while (needsNewInput){
                        if (client == 1){
                            isClient = true;
                            needsNewInput = false;
                        } else {
                            if (client == 2){
                                needsNewInput = false;
                            } else {
                                System.out.println("\nOpción invalida. Presione 1 si es cliente o 2 si es publico general: ");
                                client = in.nextInt();
                            }
                        }
                        Person newPerson = new Person(name, age, isClient);
        
                        Main.addPersonToLine(newPerson, seniorCustomers, clientCustomers, nonClientCustomers);
                    }
                    break;
                case 2: 
                    Main.attendCustomer(seniorCustomers, clientCustomers, nonClientCustomers);
                    break;
                case 3: 
                    System.out.println("\nCerrando programa. Se indicaran las personas que quedaron en las filas.");
                    System.out.println("\nPersonas en fila de tercera edad: ");
                    seniorCustomers.printNames();
                    System.out.println("\nPersonas en fila de clientes: ");
                    clientCustomers.printNames();
                    System.out.println("\nPersonas en fila de público general: ");
                    nonClientCustomers.printNames();
                    continueProgram = false;
                    break;
                default: 
                    System.out.println("Opción invalida. Intente de nuevo.");
                    break;
  
            }

        }

    }

}  