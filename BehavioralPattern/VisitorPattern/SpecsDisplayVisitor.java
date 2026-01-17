package VisitorPattern;

public class SpecsDisplayVisitor implements ComputerPartVisitor {
    
    @Override
    public void visit(CPU cpu) {
        System.out.println("   CPU Specifications:");
        System.out.println("       Model: " + cpu.getModel());
        System.out.println("       Cores: " + cpu.getCores());
        System.out.println("       Speed: " + cpu.getSpeed() + " GHz");
        System.out.println("       Price: $" + cpu.getPrice());
    }
    
    @Override
    public void visit(RAM ram) {
        System.out.println("   RAM Specifications:");
        System.out.println("       Model: " + ram.getModel());
        System.out.println("       Capacity: " + ram.getCapacity() + " GB");
        System.out.println("       Type: " + ram.getType());
        System.out.println("       Price: $" + ram.getPrice());
    }
    
    @Override
    public void visit(HDD hdd) {
        System.out.println("   Storage Specifications:");
        System.out.println("       Model: " + hdd.getModel());
        System.out.println("       Capacity: " + hdd.getCapacity() + " GB");
        System.out.println("       Type: " + hdd.getType());
        System.out.println("       Price: $" + hdd.getPrice());
    }
    
    @Override
    public void visit(GPU gpu) {
        System.out.println("   GPU Specifications:");
        System.out.println("       Model: " + gpu.getModel());
        System.out.println("       Memory: " + gpu.getMemory() + " GB");
        System.out.println("       Brand: " + gpu.getBrand());
        System.out.println("       Price: $" + gpu.getPrice());
    }
}