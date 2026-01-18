package CreationalPatterns.BuilderPattern;

public class ConcreteProductBuilder implements ProductBuilder {
    private Product product;

    public ConcreteProductBuilder() {
        product = new Product();
    }

    @Override
    public void buildPartA() {
        product.setPartA("PartA built by ConcreteProductBuilder");
    }

    @Override
    public void buildPartB() {
        product.setPartB("PartB built by ConcreteProductBuilder");
    }

    @Override
    public void buildPartC() {
        product.setPartC("PartC built by ConcreteProductBuilder");
    }

    @Override
    public Product getProduct() {
        return product;
    }
}

