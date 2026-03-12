/**
 * Product represents a single item that can be sold.
 * It knows its name and unit price — nothing else.
 */
public class Product {

    // Fields: private means only this class can access them directly
    private String name;
    private double unitPrice;

    // Constructor: called when you do "new Product(...)"
    public Product(String name, double unitPrice) {
        this.name = name;           // "this.name" = the field; "name" = the parameter
        this.unitPrice = unitPrice;
    }

    // Getters: the only way for other classes to read these values
    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    // toString: Java calls this automatically when you print a Product
    @Override
    public String toString() {
        return name + " @ $" + String.format("%.2f", unitPrice);
    }
}
