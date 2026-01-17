package VisitorPattern;

public class CPU implements ComputerPart {
    private String model;
    private double price;
    private int cores;
    private double speed; // in GHz
    
    public CPU(String model, double price, int cores, double speed) {
        this.model = model;
        this.price = price;
        this.cores = cores;
        this.speed = speed;
    }
    
    public String getModel() {
        return model;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getCores() {
        return cores;
    }
    
    public double getSpeed() {
        return speed;
    }
    
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}
