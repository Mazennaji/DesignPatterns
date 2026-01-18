package CreationalPatterns.FactoryMethodPattern;


public abstract class Creator {
    public abstract Product createProduct();

    public void performAction() {
        Product product = createProduct();
        product.use();
    }
}
