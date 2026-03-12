import java.util.ArrayList;
import java.util.List;


public class Invoice {

    private List<LineItem> items;

    public Invoice() {
        items = new ArrayList<>();
    }

    // Add a line item to the invoice
    public void addItem(LineItem item) {
        items.add(item);
    }

    // Loop through all items and sum up their subtotals
    public double getTotal() {
        double total = 0;
        for (LineItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public List<LineItem> getItems() {
        return items;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append(String.format("%-20s %3s   %8s   %9s%n",
                "Product", "Qty", "Unit $", "Subtotal"));
        sb.append("----------------------------------------\n");

        for (LineItem item : items) {
            sb.append(item.toString()).append("\n");
        }

        sb.append("----------------------------------------\n");
        sb.append(String.format("%-34s $%8.2f%n", "TOTAL DUE:", getTotal()));
        sb.append("========================================\n");
        return sb.toString();
    }
}
