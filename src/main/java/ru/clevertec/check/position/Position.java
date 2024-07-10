package main.java.ru.clevertec.check.position;

import java.util.Locale;

public abstract class  Position {
    int quantity;
    String description;
    double price;
    double discount;
    double total;
    double discountRate;
    abstract double calculateDiscount();

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return quantity + ";"
                + description + ";"
                + String.format(Locale.US,"%.2f", ((int)(price * 100)) / 100.00) + "$" + ";"
                + String.format(Locale.US,"%.2f",((int)(discount * 100)) / 100.00)+ "$" + ";"
                + String.format(Locale.US,"%.2f",((int)(total * 100)) / 100.00)+ "$";
    }
}
