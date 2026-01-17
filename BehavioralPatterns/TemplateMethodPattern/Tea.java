package TemplateMethodPattern;

public class Tea extends Beverage {
    
    @Override
    protected void brew() {
        System.out.println("Steeping the tea bag...");
    }
    
    @Override
    protected void addCondiments() {
        System.out.println("Adding lemon...");
    }
    
    @Override
    protected void addExtras() {
        System.out.println("Adding honey");
    }
    
    @Override
    protected boolean customerWantsExtras() {
        return true;
    }
}
