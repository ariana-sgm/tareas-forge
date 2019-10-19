class Product {
    private String code, name;
    private double price;

    //Constructor predeterminado
    public Product(){
        this.code = "A1234";
        this.name = "Product 1";
        this.price = 10.00;
    }

    //Constructor entregando codigo, nombre y precio
    public Product(String code, String name, double price){
        this.code = code;
        this.name = name;
        this.price = price;
    }

    // Metodos get
    public String getCode(){
        return this.code;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    //Metodo addDiscount 
    public void addDiscount(int discount){
        this.price = this.price + ((discount / 100 )* this.price);
    }

    // Metodo imprimir información de producto
    public void printProduct(){
        System.out.println(" Codigo: " + this.code
                          + "\n   Nombre: " + this.name
                          + "\n   Precio: " + this.price 
                          + "\n");
    }
}