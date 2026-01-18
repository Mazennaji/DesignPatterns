package StructuralPatterns.ProxyPattern;

public class Main {
    
    public static void main(String[] args) {
        printHeader();
        
        scenario1_WithoutProxy();
        
        pause();
        
        scenario2_WithProxy();
        
        pause();
        
        scenario3_CachingBenefits();
        
        pause();
        
        scenario4_PerformanceComparison();
        
        printConclusion();
    }
    
    private static void scenario1_WithoutProxy() {
        printScenarioHeader("SCENARIO 1: WITHOUT PROXY (Immediate Loading)");
        
        System.out.println("Creating 3 RealImage objects directly...\n");
        
        long startTime = System.currentTimeMillis();
        
        RealImage image1 = new RealImage("photo1.jpg");
        System.out.println();
        
        RealImage image2 = new RealImage("photo2.jpg");
        System.out.println();
        
        RealImage image3 = new RealImage("photo3.jpg");
        
        long loadTime = System.currentTimeMillis() - startTime;
        
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("  Total load time: " + loadTime + "ms");
        System.out.println(" Memory used: ~15 MB (all 3 images loaded)");
        System.out.println("═══════════════════════════════════════════════════════");
        
        System.out.println("\n Now displaying only ONE image...\n");
        image1.display();
        
        System.out.println("\n Problems:");
        System.out.println("   . ALL 3 images loaded (wasted time: ~" + loadTime + "ms)");
        System.out.println("   . ALL 3 images in memory (wasted space: ~15 MB)");
        System.out.println("   . Only displayed 1 image (66% waste)");
        System.out.println("   . Slow startup time");
        System.out.println("   . No access control or logging\n");
    }
    
    private static void scenario2_WithProxy() {
        printScenarioHeader("SCENARIO 2: WITH PROXY (Lazy Loading)");
        
        System.out.println("Creating 3 ProxyImage objects...\n");
        
        long startTime = System.currentTimeMillis();
        
        Image image1 = new ProxyImage("vacation1.jpg");
        Image image2 = new ProxyImage("vacation2.jpg");
        Image image3 = new ProxyImage("vacation3.jpg");
        
        long proxyTime = System.currentTimeMillis() - startTime;
        
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println(" Proxy creation time: " + proxyTime + "ms (instant!)");
        System.out.println(" Memory used: ~0 MB (no images loaded yet)");
        System.out.println("═══════════════════════════════════════════════════════");
        
        System.out.println("\n Now displaying only ONE image...\n");
        image1.display();
        
        System.out.println("\n Benefits:");
        System.out.println("   . Only 1 image loaded (saved time: ~2000ms)");
        System.out.println("   . Only 1 image in memory (saved space: ~10 MB)");
        System.out.println("   . Fast startup (instant proxy creation)");
        System.out.println("   . Access logging enabled");
        System.out.println("   . Load on demand - perfect for galleries!\n");
    }
    
    private static void scenario3_CachingBenefits() {
        printScenarioHeader("SCENARIO 3: Caching Benefits");
        
        System.out.println("Creating a proxy and displaying the same image multiple times...\n");
        
        ProxyImage image = new ProxyImage("portrait.jpg");
        
        System.out.println("\n---------------------------------------------------------------");
        System.out.println("First display (will load from disk):");
        System.out.println("---------------------------------------------------------------\n");
        
        long firstDisplayStart = System.currentTimeMillis();
        image.display();
        long firstDisplayTime = System.currentTimeMillis() - firstDisplayStart;
        
        System.out.println("\n---------------------------------------------------------------");
        System.out.println("Second display (will use cache):");
        System.out.println("---------------------------------------------------------------\n");
        
        long secondDisplayStart = System.currentTimeMillis();
        image.display();
        long secondDisplayTime = System.currentTimeMillis() - secondDisplayStart;
        
        System.out.println("\n---------------------------------------------------------------");
        System.out.println("Third display (will use cache):");
        System.out.println("---------------------------------------------------------------\n");
        
        long thirdDisplayStart = System.currentTimeMillis();
        image.display();
        long thirdDisplayTime = System.currentTimeMillis() - thirdDisplayStart;
        
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("  Performance Comparison:");
        System.out.println("   First display:  " + firstDisplayTime + "ms (loaded from disk)");
        System.out.println("   Second display: " + secondDisplayTime + "ms (from cache)");
        System.out.println("   Third display:  " + thirdDisplayTime + "ms (from cache)");
        System.out.println("\n   Speed improvement: ~" + (firstDisplayTime / Math.max(secondDisplayTime, 1)) + "x faster!");
        System.out.println("═══════════════════════════════════════════════════════");
        
        System.out.println("\n Proxy Statistics:");
        System.out.println("---------------------------------------------------------------");
        System.out.println(image.getStatistics());
        
        System.out.println(" Caching Benefits:");
        System.out.println("   . First access: Slow (loads from disk)");
        System.out.println("   . Subsequent access: Fast (uses cached image)");
        System.out.println("   . No redundant disk I/O");
        System.out.println("   . Perfect for repeated access patterns\n");
    }
    
