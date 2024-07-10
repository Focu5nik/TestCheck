package main.java.ru.clevertec.check.discount_card;


public class DiscountCardSpecificationByName implements DiscountCardSpecification{
    private String desiredNumber;

    public DiscountCardSpecificationByName(String number) {
        super();
        this.desiredNumber = number;
    }

    @Override
    public boolean specified(DiscountCard discountCard) {
        return (discountCard.getNumber().equals(desiredNumber));
    }

}
