# Adapter Design Pattern (Java)

## 📌 Overview
The **Adapter Pattern** is a structural design pattern that **allows incompatible interfaces to work together**, acting as a bridge between two incompatible interfaces by wrapping an object with a new interface.

It converts the interface of a class into another interface that clients expect, allowing classes to work together that couldn't otherwise because of incompatible interfaces.

---

## 🎯 Intent
- Convert an interface into another interface clients expect
- Allow incompatible interfaces to work together
- Wrap an existing class with a new interface
- Enable reuse of existing classes with incompatible interfaces
- Decouple client code from specific implementations

---

## 🧠 Key Idea
> "Make incompatible interfaces compatible by creating a wrapper that translates one interface to another."

---

## ⏱️ When to Use
Use the Adapter Pattern when you want to use an existing class, but its interface doesn't match the one you need, or when you want to create a reusable class that cooperates with unrelated classes with incompatible interfaces.

---

## 🧩 Participants
| Component | Role |
|---------|------|
| Target | Defines the domain-specific interface that Client uses |
| Adapter | Adapts the Adaptee interface to the Target interface |
| Adaptee | Defines an existing interface that needs adapting |
| Client | Collaborates with objects conforming to Target interface |

---

## 📐 UML Class Diagram (Adapter Pattern)

```text
    ┌─────────────────────┐
    │      Client         │
    └─────────────────────┘
              │
              │ uses
              ▼
    ┌─────────────────────┐
    │  <<interface>>      │
    │      Target         │
    │   (MediaPlayer)     │
    ├─────────────────────┤
    │ +play(type, file)   │
    └─────────────────────┘
              ▲
              │
              │ implements
    ┌─────────┴─────────┐
    │                   │
┌───┴──────────┐  ┌─────┴────────────┐
│ AudioPlayer  │  │  MediaAdapter    │
├──────────────┤  ├──────────────────┤
│ +play()      │  │ -advancedPlayer  │
└──────────────┘  │ +play()          │
                  └──────────────────┘
                          │
                          │ uses/wraps
                          ▼
                  ┌──────────────────┐
                  │  <<interface>>   │
                  │  Adaptee         │
                  │ (AdvancedMedia   │
                  │    Player)       │
                  ├──────────────────┤
                  │ +playVlc()       │
                  │ +playMp4()       │
                  └──────────────────┘
                          ▲
                          │
                    ┌─────┴─────┐
                    │           │
            ┌───────┴──┐  ┌─────┴──────┐
            │VlcPlayer │  │ Mp4Player  │
            ├──────────┤  ├────────────┤
            │+playVlc()│  │ +playMp4() │
            └──────────┘  └────────────┘
```

## 🧠 UML Diagram Explanation

The UML diagram illustrates the structure and relationships of the **Adapter Design Pattern** implementation.

### 1️⃣ Target (MediaPlayer Interface)
- Defines the interface that the client expects and uses.
- Declares the method signature that the client code works with.
- In our example: `play(String audioType, String fileName)`
- **Key Principle**: This is the interface we want everything to conform to.
- Client code is written to work with this interface.

---

### 2️⃣ Adapter (MediaAdapter Class)
- Implements the Target interface (MediaPlayer).
- Holds a reference to an Adaptee object (AdvancedMediaPlayer).
- **Translates** calls from the Target interface to the Adaptee interface.
- Acts as a **bridge** between incompatible interfaces.
- Key methods:
  - Constructor: Creates appropriate Adaptee based on type
  - `play()`: Adapts the call to `playVlc()` or `playMp4()`
- **Key Benefit**: Allows incompatible interfaces to work together seamlessly.

---

### 3️⃣ Adaptee (AdvancedMediaPlayer Interface)
- The existing interface that needs adapting.
- Has methods that are **incompatible** with the Target interface.
- In our example:
  - `playVlc(String fileName)` - specific method for VLC files
  - `playMp4(String fileName)` - specific method for MP4 files
- **Key Point**: This interface already exists and can't be changed.
- Multiple concrete implementations (VlcPlayer, Mp4Player).

---

