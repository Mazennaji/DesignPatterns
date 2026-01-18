import java.util.HashMap;
import java.util.Map;

public class TreeFactory {
    private static Map<String, TreeType> treeTypes = new HashMap<>();
    
    public static TreeType getTreeType(TreeName name, TreeColor color, TreeTexture texture) {
        String key = name.name() + "_" + color.name() + "_" + texture.name();
        
        TreeType type = treeTypes.get(key);
        
        if (type == null) {
            type = new ConcreteTreeType(name, color, texture);
            treeTypes.put(key, type);
            System.out.println("    New tree type created and cached (Total types: " + 
                             treeTypes.size() + ")");
        } else {
            System.out.println("     Reusing existing tree type (Total types: " + 
                             treeTypes.size() + ")");
        }
        
        return type;
    }
    
    public static int getTreeTypeCount() {
        return treeTypes.size();
    }
    
    public static void displayTreeTypes() {
        System.out.println("\n Cached Tree Types:");
        System.out.println("─────────────────────────────────────────────");
        for (Map.Entry<String, TreeType> entry : treeTypes.entrySet()) {
            TreeType type = entry.getValue();
            System.out.println("  . " + type.getName() + " (" + type.getColor() + 
                             ", " + type.getTexture() + ") - " + 
                             Integer.toHexString(System.identityHashCode(type)));
        }
        System.out.println("─────────────────────────────────────────────");
        System.out.println("Total unique types: " + treeTypes.size());
    }
    
    public static void clearCache() {
        treeTypes.clear();
    }
}