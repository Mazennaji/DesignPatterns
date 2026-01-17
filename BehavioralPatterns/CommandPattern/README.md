# 🏠 Command Design Pattern (Java) - Smart Home Automation System

## 📌 Overview

The **Command Pattern** is a **behavioral design pattern** that turns a request into a stand-alone object containing all information about the request. This transformation allows you to parameterize methods with different requests, delay or queue a request's execution, and support undoable operations.

This example demonstrates a **Smart Home Automation System** where various devices (lights, TV, thermostat) can be controlled through a universal remote control, with full support for undo functionality.

---

## 🎯 Intent

> Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.

---

## 🧠 Key Idea

> "Don't call methods directly—wrap them in objects so you can store, pass, and undo them."

---

## ❓ Why Use the Command Pattern?

* Decouples the object that invokes the operation from the one that performs it
* Allows parameterization of objects with operations
* Supports queuing of requests and logging
* Enables undo/redo functionality
* Supports macro commands (composite commands)
* Makes it easy to add new commands without changing existing code

---

## ⏱️ When to Use

Use the Command Pattern when you want to parameterize objects with operations, queue operations, schedule operations for later execution, or support undo/redo functionality.

---

## 🧩 Pattern Participants

| Component | Role |
|-----------|------|
| **Command** | Interface declaring execution method |
| **Concrete Commands** | Implement command; invoke receiver methods |
| **Receiver** | Knows how to perform the work |
| **Invoker** | Asks command to execute the request |
| **Client** | Creates commands and sets their receivers |

---

## 📐 UML Class Diagram (Command Pattern)

```text
+--------------------+
| <<interface>>      |
|     Command        |
+--------------------+
| +execute()         |
| +undo()            |
+--------------------+
        ▲
        |
        | implements
        |
   +----|----+----+----+----+----+
   |    |    |    |    |    |    |
+-------+ +-------+ +-------+ +-------+ +-------+ +-------+
|Light  | |Light  | |TVOn   | |TVOff  | |Thermo | |Thermo |
|On     | |Off    | |Command| |Command| |statUp | |statDn |
|Command| |Command| +-------+ +-------+ |Command| |Command|
+-------+ +-------+ |       | |       | +-------+ +-------+
|-light | |-light | |-tv    | |-tv    | |-thermo| |-thermo|
+-------+ +-------+ +-------+ +-------+ +-------+ +-------+
|+exec()| |+exec()| |+exec()| |+exec()| |+exec()| |+exec()|
|+undo()| |+undo()| |+undo()| |+undo()| |+undo()| |+undo()|
+-------+ +-------+ +-------+ +-------+ +-------+ +-------+
    |         |         |         |         |         |
    |         |         |         |         |         |
    | uses    | uses    | uses    | uses    | uses    | uses
    ▼         ▼         ▼         ▼         ▼         ▼
+-------+ +-------+ +-------+ +-------+ +---------------+
| Light | | Light | |  TV   | |  TV   | | Thermostat    |
|(Recv) | |(Recv) | |(Recv) | |(Recv) | | (Receiver)    |
+-------+ +-------+ +-------+ +-------+ +---------------+
|+on()  | |+on()  | |+on()  | |+on()  | |+increaseTemp()|
|+off() | |+off() | |+off() | |+off() | |+decreaseTemp()|
+-------+ +-------+ |+volUp()| |+volUp()| +---------------+
                    +-------+ +-------+

+---------------------------+
|     RemoteControl         |
|       (Invoker)           |
+---------------------------+
| -onCommands: Command[]    |
| -offCommands: Command[]   |
| -history: Stack<Command>  |
+---------------------------+
| +setCommand(slot, on, off)|
| +onButtonPressed(slot)    |
| +offButtonPressed(slot)   |
| +undoButtonPressed()      |
+---------------------------+
            |
            | stores and invokes
            ▼
        [Commands]

+--------------------+
|       Main         |
|     (Client)       |
+--------------------+
| +main(String[])    |
+--------------------+
```

---

## 🧠 UML Diagram Explanation

The UML diagram illustrates the structure and relationships of the **Command Design Pattern** implementation.

### 1️⃣ Command Interface
- Defines the contract for all command objects
- Declares two methods:
  - `execute()` - performs the operation
  - `undo()` - reverses the operation
- Ensures all commands can be used interchangeably
- Enables polymorphic command handling

---