### 4️⃣ Concrete Adaptees (VlcPlayer, Mp4Player)
- Implement the Adaptee interface (AdvancedMediaPlayer).
- Provide actual functionality for specific media formats.
- **VlcPlayer**: Implements `playVlc()` to play VLC files
- **Mp4Player**: Implements `playMp4()` to play MP4 files
- These are the existing classes we want to reuse.
- Their interfaces don't match what the client expects.

---

### 5️⃣ Concrete Target (AudioPlayer)
- Implements the Target interface (MediaPlayer).
- Can handle some formats natively (MP3 in our example).
- Uses the Adapter for formats it doesn't support natively.
- Demonstrates how the adapter integrates into existing code.
- Client code works with this class through the MediaPlayer interface.

---

### 6️⃣ Client (AdapterPatternDemo)
- Uses objects through the Target interface (MediaPlayer).
- **Doesn't know** about the Adapter or Adaptee.
- Works uniformly with all media types.
- Benefits from the adapter without being aware of it.
- Clean, simple code that doesn't deal with incompatibilities.

---

## 🔗 Relationships Summary
- Client **uses** Target interface (MediaPlayer)
- Adapter **implements** Target interface
- Adapter **has-a** Adaptee (composition/aggregation)
- Concrete Adaptees **implement** Adaptee interface
- AudioPlayer **implements** Target and **uses** Adapter
- **Translation flow**: Client → Target → Adapter → Adaptee
- Adapter acts as a **wrapper** around Adaptee
- Client remains **decoupled** from Adaptee implementation

---

## ✅ Key Design Benefits
- Enables incompatible interfaces to work together
- Reuses existing code without modification
- Follows Open/Closed Principle (open for extension)
- Decouples client from implementation details
- Single Responsibility - adapter handles conversion
- Increases flexibility and reusability

---

## 🔄 Adapter Pattern vs. No Adapter

### ❌ **Without Adapter Pattern (Tight Coupling)**

```java
// Client code must know about all specific player types - messy!
public class MediaApp {
    public void playMedia(String type, String fileName) {
        // Tight coupling to specific implementations
        if (type.equals("vlc")) {
            VlcPlayer vlcPlayer = new VlcPlayer();
            vlcPlayer.playVlc(fileName);  // Different method signature!
            
        } else if (type.equals("mp4")) {
            Mp4Player mp4Player = new Mp4Player();
            mp4Player.playMp4(fileName);  // Different method signature!
            
        } else if (type.equals("mp3")) {
            Mp3Player mp3Player = new Mp3Player();
            mp3Player.playMp3(fileName);  // Different method signature!
        }
        // Adding new format requires modifying this code!
    }
    
    // Different methods for different players - code duplication!
    public void playVlcFile(String fileName) {
        VlcPlayer player = new VlcPlayer();
        player.playVlc(fileName);
    }
    
    public void playMp4File(String fileName) {
        Mp4Player player = new Mp4Player();
        player.playMp4(fileName);
    }
    
    // More methods for each format...
}
```

**Problems with this approach:**
- ⚠️ **Tight Coupling**: Client knows about all specific implementations
- ⚠️ **Violates Open/Closed**: Adding formats requires modifying client code
- ⚠️ **Code Duplication**: Similar logic repeated for each format
- ⚠️ **Hard to Test**: Must test each format separately
- ⚠️ **No Polymorphism**: Can't treat different formats uniformly
- ⚠️ **Fragile**: Changes to player classes break client code
- ⚠️ **Limited Reuse**: Can't easily swap implementations

---

### ✅ **With Adapter Pattern (Loose Coupling)**

```java
// Uniform interface - clean!
public interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adapter handles the conversion
public class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;
    
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer = new Mp4Player();
        }
    }
    
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer.playMp4(fileName);
        }
    }
}

// Client code is simple and uniform!
public class AudioPlayer implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3: " + fileName);
        } else {
            // Use adapter for other formats
            MediaAdapter adapter = new MediaAdapter(audioType);
            adapter.play(audioType, fileName);
        }
    }
}

// Usage is clean and consistent
MediaPlayer player = new AudioPlayer();
player.play("mp3", "song.mp3");    // Same interface
player.play("mp4", "video.mp4");   // Same interface
player.play("vlc", "movie.vlc");   // Same interface
// Adding new format? Just extend the adapter!
```

