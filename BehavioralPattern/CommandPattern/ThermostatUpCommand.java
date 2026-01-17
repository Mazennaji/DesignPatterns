package CommandPattern;

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
