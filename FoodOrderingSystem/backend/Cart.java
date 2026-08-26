import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<FoodItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(FoodItem item) {
        items.add(item);
    }

    public void removeItem(int id) {

        items.removeIf(item -> item.getId() == id);
    }

    public List<FoodItem> getItems() {
        return items;
    }

    public double calculateTotal() {

        double total = 0;

        for (FoodItem item : items) {
            total += item.getPrice();
        }

        return total;
    }
}