### 2️⃣ Concrete Commands
- Implement the `Command` interface
- Each command encapsulates a specific request
- Stores a reference to a **receiver** (the device)
- **execute()** calls appropriate receiver methods
- **undo()** reverses the operation by calling opposite methods
- Examples:
  - `LightOnCommand` → calls `light.on()`, undoes with `light.off()`
  - `TVVolumeUpCommand` → calls `tv.volumeUp()`, undoes with `tv.volumeDown()`
  - `ThermostatUpCommand` → calls `thermostat.increaseTemperature()`

---

### 3️⃣ Receivers (Light, TV, Thermostat)
- The actual objects that perform the work
- Contain the business logic for operations
- Know how to carry out the request
- **Don't know** about commands or the invoker
- Examples:
  - `Light` → has `on()` and `off()` methods
  - `TV` → has `on()`, `off()`, `volumeUp()`, `volumeDown()` methods
  - `Thermostat` → has `increaseTemperature()`, `decreaseTemperature()` methods

---

### 4️⃣ Invoker (RemoteControl)
- Stores command objects
- Triggers command execution by calling `execute()`
- **Doesn't know** what the command does or which receiver it uses
- Maintains command history for undo functionality
- Can be configured with different commands at runtime
- Uses arrays to store on/off commands for multiple slots
- Uses a stack to track command history for undo

---

### 5️⃣ Client (Main)
- Creates receiver objects (devices)
- Creates command objects and associates them with receivers
- Configures the invoker with commands
- Decides which commands go into which remote slots
- Triggers operations through the invoker

---

## 🔗 Relationships Summary

- **Concrete Commands** implement `Command` interface
- **Concrete Commands** hold references to **Receivers**
- **Invoker** stores and executes **Commands**
- **Client** creates **Receivers**, **Commands**, and **Invoker**
- **Client** configures **Invoker** with **Commands**
- **Commands** delegate work to **Receivers**
- **Invoker** doesn't know about **Receivers** (decoupled)

---

## ✅ Key Design Benefits

- **Decoupling**: Invoker and receiver are completely decoupled
- **Single Responsibility**: Each command handles one operation
- **Open/Closed**: Easy to add new commands without modifying existing code
- **Undo/Redo**: Built-in support for reversible operations
- **Macro Commands**: Can combine multiple commands into one
- **Command History**: Can log, queue, or schedule commands

---

## 🔄 Command Pattern vs. Direct Method Calls

### ❌ **Without Command Pattern (Direct Coupling)**

```java
// Remote control with hardcoded device references
public class RemoteControl {
    private Light livingRoomLight;
    private Light kitchenLight;
    private TV tv;
    private Thermostat thermostat;
    
    // Tight coupling - remote knows about all devices!
    public RemoteControl() {
        this.livingRoomLight = new Light("Living Room");
        this.kitchenLight = new Light("Kitchen");
        this.tv = new TV("Living Room");
        this.thermostat = new Thermostat();
    }
    
    // Messy methods for each button
    public void button1Pressed() {
        livingRoomLight.on(); // Hardcoded behavior!
    }
    
    public void button2Pressed() {
        kitchenLight.on(); // Can't change at runtime!
    }
    
    public void button3Pressed() {
        tv.on(); // No flexibility!
    }
    
    public void button4Pressed() {
        thermostat.increaseTemperature();
    }
    
    public void button1Off() {
        livingRoomLight.off();
    }
    
    public void button2Off() {
        kitchenLight.off();
    }
    
    public void button3Off() {
        tv.off();
    }
    
    public void button4Off() {
        thermostat.decreaseTemperature();
    }
    
    // Undo is a NIGHTMARE!
    private String lastAction = "";
    
    public void undo() {
        // Horrible conditional logic to track what to undo
        if (lastAction.equals("button1Pressed")) {
            livingRoomLight.off();
        } else if (lastAction.equals("button2Pressed")) {
            kitchenLight.off();
        } else if (lastAction.equals("button3Pressed")) {
            tv.off();
        } else if (lastAction.equals("button4Pressed")) {
            thermostat.decreaseTemperature();
        } else if (lastAction.equals("button1Off")) {
            livingRoomLight.on();
        } else if (lastAction.equals("button2Off")) {
            kitchenLight.on();
        } else if (lastAction.equals("button3Off")) {
            tv.on();
        } else if (lastAction.equals("button4Off")) {
            thermostat.increaseTemperature();
        }
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();
        
        // No flexibility - buttons are hardcoded
        remote.button1Pressed(); // Always controls living room light
        remote.button2Pressed(); // Always controls kitchen light
        
        // Can't reassign buttons!
        // Can't add new devices easily!
        // Undo logic is a mess!
    }
}
```

