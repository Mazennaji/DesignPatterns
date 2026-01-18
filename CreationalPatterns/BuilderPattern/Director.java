package CreationalPatterns.BuilderPattern;

public class Director {
    private ProductBuilder builder;

    public Director(ProductBuilder builder) {
        this.builder = builder;
    }

    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getProduct();
    }
}

