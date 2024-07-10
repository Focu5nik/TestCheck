package main.java.ru.clevertec.check.position;

import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.product.Product;

public class PositionFactory {
    public Position getPosition(Product product, int quantity, double discount_rate) throws Exception {
        if (product == null) {
            return null;
        }
        if (quantity <= 0){
            throw new BadRequestException("Quantity should be more than zero");
        }
        if (product.isWholesaleProduct() && quantity >= 5) {
            return new WholesalePosition(product, quantity);
        } else {
            return new RegularPosition(product, quantity, discount_rate);
        }
    }
}