**Benefits of this approach:**
- ✅ **Loose Coupling**: Client only knows MediaPlayer interface
- ✅ **Open/Closed**: Add formats by extending adapter
- ✅ **No Duplication**: Conversion logic in one place
- ✅ **Easy Testing**: Mock the adapter interface
- ✅ **Polymorphism**: Uniform treatment of all formats
- ✅ **Robust**: Changes to players don't affect client
- ✅ **Reusable**: Adapter can be used anywhere

---

### 📊 Side-by-Side Comparison

| Aspect | Without Adapter | With Adapter Pattern |
|--------|----------------|---------------------|
| **Client Coupling** | Tightly coupled to implementations | Coupled only to interface |
| **Adding New Types** | Modify client code everywhere | Extend adapter only |
| **Code Duplication** | High (repeated logic) | Low (centralized in adapter) |
| **Interface Uniformity** | Different methods per type | Single uniform interface |
| **Testability** | Difficult (many dependencies) | Easy (mock interface) |
| **Maintainability** | Hard (changes ripple everywhere) | Easy (changes isolated) |
| **Flexibility** | Rigid | Highly flexible |
| **Reusability** | Limited | High |

---

### 🎯 Real-World Impact

**Scenario**: You need to add support for a new audio format (WAV).

**Without Adapter Pattern:**
```java
// Must modify client code in multiple places!
public class MediaApp {
    public void playMedia(String type, String fileName) {
        if (type.equals("vlc")) {
            // existing code...
        } else if (type.equals("mp4")) {
            // existing code...
        } else if (type.equals("wav")) {  // NEW - breaks Open/Closed!
            WavPlayer wavPlayer = new WavPlayer();
            wavPlayer.playWav(fileName);  // Different signature!
        }
    }
    
    public void playWavFile(String fileName) {  // NEW method needed
        WavPlayer player = new WavPlayer();
        player.playWav(fileName);
    }
    // VIOLATES OPEN/CLOSED PRINCIPLE!
}
```

**With Adapter Pattern:**
```java
// Just extend the adapter - client code unchanged!
public class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;
    
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer = new Mp4Player();
        } else if (audioType.equalsIgnoreCase("wav")) {  // Just add here!
            advancedPlayer = new WavPlayer();
        }
    }
    
    @Override
    public void play(String audioType, String fileName) {
        // Add corresponding case
        if (audioType.equalsIgnoreCase("wav")) {
            advancedPlayer.playWav(fileName);
        }
        // Other cases...
    }
}

// Client code remains UNCHANGED!
player.play("wav", "audio.wav");  // Just works!
// FOLLOWS OPEN/CLOSED PRINCIPLE!
```

---

### 💡 Key Takeaway

> **Without Adapter** = "Client must know about every implementation and handle each differently"  
> **With Adapter** = "Client uses one interface; adapter handles all conversions transparently"

This comparison shows why the Adapter Pattern is essential when integrating incompatible interfaces while keeping client code clean and maintainable.

---

## 🛠️ Technologies Used
- Java 8+
- Object-Oriented Programming
- Structural Design Pattern
- Interface-based Design

---

## 📚 Example Explanation
In this example:
- `MediaPlayer` is the target interface that clients use
- `AdvancedMediaPlayer` is the adaptee interface (existing, incompatible)
- `VlcPlayer` and `Mp4Player` are concrete adaptees with incompatible interfaces
- `MediaAdapter` adapts AdvancedMediaPlayer to MediaPlayer interface
- `AudioPlayer` uses the adapter to play advanced formats
- Client code works uniformly with all media formats through MediaPlayer interface

---

## ✅ Advantages
- **Reuses Existing Code**: Works with existing classes without modification
- **Single Responsibility**: Adapter handles interface conversion only
- **Open/Closed Principle**: Add new adapters without changing existing code
- **Loose Coupling**: Client decoupled from specific implementations
- **Flexibility**: Easy to swap implementations
- **Testability**: Can mock adapter interface
- **Polymorphism**: Treat different implementations uniformly

---

## ❌ Disadvantages
- **Increased Complexity**: Additional layer of abstraction
- **Performance Overhead**: Extra method calls through adapter
- **Maintenance**: More classes to maintain
- **Over-engineering**: Overkill for simple scenarios
- **Indirection**: Harder to trace code flow
- **Learning Curve**: Requires understanding of pattern

