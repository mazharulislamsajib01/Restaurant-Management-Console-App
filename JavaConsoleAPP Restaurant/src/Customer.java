public class Customer {
    private final Menu menu;

    public Customer(Menu menu) {
        this.menu = menu;
    }

    public void start() {
        if (!InputHelper.readYesNo("\nDo you want to see the menu?")) {
            System.out.println("Thank you!");
            return;
        }

        menu.display();
        Order order = new Order();

        // Customer "no" na bola porjonto order nite thakbe
        do {
            takeSingleOrder(order);
        } while (InputHelper.readYesNo("Do you want to order more?"));

        if (order.isEmpty()) {
            System.out.println("You did not order anything. Thank you!");
            return;
        }

        order.displayBill();

        if (InputHelper.readYesNo("Do you want to confirm your order?")) {
            order.displayBill();
            System.out.println("Thank you for ordering!");
        } else {
            System.out.println("Thank you!");
        }
    }

    private void takeSingleOrder(Order order) {
        System.out.println("\n--- New Item ---");
        int categoryCode = InputHelper.readIntInRange(
                "Enter category (1 = Starter, 2 = Main Course, 3 = Drinks): ", 1, 3);
        Category category = Category.fromCode(categoryCode);

        int foodId = InputHelper.readPositiveInt("Enter food id: ");
        Food food = menu.findFood(category, foodId);

        if (food == null) {
            System.out.println("Invalid food id! No such food in " + category.getDisplayName() + ".");
            return;
        }

        int quantity = InputHelper.readPositiveInt("Enter quantity: ");
        order.addItem(new OrderItem(food, quantity));
        System.out.println("Added: " + food.getName() + " x " + quantity);
    }
}
