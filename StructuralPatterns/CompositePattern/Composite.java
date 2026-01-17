package StructuralPatterns.CompositePattern;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    private String name;
    private List<Component> children;
    
    public Composite(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }
    
    @Override
    public void add(Component component) {
        children.add(component);
    }
    
    @Override
    public void remove(Component component) {
        children.remove(component);
    }
    

    @Override
    public Component getChild(int index) {
        if (index >= 0 && index < children.size()) {
            return children.get(index);
        }
        throw new IndexOutOfBoundsException("Invalid child index: " + index);
    }
    
    @Override
    public void operation() {
        System.out.println("\n" + name + ":");
        for (Component child : children) {
            child.operation();
        }
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public boolean isComposite() {
        return true;
    }
    
    public int getChildCount() {
        return children.size();
    }
    
    public double getTotalPrice() {
        double total = 0.0;
        for (Component child : children) {
            if (child instanceof Leaf) {
                total += ((Leaf) child).getPrice();
            } else if (child instanceof Composite) {
                total += ((Composite) child).getTotalPrice();
            }
        }
        return total;
    }
}
