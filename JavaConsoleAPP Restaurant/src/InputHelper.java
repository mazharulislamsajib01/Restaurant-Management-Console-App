import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    // min theke max er moddhe integer nibe, invalid hole abar chaibe
    public static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Invalid input! Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    public static int readPositiveInt(String prompt) {
        return readIntInRange(prompt, 1, Integer.MAX_VALUE);
    }

    public static double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(line);
                if (value > 0) {
                    return value;
                }
                System.out.println("Invalid input! Price must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid price.");
            }
        }
    }

    public static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Invalid input! This field cannot be empty.");
        }
    }

    // yes/y/no/n (choto-boro hater jekono) accept korbe
    public static boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (yes/no): ");
            String line = scanner.nextLine().trim().toLowerCase();
            if (line.equals("yes") || line.equals("y")) {
                return true;
            }
            if (line.equals("no") || line.equals("n")) {
                return false;
            }
            System.out.println("Invalid input! Please type yes or no.");
        }
    }
}