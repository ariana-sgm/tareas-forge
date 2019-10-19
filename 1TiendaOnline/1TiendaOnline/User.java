import java.util.ArrayList;
import java.util.LinkedList;

class User {
    private String name;
    private String password;
    private LinkedList <Product> shoppingCart;

    // Constructor predeterminado
    public User (){
        this.name = "Invitado";
        this.password = "1234";
        this.shoppingCart = new LinkedList<Product>();
    }

    //Constructor entregando nombre y contraseña. Shopping cart vacio predeterminado.
    public User(String name, String password){
        this.name = name;
        this.password = password;
        this.shoppingCart = new LinkedList <Product> ();
    }

    //Metodos get
    public String getName(){
        return this.name;
    }

    public String getPassword(){
        return this.password;
    }

    public LinkedList <Product> getShoppingCart(){
        return this.shoppingCart;
    }

    //Metodo addToShoppingCart permite añadir producto a carrito de compras
    public void addToShoppingCart(Product newProduct){
        this.shoppingCart.add(newProduct);
        System.out.println("Articulo agregado al carrito exitosamente.");
    }

    //Metodo removeFromShoppingCart permite remover producto del carrito
    public void removeFromShoppingCart(Product newProduct){
        this.shoppingCart.remove(newProduct);
        System.out.println("Articulo se ha removido del carrito exitosamente.");
    }

    // Metodo printShoppingCart imprime los productos actuales en el carrito
    public void printShoppingCart (){
        System.out.println("\nProductos en el carrito:\n");
        int counter = 1;
        for (Product product : shoppingCart){
            System.out.print(counter + ".");
            product.printProduct();
            counter++;
        }
    }

    // Metodo checkOut indica el total a pagar y elimina productos del carrito
    public double amountToPay (){
        double total = 0;
        for (Product product : shoppingCart){
            total = total + product.getPrice();
        }
        return total;
    }

    public void checkOut (){
        this.shoppingCart = new LinkedList<Product>();
    }
}