public enum TreeName {
    OAK("Oak"),
    PINE("Pine"),
    BIRCH("Birch"),
    MAPLE("Maple"),
    WILLOW("Willow"),
    SPRUCE("Spruce");
    
    private final String displayName;
    
    TreeName(String displayName) {
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
