import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Menu {
    private final List<Food> foods = new ArrayList<>();

//    public Menu() {
//        loadDefaultItems();
//    }

    public Menu() {
        if (MenuStorage.fileExists()) {
            foods.addAll(MenuStorage.load());   // file thakle file theke nao
        } else {
            loadDefaultItems();                 // prothom bar: default food
            save();                             // ar sheta file e likhe rakho
        }
    }

    public void save() {
        MenuStorage.save(foods);
    }

    // Program shuru te kichu default food thakbe
    private void loadDefaultItems() {
        addFood(new Food(Category.STARTER, 1, "Chicken Soup", 150));
        addFood(new Food(Category.STARTER, 2, "French Fries", 120));
        addFood(new Food(Category.MAIN_COURSE, 1, "Chicken Biryani", 280));
        addFood(new Food(Category.MAIN_COURSE, 2, "Beef Curry with Rice", 320));
        addFood(new Food(Category.DRINKS, 1, "Coca Cola", 50));
        addFood(new Food(Category.DRINKS, 2, "Mango Juice", 80));
    }

    // Same category te same id thakle add hobe na (false return korbe)
    public boolean addFood(Food food) {
        if (findFood(food.getCategory(), food.getId()) != null) {
            return false;
        }
        foods.add(food);
        foods.sort(Comparator.comparingInt(Food::getId));
        return true;
    }

    public Food findFood(Category category, int id) {
        for (Food f : foods) {
            if (f.getCategory() == category && f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    // Ei category er shobcheye boro id ber kore tar por er id ferot dey
    public int getNextId(Category category) {
        int maxId = 0;
        for (Food f : foods) {
            if (f.getCategory() == category && f.getId() > maxId) {
                maxId = f.getId();
            }
        }
        return maxId + 1;
    }

    public void display() {
        System.out.println("\n================ MENU ================");
        for (Category category : Category.values()) {
            System.out.println("\n[" + category.getCode() + "] " + category.getDisplayName());
            System.out.printf("   %-5s %-25s %10s%n", "ID", "Name", "Price");
            boolean hasItem = false;
            for (Food f : foods) {
                if (f.getCategory() == category) {
                    System.out.printf("   %-5d %-25s %10.2f Tk%n", f.getId(), f.getName(), f.getPrice());
                    hasItem = true;
                }
            }
            if (!hasItem) {
                System.out.println("   (No items available)");
            }
        }
        System.out.println("\n======================================");
    }
}