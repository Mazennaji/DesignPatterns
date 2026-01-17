package StructuralPatterns.AdapterPattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║    Adapter Pattern - Media Player Demo         ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        AudioPlayer audioPlayer = new AudioPlayer();
        
        System.out.println("---------------------------------------------------");
        System.out.println(" Testing Native MP3 Support");
        System.out.println("---------------------------------------------------");
        audioPlayer.play("mp3", "song.mp3");
        
        System.out.println("\n---------------------------------------------------");
        System.out.println(" Testing Adapter for MP4 Format");
        System.out.println("---------------------------------------------------");
        audioPlayer.play("mp4", "video.mp4");
        
        System.out.println("\n---------------------------------------------------");
        System.out.println(" Testing Adapter for VLC Format");
        System.out.println("---------------------------------------------------");
        audioPlayer.play("vlc", "movie.vlc");
        
        System.out.println("\n---------------------------------------------------");
        System.out.println(" Testing Unsupported Format");
        System.out.println("---------------------------------------------------");
        audioPlayer.play("avi", "clip.avi");
        
        System.out.println("\n---------------------------------------------------");
        System.out.println(" Playing Multiple Files");
        System.out.println("---------------------------------------------------");
        
        String[] playlist = {
            "mp3:jazz.mp3",
            "mp4:tutorial.mp4",
            "vlc:documentary.vlc",
            "mp3:rock.mp3",
            "mp4:presentation.mp4"
        };
        
        for (String track : playlist) {
            String[] parts = track.split(":");
            String format = parts[0];
            String filename = parts[1];
            audioPlayer.play(format, filename);
        }
        
        System.out.println("\n---------------------------------------------------");
        System.out.println(" Demo Complete!");
        System.out.println("---------------------------------------------------");
        
        System.out.println("\n Key Observations:");
        System.out.println("    AudioPlayer plays MP3 natively (no adapter needed)");
        System.out.println("    MediaAdapter enables MP4 and VLC playback");
        System.out.println("    Adapter translates MediaPlayer interface to AdvancedMediaPlayer");
        System.out.println("    Client code remains unchanged when adding new formats");
        System.out.println("    Incompatible interfaces work together seamlessly");
    }
}
