import java.util.Comparator;
import java.util.ArrayList;
import java.util.LinkedList;


class OnlineStore {
    private LinkedList <Product> stock;
    private ArrayList <User> registeredUsers;

    // Constructor predeterminado
    public OnlineStore (){
        this.stock = new LinkedList<Product>();
        this.registeredUsers = new ArrayList<User>();
    }

    // Constructor recibe stock de tienda
    public OnlineStore (LinkedList<Product> stock){
        this.stock = stock;
        this.registeredUsers = new ArrayList<User>(registeredUsers);
    }

    // Metodos get 
    public LinkedList <Product> getStock (){
        return this.stock;
    }

    // Metodo agregar producto a stock
    public void addProductToStock (Product newProduct){
        this.stock.add(newProduct);
    }

    // Metodo registrar usuario
    public void newUser(String name, String password){
        User newUser = new User(name, password);
        registeredUsers.add(newUser);
        System.out.println("\nRegistro de usuario exitoso.");
    }

    // Metodo usernameAvailable verifica el nombre de usuario, y si ya existe lanza una excepcion.
    public void nameAvailable (String name) throws ExistentUsernameException{
        for (User user : registeredUsers){
            if (name.equals(user.getName())){
                throw new ExistentUsernameException("Nombre de usuario existente. Intente con uno nuevo.");
            }
        }
        System.out.println("\nNombre de usuario disponible.");
    }
    

    // Metodo iniciar secion
    public boolean canLogIn (String name, String password){
        for (User user : registeredUsers){
            if (name.equals(user.getName()) && password.equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    public void logIn (boolean canLogIn) throws InvalidLogInException{
        if (canLogIn == false){
            throw new InvalidLogInException("Nombre de usuario y/o contraseña incorrectos.");
        }
        System.out.println("\nInicio de sesión exitoso.");
    }
    

    // Metodo getUser recibe el nombre de usuario y retorna el Usuario correspondiente
    // PU: Alternativa?
    public User getUser (String name){
        User currentUser = new User();
        for (User user : registeredUsers){
            if (name.equals(user.getName())){
                currentUser = user;
                break;
            }
        }
        return currentUser;
    }

    // Metodo getProduct recibe el codigo del producto y retorna el producto correspondiente
    // PU: Considerar HashMap o eliminar metodo
    public Product getProduct (String code){
        Product currentProduct = new Product();
        for (Product product : stock){
            if (code.equals(product.getCode())){
                currentProduct = product;
                break;
            }
        }
        return currentProduct;
    }

    //Metodo getProduct recibe index
    public Product getProductByIndex (int index){
        return stock.get(index);
    }


    // Imprimir catalogo de productos enumerado
    public void printProductStock (){
        System.out.println("\nCatalogo de productos:\n");
        int counter = 1;
        for (Product product : stock){
            System.out.print(counter + ".");
            product.printProduct();
            counter++;
        }
    }
}