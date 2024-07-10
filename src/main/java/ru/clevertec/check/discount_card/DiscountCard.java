package main.java.ru.clevertec.check.discount_card;

public class DiscountCard {
    private int id;
    private String number;
    private double discountAmount;

    public DiscountCard(){

    }

    public DiscountCard(int id, String number, double discountAmount) {
        this.id = id;
        this.number = number;
        this.discountAmount = discountAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
