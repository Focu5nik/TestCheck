package main.java.ru.clevertec.check.check;

import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;

public class CheckDirector {
    public CheckDirector(){

    }
    public void makeCheck(CheckBuilder builder, String[] input) throws BadRequestException, NotEnoughMoneyException {
        builder.reset();
        builder.parseInput(input);
        builder.setDiscountCard();
        builder.calculatePositions();
        builder.calculateTotals();
    }
}
