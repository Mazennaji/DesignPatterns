package CommandPattern;

public class ThermostatDownCommand implements Command {
    private Thermostat thermostat;
    
    public ThermostatDownCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }
    
    @Override
    public void execute() {
        thermostat.decreaseTemperature();
    }
    
    @Override
    public void undo() {
        thermostat.increaseTemperature();
    }
}
