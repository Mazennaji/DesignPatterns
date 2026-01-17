package CommandPattern;
public class TVVolumeUpCommand implements Command {
    private TV tv;
    private int previousVolume;
    
    public TVVolumeUpCommand(TV tv) {
        this.tv = tv;
    }
    
    @Override
    public void execute() {
        previousVolume = tv.getVolume();
        tv.volumeUp();
    }
    
    @Override
    public void undo() {
        tv.volumeDown();
    }
}