/**
 * LineItem represents one row on an invoice.
 * It holds a quantity and a Product, and can calculate its own subtotal.
 */
public class LineItem {

    private int quantity;
    private Product product;  // LineItem "has-a" Product (matches your UML)

    public LineItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    // Calculates this line's total: quantity x unit price
    public double getSubtotal() {
        return quantity * product.getUnitPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // Formats this line for display, e.g.:  2x Widget @ $5.00 = $10.00
    @Override
    public String toString() {
        return String.format("%-20s %3d x $%7.2f = $%8.2f",
                product.getName(),
                quantity,
                product.getUnitPrice(),
                getSubtotal());
    }
}
