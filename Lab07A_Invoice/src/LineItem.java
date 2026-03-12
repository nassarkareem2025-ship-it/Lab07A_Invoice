
public class LineItem {

    private int quantity;
    private Product product;  

    public LineItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public double getSubtotal() {
        return quantity * product.getUnitPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("%-20s %3d x $%7.2f = $%8.2f",
                product.getName(),
                quantity,
                product.getUnitPrice(),
                getSubtotal());
    }
}
