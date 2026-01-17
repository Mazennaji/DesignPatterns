# Chain of Responsibility Design Pattern (Java)

## 📌 Overview
The **Chain of Responsibility Pattern** is a behavioral design pattern that **passes requests along a chain of handlers** where each handler decides either to process the request or to pass it to the next handler in the chain.

It decouples the sender of a request from its receivers, allowing multiple objects to handle the request without the sender needing to know which object will ultimately process it.

---

## 🎯 Intent
- Avoid coupling the sender of a request to its receiver
- Allow more than one object to handle a request
- Pass the request along a chain until an object handles it
- Give multiple objects a chance to handle the request

---

## 🧠 Key Idea
> "Don't hardcode who handles a request—let it flow through a chain of potential handlers until someone can process it."

---

## ⏱️ When to Use
Use the Chain of Responsibility Pattern when you want to give more than one object a chance to handle a request, or when you don't know in advance which handler will process it.

---

## 🧩 Participants
| Component | Role |
|---------|------|
| Handler | Defines interface for handling requests |
| ConcreteHandler | Handles requests it's responsible for |
| Client | Initiates the request to a handler in chain |

---

## 📐 UML Class Diagram (Chain of Responsibility Pattern)

```text
+------------------------+
| <<abstract>>           |
|   SupportHandler       |
+------------------------+
| #nextHandler           |
| #handlerName           |
+------------------------+
| +setNext(handler)      |
| +handleRequest(ticket) |
| #canHandle(ticket)     |
| #processRequest(ticket)|
+------------------------+
            ▲
            |
            |
   +--------+--------+--------+--------+
   |        |        |        |        |
+--+---+ +--+---+ +--+---+ +--+------+
|Level1| |Level2| |Level3| |Manager  |
|Support| |Support| |Support| |Support |
+------+ +------+ +------+ +---------+
|BASIC | |MODERATE| |CRITICAL| |EXECUTIVE|
+------+ +------+ +------+ +---------+

        Chain Flow:
Level1 → Level2 → Level3 → Manager

+------------------+
|  SupportTicket   |
+------------------+
| -issue           |
| -priority        |
| -customerName    |
+------------------+
| +getPriority()   |
| +getIssue()      |
| +getCustomerName()|
+------------------+
```

## 🧠 UML Diagram Explanation

The UML diagram illustrates the structure and relationships of the **Chain of Responsibility Design Pattern** implementation.

### 1️⃣ SupportHandler (Abstract Handler)
- Defines the interface for handling requests.
- Maintains:
  - A reference to the next handler in the chain (`nextHandler`)
  - Handler's name for identification
- Declares:
  - `setNext()` to build the chain by linking handlers
  - `handleRequest()` to process or forward requests
  - `canHandle()` abstract method to check if handler can process request
  - `processRequest()` abstract method to actually handle the request
- **Key Responsibility**: Implement the chain traversal logic.

---

### 2️⃣ Concrete Handlers (Level1Support, Level2Support, Level3Support, ManagerSupport)
- Extend the `SupportHandler` abstract class.
- Each handler has specific responsibilities:
  - **Level1Support**: Handles BASIC priority tickets (simple issues)
  - **Level2Support**: Handles MODERATE priority tickets (intermediate issues)
  - **Level3Support**: Handles CRITICAL priority tickets (urgent issues)
  - **ManagerSupport**: Handles EXECUTIVE priority tickets (VIP issues)
- Implement `canHandle()` to define their processing criteria.
- Implement `processRequest()` to define how they handle requests.
- If cannot handle, automatically pass to next handler in chain.

---

### 3️⃣ SupportTicket (Request Object)
- Represents the request being passed through the chain.
- Contains:
  - Issue description
  - Priority level (BASIC, MODERATE, CRITICAL, EXECUTIVE)
  - Customer name
- Carries all information needed by handlers to make decisions.
- Remains unchanged as it travels through the chain.

---

### 4️⃣ Client (SupportSystem)
- Creates the chain of handlers.
- Links handlers together using `setNext()`.
- Initiates request by calling `handleRequest()` on first handler.
- Does **not** know which handler will ultimately process the request.

---

## 🔗 Relationships Summary
- Concrete handlers **extend** `SupportHandler`
- Each handler **has a reference to** the next handler
- Handlers are **chained together** in a linked list structure
- Request **flows through** the chain until handled
- Client **interacts only with** the first handler
- Each handler **decides independently** whether to handle or forward
- Chain can be **dynamically configured** at runtime

---

## ✅ Key Design Benefits
- Loose coupling between sender and receiver
- Dynamic chain configuration
- Flexible responsibility assignment
- Single Responsibility Principle (each handler has one job)
- Open/Closed Principle (add new handlers without modifying existing ones)
- Request can be handled at multiple levels

---

## 🔄 Chain of Responsibility vs. Direct Dispatch

### ❌ **Without Chain of Responsibility (Direct Dispatch)**

