public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();

        System.out.println("===== Welcome to our Restaurant =====");

        do {
            int role = InputHelper.readIntInRange("\nAre you a customer or manager? (1 = Customer, 2 = Manager): ", 1, 2);

            if (role == 1) {
                new Customer(menu).start();
            } else {
                new Manager(menu).start();
            }
        } while (InputHelper.readYesNo("\nDo you want to continue using the program?"));

        System.out.println("Goodbye!");
    }
}