package StructuralPatterns.CompositePattern;

public interface Component {
    
    void operation();
    

    default void add(Component component) {
        throw new UnsupportedOperationException("Cannot add to a leaf component");
    }
    
    default void remove(Component component) {
        throw new UnsupportedOperationException("Cannot remove from a leaf component");
    }
    
    default Component getChild(int index) {
        throw new UnsupportedOperationException("Leaf components have no children");
    }
    
    String getName();
    
    default boolean isComposite() {
        return false;
    }
}
