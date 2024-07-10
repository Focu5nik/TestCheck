package main.java.ru.clevertec.check.discount_card;
import main.java.ru.clevertec.check.exception.InternalServerErrorException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DiscountCardRepository {
    private List<DiscountCard> discountCards;

    public DiscountCardRepository(String csvFilePath) throws InternalServerErrorException {
        discountCards = new ArrayList<>();
        loadDiscountCardsFromCsv(csvFilePath);
    }

    public void addDiscountCard(DiscountCard discountCard) {
        discountCards.add(discountCard);
    }

    public void removeDiscountCard(DiscountCard discountCard) {
        discountCards.remove(discountCard);
    }

    public List<DiscountCard> getAllDiscountCards() {
        return discountCards;
    }

    public List<DiscountCard> query(DiscountCardSpecification specification) {
        return discountCards.stream()
                .filter(specification::specified)
                .collect(Collectors.toList());
    }

    private void loadDiscountCardsFromCsv(String csvFilePath) throws InternalServerErrorException {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 3) {
                    int id = Integer.parseInt(data[0].trim());
                    String number = data[1].trim();
                    double discountAmount = Double.parseDouble(data[2].replace(",", ".").trim());
                    DiscountCard discountCard = new DiscountCard(id, number, discountAmount);
                    discountCards.add(discountCard);
                }
            }
        } catch (IOException e) {
            throw new InternalServerErrorException("Internal server error occurred.");
        }
    }
}