---

## 🎓 Use Cases

### 1. Legacy Code Integration 🏛️
Integrate modern systems with legacy code that has incompatible interfaces without modifying the legacy code.

### 2. Third-Party Library Integration 🔌
Adapt third-party libraries to match your application's interfaces without modifying the library source.

### 3. Multiple Database Connections 💾
Create adapters for different database systems (MySQL, PostgreSQL, MongoDB) to work with a uniform interface.

### 4. Payment Gateway Integration 💳
Adapt different payment gateways (PayPal, Stripe, Square) to a common payment interface.

### 5. File Format Conversion 📄
Handle different file formats (PDF, DOCX, TXT) through a uniform document reader interface.

### 6. API Version Compatibility 🔄
Adapt older API versions to work with newer client code without breaking changes.

### 7. Device Driver Abstraction 🖨️
Create uniform interfaces for different hardware devices (printers, scanners, cameras).

---

## 🌐 Real-World Examples

### 1. Java I/O Streams 📁
```java
// InputStreamReader adapts byte streams to character streams
FileInputStream fis = new FileInputStream("file.txt");
InputStreamReader isr = new InputStreamReader(fis);  // Adapter!
BufferedReader reader = new BufferedReader(isr);

// Adapter converts InputStream (bytes) to Reader (characters)
```

### 2. Arrays.asList() Adapter 📋
```java
// Arrays.asList() adapts array to List interface
String[] array = {"A", "B", "C"};
List<String> list = Arrays.asList(array);  // Adapter!

// Array doesn't implement List, but adapter makes it work
```

### 3. Payment Gateway Integration 💳
```java
// Target interface
public interface PaymentProcessor {
    boolean processPayment(double amount);
}

// Adaptee - PayPal (existing, incompatible API)
public class PayPalAPI {
    public void makePayment(String email, double amount) {
        System.out.println("PayPal: $" + amount + " to " + email);
    }
}

// Adapter
public class PayPalAdapter implements PaymentProcessor {
    private PayPalAPI paypal = new PayPalAPI();
    private String email;
    
    public PayPalAdapter(String email) {
        this.email = email;
    }
    
    @Override
    public boolean processPayment(double amount) {
        paypal.makePayment(email, amount);
        return true;
    }
}

// Usage
PaymentProcessor processor = new PayPalAdapter("user@email.com");
processor.processPayment(99.99);  // Uniform interface!
```

### 4. Database Connection Adapter 💾
```java
// Target interface
public interface DatabaseConnection {
    void connect(String url);
    void executeQuery(String query);
    void disconnect();
}

// MongoDB Adapter
public class MongoDBAdapter implements DatabaseConnection {
    private MongoClient mongoClient;
    
    @Override
    public void connect(String url) {
        mongoClient = new MongoClient(url);
        System.out.println("Connected to MongoDB");
    }
    
    @Override
    public void executeQuery(String query) {
        // Adapt SQL-like query to MongoDB operations
        mongoClient.getDatabase("mydb").runCommand(query);
    }
    
    @Override
    public void disconnect() {
        mongoClient.close();
    }
}

// PostgreSQL Adapter
public class PostgreSQLAdapter implements DatabaseConnection {
    private Connection connection;
    
    @Override
    public void connect(String url) {
        connection = DriverManager.getConnection(url);
        System.out.println("Connected to PostgreSQL");
    }
    
    @Override
    public void executeQuery(String query) {
        Statement stmt = connection.createStatement();
        stmt.execute(query);
    }
    
    @Override
    public void disconnect() {
        connection.close();
    }
}

// Usage - same interface for different databases!
DatabaseConnection db1 = new MongoDBAdapter();
DatabaseConnection db2 = new PostgreSQLAdapter();

db1.connect("mongodb://localhost");
db2.connect("jdbc:postgresql://localhost/mydb");

db1.executeQuery("SELECT * FROM users");  // Adapted to MongoDB
db2.executeQuery("SELECT * FROM users");  // Native PostgreSQL
```

