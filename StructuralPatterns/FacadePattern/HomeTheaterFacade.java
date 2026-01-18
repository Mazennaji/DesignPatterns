package StructuralPatterns.FacadePattern;

public class HomeTheaterFacade {
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private SoundSystem soundSystem;
    private Lights lights;
    
    public HomeTheaterFacade(DVDPlayer dvdPlayer, 
                            Projector projector, 
                            SoundSystem soundSystem, 
                            Lights lights) {
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.soundSystem = soundSystem;
        this.lights = lights;
    }

    public void watchMovie(String movie) {
        System.out.println("\n Get ready to watch a movie...\n");
        
        lights.dim(10);
        projector.on();
        projector.wideScreenMode();
        projector.focus();
        soundSystem.on();
        soundSystem.setVolume(15);
        soundSystem.setSurroundSound();
        dvdPlayer.on();
        dvdPlayer.play(movie);
        
        System.out.println("\n Movie setup complete! Enjoy your movie!\n");
    }
    
    public void endMovie() {
        System.out.println("\n Shutting down home theater...\n");
        
        dvdPlayer.stop();
        dvdPlayer.eject();
        dvdPlayer.off();
        soundSystem.off();
        projector.off();
        lights.on();
        
        System.out.println("\n Movie ended. Home theater shut down complete!\n");
    }
    
    public void listenToMusic() {
        System.out.println("\n Setting up for music...\n");
        
        lights.dim(30);
        soundSystem.on();
        soundSystem.setStereo();
        soundSystem.setVolume(10);
        
        System.out.println("\n Music setup complete! Enjoy!\n");
    }
    
    public void endMusic() {
        System.out.println("\n Ending music session...\n");
        
        soundSystem.off();
        lights.on();
        
        System.out.println("\n Music ended!\n");
    }
    
    public void allOff() {
        System.out.println("\n Shutting down all systems...\n");
        
        dvdPlayer.off();
        projector.off();
        soundSystem.off();
        lights.off();
        
        System.out.println("\n All systems powered OFF!\n");
    }
}
