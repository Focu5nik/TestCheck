package main.java.ru.clevertec.check.product;
import main.java.ru.clevertec.check.exception.InternalServerErrorException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;




public class ProductRepository {
    private List<Product> products;

    public ProductRepository(String csvFilePath) throws InternalServerErrorException {
        products = new ArrayList<>();
        loadProductsFromCsv(csvFilePath);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public List<Product> query(ProductSpecification specification){
        return products.stream()
                .filter(specification::specified)
                .collect(Collectors.toList());
    }

    private void loadProductsFromCsv(String csvFilePath)  throws InternalServerErrorException {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 5) {
                    int id = Integer.parseInt(data[0].trim());
                    String description = data[1].trim();
                    double price = Double.parseDouble(data[2].replace(",", ".").trim());
                    int quantityInStock = Integer.parseInt(data[3].trim());
                    boolean wholesaleProduct = data[4].trim().equals("+");;
                    Product product = new Product(id, description, price, quantityInStock, wholesaleProduct);
                    products.add(product);
                }
            }
        } catch (IOException e) {
            throw new InternalServerErrorException("Internal server error occurred.");
        }
    }
}
