import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Forest {
    private List<Tree> trees = new ArrayList<>();
    
    public void plantTree(int x, int y, TreeName name, TreeColor color, TreeTexture texture) {
        TreeType type = TreeFactory.getTreeType(name, color, texture);
        
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }
    
    public void draw(String canvas) {
        System.out.println("\n Drawing Forest on " + canvas + ":");
        System.out.println("═══════════════════════════════════════════════");
        for (Tree tree : trees) {
            tree.draw(canvas);
        }
        System.out.println("═══════════════════════════════════════════════\n");
    }
    

    public int getTreeCount() {
        return trees.size();
    }
    
    public void printMemoryStats() {
        int treeCount = trees.size();
        int treeTypeCount = TreeFactory.getTreeTypeCount();
        
        System.out.println("\n MEMORY STATISTICS:");
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║  Total Trees: " + String.format("%-33d", treeCount) + "║");
        System.out.println("║  Unique Tree Types: " + String.format("%-26d", treeTypeCount) + "║");
        System.out.println("╠════════════════════════════════════════════════╣");
        
        
        int bytesWithoutFlyweight = treeCount * 170;
        
        
        int bytesWithFlyweight = (treeTypeCount * 12) + (treeCount * 12);
        
        int savedBytes = bytesWithoutFlyweight - bytesWithFlyweight;
        double savedPercent = ((double) savedBytes / bytesWithoutFlyweight) * 100;
        
        System.out.println("║  WITHOUT Flyweight: ~" + String.format("%-23s", formatBytes(bytesWithoutFlyweight)) + "║");
        System.out.println("║  WITH Flyweight:    ~" + String.format("%-23s", formatBytes(bytesWithFlyweight)) + "║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║  Memory Saved: ~" + String.format("%-30s", formatBytes(savedBytes)) + "║");
        System.out.println("║  Reduction: " + String.format("%-36s", String.format("%.1f%%", savedPercent)) + "║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
    }
    
    /**
     * Formats bytes into readable format
     */
    private String formatBytes(int bytes) {
        if (bytes < 1024) {
            return bytes + " bytes";
        } else if (bytes < 1024 * 1024) {
            return String.format("%.2f KB", bytes / 1024.0);
        } else {
            return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
        }
    }
    
    public void plantRandomTrees(int count) {
        Random random = new Random();
        TreeName[] names = TreeName.values();
        TreeColor[] colors = TreeColor.values();
        TreeTexture[] textures = TreeTexture.values();
        
        System.out.println(" Planting " + count + " trees...\n");
        
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(1000);
            int y = random.nextInt(1000);
            TreeName name = names[random.nextInt(names.length)];
            TreeColor color = colors[random.nextInt(colors.length)];
            TreeTexture texture = textures[random.nextInt(textures.length)];
            
            plantTree(x, y, name, color, texture);
        }
        
        System.out.println("\n Finished planting " + count + " trees!");
    }
}