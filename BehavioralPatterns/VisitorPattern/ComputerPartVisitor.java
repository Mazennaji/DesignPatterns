package VisitorPattern;


public interface ComputerPartVisitor {

    void visit(CPU cpu);

    void visit(RAM ram);

    void visit(HDD hdd);
    
    void visit(GPU gpu);
}
