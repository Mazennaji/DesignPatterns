public class ConcreteTreeType implements TreeType {
    private final TreeName name;
    private final TreeColor color;
    private final TreeTexture texture;

    public ConcreteTreeType(TreeName name, TreeColor color, TreeTexture texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
        
        System.out.println(" Creating TreeType: " + name + " (Loading textures and models...)");
    }
    
    @Override
    public void draw(String canvas, int x, int y) {
        System.out.println("   Drawing " + name + " tree [" + color + ", " + texture + 
                         "] at (" + x + ", " + y + ") on " + canvas);
    }
    
    @Override
    public TreeName getName() {
        return name;
    }
    
    @Override
    public TreeColor getColor() {
        return color;
    }
    
    @Override
    public TreeTexture getTexture() {
        return texture;
    }
    
    @Override
    public String toString() {
        return "TreeType{" + name + ", " + color + ", " + texture + "} @" + 
               Integer.toHexString(System.identityHashCode(this));
    }
}