package main.java.ru.clevertec.check.check;

import main.java.ru.clevertec.check.discount_card.*;
import main.java.ru.clevertec.check.exception.*;
import main.java.ru.clevertec.check.position.*;
import main.java.ru.clevertec.check.product.*;

import java.util.*;



public class RegularCheckBuilder implements CheckBuilder{
    private Check check;
    Map<Integer, Integer> productsAmount = new HashMap<>();
    private DiscountCard discountCard;
    private double debitCardBalance;
    private DiscountCardRepository discountCardRepository;
    private ProductRepository productRepository;

    public RegularCheckBuilder(DiscountCardRepository discountCardRepository, ProductRepository productRepository){
        this.discountCardRepository = discountCardRepository;
        this.productRepository = productRepository;
        this.discountCard = new DiscountCard(0,"0",0);
    }

    public void reset(){
        this.check = new Check();
    }

    public void parseInput(String[] input) throws BadRequestException{
        for (String arg : input) {
            if(Character.isDigit(arg.charAt(0))) {
                String[] key_value = arg.split("-");
                try {
                    int product_id = Integer.parseInt(key_value[0]);
                    int product_amount = Integer.parseInt(key_value[1]);
                    productsAmount.compute(product_id, (k, v) -> (v == null) ? product_amount : v + product_amount);
                } catch (NumberFormatException e) {
                    throw new BadRequestException("Wrong product pair values");
                }
            }
            else {
                String[] key_value = arg.split("=");
                String card_type = key_value[0];
                String card_value = key_value[1];
                if (card_type.equals("discountCard")){
                    discountCard = discountCardRepository.query(new DiscountCardSpecificationByName(card_value)).getFirst();
                }
                else if (card_type.equals("balanceDebitCard")){
                    try {
                        debitCardBalance = Double.parseDouble(card_value);
                    } catch (NumberFormatException e) {
                        throw new BadRequestException("Debit card balance must be in a valid numeric format.");
                    }
                }
            }
        }
    }

    public void setDiscountCard(){
        this.check.setDiscountCard(this.discountCard);
    }

    public void calculatePositions() throws BadRequestException{
        PositionFactory positionFactory = new PositionFactory();
        List<Position> positions = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : this.productsAmount.entrySet()) {
            int amount = entry.getValue();
            try{
                Product prod = this.productRepository.query(new ProductSpecificationById(entry.getKey())).getFirst();
                if (amount > prod.getQuantityInStock()){
                    throw new BadRequestException("Not enought product in stock");
                }
                Position position = positionFactory.getPosition(prod, amount, this.discountCard.getDiscountAmount());
                positions.add(position);
            } catch (BadRequestException exception) {
                throw exception;
            }
            catch (Exception exception) {
                throw new BadRequestException("No such product");
            }
        }
        this.check.setPositions(positions);
    }

    public void calculateTotals() throws BadRequestException, NotEnoughMoneyException{
        this.check.setTotalPrice( this.check.getPositions().stream()
                .mapToDouble(Position::getTotal)
                .sum());
        this.check.setTotalDiscount(this.check.getPositions().stream()
                .mapToDouble(Position::getDiscount)
                .sum());
        this.check.setTotalWithDiscount(this.check.getTotalPrice() - this.check.getTotalDiscount());
        if(this.check.getTotalDiscount() > this.debitCardBalance){
            throw new NotEnoughMoneyException("Not enought money");
        }
    }
    public Check getCheck(){
        return this.check;
    }
}
