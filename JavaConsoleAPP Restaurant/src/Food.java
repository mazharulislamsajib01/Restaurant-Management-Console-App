public class Food {
    private final Category category;
    private final int id;
    private final String name;
    private final double price;

    public Food(Category category, int id, String name, double price) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
