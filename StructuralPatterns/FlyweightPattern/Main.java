public class Main {
    
    public static void main(String[] args) {
        printHeader();
        
        scenario1_SmallForest();
        
        pause();
        
        scenario2_LargeForest();
        
        pause();
        
        scenario3_VerifySharing();
        
        printConclusion();
    }
    
    private static void scenario1_SmallForest() {
        printScenarioHeader("SCENARIO 1: Small Forest (Manual Planting)");
        
        Forest forest = new Forest();
        
        System.out.println("Planting trees manually using ENUMS...\n");
        
        forest.plantTree(10, 20, TreeName.OAK, TreeColor.GREEN, TreeTexture.BARK);
        forest.plantTree(30, 40, TreeName.OAK, TreeColor.GREEN, TreeTexture.BARK);
        forest.plantTree(50, 60, TreeName.OAK, TreeColor.GREEN, TreeTexture.BARK);
        
        forest.plantTree(70, 80, TreeName.PINE, TreeColor.DARK_GREEN, TreeTexture.ROUGH);
        forest.plantTree(90, 100, TreeName.PINE, TreeColor.DARK_GREEN, TreeTexture.ROUGH);
        
        forest.plantTree(110, 120, TreeName.OAK, TreeColor.LIGHT_GREEN, TreeTexture.BARK); 
        
        forest.plantTree(130, 140, TreeName.BIRCH, TreeColor.LIGHT_GREEN, TreeTexture.SMOOTH);
        
        TreeFactory.displayTreeTypes();
        
        forest.draw("Canvas1");
        
        forest.printMemoryStats();
    }
    
    private static void scenario2_LargeForest() {
        printScenarioHeader("SCENARIO 2: Large Forest (10,000 Random Trees)");
        
        TreeFactory.clearCache();
        
        Forest forest = new Forest();
        
        forest.plantRandomTrees(10000);
        
        TreeFactory.displayTreeTypes();
        
        forest.printMemoryStats();
        
        System.out.println(" Notice: Even with 10,000 trees, we only have a limited number");
        System.out.println("   of unique tree types (combinations of enums)!");
        System.out.println("   This is the power of the Flyweight pattern! \n");
        
        System.out.println(" With Enums:");
        System.out.println("   - Type safety: Can't pass invalid tree names");
        System.out.println("   - Compile-time checking: Errors caught early");
        System.out.println("   - Better IDE support: Autocomplete for tree types");
        System.out.println("   - Memory efficient: Enums are singleton instances\n");
    }
    
    private static void scenario3_VerifySharing() {
        printScenarioHeader("SCENARIO 3: Verify Object Sharing with Enums");
        
        TreeFactory.clearCache();
        
        System.out.println("Creating two identical Oak tree types using ENUMS...\n");
        
        TreeType oak1 = TreeFactory.getTreeType(TreeName.OAK, TreeColor.GREEN, TreeTexture.BARK);
        TreeType oak2 = TreeFactory.getTreeType(TreeName.OAK, TreeColor.GREEN, TreeTexture.BARK);
        
        System.out.println("\n Verification:");
        System.out.println("─────────────────────────────────────────────");
        System.out.println("oak1 memory address: " + Integer.toHexString(System.identityHashCode(oak1)));
        System.out.println("oak2 memory address: " + Integer.toHexString(System.identityHashCode(oak2)));
        System.out.println("─────────────────────────────────────────────");
        
        if (oak1 == oak2) {
            System.out.println(" SUCCESS: oak1 == oak2 (Same object in memory!)");
            System.out.println("   The Flyweight pattern is working correctly!");
        } else {
            System.out.println(" FAILURE: Different objects (Pattern not working)");
        }
        
        System.out.println("\n Key Point: Using '==' instead of '.equals()' confirms");
        System.out.println("   they are literally the SAME object, not just equal objects.");
        
        System.out.println("\n Enum Advantages:");
        System.out.println("   - TreeName.OAK is guaranteed to be a singleton");
        System.out.println("   - Can't create invalid combinations like \"Oaks\" (typo)");
        System.out.println("   - Compiler enforces valid enum values only\n");
    }
    
    /**
     * Prints the main header
     */
    private static void printHeader() {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  FLYWEIGHT DESIGN PATTERN - Forest Simulation (Enums)  ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
    }
    
    /**
     * Prints a scenario header
     */
    private static void printScenarioHeader(String title) {
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("  " + title);
        System.out.println("═══════════════════════════════════════════════════════════\n");
    }
    
    /**
     * Prints conclusion
     */
    private static void printConclusion() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                   KEY BENEFITS                           ║");
        System.out.println("╠════════════════════════════════════════════════════════ ╣");
        System.out.println("║   Dramatic Memory Reduction (70-90%)                 ║");
        System.out.println("║   Supports Large Numbers of Objects                  ║");
        System.out.println("║   Faster Object Creation (Reuses Instances)          ║");
        System.out.println("║   Clear Separation: Shared vs Unique State           ║");
        System.out.println("║   Thread-Safe When Immutable                         ║");
        System.out.println("║   Type Safety with Enums                             ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
        
        System.out.println(" Summary:");
        System.out.println("───────────────────────────────────────────────────────────");
        System.out.println("The Flyweight Pattern achieves massive memory optimization by:");
        System.out.println("  1. Separating INTRINSIC (shared) from EXTRINSIC (unique) state");
        System.out.println("  2. Using a Factory to manage an object pool");
        System.out.println("  3. Making flyweights IMMUTABLE for safe sharing");
        System.out.println("  4. Reusing objects instead of creating duplicates");
        System.out.println("  5. Using ENUMS for type safety and compile-time checking");
        System.out.println("───────────────────────────────────────────────────────────");
        System.out.println("Perfect for: Games, text editors, GUI frameworks, and any");
        System.out.println("application dealing with large numbers of similar objects! \n");
    }
    
    /**
     * Pause between scenarios
     */
    private static void pause() {
        System.out.println("\n" + "─".repeat(60) + "\n");
    }
}