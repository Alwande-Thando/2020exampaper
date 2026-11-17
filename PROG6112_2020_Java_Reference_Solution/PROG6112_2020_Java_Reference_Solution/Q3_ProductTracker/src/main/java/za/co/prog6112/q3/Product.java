package za.co.prog6112.q3;

/** Product record containing its ID, name and price. */
public class Product {
    private final String id;
    private final String name;
    private final double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override public String toString() {
        return id + System.lineSeparator() + name + System.lineSeparator()
                + String.format("%.2f", price);
    }

    public String toDisplayString() {
        return String.format("ID: %s | Name: %s | Price: R%.2f", id, name, price);
    }
}
