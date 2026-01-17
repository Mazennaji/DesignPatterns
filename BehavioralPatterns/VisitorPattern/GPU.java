package VisitorPattern;

public class GPU implements ComputerPart {
    private String model;
    private double price;
    private int memory; // in GB
    private String brand; // NVIDIA, AMD, etc.
    
    public GPU(String model, double price, int memory, String brand) {
        this.model = model;
        this.price = price;
        this.memory = memory;
        this.brand = brand;
    }
    
    public String getModel() {
        return model;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getMemory() {
        return memory;
    }
    
    public String getBrand() {
        return brand;
    }
    
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}
