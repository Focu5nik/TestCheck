package main.java.ru.clevertec.check;
import main.java.ru.clevertec.check.check.*;
import main.java.ru.clevertec.check.discount_card.*;
import main.java.ru.clevertec.check.exception.*;
import main.java.ru.clevertec.check.product.*;
import java.io.*;
import java.time.*;
import java.time.format.*;


public class CheckRunner {
    public static void main(String[] args) {
        String result_path = "./src/result.csv";
        String error_message = null;
        Check check = new Check();
        try{
            ProductRepository productRepository = new ProductRepository("./src/main/resources/products.csv");
            DiscountCardRepository discountCardRepository = new DiscountCardRepository("./src/main/resources/discountCards.csv");

            RegularCheckBuilder regularCheckBuilder = new RegularCheckBuilder(discountCardRepository, productRepository);
            CheckDirector checkDirector = new CheckDirector();
            checkDirector.makeCheck(regularCheckBuilder, args);
            check = regularCheckBuilder.getCheck();

            System.out.println(check);
        }
        catch (InternalServerErrorException e){
            System.out.println(e.getMessage());
            error_message = "INTERNAL SERVER ERROR";
        }
        catch (BadRequestException e){
            System.out.println(e.getMessage());
            error_message = "BAD REQUEST";
        }
        catch (NotEnoughMoneyException e){
            System.out.println(e.getMessage());
            error_message = "NOT ENOUGH MONEY";
        }
        CheckRunner.writeResultCSV(result_path, error_message, check);
    }


    public static void writeResultCSV(String result_path, String error_message, Check check){
        try {
            File file = new File(result_path);

            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            if(error_message != null){
                bufferedWriter.write("ERROR\n");
                bufferedWriter.write(error_message);
            }
            else {
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                String date = currentDate.format(dateFormatter);

                LocalTime currentTime = LocalTime.now();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String time = currentTime.format(timeFormatter);

                bufferedWriter.write("Date;Time\n");
                bufferedWriter.write(date + ";" + time + "\n\n");
                bufferedWriter.write(check.toString());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}