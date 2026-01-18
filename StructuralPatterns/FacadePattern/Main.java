package StructuralPatterns.FacadePattern;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║     FACADE DESIGN PATTERN - Home Theater System        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
        
        DVDPlayer dvdPlayer = new DVDPlayer();
        Projector projector = new Projector();
        SoundSystem soundSystem = new SoundSystem();
        Lights lights = new Lights();
        
        System.out.println("-------------------------------------------------------------");
        System.out.println("  SCENARIO 1: WITHOUT FACADE (Complex Way) ");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Client must manually manage all subsystem interactions:\n");
        
        System.out.println(">>> Starting movie the hard way...");
        lights.dim(10);
        projector.on();
        projector.wideScreenMode();
        projector.focus();
        soundSystem.on();
        soundSystem.setVolume(15);
        soundSystem.setSurroundSound();
        dvdPlayer.on();
        dvdPlayer.play("The Matrix");
        System.out.println(">>> [Many steps required! Complex and error-prone]\n");
        
        System.out.println(">>> Watching movie...\n");
        
        System.out.println(">>> Ending movie the hard way...");
        dvdPlayer.stop();
        dvdPlayer.eject();
        dvdPlayer.off();
        soundSystem.off();
        projector.off();
        lights.on();
        System.out.println(">>> [Again, many manual steps!]\n");
        

        printSeparator();
        
        System.out.println("-------------------------------------------------------------");
        System.out.println("  SCENARIO 2: WITH FACADE (Simple Way) ");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Client uses simple facade methods:\n");
        
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(
            dvdPlayer, 
            projector, 
            soundSystem, 
            lights
        );
        
        homeTheater.watchMovie("Inception");
        
        System.out.println(">>> Watching movie...\n");
        
        homeTheater.endMovie();
        
        printSeparator();
        
        System.out.println("-------------------------------------------------------------");
        System.out.println("  SCENARIO 3: Music Mode ");
        System.out.println("-------------------------------------------------------------\n");
        
        homeTheater.listenToMusic();
        System.out.println(">>> Listening to music...\n");
        homeTheater.endMusic();
        
        printSeparator();
        
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                   KEY BENEFITS                           ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║   Simplified Interface                                   ║");
        System.out.println("║   Reduced Complexity for Client                          ║");
        System.out.println("║   Loose Coupling                                         ║");
        System.out.println("║   Easy to Use and Maintain                               ║");
        System.out.println("║   Hides Subsystem Complexity                             ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        System.out.println("Comparison:");
        System.out.println("  Without Facade: ~8-10 lines of code per operation");
        System.out.println("  With Facade:    1 line of code per operation");
        System.out.println("\n  That's an 80-90% reduction in client code complexity!\n");
    }
    
    private static void printSeparator() {
        System.out.println("---------------------------------------------------------------\n");
    }
}
