package StructuralPatterns.FacadePattern;

public class Lights {
    private int brightness;
    
    public void on() {
        this.brightness = 100;
        System.out.println("Lights: ON (Brightness: 100%)");
    }
    
    public void dim(int level) {
        this.brightness = level;
        System.out.println("Lights: Dimmed to " + level + "%");
    }
    
    public void off() {
        this.brightness = 0;
        System.out.println("Lights: OFF");
    }
    
    public int getBrightness() {
        return this.brightness;
    }
}
