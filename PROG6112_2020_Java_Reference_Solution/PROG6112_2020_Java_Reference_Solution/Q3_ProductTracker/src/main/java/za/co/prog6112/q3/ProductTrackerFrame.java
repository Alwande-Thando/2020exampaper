package za.co.prog6112.q3;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/** GUI for adding, viewing and searching products stored in Products.txt. */
public class ProductTrackerFrame extends JFrame {
    private final ProductFileService service = new ProductFileService(Path.of("Products.txt"));
    private final JTextField idField = new JTextField(16);
    private final JTextField nameField = new JTextField(16);
    private final JTextField priceField = new JTextField(16);
    private final JTextArea productArea = new JTextArea(12, 42);

    public ProductTrackerFrame() {
        super("Product Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 470);
        setLocationRelativeTo(null);

        JPanel fields = new JPanel(new GridLayout(3, 2, 8, 8));
        fields.add(new JLabel("Product ID:")); fields.add(idField);
        fields.add(new JLabel("Product Name:")); fields.add(nameField);
        fields.add(new JLabel("Product Price:")); fields.add(priceField);

        JButton submit = new JButton("Submit");
        JButton search = new JButton("Search");
        JPanel buttons = new JPanel();
        buttons.add(submit); buttons.add(search);

        productArea.setEditable(false);
        add(fields, BorderLayout.NORTH);
        add(new JScrollPane(productArea), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        submit.addActionListener(e -> saveProduct());
        search.addActionListener(e -> searchProduct());
        // Populate the read-only text area from the sequential file on form load.
        refreshProductList();
    }

    private void saveProduct() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String priceText = priceField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter the product ID, name and price.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            if (price < 0) {
                JOptionPane.showMessageDialog(this, "Product price cannot be negative.");
                return;
            }
            if (service.findById(id) != null) {
                JOptionPane.showMessageDialog(this, "A product with this ID already exists.");
                return;
            }
            service.appendProduct(new Product(id, name, price));
            refreshProductList();
            idField.setText("");
            nameField.setText("");
            priceField.setText("");
            JOptionPane.showMessageDialog(this, "Product saved.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid numeric product price.");
        } catch (IOException ex) {
            showFileError(ex);
        }
    }

    private void refreshProductList() {
        try {
            List<Product> products = service.loadProducts();
            StringBuilder text = new StringBuilder();
            for (Product product : products) {
                text.append(product.toDisplayString()).append(System.lineSeparator());
            }
            productArea.setText(text.toString());
        } catch (IOException | NumberFormatException ex) {
            showFileError(ex);
        }
    }

    private void searchProduct() {
        String id = JOptionPane.showInputDialog(this, "Enter the Product ID", "Search Product",
                JOptionPane.QUESTION_MESSAGE);
        if (id == null || id.trim().isEmpty()) return;
        try {
            Product product = service.findById(id);
            if (product == null) {
                JOptionPane.showMessageDialog(this, "Product ID not found.");
            } else {
                JOptionPane.showMessageDialog(this,
                        product.getName() + ", R " + String.format("%.2f", product.getPrice()),
                        "Product Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException | NumberFormatException ex) {
            showFileError(ex);
        }
    }

    private void showFileError(Exception ex) {
        JOptionPane.showMessageDialog(this, "Could not process Products.txt: " + ex.getMessage(),
                "File error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductTrackerFrame().setVisible(true));
    }
}
