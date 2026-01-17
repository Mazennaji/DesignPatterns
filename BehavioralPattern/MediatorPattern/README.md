# Mediator Design Pattern (Java)

## 📌 Overview
The **Mediator Pattern** is a behavioral design pattern that **reduces chaotic dependencies between objects** by making them communicate indirectly through a mediator object.

Instead of objects communicating directly with each other (creating tight coupling), they communicate through a centralized mediator, promoting loose coupling and easier maintenance.

---

## 🎯 Intent
- Define an object that encapsulates how a set of objects interact
- Promote loose coupling by preventing objects from referring to each other explicitly
- Centralize complex communications and control logic
- Make object interactions easier to understand and maintain

---

## 🧠 Key Idea
> "Don't let objects talk directly to each other—make them go through a mediator that coordinates all interactions."

---

## ⏱️ When to Use
Use the Mediator Pattern when a set of objects communicate in complex but well-defined ways, or when reusing an object is difficult because it communicates with many other objects.

---

## 🧩 Participants
| Component | Role |
|---------|------|
| Mediator | Defines interface for communication |
| ConcreteMediator | Coordinates communication between colleagues |
| Colleague | Defines interface for objects that interact |
| ConcreteColleague | Communicates with other colleagues through mediator |
| Client | Creates mediator and colleagues |

---

## 📐 UML Class Diagram (Mediator Pattern)

```text
+------------------------+                    +---------------------+
| <<interface>>          |                    | <<abstract>>        |
|     ChatMediator       |                    |        User         |
+------------------------+                    +---------------------+
| +sendMessage()         |                    | #mediator           |
| +addUser()             |                    | #name               |
| +removeUser()          |                    +---------------------+
+------------------------+                    | +send()             |
         ▲                                    | +receive()          |
         |                                    | +getName()          |
         |                                    +---------------------+
         |                                              ▲
+------------------------+                              |
|      ChatRoom          |                    +---------+-----------+
+------------------------+                    |                     |
| -users: List<User>     |◄───────────+----------+            +----------+
| -roomName: String      |            |BasicUser |            |PremiumUser|
+------------------------+            +----------+            +----------+
| +sendMessage()         |            | +send()  |            | +send()   |
| +addUser()             |            |+receive()|            |+receive() |
| +removeUser()          |            +----------+            +----------+
| +getRoomName()         |
| +getUserCount()        |
+------------------------+

         Mediator
            ↓
    Coordinates all
    communication
            ↓
       Colleagues
```

## 🧠 UML Diagram Explanation

The UML diagram illustrates the structure and relationships of the **Mediator Design Pattern** implementation.

### 1️⃣ ChatMediator (Mediator Interface)
- Defines the interface for communication between colleagues.
- Declares:
  - `sendMessage()` to broadcast messages
  - `addUser()` to register new colleagues
  - `removeUser()` to unregister colleagues
- This interface ensures different mediators can be implemented consistently.

---

### 2️⃣ ChatRoom (Concrete Mediator)
- Implements the `ChatMediator` interface.
- Maintains:
  - A list of all registered users (`List<User>`)
  - The chat room name
- Responsible for coordinating all communication between users.
- **Key Responsibility**: Users send messages to the ChatRoom, which then distributes them to other users.
- Encapsulates the interaction logic so colleagues don't need to know about each other.

---

### 3️⃣ User (Abstract Colleague)
- Defines the base class for all users in the system.
- Maintains:
  - A reference to the mediator (`ChatMediator`)
  - User's name
- Declares:
  - `send()` to send messages through the mediator
  - `receive()` to receive messages from the mediator
- Ensures all colleague types follow the same communication protocol.

---

### 4️⃣ Concrete Colleagues (BasicUser, PremiumUser)
- Extend the `User` abstract class.
- Each type can have different behavior:
  - **BasicUser**: Standard user with basic functionality
  - **PremiumUser**: Enhanced user with additional features
- Both types communicate **only** through the mediator.
- Do **not** have direct references to other users.
- Register themselves with the mediator upon creation.

---

### 5️⃣ Client (ChatApplication)
- Creates the mediator (`ChatRoom`)
- Creates colleagues (users)
- Users automatically register with the mediator
- Triggers interactions by having users send messages

---

## 🔗 Relationships Summary
- `ChatRoom` **implements** `ChatMediator`
- `BasicUser` and `PremiumUser` **extend** `User`
- `ChatRoom` **maintains references to** multiple `User` objects
- `User` **has a reference to** `ChatMediator`
- Users **communicate through** the mediator, not directly with each other
- `ChatRoom` **coordinates** all interactions between users
- Adding/removing users is managed by the mediator

---

