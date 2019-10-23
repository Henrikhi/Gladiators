package gladiators.ui;

import gladiators.logic.Logics;
import java.awt.image.BufferedImage;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GladiatorMain extends Application {

    static Logics gamelogics;
    static Pane screen;
    static Animation heroAnimation;
    static Animation enemyAnimation;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        this.gamelogics = new Logics("Jukka");
        this.screen = initScreen();

        gamelogics.buttons.getQuickButton().setOnMouseClicked(click -> {
            quickClicked();
        });

        gamelogics.buttons.getHeavyButton().setOnMouseClicked(click -> {
            heavyClicked();
        });

        gamelogics.buttons.getRecoverButton().setOnMouseClicked(click -> {
            recoverClicked();
        });

        gamelogics.buttons.getMenuButton().setOnMouseClicked(click -> {
            menuClicked();
        });

        stage.setScene(new Scene(screen));
        stage.setTitle("Gladiator");
        stage.setResizable(false);
        stage.show();
    }

    public ImageView setImageSettings(ImageView gif, boolean isHero) {
        if (isHero) {
            gif.setTranslateX(10);
            gif.setTranslateY(120);
        } else {
            gif.setTranslateX(450);
            gif.setTranslateY(0);
        }
        return gif;
    }

    private Pane initScreen() {
        Pane screen = new Pane();
        BackGround bg = new BackGround();

        this.heroAnimation = new AnimatedGif(gamelogics.hero.getImagePath(), 400);
        this.heroAnimation.setCycleCount(1);
        this.heroAnimation.imageView = setImageSettings(this.heroAnimation.imageView, true);

        this.enemyAnimation = null;
        refreshEnemyAnimation();

        screen.setPrefSize(800, 600);
        screen.getChildren().addAll(bg.getImageview(),
                this.heroAnimation.getView(),
                this.enemyAnimation.getView());

        screen.getChildren().add(gamelogics.buttons.getQuickButton());
        screen.getChildren().add(gamelogics.buttons.getHeavyButton());
        screen.getChildren().add(gamelogics.buttons.getMenuButton());
        screen.getChildren().add(gamelogics.buttons.getRecoverButton());
        screen.getChildren().add(gamelogics.heroText);
        screen.getChildren().add(gamelogics.enemyText);
        screen.getChildren().add(gamelogics.infoText);
        return screen;
    }

    private void quickClicked() {
        if (gamelogics.hero.isAlive()) {
            this.heroAnimation.play();
            screen.getChildren().remove(this.enemyAnimation.getView());
            gamelogics.quickClicked();
            refreshEnemyAnimation();
            screen.getChildren().add(this.enemyAnimation.getView());
        }
    }

    private void heavyClicked() {
        if (gamelogics.hero.isAlive()) {
            this.heroAnimation.play();
            screen.getChildren().remove(this.enemyAnimation.getView());
            gamelogics.heavyClicked();
            refreshEnemyAnimation();
            screen.getChildren().add(this.enemyAnimation.getView());
        }
    }

    private void recoverClicked() {
        if (gamelogics.hero.isAlive()) {
            gamelogics.RecoverClicked();
        }
    }

    private void menuClicked() {
        System.exit(0);
    }

    private void refreshEnemyAnimation() {
        this.enemyAnimation = new AnimatedGif(gamelogics.enemy.getImagePath(), 400);
        this.enemyAnimation.setCycleCount(1);
        this.enemyAnimation.imageView = setImageSettings(this.enemyAnimation.imageView, false);
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