### 5. Temperature Sensor Adapter 🌡️
```java
// Target interface (Celsius)
public interface TemperatureSensor {
    double getTemperature();  // Returns Celsius
}

// Adaptee (Fahrenheit sensor)
public class FahrenheitSensor {
    public double getTemperatureF() {
        return 98.6;  // Returns Fahrenheit
    }
}

// Adapter
public class TemperatureAdapter implements TemperatureSensor {
    private FahrenheitSensor sensor;
    
    public TemperatureAdapter(FahrenheitSensor sensor) {
        this.sensor = sensor;
    }
    
    @Override
    public double getTemperature() {
        // Convert Fahrenheit to Celsius
        double fahrenheit = sensor.getTemperatureF();
        return (fahrenheit - 32) * 5.0 / 9.0;
    }
}

// Usage
FahrenheitSensor fSensor = new FahrenheitSensor();
TemperatureSensor tempSensor = new TemperatureAdapter(fSensor);

System.out.println("Temperature: " + tempSensor.getTemperature() + "°C");
// Transparently converts Fahrenheit to Celsius!
```

---

## 📊 Code Example Output

```
╔════════════════════════════════════════════════╗
║    Adapter Pattern - Media Player Demo        ║
╚════════════════════════════════════════════════╝

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📀 Testing Native MP3 Support
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🎵 Playing MP3 file: song.mp3

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔌 Testing Adapter for MP4 Format
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🎥 Playing MP4 file: video.mp4

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔌 Testing Adapter for VLC Format
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🎬 Playing VLC file: movie.vlc

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
❌ Testing Unsupported Format
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
❌ Invalid media format: avi. Format not supported.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🎬 Playing Multiple Files
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🎵 Playing MP3 file: jazz.mp3
🎥 Playing MP4 file: tutorial.mp4
🎬 Playing VLC file: documentary.vlc
🎵 Playing MP3 file: rock.mp3
🎥 Playing MP4 file: presentation.mp4

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ Demo Complete!
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📝 Key Observations:
   • AudioPlayer plays MP3 natively (no adapter needed)
   • MediaAdapter enables MP4 and VLC playback
   • Adapter translates MediaPlayer interface to AdvancedMediaPlayer
   • Client code remains unchanged when adding new formats
   • Incompatible interfaces work together seamlessly
```

---

## ⚡ Complexity Analysis

### Time Complexity

| Operation | Complexity | Description |
|-----------|------------|-------------|
| **Create Adapter** | O(1) | Instantiate adapter with adaptee |
| **Method Call** | O(1) | Adapter delegates to adaptee |
| **Interface Conversion** | O(1) | Simple method translation |

### Space Complexity

| Aspect | Complexity | Description |
|--------|------------|-------------|
| **Adapter Object** | O(1) | One adapter instance per adaptee |
| **Adaptee Reference** | O(1) | Single reference to wrapped object |
| **Total Memory** | O(1) | Constant overhead per adapter |

### Performance Considerations

**Best Case:** 
- O(1) for all operations
- Minimal overhead for interface translation

**Average Case:**
- O(1) with negligible performance impact
- One additional method call per operation

**Worst Case:**
- O(1) but with method call overhead
- May affect performance in tight loops

### Optimization Strategies

1. **Minimize Adapter Layers**: Avoid chaining multiple adapters
2. **Cache Adapters**: Reuse adapter instances when possible
3. **Inline Simple Conversions**: For performance-critical code
4. **Use Object Pools**: For frequently created/destroyed adapters

---

## 🎓 Best Practices

### Do's ✓

1. **Keep Adapter Focused**
   ```java
   // Good: Adapter does one thing - interface conversion
   public class PayPalAdapter implements PaymentProcessor {
       private PayPalAPI paypal;
       
       @Override
       public boolean processPayment(double amount) {
           paypal.makePayment(amount);
           return true;
       }
   }
   ```

2. **Use Interfaces for Flexibility**
   ```java
   // Good: Program to interfaces
   MediaPlayer player = new AudioPlayer();
   MediaAdapter adapter = new MediaAdapter("vlc");
   
   // Bad: Concrete types limit flexibility
   AudioPlayer player = new AudioPlayer();
   ```

