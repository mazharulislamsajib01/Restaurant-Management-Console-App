import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getTotalBill() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void displayBill() {
        System.out.println("\n============== YOUR ORDER ==============");
        System.out.printf("%-25s %5s %10s %10s%n", "Item", "Qty", "Price", "Total");
        for (OrderItem item : items) {
            System.out.printf("%-25s %5d %10.2f %10.2f%n",
                    item.getFood().getName(),
                    item.getQuantity(),
                    item.getFood().getPrice(),
                    item.getTotalPrice());
        }
        System.out.println("----------------------------------------");
        System.out.printf("TOTAL BILL: %.2f Tk%n", getTotalBill());
        System.out.println("========================================");
    }
}
