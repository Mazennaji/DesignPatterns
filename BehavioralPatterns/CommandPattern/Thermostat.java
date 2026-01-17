package CommandPattern;

public class Thermostat {
    private int temperature;
    
    public Thermostat() {
        this.temperature = 72;
    }
    
    public void increaseTemperature() {
        temperature++;
        System.out.println("Temperature increased to " + temperature + "°F");
    }
    
    public void decreaseTemperature() {
        temperature--;
        System.out.println("Temperature decreased to " + temperature + "°F");
    }
    
    public int getTemperature() {
        return temperature;
    }
}