## ✅ Key Design Benefits
- Loose coupling between colleague objects
- Centralized control logic
- Simplified object protocols
- Easy to add new colleagues or change interaction logic
- Compliance with **Single Responsibility Principle**
- Reusable mediator and colleague classes

---

## 🔄 Mediator Pattern vs. Direct Communication

### ❌ **Without Mediator Pattern (Direct Communication)**

```java
// Users must know about and communicate directly with each other
public class User {
    private String name;
    private List<User> contacts; // Must maintain list of all other users!
    
    public User(String name) {
        this.name = name;
        this.contacts = new ArrayList<>();
    }
    
    public void addContact(User user) {
        contacts.add(user);
    }
    
    public void sendMessage(String message) {
        // Problem: Must manually send to each user
        for (User user : contacts) {
            user.receive(message, this);
        }
    }
    
    public void receive(String message, User sender) {
        System.out.println(name + " received from " + sender.getName() + ": " + message);
    }
}

// Client code - NIGHTMARE!
User alice = new User("Alice");
User bob = new User("Bob");
User charlie = new User("Charlie");
User diana = new User("Diana");

// Problem: Must manually wire everyone to everyone else!
alice.addContact(bob);
alice.addContact(charlie);
alice.addContact(diana);

bob.addContact(alice);
bob.addContact(charlie);
bob.addContact(diana);

charlie.addContact(alice);
charlie.addContact(bob);
charlie.addContact(diana);

diana.addContact(alice);
diana.addContact(bob);
diana.addContact(charlie);

// If you add a 5th user, you need to update ALL existing users!
```

**Problems with this approach:**
- ⚠️ **Tight Coupling**: Each user must know about every other user
- ⚠️ **N² Connections**: With N users, you need N×(N-1) connections!
- ⚠️ **Maintenance Nightmare**: Adding/removing users requires updating all other users
- ⚠️ **Scattered Logic**: Communication logic is duplicated in every user
- ⚠️ **Hard to Test**: Must create and wire many objects for testing
- ⚠️ **No Central Control**: Can't easily implement rules, logging, or filtering

---

### ✅ **With Mediator Pattern (Centralized Communication)**

```java
// Users only know about the mediator, not each other
public class User {
    private ChatMediator mediator;
    private String name;
    
    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
        mediator.addUser(this); // Self-registration
    }
    
    public void sendMessage(String message) {
        // Simple: Just tell the mediator
        mediator.sendMessage(message, this);
    }
    
    public void receive(String message, User sender) {
        System.out.println(name + " received from " + sender.getName() + ": " + message);
    }
}

// Client code - CLEAN!
ChatRoom chatRoom = new ChatRoom("General");

// Simple: Users automatically register with the mediator
User alice = new BasicUser(chatRoom, "Alice");
User bob = new BasicUser(chatRoom, "Bob");
User charlie = new PremiumUser(chatRoom, "Charlie");
User diana = new BasicUser(chatRoom, "Diana");

// Easy: Send messages without knowing about other users
alice.sendMessage("Hello everyone!");

// Easy: Add 10 more users with no changes to existing code!
User evan = new BasicUser(chatRoom, "Evan");
```

**Benefits of this approach:**
- ✅ **Loose Coupling**: Users only depend on the mediator interface
- ✅ **Linear Connections**: With N users, you need only N connections to mediator
- ✅ **Easy Maintenance**: Add/remove users without touching other users
- ✅ **Centralized Logic**: All communication logic in one place
- ✅ **Easy Testing**: Mock the mediator, test users independently
- ✅ **Central Control**: Easy to add logging, filtering, rules, etc.

---

### 📊 Side-by-Side Comparison

| Aspect | Direct Communication | Mediator Pattern |
|--------|---------------------|------------------|
| **Coupling** | Tight (everyone knows everyone) | Loose (only know mediator) |
| **Connections** | N×(N-1) connections | N connections |
| **Adding Users** | Update all existing users | Just create new user |
| **Removing Users** | Update all existing users | Remove from mediator |
| **Communication Logic** | Scattered in each user | Centralized in mediator |
| **Testing** | Complex (need all objects) | Simple (mock mediator) |
| **Control** | None (distributed) | Centralized |
| **Maintainability** | Difficult | Easy |

---

### 🎯 Real-World Impact

**Scenario**: You need to add a "ModeratorUser" who can see all messages but also has special privileges.

**Without Mediator Pattern:**
```java
// Must modify EVERY existing user class!
public class User {
    private List<User> contacts;
    private List<ModeratorUser> moderators; // Add new field
    
    public void sendMessage(String message) {
        // Update all communication logic
        for (User user : contacts) {
            user.receive(message, this);
        }
        // Add new moderator notification
        for (ModeratorUser mod : moderators) {
            mod.receiveForModeration(message, this);
        }
    }
}
// Every user class needs these changes!
```

