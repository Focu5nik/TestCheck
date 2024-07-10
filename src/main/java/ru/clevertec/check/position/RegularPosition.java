package main.java.ru.clevertec.check.position;

import main.java.ru.clevertec.check.product.Product;

public class RegularPosition extends Position {

    public RegularPosition(Product product, int quantity, double discount_rate){
        this.discountRate = discount_rate / 100;
        this.quantity = quantity;
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.total = this.price * this.quantity;
        this.discount = this.calculateDiscount();
    }

    public double calculateDiscount(){
        return this.discountRate * this.total;
    }
}
