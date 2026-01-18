package StructuralPatterns.FacadePattern;

public class DVDPlayer {
    private String currentMovie;
    
    public void on() {
        System.out.println("DVD Player: Powering ON");
    }
    
    public void play(String movie) {
        this.currentMovie = movie;
        System.out.println("DVD Player: Playing \"" + movie + "\"");
    }
    
    public void stop() {
        System.out.println("DVD Player: Stopping \"" + currentMovie + "\"");
        this.currentMovie = null;
    }
    
    public void eject() {
        System.out.println("DVD Player: Ejecting disc");
    }
    
    public void off() {
        System.out.println("DVD Player: Powering OFF");
    }
}