**With Mediator Pattern:**
```java
// Mediator handles it - NO changes to existing users!
public class ModeratorUser extends User {
    public ModeratorUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }
    
    @Override
    public void receive(String message, User sender) {
        // Special moderator behavior
        System.out.println("[MODERATOR] " + name + " monitoring: " + message);
    }
}

// Just create the moderator - that's it!
User moderator = new ModeratorUser(chatRoom, "Admin");
```

---

### 💡 Key Takeaway

> **Direct Communication** = "I need to know about and manage relationships with everyone"  
> **Mediator Pattern** = "I just talk to the mediator, it handles all the complexity"

This comparison shows why the Mediator Pattern is essential for managing complex interactions between multiple objects without creating a tangled web of dependencies.

---

## 🛠️ Technologies Used
- Java
- Object-Oriented Programming
- Behavioral Design Pattern

---

## 📚 Example Explanation
In this example:
- `ChatRoom` is the mediator that coordinates all communication
- `User` is the abstract colleague class
- `BasicUser` and `PremiumUser` are concrete colleagues
- Users send messages through the mediator, not directly to each other
- The mediator broadcasts messages to all users except the sender
- Users can be added or removed dynamically

---

## ✅ Advantages
- Reduces coupling between colleague objects
- Centralizes control and communication logic
- Simplifies object protocols
- Easy to add new colleagues or change interactions
- Improves code maintainability
- Makes the system easier to understand

---

## ❌ Disadvantages
- Mediator can become a "god object" (too complex)
- May be harder to understand overall control flow
- Can introduce a single point of failure
- Mediator itself can become difficult to maintain if too complex

---

## 🎓 Use Cases
- Chat applications (users communicating through chat room)
- Air traffic control (planes communicating through tower)
- GUI components (widgets coordinating through dialog/window)
- Multiplayer games (players communicating through game server)
- Smart home systems (devices communicating through hub)
- Workflow systems (tasks coordinating through workflow engine)
- Event management systems

---

## 🔄 Related Patterns
- **Facade**: Simplifies interface vs. Mediator coordinates interactions
- **Observer**: Often used together (mediator notifies colleagues)
- **Command**: Can be used to encapsulate requests in mediator

---

## 🌐 Real-World Examples

### 1. Air Traffic Control 🛫
Planes don't communicate directly with each other. They all communicate with the control tower (mediator), which coordinates takeoffs, landings, and flight paths.

### 2. Chat Applications 💬
Users don't send messages directly to each other's devices. Messages go through a server (mediator) that handles routing, storage, and delivery.

### 3. Smart Home Hub 🏠
Smart devices (lights, thermostat, security) don't communicate directly. They communicate through a central hub that coordinates their actions.

### 4. Stock Exchange 📈
Buyers and sellers don't trade directly. The exchange (mediator) matches orders, ensures fair pricing, and handles transactions.

---

## 📚 Design Principles

This pattern follows these principles:
- **Loose Coupling**: Objects interact without knowing about each other
- **Single Responsibility**: Mediator handles coordination, colleagues handle their specific tasks
- **Open/Closed**: Easy to add new colleague types without modifying mediator interface
- **Dependency Inversion**: Colleagues depend on mediator abstraction, not concrete implementations

---

## 🎯 Best Practices

1. **Keep Mediator Focused**: Don't let it become a god object
2. **Use Interfaces**: Define clear mediator and colleague interfaces
3. **Avoid Bidirectional**: Mediator knows colleagues, but colleagues only know mediator interface
4. **Consider Observer**: Use Observer pattern for notifications within mediator
5. **Document Interactions**: Clearly document how colleagues interact through mediator
6. **Test Independently**: Test mediator and colleagues separately

---

## 🚀 How to Run

### Compile
```bash
javac *.java
```

### Run
```bash
java Main
```

### Expected Output
```
╔════════════════════════════════════════╗
║   Mediator Pattern - Chat Room Demo   ║
╚════════════════════════════════════════╝

🏠 Chat Room 'General' created!
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📝 Adding users to chat room...
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

✅ Alice joined 'General'
📊 Total users in room: 1

✅ Bob joined 'General'
📊 Total users in room: 2

[... more output showing message exchanges ...]
```

---

## 📖 Additional Resources

- **Design Patterns** by Gang of Four
- **Head First Design Patterns** by Freeman & Freeman
- **Refactoring Guru**: https://refactoring.guru/design-patterns/mediator

---

## ✍️ Author
**Mazen Naji**  
Software Engineer | Full Stack Developer  

---

## 📄 License
Educational use only