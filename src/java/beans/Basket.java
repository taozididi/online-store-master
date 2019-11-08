package beans;

import entities.Product;
import java.util.*;
import javax.ejb.*;

@Stateful
public class Basket {

    List<Product> items;
    private int numberOfItems;
    private double total;

    int[] counts = new int[36];

    public Basket() {
        items = new ArrayList<>();
        numberOfItems = 0;
        total = 0;
    }

    public synchronized void addItem(Product product) {

        int id = product.getId();
        counts[id]++;

        boolean newItem = true;

        for (Product item : items) {

            if (Objects.equals(item.getId(), id)) {

                newItem = false;
                break;
            }
        }

        if (newItem) {
            items.add(product);
        }
    }

    public synchronized void deductItem(Product product) {

        int id = product.getId();
        if (counts[id] == 0) {
            return;
        }
        counts[id]--;
    }

    public synchronized List<Product> getItems() {
        return items;
    }

    public synchronized int getNumberOfItems() {

        numberOfItems = 0;

        for (Product item : items) {

            numberOfItems += counts[item.getId()];
        }

        return numberOfItems;
    }

    public synchronized void calculateTotal() {
        total = 0;

        items.stream().forEach((Product item) -> {
            total += (counts[item.getId()] * item.getPrice().doubleValue());
        });
    }

    public synchronized double getTotal() {
        calculateTotal();
        return total;
    }

    public synchronized void clear() {
        items.clear();
        numberOfItems = 0;
        total = 0;
        Arrays.fill(counts, 0);
    }

    public int[] getCounts() {
        return counts;
    }

    public String getString() {
        StringBuilder allProducts = new StringBuilder();

        for (Product item : items) {
            allProducts.append(counts[item.getId()] + " " + item + " | ");
        }

        return allProducts.toString();
    }
}
