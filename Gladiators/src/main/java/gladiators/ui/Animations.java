package gladiators.ui;

import gladiators.logic.GifDecoder;
import java.awt.image.BufferedImage;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;

public class Animations {

    private Animation heroIdle;
    private Animation heroQuick;
    private Animation heroHeavy;
    private Animation heroPotion;
    private Animation heroEntry;
    private Animation heroDeath;
    private Animation enemyIdle;
    private Animation enemyQuick;
    private Animation enemyHeavy;
    private Animation enemyEntry;
    private Animation enemyDeath;

    public Animations() {
    }

    public void createHeroIdle(String path, int speed) {
        this.heroIdle = new AnimatedGif(path, speed);
        this.heroIdle.setCycleCount(-1);
        this.heroIdle.imageView = setImageSettings(this.heroIdle.imageView, true);
    }

    public void createHeroQuick(String path, int speed) {
        this.heroQuick = new AnimatedGif(path, speed);
        this.heroQuick.setCycleCount(1);
        this.heroQuick.imageView = setImageSettings(this.heroQuick.imageView, true);
    }

    public void createHeroHeavy(String path, int speed) {
        this.heroHeavy = new AnimatedGif(path, speed);
        this.heroHeavy.setCycleCount(1);
        this.heroHeavy.imageView = setImageSettings(this.heroHeavy.imageView, true);
    }

    public void createHeroEntry(String path, int speed) {
        this.heroEntry = new AnimatedGif(path, speed);
        this.heroEntry.setCycleCount(1);
        this.heroEntry.imageView = setImageSettings(this.heroEntry.imageView, true);
    }

    public void createHeroDeath(String path, int speed) {
        this.heroDeath = new AnimatedGif(path, speed);
        this.heroDeath.setCycleCount(1);
        this.heroDeath.imageView = setImageSettings(this.heroDeath.imageView, true);
    }

    public void createHeroPotion(String path, int speed) {
        this.heroPotion = new AnimatedGif(path, speed);
        this.heroPotion.setCycleCount(1);
        this.heroPotion.imageView = setImageSettings(this.heroPotion.imageView, true);
    }

    public void createEnemyIdle(String path, int speed) {
        this.enemyIdle = new AnimatedGif(path, speed);
        this.enemyIdle.setCycleCount(-1);
        this.enemyIdle.imageView = setImageSettings(this.enemyIdle.imageView, false);
    }

    public void createEnemyQuick(String path, int speed) {
        this.enemyQuick = new AnimatedGif(path, speed);
        this.enemyQuick.setCycleCount(1);
        this.enemyQuick.imageView = setImageSettings(this.enemyQuick.imageView, false);
    }

    public void createEnemyHeavy(String path, int speed) {
        this.enemyHeavy = new AnimatedGif(path, speed);
        this.enemyHeavy.setCycleCount(1);
        this.enemyHeavy.imageView = setImageSettings(this.enemyHeavy.imageView, false);
    }

    public void createEnemyEntry(String path, int speed) {
        this.enemyEntry = new AnimatedGif(path, speed);
        this.enemyEntry.setCycleCount(1);
        this.enemyEntry.imageView = setImageSettings(this.enemyEntry.imageView, false);
    }

    public void createEnemyDeath(String path, int speed) {
        this.enemyDeath = new AnimatedGif(path, speed);
        this.enemyDeath.setCycleCount(1);
        this.enemyDeath.imageView = setImageSettings(this.enemyDeath.imageView, false);
    }

    public ImageView setImageSettings(ImageView gif, boolean isHero) {
        if (isHero) {
            gif.setTranslateX(10); //10
            gif.setTranslateY(-40); //-40
        } else {
            gif.setTranslateX(180); //180
            gif.setTranslateY(0); //0
        }
        return gif;
    }

    public Animation getHeroIdle() {
        return heroIdle;
    }

    public Animation getHeroQuick() {
        return heroQuick;
    }

    public Animation getHeroHeavy() {
        return heroHeavy;
    }

    public Animation getHeroEntry() {
        return heroEntry;
    }

    public Animation getHeroDeath() {
        return heroDeath;
    }

    public Animation getHeroPotion() {
        return heroPotion;
    }

    public Animation getEnemyIdle() {
        return enemyIdle;
    }

    public Animation getEnemyQuick() {
        return enemyQuick;
    }

    public Animation getEnemyHeavy() {
        return enemyHeavy;
    }

    public Animation getEnemyEntry() {
        return enemyEntry;
    }

    public Animation getEnemyDeath() {
        return enemyDeath;
    }

    public String getAnimationToString(Animation ani) {
        if (ani.equals(this.heroIdle)) {
            return "heroIdle";
        }
        if (ani.equals(this.heroQuick)) {
            return "heroQuick";
        }
        if (ani.equals(this.heroHeavy)) {
            return "heroHeavy";
        }
        if (ani.equals(this.enemyIdle)) {
            return "enemyIdle";
        }
        if (ani.equals(this.enemyQuick)) {
            return "enemyQuick";
        }
        if (ani.equals(this.enemyHeavy)) {
            return "enemyHeavy";
        }
        return null;
    }

    public class AnimatedGif extends Animation {

        public AnimatedGif(String filename, double durationMs) {

            GifDecoder d = new GifDecoder();
            d.read(filename);

            Image[] sequence = new Image[d.getFrameCount()];
            for (int i = 0; i < d.getFrameCount(); i++) {

                WritableImage wimg = null;
                BufferedImage bimg = d.getFrame(i);
                sequence[i] = SwingFXUtils.toFXImage(bimg, wimg);

            }

            super.init(sequence, durationMs);
        }

    }

    public class Animation extends Transition {

        private ImageView imageView;
        private int count;

        private int lastIndex;

        private Image[] sequence;

        private Animation() {
        }

        public Animation(Image[] sequence, double durationMs) {
            init(sequence, durationMs);
        }

        private void init(Image[] sequence, double durationMs) {
            this.imageView = new ImageView(sequence[0]);
            this.sequence = sequence;
            this.count = sequence.length;

            setCycleCount(1);
            setCycleDuration(Duration.millis(durationMs));
            setInterpolator(Interpolator.LINEAR);

        }

        protected void interpolate(double k) {

            final int index = Math.min((int) Math.floor(k * count), count - 1);
            if (index != lastIndex) {
                imageView.setImage(sequence[index]);
                lastIndex = index;
            }
        }

        public ImageView getView() {
            return imageView;
        }

    }
}
