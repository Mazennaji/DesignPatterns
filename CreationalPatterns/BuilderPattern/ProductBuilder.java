package CreationalPatterns.BuilderPattern;

public interface ProductBuilder {
    void buildPartA();
    void buildPartB();
    void buildPartC();
    Product getProduct();
}