3. **Document the Adaptation**
   ```java
   /**
    * Adapts AdvancedMediaPlayer to MediaPlayer interface.
    * Converts play(type, file) calls to specific playVlc() or playMp4() calls.
    */
   public class MediaAdapter implements MediaPlayer {
       // Implementation...
   }
   ```

4. **Handle Errors Gracefully**
   ```java
   @Override
   public void play(String audioType, String fileName) {
       try {
           if (audioType.equalsIgnoreCase("vlc")) {
               advancedPlayer.playVlc(fileName);
           }
       } catch (Exception e) {
           System.err.println("Error playing file: " + e.getMessage());
       }
   }
   ```

5. **Make Adapters Immutable When Possible**
   ```java
   public class DatabaseAdapter {
       private final DatabaseConnection connection;
       
       public DatabaseAdapter(DatabaseConnection connection) {
           this.connection = connection;
       }
       // No setters - immutable
   }
   ```

### Don'ts ✗

1. **Don't Add Business Logic to Adapters**
   ```java
   // Bad: Adapter should not contain business logic
   public class MediaAdapter implements MediaPlayer {
       @Override
       public void play(String type, String file) {
           if (isPremiumUser()) {  // Business logic!
               advancedPlayer.playVlc(file);
           }
       }
   }
   
   // Good: Keep it simple
   public class MediaAdapter implements MediaPlayer {
       @Override
       public void play(String type, String file) {
           advancedPlayer.playVlc(file);  // Just adapt!
       }
   }
   ```

2. **Don't Create Unnecessary Adapters**
   ```java
   // Bad: Adapter not needed if interfaces match
   public class UnnecessaryAdapter implements List {
       private ArrayList list;  // ArrayList already implements List!
   }
   
   // Good: Use existing interface directly
   List<String> list = new ArrayList<>();
   ```

3. **Don't Modify the Adaptee**
   ```java
   // Bad: Don't change the existing class
   public class VlcPlayer {
       public void playVlc(String file) { }
       public void play(String type, String file) { }  // Don't add this!
   }
   
   // Good: Create adapter instead
   public class VlcAdapter implements MediaPlayer {
       private VlcPlayer vlc = new VlcPlayer();
       // Adapt here
   }
   ```

4. **Don't Chain Too Many Adapters**
   ```java
   // Bad: Too many layers
   Adapter1 → Adapter2 → Adapter3 → Adaptee
   
   // Good: Single adapter
   Adapter → Adaptee
   ```

---

## 🔄 Related Patterns

### Adapter vs Bridge
- **Adapter**: Makes incompatible interfaces work together (after design)
- **Bridge**: Separates abstraction from implementation (during design)
- **Similarity**: Both use composition and delegation
- **Difference**: Adapter is remedial, Bridge is proactive

```java
// Adapter (after the fact)
MediaAdapter adapts AdvancedMediaPlayer to MediaPlayer

// Bridge (planned separation)
RemoteControl uses TV implementation through abstraction
```

### Adapter vs Decorator
- **Adapter**: Changes interface
- **Decorator**: Adds functionality, keeps same interface
- **Similarity**: Both wrap objects
- **Difference**: Purpose and interface modification

```java
// Adapter - changes interface
MediaPlayer adapter = new MediaAdapter(advancedPlayer);

// Decorator - same interface, adds behavior
InputStream decoratedStream = new BufferedInputStream(fileStream);
```

### Adapter vs Proxy
- **Adapter**: Adapts interface
- **Proxy**: Controls access to object
- **Similarity**: Both are wrappers
- **Difference**: Purpose (adaptation vs control)

```java
// Adapter - interface conversion
DatabaseAdapter adapts MongoDB to SQL interface

// Proxy - access control
ProxyImage controls access to RealImage
```

### Adapter vs Facade
- **Adapter**: Wraps single class to change interface
- **Facade**: Simplifies complex subsystem
- **Similarity**: Both provide alternative interfaces
- **Difference**: Scope (single class vs subsystem)

```java
// Adapter - single class
MediaAdapter wraps AdvancedMediaPlayer

// Facade - multiple classes
ComputerFacade simplifies CPU, Memory, HardDrive
```

---

## 📚 Further Reading

