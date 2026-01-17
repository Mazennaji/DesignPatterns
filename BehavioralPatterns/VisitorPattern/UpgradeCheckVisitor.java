package VisitorPattern;

public class UpgradeCheckVisitor implements ComputerPartVisitor {
    private int upgradeCount = 0;
    
    @Override
    public void visit(CPU cpu) {
        System.out.print("    Checking CPU: " + cpu.getModel() + " ... ");
        if (cpu.getCores() < 4 || cpu.getSpeed() < 2.5) {
            System.out.println("  UPGRADE RECOMMENDED");
            System.out.println("       Reason: Cores < 4 or Speed < 2.5 GHz");
            upgradeCount++;
        } else {
            System.out.println(" OK");
        }
    }
    
    @Override
    public void visit(RAM ram) {
        System.out.print("    Checking RAM: " + ram.getModel() + " ... ");
        if (ram.getCapacity() < 8 || ram.getType().equals("DDR3")) {
            System.out.println("  UPGRADE RECOMMENDED");
            System.out.println("       Reason: Capacity < 8GB or outdated DDR3");
            upgradeCount++;
        } else {
            System.out.println(" OK");
        }
    }
    
    @Override
    public void visit(HDD hdd) {
        System.out.print("    Checking Storage: " + hdd.getModel() + " ... ");
        if (hdd.getType().equals("HDD") || hdd.getCapacity() < 256) {
            System.out.println("  UPGRADE RECOMMENDED");
            System.out.println("       Reason: Using HDD instead of SSD or Capacity < 256GB");
            upgradeCount++;
        } else {
            System.out.println(" OK");
        }
    }
    
    @Override
    public void visit(GPU gpu) {
        System.out.print("    Checking GPU: " + gpu.getModel() + " ... ");
        if (gpu.getMemory() < 4) {
            System.out.println("  UPGRADE RECOMMENDED");
            System.out.println("       Reason: Memory < 4GB");
            upgradeCount++;
        } else {
            System.out.println(" OK");
        }
    }
    
    public void displaySummary() {
        System.out.println("\n    Upgrade Summary:");
        if (upgradeCount == 0) {
            System.out.println("        All components meet current standards!");
        } else {
            System.out.println("         " + upgradeCount + " component(s) need upgrading");
        }
    }
}
