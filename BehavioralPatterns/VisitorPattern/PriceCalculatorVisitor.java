package VisitorPattern;


public class PriceCalculatorVisitor implements ComputerPartVisitor {
    private double totalPrice = 0.0;
    
    @Override
    public void visit(CPU cpu) {
        System.out.println("   Calculating CPU price: $" + cpu.getPrice());
        totalPrice += cpu.getPrice();
    }
    
    @Override
    public void visit(RAM ram) {
        System.out.println("   Calculating RAM price: $" + ram.getPrice());
        totalPrice += ram.getPrice();
    }
    
    @Override
    public void visit(HDD hdd) {
        System.out.println("   Calculating HDD price: $" + hdd.getPrice());
        totalPrice += hdd.getPrice();
    }
    
    @Override
    public void visit(GPU gpu) {
        System.out.println("   Calculating GPU price: $" + gpu.getPrice());
        totalPrice += gpu.getPrice();
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public void displayTotal() {
        System.out.println("\n   Total System Price: $" + String.format("%.2f", totalPrice));
    }
}