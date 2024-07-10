package main.java.ru.clevertec.check.position;

import main.java.ru.clevertec.check.product.Product;

public class WholesalePosition extends Position {

    public static final double wholesaleDiscountRate = 0.1; // 10%

    public WholesalePosition(Product product, int quantity){
        this.quantity = quantity;
        this.description = product.getDescription();
        this.discountRate = wholesaleDiscountRate;
        this.price = product.getPrice();
        this.total = this.price * this.quantity;
        this.discount = this.calculateDiscount();
    }

    public double calculateDiscount(){
        return wholesaleDiscountRate * this.total;
    }
}
