package main.java.ru.clevertec.check.check;

import main.java.ru.clevertec.check.discount_card.DiscountCard;
import main.java.ru.clevertec.check.position.Position;

import java.util.List;
import java.util.Locale;

public class Check {
    private List<Position> positions;
    private DiscountCard discountCard;
    private double totalPrice;
    private double totalDiscount;
    private double totalWithDiscount;

    public Check() {}

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public void setTotalWithDiscount(double totalWithDiscount) {
        this.totalWithDiscount = totalWithDiscount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n");
        for (Position position : positions) {
            sb.append(position).append("\n");
        }

        if(!discountCard.getNumber().equals("0")) {
            sb.append("\nDISCOUNT CARD;DISCOUNT PERCENTAGE\n");
            sb.append(discountCard.getNumber()).append(";")
                    .append((int) discountCard.getDiscountAmount()).append("%").append("\n");
        }

        sb.append("\n").append("TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n");
        sb.append(String.format(Locale.US, "%.2f", ((int)(totalPrice * 100)) / 100.00)).append("$").append(";")
                .append(String.format(Locale.US, "%.2f",((int)(totalDiscount * 100)) / 100.00)).append("$").append(";")
                .append(String.format(Locale.US, "%.2f",((int)(totalWithDiscount * 100)) / 100.00)).append("$");
        return sb.toString();
    }
}
