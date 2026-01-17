package StructuralPatterns.AdapterPattern;

public class MP4Player implements AdvancedMediaPlayer {
    
    @Override
    public void playVlc(String fileName) {
        // MP4 player doesn't support VLC format
        // This method does nothing
    }
    
    @Override
    public void playMp4(String fileName) {
        System.out.println(" Playing MP4 file: " + fileName);
    }
}
