package CommandPattern;

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
        System.out.println(location + " TV is ON");
    }
    
    public void off() {
        isOn = false;
        System.out.println(location + " TV is OFF");
    }
    
    public void volumeUp() {
        if (isOn) {
            volume++;
            System.out.println(location + " TV volume: " + volume);
        } else {
            System.out.println(location + " TV is off. Turn it on first.");
        }
    }
    
    public void volumeDown() {
        if (isOn && volume > 0) {
            volume--;
            System.out.println(location + " TV volume: " + volume);
        } else if (!isOn) {
            System.out.println(location + " TV is off. Turn it on first.");
        }
    }
    
    public int getVolume() {
        return volume;
    }
}