```java
// Client must know about all handlers and their capabilities
public class SupportSystem {
    private Level1Support level1 = new Level1Support();
    private Level2Support level2 = new Level2Support();
    private Level3Support level3 = new Level3Support();
    private ManagerSupport manager = new ManagerSupport();
    
    public void handleTicket(SupportTicket ticket) {
        // Problem: Complex if-else logic in one place
        if (ticket.getPriority() == Priority.BASIC) {
            level1.handle(ticket);
        } else if (ticket.getPriority() == Priority.MODERATE) {
            level2.handle(ticket);
        } else if (ticket.getPriority() == Priority.CRITICAL) {
            level3.handle(ticket);
        } else if (ticket.getPriority() == Priority.EXECUTIVE) {
            manager.handle(ticket);
        } else {
            System.out.println("No one can handle this!");
        }
    }
}

// Client code
SupportSystem system = new SupportSystem();
system.handleTicket(ticket);
```

**Problems with this approach:**
- ⚠️ **Tight Coupling**: Client knows about all handlers and their criteria
- ⚠️ **Complex Logic**: All routing logic concentrated in one place
- ⚠️ **Inflexible**: Hard to add new handlers or change routing rules
- ⚠️ **Violates Open/Closed**: Must modify client code to add handlers
- ⚠️ **Single Point of Failure**: All routing logic in one class
- ⚠️ **No Fallback**: If criteria don't match exactly, request fails

---

### ✅ **With Chain of Responsibility (Dynamic Chain)**

```java
// Handlers are self-contained and linked
public abstract class SupportHandler {
    protected SupportHandler nextHandler;
    
    public SupportHandler setNext(SupportHandler handler) {
        this.nextHandler = handler;
        return handler;
    }
    
    public void handleRequest(SupportTicket ticket) {
        if (canHandle(ticket)) {
            processRequest(ticket);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(ticket); // Pass it along
        }
    }
    
    protected abstract boolean canHandle(SupportTicket ticket);
    protected abstract void processRequest(SupportTicket ticket);
}

// Client code - CLEAN!
SupportHandler chain = new Level1Support();
chain.setNext(new Level2Support())
     .setNext(new Level3Support())
     .setNext(new ManagerSupport());

// Simple: Just start the chain
chain.handleRequest(ticket);
```

**Benefits of this approach:**
- ✅ **Loose Coupling**: Client only knows the first handler
- ✅ **Distributed Logic**: Each handler knows its own criteria
- ✅ **Flexible**: Easy to add, remove, or reorder handlers
- ✅ **Open/Closed Principle**: Add handlers without modifying existing code
- ✅ **Maintainable**: Each handler is independent
- ✅ **Natural Fallback**: Request automatically flows to next handler

---

### 📊 Side-by-Side Comparison

| Aspect | Direct Dispatch | Chain of Responsibility |
|--------|----------------|------------------------|
| **Coupling** | Tight (client knows all handlers) | Loose (client knows first handler only) |
| **Logic Location** | Centralized (one if-else block) | Distributed (each handler decides) |
| **Adding Handlers** | Modify client code | Add to chain, no client changes |
| **Flexibility** | Low (fixed routing) | High (dynamic chain configuration) |
| **Maintainability** | Difficult (central bottleneck) | Easy (independent handlers) |
| **Extensibility** | Must modify existing code | Just create new handler class |
| **Fallback Handling** | Manual (explicit code) | Automatic (flows through chain) |

---

### 🎯 Real-World Impact

**Scenario**: You need to add a "SeniorManagerSupport" level between Level 3 and Manager for high-priority executive issues.

**Without Chain of Responsibility:**
```java
// Must modify the central routing logic!
public void handleTicket(SupportTicket ticket) {
    if (ticket.getPriority() == Priority.BASIC) {
        level1.handle(ticket);
    } else if (ticket.getPriority() == Priority.MODERATE) {
        level2.handle(ticket);
    } else if (ticket.getPriority() == Priority.CRITICAL) {
        level3.handle(ticket);
    } else if (ticket.getPriority() == Priority.HIGH_EXECUTIVE) { // NEW
        seniorManager.handle(ticket); // NEW
    } else if (ticket.getPriority() == Priority.EXECUTIVE) {
        manager.handle(ticket);
    }
    // Breaks Open/Closed Principle!
}
```

**With Chain of Responsibility:**
```java
// Just insert the new handler into the chain - NO other changes needed!
SupportHandler chain = new Level1Support();
chain.setNext(new Level2Support())
     .setNext(new Level3Support())
     .setNext(new SeniorManagerSupport()) // NEW - just add it!
     .setNext(new ManagerSupport());

// Client code unchanged!
// All existing handlers unchanged!
// Follows Open/Closed Principle!
```

---

### 💡 Key Takeaway

> **Direct Dispatch** = "I need to know everyone's job and route everything myself"  
> **Chain of Responsibility** = "I'll hand it to the first person, and they'll figure out who should handle it"

