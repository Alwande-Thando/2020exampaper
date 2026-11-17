package za.co.prog6112.q3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/** Reads and appends product records in the sequential Products.txt file. */
public class ProductFileService {
    private final Path file;

    public ProductFileService(Path file) { this.file = file; }

    public List<Product> loadProducts() throws IOException {
        List<Product> products = new ArrayList<>();
        if (!Files.exists(file)) return products;

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        // Each product is stored on three consecutive lines: ID, name and price.
        for (int i = 0; i + 2 < lines.size(); i += 3) {
            String id = lines.get(i).trim();
            String name = lines.get(i + 1).trim();
            String priceText = lines.get(i + 2).trim();
            if (!id.isEmpty() && !name.isEmpty() && !priceText.isEmpty()) {
                products.add(new Product(id, name, Double.parseDouble(priceText)));
            }
        }
        return products;
    }

    public void appendProduct(Product product) throws IOException {
        // Append the three product fields as a sequential record.
        Files.writeString(file, product.toString() + System.lineSeparator(),
                StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public Product findById(String id) throws IOException {
        for (Product product : loadProducts()) {
            if (product.getId().equalsIgnoreCase(id.trim())) return product;
        }
        return null;
    }
}
