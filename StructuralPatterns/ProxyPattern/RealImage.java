package StructuralPatterns.ProxyPattern;

public class RealImage implements Image {
    private String fileName;
    private byte[] imageData;
    private int width;
    private int height;
    private long loadTime;
    
    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }
    
    private void loadFromDisk() {
        System.out.println(" RealImage: Loading '" + fileName + "' from disk...");
        
        long startTime = System.currentTimeMillis();
        
        try {
            Thread.sleep(1000);
            
            this.imageData = new byte[1024 * 1024 * 5]; // 5MB image
            this.width = 1920;
            this.height = 1080;
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        this.loadTime = System.currentTimeMillis() - startTime;
        
        System.out.println("    Image loaded successfully!");
        System.out.println("   Size: " + (imageData.length / (1024 * 1024)) + " MB");
        System.out.println("   Dimensions: " + width + "x" + height);
        System.out.println("   Load time: " + loadTime + "ms");
    }
    
    @Override
    public void display() {
        System.out.println("  Displaying: " + fileName + " [" + width + "x" + height + "]");
    }
    
    @Override
    public String getFileName() {
        return fileName;
    }
    
    @Override
    public String getDimensions() {
        return width + "x" + height;
    }
    
    public int getImageSizeMB() {
        return imageData.length / (1024 * 1024);
    }
    
    public long getLoadTime() {
        return loadTime;
    }
}
