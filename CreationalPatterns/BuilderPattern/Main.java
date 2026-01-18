package CreationalPatterns.BuilderPattern;

public class Main {
    public static void main(String[] args) {
        ProductBuilder builder = new ConcreteProductBuilder();
        Director director = new Director(builder);

        Product product = director.construct();
        product.showParts();
    }
}

