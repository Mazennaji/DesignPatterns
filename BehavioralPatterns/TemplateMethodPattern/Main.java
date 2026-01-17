package TemplateMethodPattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Preparing Tea ===");
        var  tea = new Tea();
        tea.prepareBeverage();
        
        System.out.println("=== Preparing Coffee ===");
        var coffee = new Coffee();
        coffee.prepareBeverage();
        
        System.out.println("=== Preparing Hot Chocolate ===");
        var hotChocolate = new HotChocolate();
        hotChocolate.prepareBeverage();
    }
}
