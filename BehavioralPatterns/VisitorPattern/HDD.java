package VisitorPattern;

public class HDD implements ComputerPart {
    private String model;
    private double price;
    private int capacity; // in GB
    private String type; // HDD or SSD
    
    public HDD(String model, double price, int capacity, String type) {
        this.model = model;
        this.price = price;
        this.capacity = capacity;
        this.type = type;
    }
    
    public String getModel() {
        return model;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public String getType() {
        return type;
    }
    
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}