    private static void scenario4_PerformanceComparison() {
        printScenarioHeader("SCENARIO 4: Performance Comparison at Scale");
        
        int imageCount = 10;
        
        System.out.println("Simulating image gallery with " + imageCount + " images...\n");
        
        System.out.println(" WITHOUT PROXY:");
        System.out.println("---------------------------------------------------------------");
        long withoutProxyStart = System.currentTimeMillis();
        
        RealImage[] realImages = new RealImage[imageCount];
        System.out.println("Loading all " + imageCount + " images immediately...");
        for (int i = 0; i < imageCount; i++) {
            realImages[i] = new RealImage("image" + i + ".jpg");
        }
        
        long withoutProxyTime = System.currentTimeMillis() - withoutProxyStart;
        int withoutProxyMemory = imageCount * 5; // 5MB per image
        
        System.out.println("\n   Load time: " + withoutProxyTime + "ms");
        System.out.println("   Memory used: ~" + withoutProxyMemory + " MB");
        System.out.println("   User waits: " + (withoutProxyTime / 1000) + " seconds\n");
        
        // With Proxy
        System.out.println(" WITH PROXY:");
        System.out.println("---------------------------------------------------------------");
        long withProxyStart = System.currentTimeMillis();
        
        ProxyImage[] proxyImages = new ProxyImage[imageCount];
        System.out.println("Creating " + imageCount + " proxy objects...");
        for (int i = 0; i < imageCount; i++) {
            proxyImages[i] = new ProxyImage("image" + i + ".jpg");
        }
        
        long withProxyTime = System.currentTimeMillis() - withProxyStart;
        int withProxyMemory = 0; 
        
        System.out.println("\n   Creation time: " + withProxyTime + "ms (instant!)");
        System.out.println("   Memory used: ~" + withProxyMemory + " MB (nothing loaded)");
        System.out.println("   User waits: ~0 seconds\n");
        
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println(" COMPARISON RESULTS:");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("Time Savings:");
        System.out.println("   . Without Proxy: " + withoutProxyTime + "ms");
        System.out.println("   . With Proxy: " + withProxyTime + "ms");
        System.out.println("   . Improvement: " + (withoutProxyTime / Math.max(withProxyTime, 1)) + "x faster!");
        System.out.println();
        System.out.println("Memory Savings:");
        System.out.println("   . Without Proxy: ~" + withoutProxyMemory + " MB");
        System.out.println("   . With Proxy: ~" + withProxyMemory + " MB");
        System.out.println("   . Saved: ~" + withoutProxyMemory + " MB (100%)");
        System.out.println("═══════════════════════════════════════════════════════");
        
        System.out.println("\n Real-World Impact:");
        System.out.println("---------------------------------------------------------------──");
        System.out.println("   . Photo gallery: Load images as user scrolls");
        System.out.println("   . Video streaming: Buffer only visible content");
        System.out.println("   . Document viewer: Load pages on demand");
        System.out.println("   . Game assets: Load textures when needed");
        System.out.println("   . Web browser: Lazy load images below fold\n");
    }
    
    /**
     * Prints the main header
     */
    private static void printHeader() {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║     PROXY DESIGN PATTERN - Image Viewer System         ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
    }
    
    private static void printScenarioHeader(String title) {
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("  " + title);
        System.out.println("═══════════════════════════════════════════════════════════\n");
    }
    
    private static void printConclusion() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                   KEY BENEFITS                           ║");
        System.out.println("╠════════════════════════════════════════════════════════  ╣");
        System.out.println("║   Lazy Loading (create objects only when needed)         ║");
        System.out.println("║   Caching (reuse loaded objects)                         ║");
        System.out.println("║   Access Control (log and restrict access)               ║");
        System.out.println("║   Performance (faster startup, less memory)              ║");
        System.out.println("║   Transparency (same interface as real object)           ║");
        System.out.println("║   Flexibility (add functionality without changes)        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        System.out.println(" Summary:");
        System.out.println("---------------------------------------------------------------──");
        System.out.println("The Proxy Pattern provides a surrogate that:");
        System.out.println("  1. CONTROLS access to expensive objects");
        System.out.println("  2. DELAYS creation until actually needed (lazy loading)");
        System.out.println("  3. CACHES results for better performance");
        System.out.println("  4. LOGS and monitors object access");
        System.out.println("  5. Remains TRANSPARENT to the client");
        System.out.println("---------------------------------------------------------------──");
        System.out.println("Perfect for: Image galleries, database connections, remote");
        System.out.println("services, document viewers, and any expensive resources! \n");
    }
    
    private static void pause() {
        System.out.println("\n" + "─".repeat(60) + "\n");
    }
}
