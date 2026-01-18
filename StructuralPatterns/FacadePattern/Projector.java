package StructuralPatterns.FacadePattern;

public class Projector {
    
    public void on() {
        System.out.println("Projector: Powering ON");
    }
    
    public void wideScreenMode() {
        System.out.println("Projector: Setting to Widescreen mode (16:9)");
    }
    
    public void standardMode() {
        System.out.println("Projector: Setting to Standard mode (4:3)");
    }
    
    public void focus() {
        System.out.println("Projector: Adjusting focus");
    }
    
    public void off() {
        System.out.println("Projector: Powering OFF");
    }
}
