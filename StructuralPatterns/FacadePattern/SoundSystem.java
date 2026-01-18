package StructuralPatterns.FacadePattern;

public class SoundSystem {
    private int volume;
    
    public void on() {
        System.out.println("Sound System: Powering ON");
    }
    
    public void setVolume(int level) {
        this.volume = level;
        System.out.println("Sound System: Setting volume to " + level);
    }
    
    public void setSurroundSound() {
        System.out.println("Sound System: Enabling Surround Sound mode");
    }
    
    public void setStereo() {
        System.out.println("Sound System: Setting to Stereo mode");
    }
    
    public void mute() {
        System.out.println("Sound System: Muted");
    }
    
    public void off() {
        System.out.println("Sound System: Powering OFF");
    }
}