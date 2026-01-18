package StructuralPatterns.ProxyPattern;


public class ProxyImage implements Image {
    private String fileName;
    private RealImage realImage;
    private int accessCount;
    private long firstAccessTime;
    
    public ProxyImage(String fileName) {
        this.fileName = fileName;
        this.accessCount = 0;
        System.out.println(" ProxyImage: Created proxy for '" + fileName + "' (image NOT loaded yet)");
    }
    
    @Override
    public void display() {
        accessCount++;
        
        if (accessCount == 1) {
            firstAccessTime = System.currentTimeMillis();
        }
        
        logAccess();
        
        if (!checkAccess()) {
            System.out.println(" Access denied to '" + fileName + "'");
            return;
        }
        
        if (realImage == null) {
            System.out.println(" ProxyImage: First access - loading real image...");
            realImage = new RealImage(fileName);
            System.out.println(" ProxyImage: Real image cached for future use");
        } else {
            System.out.println("  ProxyImage: Using cached image (no disk access needed)");
        }
        
        System.out.println();
        
        realImage.display();
    }
    
    @Override
    public String getFileName() {
        return fileName;
    }
    
    @Override
    public String getDimensions() {
        if (realImage != null) {
            return realImage.getDimensions();
        }
        return "Unknown (not loaded)";
    }
    
    private boolean checkAccess() {return true;}
    
    private void logAccess() {
        System.out.println(" ProxyImage: Access #" + accessCount + " to '" + fileName + "'");
    }
    
    public int getAccessCount() {
        return accessCount;
    }
    
    public boolean isLoaded() {
        return realImage != null;
    }
    
    public String getStatistics() {
        StringBuilder stats = new StringBuilder();
        stats.append("File: ").append(fileName).append("\n");
        stats.append("Access Count: ").append(accessCount).append("\n");
        stats.append("Loaded: ").append(isLoaded() ? "Yes" : "No").append("\n");
        
        if (isLoaded()) {
            stats.append("Size: ").append(realImage.getImageSizeMB()).append(" MB\n");
            stats.append("Load Time: ").append(realImage.getLoadTime()).append(" ms\n");
        }
        
        return stats.toString();
    }
}
