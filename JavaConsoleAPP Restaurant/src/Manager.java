public class Manager {
    private final Menu menu;

    public Manager(Menu menu) {
        this.menu = menu;
    }

    public void start() {
        do {
            addNewFood();
        } while (InputHelper.readYesNo("Do you want to add another food?"));

        System.out.println("Thank you!");
    }



    private void addNewFood() {
        System.out.println("\n--- Add New Food ---");
        int categoryCode = InputHelper.readIntInRange("Enter category (1 = Starter, 2 = Main Course, 3 = Drinks): ", 1, 3);
        Category category = Category.fromCode(categoryCode);

        String name = InputHelper.readNonEmptyString("Enter food name: ");
        double price = InputHelper.readPositiveDouble("Enter food price: ");

        int id = menu.getNextId(category);   // id auto generate
        menu.addFood(new Food(category, id, name, price));
        menu.save();

        System.out.println("Food added successfully! Food id: " + id + ". Updated menu:");
        menu.display();
    }
}
