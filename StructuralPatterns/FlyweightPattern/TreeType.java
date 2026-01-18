public interface TreeType {
    
    void draw(String canvas, int x, int y);
    
    TreeName getName();

    TreeColor getColor();
    
    TreeTexture getTexture();
}