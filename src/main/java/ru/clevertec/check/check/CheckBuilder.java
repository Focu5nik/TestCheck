package main.java.ru.clevertec.check.check;

import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;

public interface CheckBuilder {
    void reset();
    void parseInput(String[] input) throws BadRequestException;
    void setDiscountCard();
    void calculatePositions() throws BadRequestException;
    void calculateTotals() throws BadRequestException, NotEnoughMoneyException;
}
