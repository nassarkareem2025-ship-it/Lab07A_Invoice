import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class InvoicePanel extends JPanel {

    private Invoice invoice;

    private JTextField custNameField;
    private JTextField custStreetField;
    private JTextField custCityField;

    private JTextField itemNameField;
    private JTextField priceField;
    private JTextField qtyField;

    private JTextArea displayArea;

    public InvoicePanel() {
        invoice = new Invoice();
        buildUI();
    }

    public void buildUI() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JPanel custPanel = new JPanel(new GridLayout(6, 4));
        custPanel.setBorder(BorderFactory.createTitledBorder("Customer Info"));
        custPanel.add(new JLabel("Business Name:"));
        custNameField = new JTextField();
        custPanel.add(custNameField);
        custPanel.add(new JLabel("Street:"));
        custStreetField = new JTextField();
        custPanel.add(custStreetField);
        custPanel.add(new JLabel("City, State ZIP:"));
        custCityField = new JTextField();
        custPanel.add(custCityField);
        custPanel.add(new JLabel(""));
        JButton updateCustButton = new JButton("Update");
        custPanel.add(updateCustButton);

        JPanel linePanel = new JPanel(new GridLayout(6, 4));
        linePanel.setBorder(BorderFactory.createTitledBorder("Add Line Item"));
        linePanel.add(new JLabel("Item Name:"));
        itemNameField = new JTextField();
        linePanel.add(itemNameField);
        linePanel.add(new JLabel("Unit Price ($):"));
        priceField = new JTextField();
        linePanel.add(priceField);
        linePanel.add(new JLabel("Quantity:"));
        qtyField = new JTextField();
        linePanel.add(qtyField);
        linePanel.add(new JLabel(""));
        JButton addButton = new JButton("Add Item");
        linePanel.add(addButton);

        JPanel clearPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton clearButton = new JButton("Clear Invoice");
        clearPanel.add(clearButton);

        formPanel.add(custPanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(linePanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(clearPanel);

        add(formPanel, BorderLayout.WEST);

        displayArea = new JTextArea(20, 42);
        displayArea.setEditable(false);
        displayArea.setText("");
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

       addButton.addActionListener(e -> addLineItem());

        updateCustButton.addActionListener(e -> displayInvoice());

        clearButton.addActionListener(e -> {
            invoice = new Invoice();
            displayArea.setText("");
            itemNameField.setText("");
            priceField.setText("");
            qtyField.setText("");
        });
    }


    public void addLineItem() {
        String name = itemNameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an item name.");
            return;
        }
        double price;
        int qty;
        try {
            price = Double.parseDouble(priceField.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price (e.g. 29.95).");
            return;
        }
        try {
            qty = Integer.parseInt(qtyField.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a whole number for quantity.");
            return;
        }

        Product product = new Product(name, price);
        LineItem item   = new LineItem(qty, product);
        invoice.addItem(item);

        displayInvoice();

        itemNameField.setText("");
        priceField.setText("");
        qtyField.setText("");
        itemNameField.requestFocus();
    }


    public void displayInvoice() {
        StringBuilder sb = new StringBuilder();
        String div1 = "==========================================\n";
        String div2 = "------------------------------------------\n";

        sb.append(div1);
        sb.append(String.format("%28s%n", "INVOICE"));
        sb.append(div2);

        sb.append(custNameField.getText().trim()).append("\n");
        sb.append(custStreetField.getText().trim()).append("\n");
        sb.append(custCityField.getText().trim()).append("\n");
        sb.append(div1);

        sb.append(String.format("%-18s %4s  %8s  %9s%n",
                "Item", "Qty", "Price", "Total"));
        sb.append(div2);

        for (LineItem item : invoice.getItems()) {
            sb.append(String.format("%-18s %4d  $%7.2f  $%8.2f%n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getProduct().getUnitPrice(),
                    item.getSubtotal()));
        }

        sb.append(div2);
        sb.append(String.format("AMOUNT DUE:  $%.2f%n", invoice.getTotal()));
        sb.append(div1);

        displayArea.setText(sb.toString());
    }
}