**Problems with this approach:**
- ⚠️ **Tight Coupling**: Remote control knows about all specific devices
- ⚠️ **No Flexibility**: Button assignments are hardcoded
- ⚠️ **No Parameterization**: Can't pass operations around
- ⚠️ **Horrible Undo**: Requires tracking string names and complex conditionals
- ⚠️ **Hard to Extend**: Adding new devices requires modifying RemoteControl
- ⚠️ **No Queueing**: Can't store commands for later execution
- ⚠️ **No Macros**: Can't combine multiple operations

---

### ✅ **With Command Pattern (Clean Decoupling)**

```java
// Command interface
public interface Command {
    void execute();
    void undo();
}

// Concrete command - encapsulates request
public class LightOnCommand implements Command {
    private Light light;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    public void execute() {
        light.on();
    }
    
    public void undo() {
        light.off(); // Undo is built-in!
    }
}

// Invoker - knows nothing about devices!
public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Stack<Command> history;
    
    public RemoteControl(int slots) {
        onCommands = new Command[slots];
        offCommands = new Command[slots];
        history = new Stack<>();
    }
    
    // Configurable at runtime!
    public void setCommand(int slot, Command onCmd, Command offCmd) {
        onCommands[slot] = onCmd;
        offCommands[slot] = offCmd;
    }
    
    // Clean execution
    public void onButtonPressed(int slot) {
        onCommands[slot].execute();
        history.push(onCommands[slot]);
    }
    
    // Simple undo!
    public void undoButtonPressed() {
        if (!history.isEmpty()) {
            Command cmd = history.pop();
            cmd.undo(); // Polymorphic undo!
        }
    }
}

// Client code - full flexibility!
public class Main {
    public static void main(String[] args) {
        // Create devices
        Light light = new Light("Living Room");
        TV tv = new TV("Living Room");
        
        // Create commands
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        Command tvOn = new TVOnCommand(tv);
        
        // Configure remote
        RemoteControl remote = new RemoteControl(4);
        remote.setCommand(0, lightOn, lightOff);
        remote.setCommand(1, tvOn, new TVOffCommand(tv));
        
        // Use it!
        remote.onButtonPressed(0);  // Light on
        remote.undoButtonPressed(); // Light off - easy!
        
        // Can easily reassign buttons!
        remote.setCommand(0, tvOn, new TVOffCommand(tv));
    }
}
```

**Benefits of this approach:**
- ✅ **Decoupled**: Remote doesn't know about specific devices
- ✅ **Flexible**: Buttons can be reassigned at runtime
- ✅ **Parameterizable**: Commands are objects that can be passed around
- ✅ **Clean Undo**: Each command knows how to undo itself
- ✅ **Easy to Extend**: Add new commands without touching RemoteControl
- ✅ **Queueable**: Commands can be stored in queues or logs
- ✅ **Composable**: Can create macro commands combining multiple operations

---

### 📊 Side-by-Side Comparison

| Aspect | Direct Method Calls | Command Pattern |
|--------|-------------------|-----------------|
| **Coupling** | Tight (invoker knows receivers) | Loose (invoker only knows Command) |
| **Flexibility** | Hardcoded behavior | Runtime configuration |
| **Undo Support** | Complex conditionals | Built into each command |
| **Extensibility** | Modify invoker for new devices | Just add new command classes |
| **Testability** | Hard (must test with real devices) | Easy (can mock commands) |
| **Queueing** | Not possible | Easy (commands are objects) |
| **Macro Commands** | Very difficult | Simple composition |

---

### 🎯 Real-World Impact

**Scenario**: You want to add a "Movie Mode" button that dims lights, turns on TV, and adjusts volume.

**Without Command Pattern:**
```java
// Must add a new method to RemoteControl
public void movieModePressed() {
    // Hardcoded sequence - can't reuse or modify easily
    livingRoomLight.off();
    kitchenLight.off();
    tv.on();
    // Need to call volumeUp multiple times? Ugly!
    tv.volumeUp();
    tv.volumeUp();
    tv.volumeUp();
}

// Undo movieMode? Good luck with that!
```

