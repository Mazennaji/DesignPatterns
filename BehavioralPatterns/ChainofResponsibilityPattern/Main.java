package ChainofResponsibilityPattern;

/**
 * Chain of Responsibility Pattern Demo
 * Demonstrates the Chain of Responsibility Pattern with a support ticket system
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║  Chain of Responsibility - Support System Demo    ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");
        
        // Create the chain of handlers
        System.out.println(" Building the support chain...\n");
        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler level3 = new Level3Support();
        SupportHandler manager = new ManagerSupport();
        
        // Set up the chain: Level1 → Level2 → Level3 → Manager
        level1.setNext(level2)
              .setNext(level3)
              .setNext(manager);
        
        System.out.println("   Chain established: Level 1 -> Level 2 -> Level 3 -> Manager\n");
        
        System.out.println("-----------------------------------------------");
        System.out.println(" Processing support tickets...");
        System.out.println("-----------------------------------------------\n");
        
        // Create various support tickets
        SupportTicket ticket1 = new SupportTicket(
            "John Doe", 
            "Password reset request", 
            SupportTicket.Priority.BASIC
        );
        
        SupportTicket ticket2 = new SupportTicket(
            "Jane Smith", 
            "Software installation issue", 
            SupportTicket.Priority.MODERATE
        );
        
        SupportTicket ticket3 = new SupportTicket(
            "Bob Johnson", 
            "Server outage affecting production", 
            SupportTicket.Priority.CRITICAL
        );
        
        SupportTicket ticket4 = new SupportTicket(
            "Alice Williams", 
            "VIP client complaint requiring immediate attention", 
            SupportTicket.Priority.EXECUTIVE
        );
        
        // Process tickets through the chain
        System.out.println(" Ticket 1: " + ticket1);
        level1.handleRequest(ticket1);
        
        System.out.println("\n Ticket 2: " + ticket2);
        level1.handleRequest(ticket2);
        
        System.out.println("\n Ticket 3: " + ticket3);
        level1.handleRequest(ticket3);
        
        System.out.println("\n Ticket 4: " + ticket4);
        level1.handleRequest(ticket4);
        
        System.out.println("\n-----------------------------------------------");
        System.out.println("  Demonstrating Chain Behavior");
        System.out.println("-----------------------------------------------");
        System.out.println(" Each handler checks if it can process the request");
        System.out.println(" If yes, it handles the request");
        System.out.println(" If no, it passes to the next handler in the chain");
        System.out.println(" Request travels up the chain until handled");
        
        System.out.println("\n-----------------------------------------------");
        System.out.println(" Demonstrating Different Entry Points");
        System.out.println("-----------------------------------------------\n");
        
        System.out.println(" Starting from Level 2 (skipping Level 1):");
        SupportTicket ticket5 = new SupportTicket(
            "Charlie Brown", 
            "Database corruption issue", 
            SupportTicket.Priority.CRITICAL
        );
        System.out.println("   " + ticket5);
        level2.handleRequest(ticket5);
        
        System.out.println("\n Starting directly from Manager:");
        SupportTicket ticket6 = new SupportTicket(
            "Diana Prince", 
            "Board member urgent request", 
            SupportTicket.Priority.EXECUTIVE
        );
        System.out.println("   " + ticket6);
        manager.handleRequest(ticket6);
        
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║              Demo Complete!                       ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
    }
}
