public enum TreeColor {
    GREEN("Green"),
    DARK_GREEN("Dark Green"),
    LIGHT_GREEN("Light Green"),
    YELLOW_GREEN("Yellow Green"),
    BLUE_GREEN("Blue Green");
    
    private final String displayName;
    
    TreeColor(String displayName) {
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
