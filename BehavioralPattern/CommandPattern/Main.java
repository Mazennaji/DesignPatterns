package CommandPattern;

public class Main {
    public static void main(String[] args) {
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        TV livingRoomTV = new TV("Living Room");
        Thermostat thermostat = new Thermostat();

        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);

        TVOnCommand tvOn = new TVOnCommand(livingRoomTV);
        TVOffCommand tvOff = new TVOffCommand(livingRoomTV);

        TVVolumeUpCommand tvVolumeUp = new TVVolumeUpCommand(livingRoomTV);

        ThermostatUpCommand thermostatUp = new ThermostatUpCommand(thermostat);
        ThermostatDownCommand thermostatDown = new ThermostatDownCommand(thermostat);

        RemoteControl remote = new RemoteControl(4);

        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remote.setCommand(1, kitchenLightOn, kitchenLightOff);
        remote.setCommand(2, tvOn, tvOff);
        remote.setCommand(3, thermostatUp, thermostatDown);

        System.out.println(remote);

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

        System.out.println("\n--- Undo last command (temperature) ---");
        remote.undoButtonPressed();

        System.out.println("\n--- Undo again (temperature) ---");
        remote.undoButtonPressed();

        System.out.println("\n--- Undo again (TV) ---");
        remote.undoButtonPressed();

        System.out.println("\n--- Turning off living room light ---");
        remote.offButtonPressed(0);

        System.out.println("\n--- Undo (turn light back on) ---");
        remote.undoButtonPressed();

        System.out.println("\n=== Smart Home Demo Complete ===");
    }
}
