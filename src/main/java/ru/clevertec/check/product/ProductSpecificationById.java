package main.java.ru.clevertec.check.product;

public class ProductSpecificationById implements ProductSpecification{
    private int desiredId;

    public ProductSpecificationById(int id) {
        super();
        this.desiredId = id;
    }

    @Override
    public boolean specified(Product product) {
        return (product.getId() == desiredId);
    }
}