**With Command Pattern:**
```java
// Create a MacroCommand!
public class MacroCommand implements Command {
    private Command[] commands;
    
    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }
    
    public void execute() {
        for (Command cmd : commands) {
            cmd.execute();
        }
    }
    
    public void undo() {
        // Undo in reverse order
        for (int i = commands.length - 1; i >= 0; i--) {
            commands[i].undo();
        }
    }
}

// Use it:
Command[] movieMode = {
    new LightOffCommand(livingRoomLight),
    new LightOffCommand(kitchenLight),
    new TVOnCommand(tv),
    new TVVolumeUpCommand(tv),
    new TVVolumeUpCommand(tv),
    new TVVolumeUpCommand(tv)
};

MacroCommand movieModeCmd = new MacroCommand(movieMode);
remote.setCommand(5, movieModeCmd, movieModeCmd);

// Now undo reverses ALL operations!
```

---

### 💡 Key Takeaway

> **Direct Method Calls** = "Invoker knows everything about everyone and can't change its mind"  
> **Command Pattern** = "Invoker knows nothing except how to execute commands—everything else is flexible"

This comparison shows why the Command Pattern is essential—it provides flexibility, decoupling, and functionality that direct method calls cannot match.

---

## 📝 Complete Implementation

### 1️⃣ Command Interface

```java
/**
 * Command Interface - declares methods for executing and undoing commands.
 */
public interface Command {
    /**
     * Execute the command
     */
    void execute();
    
    /**
     * Undo the command (reverse the operation)
     */
    void undo();
}
```

---

### 2️⃣ Receivers

#### Light Receiver

```java
/**
 * Receiver - Light device that can be controlled.
 */
public class Light {
    private String location;
    private boolean isOn;
    
    public Light(String location) {
        this.location = location;
        this.isOn = false;
    }
    
    public void on() {
        isOn = true;
        System.out.println(location + " light is ON 💡");
    }
    
    public void off() {
        isOn = false;
        System.out.println(location + " light is OFF");
    }
    
    public boolean isOn() {
        return isOn;
    }
}
```

#### TV Receiver

```java
/**
 * Receiver - TV device that can be controlled.
 */
public class TV {
    private String location;
    private boolean isOn;
    private int volume;
    
    public TV(String location) {
        this.location = location;
        this.isOn = false;
        this.volume = 10;
    }
    
    public void on() {
        isOn = true;
        System.out.println(location + " TV is ON 📺");
    }
    
    public void off() {
        isOn = false;
        System.out.println(location + " TV is OFF");
    }
    
    public void volumeUp() {
        if (isOn) {
            volume++;
            System.out.println(location + " TV volume: " + volume);
        }
    }
    
    public void volumeDown() {
        if (isOn && volume > 0) {
            volume--;
            System.out.println(location + " TV volume: " + volume);
        }
    }
    
    public int getVolume() {
        return volume;
    }
}
```

#### Thermostat Receiver

```java
/**
 * Receiver - Thermostat device that can be controlled.
 */
public class Thermostat {
    private int temperature;
    
    public Thermostat() {
        this.temperature = 72; // Default temperature in Fahrenheit
    }
    
    public void increaseTemperature() {
        temperature++;
        System.out.println("Temperature increased to " + temperature + "°F 🌡️");
    }
    
    public void decreaseTemperature() {
        temperature--;
        System.out.println("Temperature decreased to " + temperature + "°F ❄️");
    }
    
    public int getTemperature() {
        return temperature;
    }
}
```

---

### 3️⃣ Concrete Commands

#### Light Commands

```java
/**
 * Concrete Command - Turns a light on.
 */
public class LightOnCommand implements Command {
    private Light light;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.on();
    }
    
    @Override
    public void undo() {
        light.off();
    }
}

/**
 * Concrete Command - Turns a light off.
 */
public class LightOffCommand implements Command {
    private Light light;
    
    public LightOffCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.off();
    }
    
    @Override
    public void undo() {
        light.on();
    }
}
```

#### TV Commands

```java
/**
 * Concrete Command - Turns a TV on.
 */
public class TVOnCommand implements Command {
    private TV tv;
    
    public TVOnCommand(TV tv) {
        this.tv = tv;
    }
    
    @Override
    public void execute() {
        tv.on();
    }
    
    @Override
    public void undo() {
        tv.off();
    }
}

/**
 * Concrete Command - Increases TV volume.
 */
public class TVVolumeUpCommand implements Command {
    private TV tv;
    
    public TVVolumeUpCommand(TV tv) {
        this.tv = tv;
    }
    
    @Override
    public void execute() {
        tv.volumeUp();
    }
    
    @Override
    public void undo() {
        tv.volumeDown();
    }
}
```

