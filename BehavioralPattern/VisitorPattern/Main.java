package VisitorPattern;


public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║    Visitor Pattern - Computer Shop Demo        ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        
        Computer gamingPC = createGamingPC();
        Computer officePC = createOfficePC();
        
        System.out.println("\n----------------------------------------------------------");
        System.out.println(" Visitor 1: Price Calculator");
        System.out.println("----------------------------------------------------------");
        
        PriceCalculatorVisitor priceCalculator1 = new PriceCalculatorVisitor();
        gamingPC.accept(priceCalculator1);
        priceCalculator1.displayTotal();
        
        PriceCalculatorVisitor priceCalculator2 = new PriceCalculatorVisitor();
        officePC.accept(priceCalculator2);
        priceCalculator2.displayTotal();
        
        System.out.println("\n-----------------------------------------------");
        System.out.println(" Visitor 2: Specifications Display");
        System.out.println("-----------------------------------------------");
        
        SpecsDisplayVisitor specsDisplay = new SpecsDisplayVisitor();
        gamingPC.accept(specsDisplay);
        
        System.out.println("\n-----------------------------------------------");
        System.out.println(" Visitor 3: Upgrade Check");
        System.out.println("-----------------------------------------------");
        
        UpgradeCheckVisitor upgradeCheck1 = new UpgradeCheckVisitor();
        gamingPC.accept(upgradeCheck1);
        upgradeCheck1.displaySummary();
        
        UpgradeCheckVisitor upgradeCheck2 = new UpgradeCheckVisitor();
        officePC.accept(upgradeCheck2);
        upgradeCheck2.displaySummary();
        
        System.out.println("\n----------------------------------------------------------");
        System.out.println(" Demonstrating the Visitor Pattern");
        System.out.println("----------------------------------------------------------");
        System.out.println(" Separated operations from object structure");
        System.out.println(" Added new operations without modifying elements");
        System.out.println(" Each visitor defines a different operation");
        System.out.println(" Elements accept any visitor");
        System.out.println(" Follows Open/Closed Principle");
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║            Demo Complete!                      ║");
        System.out.println("╚════════════════════════════════════════════════╝");
    }
    
    private static Computer createGamingPC() {
        Computer computer = new Computer("High-End Gaming PC");
        
        computer.addPart(new CPU("Intel Core i9-13900K", 589.99, 24, 5.8));
        computer.addPart(new RAM("Corsair Vengeance DDR5", 159.99, 32, "DDR5"));
        computer.addPart(new HDD("Samsung 980 Pro", 149.99, 1000, "SSD"));
        computer.addPart(new GPU("NVIDIA RTX 4080", 1199.99, 16, "NVIDIA"));
        
        return computer;
    }
    
    private static Computer createOfficePC() {
        Computer computer = new Computer("Budget Office PC");
        
        computer.addPart(new CPU("Intel Core i3-10100", 129.99, 4, 3.6));
        computer.addPart(new RAM("Kingston Value DDR3", 49.99, 8, "DDR3"));
        computer.addPart(new HDD("Seagate Barracuda", 54.99, 1000, "HDD"));
        computer.addPart(new GPU("Intel UHD Graphics", 0.0, 2, "Intel"));
        
        return computer;
    }
}
