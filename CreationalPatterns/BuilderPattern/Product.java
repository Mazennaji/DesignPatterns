package CreationalPatterns.BuilderPattern;

public class Product {
    private String partA;
    private String partB;
    private String partC;

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }

    public void showParts() {
        System.out.println("Product Parts:");
        System.out.println("PartA: " + partA);
        System.out.println("PartB: " + partB);
        System.out.println("PartC: " + partC);
    }
}