This comparison shows why the Chain of Responsibility Pattern is essential for building flexible systems where handling logic should be distributed and easy to extend.

---

## 🛠️ Technologies Used
- Java
- Object-Oriented Programming
- Behavioral Design Pattern

---

## 📚 Example Explanation
In this example:
- `SupportHandler` is the abstract handler defining the chain structure
- Concrete handlers (`Level1Support`, `Level2Support`, `Level3Support`, `ManagerSupport`) each handle specific priority levels
- `SupportTicket` is the request object containing issue details and priority
- Handlers are linked in a chain: Level 1 → Level 2 → Level 3 → Manager
- Each handler checks if it can handle the ticket; if not, passes to next handler
- The chain automatically escalates tickets to appropriate level
- Client only interacts with the first handler in the chain

---

## ✅ Advantages
- Reduces coupling between sender and receiver
- Adds flexibility in assigning responsibilities
- Allows dynamic chain configuration
- Easy to add new handlers without modifying existing code
- Each handler has single responsibility
- Request can be handled at multiple levels
- Follows Open/Closed Principle

---

## ❌ Disadvantages
- Request might go unhandled if chain is not configured properly
- Can be difficult to debug (trace request through chain)
- No guarantee a request will be handled
- Performance concerns with long chains
- Can be harder to understand overall flow

---

## 🎓 Use Cases
- Event handling in GUI systems
- Logging frameworks (different log levels)
- Exception handling (try-catch chains)
- Customer support escalation systems
- Authentication/authorization chains
- Request processing in web servers
- Approval workflows (expense approvals, leave requests)
- Middleware in web frameworks
- Command processing in games

---

## 🔄 Related Patterns
- **Composite**: Often used with Chain of Responsibility for tree structures
- **Command**: Requests can be encapsulated as Command objects
- **Decorator**: Similar structure but different intent (adds behavior vs. handles request)

---

## 🌐 Real-World Examples

### 1. ATM Cash Dispenser 💰
When you withdraw $350, the ATM doesn't dispense 350 $1 bills. It tries $100 bills first, then $50s, then $20s, then $10s—each dispenser in the chain handles what it can.

### 2. Customer Service Escalation 📞
Call starts with Level 1 support → If unresolved, escalates to Level 2 → Then Level 3 → Then Manager → Then Director.

### 3. Exception Handling ⚠️
```java
try {
    // code
} catch (IOException e) {
    // Handle IOException
} catch (SQLException e) {
    // Handle SQLException
} catch (Exception e) {
    // Handle any other exception
}
```

### 4. Logging Frameworks 📝
DEBUG handler → INFO handler → WARNING handler → ERROR handler. Each level passes to next if message doesn't match its level.

---

## 📚 Design Principles

This pattern follows these principles:
- **Single Responsibility**: Each handler handles one type of request
- **Open/Closed**: Open for extension (add handlers), closed for modification
- **Loose Coupling**: Sender doesn't know about receivers
- **Dependency Inversion**: Depend on handler abstraction, not concrete handlers

---

## 🎯 Best Practices

1. **Define Clear Criteria**: Each handler should have clear conditions for handling
2. **Avoid Long Chains**: Too many handlers can hurt performance
3. **Provide Default Handler**: Add a default handler at the end to catch unhandled requests
4. **Document the Chain**: Clearly document the order and responsibilities
5. **Consider Logging**: Log when requests are passed along for debugging
6. **Make Handlers Reusable**: Design handlers to be used in different chains
7. **Test Each Handler**: Unit test each handler independently

---

## 🚀 How to Run

### Compile
```bash
javac *.java
```

### Run
```bash
java Main.java
```

### Expected Output
```
╔═══════════════════════════════════════════════════╗
║  Chain of Responsibility - Support System Demo   ║
╚═══════════════════════════════════════════════════╝

🔗 Building the support chain...

   Chain established: Level 1 → Level 2 → Level 3 → Manager

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📋 Processing support tickets...
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

🎫 Ticket 1: [BASIC] John Doe: Password reset request
   ✅ Level 1 Support handled: [BASIC] John Doe: Password reset request
      → Solution: Provided standard troubleshooting steps

🎫 Ticket 2: [MODERATE] Jane Smith: Software installation issue
   ↪ Level 1 Support cannot handle this. Escalating...
   ✅ Level 2 Support handled: [MODERATE] Jane Smith: Software installation issue
      → Solution: Performed advanced diagnostics and configuration

[... more ticket processing ...]
```

---

## 📖 Additional Resources

- **Design Patterns** by Gang of Four
- **Head First Design Patterns** by Freeman & Freeman
- **Refactoring Guru**: https://refactoring.guru/design-patterns/chain-of-responsibility

---

## ✍️ Author
**Mazen Naji**  
Software Engineer | Full Stack Developer  

---

## 📄 License
Educational use only