### Books
- **Design Patterns: Elements of Reusable Object-Oriented Software** by Gang of Four
  - Chapter 4: Structural Patterns - Adapter
  
- **Head First Design Patterns** by Freeman & Freeman
  - Chapter on Adapter and Facade Patterns
  
- **Patterns of Enterprise Application Architecture** by Martin Fowler
  - Gateway patterns and adapters

### Online Resources
- **Refactoring Guru**: https://refactoring.guru/design-patterns/adapter
- **Source Making**: https://sourcemaking.com/design_patterns/adapter
- **Java Design Patterns**: https://java-design-patterns.com/patterns/adapter/
- **Oracle Java Tutorials**: https://docs.oracle.com/javase/tutorial/

### Video Tutorials
- Derek Banas - Adapter Design Pattern
- Christopher Okhravi - Design Patterns series
- Programming with Mosh - Design Patterns

---

## 📝 Summary

The Adapter pattern is a powerful structural pattern that enables incompatible interfaces to work together without modifying existing code.

### Key Takeaways

✅ **When to Use:**
- Integrating incompatible interfaces
- Reusing existing classes with incompatible interfaces
- Working with third-party libraries
- Legacy code integration
- API version compatibility

✅ **Benefits:**
- Enables code reuse
- Follows Open/Closed Principle
- Decouples client from implementation
- Single Responsibility (conversion logic)
- Increases flexibility

✅ **Trade-offs:**
- Additional complexity (extra layer)
- Performance overhead (minimal)
- More classes to maintain
- Can be overused

✅ **Real-World Applications:**
- Java I/O streams (InputStreamReader)
- Collections framework (Arrays.asList)
- JDBC drivers
- Payment gateways
- File format converters
- API integrations

✅ **Best Used With:**
- Factory pattern (create adapters)
- Strategy pattern (swap implementations)
- Facade pattern (simplify complex systems)

### Final Thought

> "The Adapter pattern is like a universal power adapter for your code - it makes incompatible pieces fit together seamlessly. Use it whenever you need to integrate existing code with incompatible interfaces."

---

## 🚀 How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Any Java IDE (Eclipse, IntelliJ IDEA, VS Code) or command line

### Compilation
```bash
# Navigate to the directory containing the Java files
cd /path/to/adapter-pattern

# Compile all Java files
javac *.java

# Or compile individually
javac MediaPlayer.java
javac AdvancedMediaPlayer.java
javac VlcPlayer.java
javac Mp4Player.java
javac MediaAdapter.java
javac AudioPlayer.java
javac AdapterPatternDemo.java
```

### Execution
```bash
# Run the demo
java Main.java
```

### Expected Output
The program will demonstrate:
1. Native MP3 playback (no adapter needed)
2. Adapter-enabled MP4 playback
3. Adapter-enabled VLC playback
4. Error handling for unsupported formats
5. Playlist with mixed formats

---

## 🤝 Contributing

Contributions are welcome! If you'd like to improve this implementation or documentation:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/improvement`)
3. Commit your changes (`git commit -am 'Add improvement'`)
4. Push to the branch (`git push origin feature/improvement`)
5. Create a Pull Request

### Areas for Contribution
- Additional adapter examples
- Performance benchmarks
- More test cases
- Implementation in other languages
- Enhanced documentation

---

## ✍️ Author

**Mazen Naji**  
Software Engineer | Full Stack Developer

---

## 🙏 Acknowledgments

- Gang of Four for documenting design patterns
- The software engineering community
- All contributors to design pattern education

---

## 📞 Contact & Support

If you have questions or need help understanding the Adapter pattern:

- Open an issue on GitHub
- Visit the discussion forum

---

**Created with ❤️ for learning design patterns and building better software**

---

## 🎯 Quick Reference

### Pattern Structure
```
Client → Target (Interface) → Adapter → Adaptee
```

### Key Components
```java
Target: MediaPlayer interface
Adapter: MediaAdapter class
Adaptee: AdvancedMediaPlayer interface
```

### When to Use
✅ Incompatible interfaces  
✅ Legacy code integration  
✅ Third-party libraries  
✅ API compatibility  

### When NOT to Use
❌ Interfaces already compatible  
❌ Simple method calls  
❌ Over-engineering  
❌ Performance critical paths  

---