import javax.swing.*;

public class InvoiceApp {

    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                launch();
            }
        });
    }

    public static void launch() {
        JFrame frame = new JFrame("Invoice Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 520);
        frame.setLocationRelativeTo(null);
        frame.add(new InvoicePanel());
        frame.setVisible(true);
    }
}