#### Thermostat Commands

```java
/**
 * Concrete Command - Increases thermostat temperature.
 */
public class ThermostatUpCommand implements Command {
    private Thermostat thermostat;
    
    public ThermostatUpCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }
    
    @Override
    public void execute() {
        thermostat.increaseTemperature();
    }
    
    @Override
    public void undo() {
        thermostat.decreaseTemperature();
    }
}
```

---

### 4️⃣ Invoker - Remote Control

```java
import java.util.Stack;

/**
 * Invoker - Remote Control that stores and executes commands.
 * Supports undo functionality by maintaining command history.
 */
public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Stack<Command> commandHistory;
    
    public RemoteControl(int slots) {
        onCommands = new Command[slots];
        offCommands = new Command[slots];
        commandHistory = new Stack<>();
        
        // Initialize with NoCommand (Null Object Pattern)
        Command noCommand = new NoCommand();
        for (int i = 0; i < slots; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }
    
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }
    
    public void onButtonPressed(int slot) {
        onCommands[slot].execute();
        commandHistory.push(onCommands[slot]);
    }
    
    public void offButtonPressed(int slot) {
        offCommands[slot].execute();
        commandHistory.push(offCommands[slot]);
    }
    
    public void undoButtonPressed() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.pop();
            command.undo();
            System.out.println("⏮️  Undo executed");
        } else {
            System.out.println("⚠️  No command to undo");
        }
    }
}
```

---

### 5️⃣ Client Code - Main

```java
/**
 * Client code demonstrating the Command Pattern.
 * Shows how commands encapsulate requests and support undo functionality.
 */
public class Main {
    public static void main(String[] args) {
        // Create receivers (devices)
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        TV livingRoomTV = new TV("Living Room");
        Thermostat thermostat = new Thermostat();
        
        // Create commands
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        
        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);
        
        TVOnCommand tvOn = new TVOnCommand(livingRoomTV);
        TVOffCommand tvOff = new TVOffCommand(livingRoomTV);
        
        ThermostatUpCommand thermostatUp = new ThermostatUpCommand(thermostat);
        ThermostatDownCommand thermostatDown = new ThermostatDownCommand(thermostat);
        
        // Create invoker (remote control with 4 slots)
        RemoteControl remote = new RemoteControl(4);
        
        // Assign commands to remote control slots
        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remote.setCommand(1, kitchenLightOn, kitchenLightOff);
        remote.setCommand(2, tvOn, tvOff);
        remote.setCommand(3, thermostatUp, thermostatDown);
        
        // Test the remote control
        System.out.println("=== Testing Smart Home Automation ===\n");

        System.out.println("\n--- Increasing TV volume ---");
        tvVolumeUp.execute();
        
        System.out.println("--- Turning on living room light ---");
        remote.onButtonPressed(0);
        
        System.out.println("\n--- Turning on kitchen light ---");
        remote.onButtonPressed(1);
        
        System.out.println("\n--- Turning on TV ---");
        remote.onButtonPressed(2);
        
        System.out.println("\n--- Increasing temperature ---");
        remote.onButtonPressed(3);
        remote.onButtonPressed(3);
        
        System.out.println("\n--- Undo last command ---");
        remote.undoButtonPressed();
        
        System.out.println("\n--- Undo again ---");
        remote.undoButtonPressed();
    }
}
```

---

## 🔄 Step-by-Step Flow Explanation

### **Setting Up the Remote Control**

```
Step 1: Create receiver objects
→ Light("Living Room"), TV("Living Room"), Thermostat()

Step 2: Create command objects
→ LightOnCommand(livingRoomLight)
→ LightOffCommand(livingRoomLight)
→ TVOnCommand(tv)
→ ThermostatUpCommand(thermostat)

Step 3: Create invoker
→ RemoteControl(4 slots)

Step 4: Configure remote with commands
→ Slot 0: LightOnCommand, LightOffCommand
→ Slot 1: KitchenLightOn, KitchenLightOff
→ Slot 2: TVOnCommand, TVOffCommand
→ Slot 3: ThermostatUpCommand, ThermostatDownCommand
```

