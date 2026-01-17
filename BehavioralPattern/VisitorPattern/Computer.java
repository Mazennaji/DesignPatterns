package VisitorPattern;

import java.util.ArrayList;
import java.util.List;

public class Computer {
    private List<ComputerPart> parts;
    private String name;
    
    public Computer(String name) {
        this.name = name;
        this.parts = new ArrayList<>();
    }
    
    public void addPart(ComputerPart part) {
        parts.add(part);
    }
    
    public void accept(ComputerPartVisitor visitor) {
        System.out.println("\n  Analyzing computer: " + name);
        System.out.println("-------------------------------------");
        for (ComputerPart part : parts) {
            part.accept(visitor);
        }
    }
    
    public String getName() {
        return name;
    }
}
