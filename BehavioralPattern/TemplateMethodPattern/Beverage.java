package TemplateMethodPattern;

public abstract class Beverage {
    
    public final void prepareBeverage() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
        
        if (customerWantsExtras()) {
            addExtras();
        }
        
        serve();
    }
    
    private void boilWater() {
        System.out.println("Boiling water...");
    }
    
    private void pourInCup() {
        System.out.println("Pouring into cup...");
    }
    
    private void serve() {
        System.out.println("Your beverage is ready! Enjoy!\n");
    }
    
    protected abstract void brew();
    protected abstract void addCondiments();
    
    protected void addExtras() {}
    
    protected boolean customerWantsExtras() {
        return false;
    }
}