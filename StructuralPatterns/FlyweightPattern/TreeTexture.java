public enum TreeTexture {
    BARK("Bark"),
    SMOOTH("Smooth"),
    ROUGH("Rough"),
    CRACKED("Cracked"),
    PEELING("Peeling");
    
    private final String displayName;
    
    TreeTexture(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
