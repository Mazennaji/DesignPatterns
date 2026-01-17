package StructuralPatterns.CompositePattern;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Composite Design Pattern Demo ===\n");
        
        Leaf mouse = new Leaf("Wireless Mouse", 25.99);
        Leaf keyboard = new Leaf("Mechanical Keyboard", 79.99);
        Leaf monitor = new Leaf("27\" Monitor", 299.99);
        Leaf hdmiCable = new Leaf("HDMI Cable", 12.99);
        
        Leaf cpu = new Leaf("Intel i7 Processor", 349.99);
        Leaf ram = new Leaf("16GB RAM", 89.99);
        Leaf ssd = new Leaf("512GB SSD", 69.99);
        Leaf motherboard = new Leaf("ATX Motherboard", 159.99);
        
        Leaf officeChair = new Leaf("Ergonomic Chair", 249.99);
        Leaf desk = new Leaf("Standing Desk", 399.99);
        Leaf lamp = new Leaf("LED Desk Lamp", 39.99);
        
        Composite peripherals = new Composite("Computer Peripherals");
        peripherals.add(mouse);
        peripherals.add(keyboard);
        peripherals.add(monitor);
        peripherals.add(hdmiCable);
        
        Composite pcComponents = new Composite("PC Components");
        pcComponents.add(cpu);
        pcComponents.add(ram);
        pcComponents.add(ssd);
        pcComponents.add(motherboard);
        
        Composite furniture = new Composite("Office Furniture");
        furniture.add(officeChair);
        furniture.add(desk);
        furniture.add(lamp);
        
        Composite completeSetup = new Composite("Complete Home Office Setup");
        completeSetup.add(peripherals);
        completeSetup.add(pcComponents);
        completeSetup.add(furniture);
        
        System.out.println("=== Product Catalog ===");
        completeSetup.operation();
        
        System.out.println("\n=== Price Calculation ===");
        System.out.printf("Peripherals Total: $%.2f%n", peripherals.getTotalPrice());
        System.out.printf("PC Components Total: $%.2f%n", pcComponents.getTotalPrice());
        System.out.printf("Furniture Total: $%.2f%n", furniture.getTotalPrice());
        System.out.printf("Complete Setup Total: $%.2f%n", completeSetup.getTotalPrice());
        
        System.out.println("\n=== Uniform Treatment Example ===");
        Component[] items = {mouse, peripherals, completeSetup};
        
        for (Component item : items) {
            System.out.println("\nProcessing: " + item.getName());
            System.out.println("Is Composite: " + item.isComposite());
            item.operation();
        }
        

        System.out.println("\n=== Dynamic Modification ===");
        System.out.println("Adding webcam to peripherals...");
        Leaf webcam = new Leaf("HD Webcam", 59.99);
        peripherals.add(webcam);
        
        System.out.println("New peripherals total: $" + peripherals.getTotalPrice());
        peripherals.operation();
        
        System.out.println("\nRemoving HDMI cable from peripherals...");
        peripherals.remove(hdmiCable);
        System.out.println("Updated peripherals total: $" + peripherals.getTotalPrice());
        peripherals.operation();
        
        System.out.println("\n=== Accessing Children ===");
        System.out.println("Number of items in PC Components: " + pcComponents.getChildCount());
        System.out.println("First item: " + pcComponents.getChild(0).getName());
    }
}