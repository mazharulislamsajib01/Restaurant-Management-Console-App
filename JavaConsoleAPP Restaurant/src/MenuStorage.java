import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MenuStorage {
    private static final String FILE_NAME = "menu.txt";

    public static boolean fileExists() {
        return new File(FILE_NAME).exists();
    }

    // Shob food file e likhe rakhe (purano content muche notun kore likhe)
    public static void save(List<Food> foods) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Food f : foods) {
                writer.println(f.getCategory().getCode() + "," + f.getId() + "," + f.getPrice() + "," + f.getName());
            }
        } catch (IOException e) {
            System.out.println("Error: Could not save menu to file. " + e.getMessage());
        }
    }

    // File theke shob food pore list banay
    public static List<Food> load() {
        List<Food> foods = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return foods;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                // limit 4 mane maximum 4 ta part, tai name e comma thakleo thik thake
                String[] parts = line.split(",", 4);
                if (parts.length < 4) {
                    continue; // kharap line skip
                }

                try {
                    Category category = Category.fromCode(Integer.parseInt(parts[0].trim()));
                    int id = Integer.parseInt(parts[1].trim());
                    double price = Double.parseDouble(parts[2].trim());
                    String name = parts[3].trim();

                    if (category != null) {
                        foods.add(new Food(category, id, name, price));
                    }
                } catch (NumberFormatException e) {
                    // number vul thakle oi line skip
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Could not read menu file. " + e.getMessage());
        }

        return foods;
    }
}
