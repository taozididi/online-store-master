package classes;

import entities.Product;

public class BasketItem {

    private Product product;
    private int quantity;

    public BasketItem(Product product) {
        this.product = product;
        quantity = 1;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

    public double getTotal() {
        double amount = (quantity * product.getPrice().doubleValue());
        return amount;
    }
    
    @Override public String toString(){
        return quantity + " " + product + " | ";
    }
}
