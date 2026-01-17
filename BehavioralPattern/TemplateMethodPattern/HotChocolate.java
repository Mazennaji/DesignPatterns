package TemplateMethodPattern;

public class HotChocolate extends Beverage {
    
    @Override
    protected void brew() {
        System.out.println("Mixing cocoa powder with hot water...");
    }
    
    @Override
    protected void addCondiments() {
        System.out.println("Adding milk and vanilla...");
    }
    
    @Override
    protected void addExtras() {
        System.out.println("Adding marshmallows and whipped cream");
    }
    
    @Override
    protected boolean customerWantsExtras() {
        return true;
    }
}
