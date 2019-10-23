package gladiators.music;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.media.AudioClip;

public class Music {

    private ArrayList<AudioClip> soundTracks;
    private Random random;
    private AudioClip clip;

    public Music() {
        this.random = new Random();
        this.soundTracks = new ArrayList();
        this.soundTracks.add(new AudioClip("file:../Files/Music/music1.wav"));
        this.clip = this.soundTracks.get(0);
    }

    public void playRandom() {
        this.clip.stop();
        this.clip = this.soundTracks.get(random.nextInt(this.soundTracks.size()));
        this.clip.setCycleCount(-1);
        this.clip.play();
    }

    public void stop() {
        this.clip.stop();
    }

    public void setVolume(double volume) {
        this.clip.setVolume(volume);
    }

}