---

### **Executing Commands**

```
Action: remote.onButtonPressed(0)
↓
Step 1: Remote retrieves onCommands[0]
        → LightOnCommand instance
↓
Step 2: Calls execute() on LightOnCommand
        → LightOnCommand.execute()
↓
Step 3: LightOnCommand delegates to receiver
        → light.on()
↓
Step 4: Receiver performs actual work
        → Light sets isOn = true
        → Prints "Living Room light is ON 💡"
↓
Step 5: Remote pushes command to history stack
        → Stack: [LightOnCommand]
```

---

### **Undo Functionality**

```
Action: remote.undoButtonPressed()
↓
Step 1: Remote checks if history is not empty
        → Stack has commands
↓
Step 2: Pop last command from stack
        → command = LightOnCommand
↓
Step 3: Call undo() on popped command
        → LightOnCommand.undo()
↓
Step 4: Command delegates to receiver
        → light.off()
↓
Step 5: Receiver performs reverse operation
        → Light sets isOn = false
        → Prints "Living Room light is OFF"
↓
Result: Operation successfully undone!
```

---

## ✅ Output

```
=== Testing Smart Home Automation ===

--- Increasing TV volume ---
Living Room TV is off. Turn it on first.

--- Turning on living room light ---
Living Room light is ON 💡

--- Turning on kitchen light ---
Kitchen light is ON 💡

--- Turning on TV ---
Living Room TV is ON 📺

--- Increasing temperature ---
Temperature increased to 73°F 🌡️
Temperature increased to 74°F 🌡️

--- Undo last command (temperature) ---
Temperature decreased to 73°F ❄️
⏮️  Undo executed

--- Undo again (temperature) ---
Temperature decreased to 72°F ❄️
⏮️  Undo executed

--- Undo again (TV) ---
Living Room TV is OFF
⏮️  Undo executed

--- Turning off living room light ---
Living Room light is OFF

--- Undo (turn light back on) ---
Living Room light is ON 💡
⏮️  Undo executed

=== Smart Home Demo Complete ===
```

---

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming
- Behavioral Design Pattern
- Stack Data Structure (for command history)
- Null Object Pattern (NoCommand)

---

## 📚 Example Explanation

In this example:
- **Command** is the interface defining `execute()` and `undo()` methods
- **Concrete Commands** (LightOnCommand, TVOnCommand, etc.) encapsulate requests
- **Receivers** (Light, TV, Thermostat) perform the actual work
- **Invoker** (RemoteControl) stores and executes commands without knowing details
- **Client** (Main) creates and configures all components
- Commands can be undone by maintaining a history stack
- The remote control is completely decoupled from the devices

---

## ✅ Advantages

- **Decouples** invoker from receiver
- Supports **undo/redo** operations
- Enables **command queuing** and logging
- Allows **macro commands** (composite operations)
- Easy to add new commands (Open/Closed Principle)
- Commands are **first-class objects** (can be stored, passed, manipulated)
- Supports **delayed execution** and **scheduling**

---

## ❌ Disadvantages

- Increases number of classes (one per command)
- Can add complexity for simple operations
- May require careful design for complex undo scenarios
- Command history can consume memory

---

## 🎓 Use Cases

- **GUI Applications**: Button clicks, menu actions
- **Text Editors**: Undo/redo functionality
- **Database Transactions**: Rollback support
- **Game Development**: Action replay, save/load states
- **Schedulers**: Job scheduling and execution
- **Networking**: Request queuing and replay
- **Macro Recording**: Recording and replaying user actions
- **Smart Home Systems**: Device control with undo support
- **Multi-level Undo**: Applications like Photoshop, IDEs

---

## 🏁 Conclusion

The **Command Pattern** provides a powerful way to decouple senders and receivers of requests while enabling sophisticated features like undo/redo, queuing, and logging. In this example, a **Smart Home Automation System** uses commands to control various devices through a universal remote control.

By encapsulating requests as objects, the pattern makes it possible to parameterize objects with operations, queue requests, log them, and support undoable operations. The remote control doesn't need to know anything about the devices it controls—it only knows how to execute commands.

The Command Pattern demonstrates that sometimes the best way to do something isn't to just do it—it's to wrap it in an object that knows both how to do it and how to undo it.

---

## ✍️ Author

**Mazen Naji**  
Software Engineer | Full Stack Developer

---

## 📄 License

Educational use only