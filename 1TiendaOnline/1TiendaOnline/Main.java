import java.util.Scanner;

class Main {
    public static void newPasswordVerification (String password, String passwordRepetition) throws DifferentPasswordException{
        if (!password.equals(passwordRepetition)){
            throw new DifferentPasswordException("Contraseñas no coinciden. Intente de nuevo.");
        }
        System.out.println("\nContraseñas coinciden.");
    }

    public static void main (String [] agrs){
        Scanner in = new Scanner(System.in);

        // Creando productos
        Product p1 = new Product("P001", "Cartera", 100);
        Product p2 = new Product("P002", "Vestido", 500);
        Product p3 = new Product("P003", "Tacones", 300);

        // Creando tienda
        OnlineStore guchi = new OnlineStore();

        //Agregando productos a tienda
        guchi.addProductToStock(p1);
        guchi.addProductToStock(p2);
        guchi.addProductToStock(p3);

        System.out.println("\nBienvenido a Guchi");

        User currentUser = new User();

        boolean again = true;
        while (again){

            // Estableciendo status de usuario
            System.out.println("\n ¿Que desea hacer? \n"
                            + "\n1 Registrar usuario"
                            + "\n2 Iniciar sesion"
                            + "\n3 Comprar como invitado\n");  

            String option = in.next();

            switch (option){
                //Registrando usuario
                case "1" :
                    // Verificar si nombre de usuario esta disponible
                    boolean notAvailable = true;
                    String name = "";
                    while (notAvailable){
                        System.out.print("\nIndique un nombre de usuario: ");
                        name = in.next();
                        try{
                            guchi.nameAvailable(name);
                            notAvailable = false;
                        } catch (ExistentUsernameException e){
                            System.out.println("\nNombre de usuario existente. Intende de nuevo.\n");
                        }
                    }

                    // Verificar si contraseña y repeticion coinciden
                    boolean dontMacth = true;
                    String password = "";
                    while (dontMacth){
                        System.out.print("\nIndique su contaseña: ");
                        password = in.next();
                        System.out.print("\nRepita su contaseña: ");
                        String passwordRepetition = in.next();
                        try{
                            newPasswordVerification(password, passwordRepetition);
                            dontMacth = false;
                        } catch (DifferentPasswordException e){
                            System.out.println("\nContraseñas no coinciden. Intende de nuevo.\n");
                        }
                    }
                    
                    guchi.newUser(name, password);
                    break;

                // Iniciando sesion   
                case "2" : 
                    // Verificando si datos ingresados son correctos
                    boolean cantLogIn = true;
                    String registeredName = "";
                    while (cantLogIn){
                        System.out.print("\nInicio de sesión\n"
                                        + "\nIndique un nombre de usuario:");
                        registeredName = in.next(); 
                        System.out.print("\nIndique su contaseña:");
                        String registeredPassword = in.next();
                        boolean canLogIn = guchi.canLogIn(registeredName, registeredPassword);
                        try{
                            guchi.logIn(canLogIn);
                            cantLogIn = false;
                        } catch (InvalidLogInException e){
                            System.out.println("Datos de inicio de sesión incorrectos. Intente de nuevo.");
                        }

                    }
                    again = false;
                    currentUser = guchi.getUser(registeredName);
                    break;

                // Estableciendo Invitado
                case "3" :
                    // PU: Ya esta predeterminado que currentUser es new User, considerar dejar solo again = false y break
                    User invitado = new User();
                    again = false;
                    currentUser = invitado;
                    break;
                default :
                    System.out.println("Opción invalida.");
                    break;
            }
        }

        // Solo sesion iniciada o invitados a partir de este punto

        // Imprimiendo catalogo de productos
        guchi.printProductStock();

        // Opciones de compra
        boolean continue2 = true;
        while (continue2){
            System.out.println("\n Menu de compra \n"
                            + "\n1 Agregar producto al carrito"
                            + "\n2 Ver carrito"
                            + "\n3 Cancelar compra y finalizar\n");  

            String option = in.next();

            switch (option){
                 // Agregando producto al carrito por numero de producto en catalogo
                case "1" : 
                    // PU: agregar funcion que vea si producto existe (relacionado con el size de catalogo)
                    boolean productDoesntExist = true;
                    while (productDoesntExist){
                        System.out.println("\nIndique el numero de producto: ");
                        int productPositionInCatalog = in.nextInt();
                        try{
                            currentUser.addToShoppingCart(guchi.getStock().get(productPositionInCatalog - 1));
                            productDoesntExist = false;
                        } catch (Exception e) {
                            System.out.println("Producto inexistente. Intende de nuevo.");
                        }
                    }
                    break;

                 // Viendo carrito
                 // PU: Agregar opcion ordenar carrito por
                case "2" :
                    boolean again2 = true;
                    while (again2){
                        currentUser.printShoppingCart();
                        System.out.println("\n¿Que desea hacer?\n"
                                        + "\n1 Eliminar producto del carrito"
                                        + "\n2 Check out"
                                        + "\n3 Volver al menu de compra"
                                        + "\n4 Cancelar compra y finalizar\n");
                        String option2 = in.next();
                        switch (option2){
                            // Eliminando producto de carrito por numero de producto en carrito
                            case "1": 
                                boolean productIsntInCart = true;
                                while (productIsntInCart){
                                    System.out.println("\nIndique el numero del producto a eliminar: ");
                                    int productPositionInCart = in.nextInt();
                                    try {
                                        currentUser.removeFromShoppingCart(currentUser.getShoppingCart().get(productPositionInCart - 1));
                                        productIsntInCart = false;
                                    } catch (Exception e){
                                        System.out.println("Producto no esta en en carrito. Intente de nuevo");
                                    }
                                }
                                //QUEDE AQUI: ARREGLAR TERMINA PROGRAMA LUEGO DE AGREGAR A CARRITO
                                break;

                            // Haciendo check out
                            // PU: Mejorar pago
                            case "2":
                                System.out.println("Total a pagar: " + currentUser.amountToPay());
                                System.out.println("Compra finalizada. ¡Gracias por su compra!");
                                currentUser.checkOut();
                                again2 = false;
                                continue2 = false;
                                break;

                            // Volviendo a menu de compra
                            case "3":
                                again2 = false;
                                break;

                            // Finalizando programa
                            case "4":
                                again2 = false;
                                continue2 = false;
                                break;
                            default:
                                System.out.println("Opcion invalida");
                                break;
                        }
                        break;
                    }
                    
                 // Finalizando programa
                case "3" :
                    continue2 = false;
                    break;
                default :
                    System.out.println("Opcion invalida");
                    break;

            }
        }

        

        

    }
}