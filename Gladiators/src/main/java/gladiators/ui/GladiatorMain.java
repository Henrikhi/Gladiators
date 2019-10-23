package gladiators.ui;

import gladiators.logic.GifDecoder;
import gladiators.logic.Logics;
import static gladiators.ui.GladiatorMain.gamelogics;
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
    static Animation heroIdle;
    static Animation heroQuick;
    static Animation heroHeavy;
    static Animation enemyIdle;
    static Animation enemyQuick;
    static Animation enemyHeavy;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        this.gamelogics = new Logics("Jukka");
        this.screen = initScreen();

        animationInit();

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
            gif.setTranslateX(180); //450
            gif.setTranslateY(0); //0
        }
        return gif;
    }

    private Pane initScreen() {
        Pane screen = new Pane();
        BackGround bg = new BackGround();

        screen.setPrefSize(800, 600);

        screen.getChildren().add(bg.getImageview());
        screen.getChildren().add(gamelogics.buttons.getQuickButton());
        screen.getChildren().add(gamelogics.buttons.getHeavyButton());
        screen.getChildren().add(gamelogics.buttons.getMenuButton());
        screen.getChildren().add(gamelogics.buttons.getRecoverButton());
        screen.getChildren().add(gamelogics.heroText);
        screen.getChildren().add(gamelogics.enemyText);
        screen.getChildren().add(gamelogics.infoText);
        return screen;
    }

    private void animationInit() {
        this.heroQuick = new AnimatedGif(gamelogics.hero.getQuickPath(), gamelogics.hero.getQuickSpeed());
        this.heroQuick.setCycleCount(1);
        this.heroQuick.imageView = setImageSettings(this.heroQuick.imageView, true);

        this.heroHeavy = new AnimatedGif(gamelogics.hero.getHeavyPath(), gamelogics.hero.getHeavySpeed());
        this.heroHeavy.setCycleCount(1);
        this.heroHeavy.imageView = setImageSettings(this.heroHeavy.imageView, true);

        this.enemyQuick = new AnimatedGif(gamelogics.enemy.getQuickPath(), gamelogics.enemy.getQuickSpeed());
        this.enemyQuick.setCycleCount(1);
        this.enemyQuick.imageView = setImageSettings(this.enemyQuick.imageView, true);

        this.enemyHeavy = new AnimatedGif(gamelogics.enemy.getHeavyPath(), gamelogics.enemy.getHeavySpeed());
        this.enemyHeavy.setCycleCount(1);
        this.enemyHeavy.imageView = setImageSettings(this.enemyHeavy.imageView, true);

        this.enemyIdle = new AnimatedGif(gamelogics.enemy.getIdlePath(), gamelogics.enemy.getIdleSpeed());
        this.enemyIdle.setCycleCount(-1);
        this.enemyIdle.imageView = setImageSettings(this.enemyIdle.imageView, false);

        this.heroIdle = new AnimatedGif(gamelogics.hero.getIdlePath(), gamelogics.hero.getIdleSpeed());
        this.heroIdle.setCycleCount(-1);
        this.heroIdle.imageView = setImageSettings(this.heroIdle.imageView, true);

        heroIdle.play();
        enemyIdle.play();

        this.screen.getChildren().add(heroIdle.getView());
        this.screen.getChildren().add(enemyIdle.getView());

    }

    private void quickClicked() {
        if (gamelogics.hero.isAlive()) {
            animateHeroQuick();

            int damage = gamelogics.hero.quickAttack();
            if (damage == 0) {
                gamelogics.missed(true);
            } else {
                gamelogics.hit(damage, true, " swiftly");
            }

            if (gamelogics.enemy.isAlive()) {
                enemyTurn();
            } else {
                gamelogics.enemyDied();
                this.screen.getChildren().remove(enemyIdle.getView());
                this.enemyIdle = new AnimatedGif(gamelogics.enemy.getIdlePath(), gamelogics.enemy.getIdleSpeed());
                this.enemyIdle.setCycleCount(-1);
                this.enemyIdle.play();
                this.enemyIdle.imageView = setImageSettings(this.enemyIdle.imageView, false);
                this.screen.getChildren().add(enemyIdle.getView());
            }
        }
    }

    private void heavyClicked() {
        if (gamelogics.hero.isAlive()) {
            animateHeroHeavy();
            int damage = gamelogics.hero.heavyAttack();
            if (damage == 0) {
                gamelogics.missed(true);
            } else {
                gamelogics.hit(damage, true, " with great power");
            }

            if (gamelogics.enemy.isAlive()) {
                enemyTurn();
            } else {
                gamelogics.enemyDied();
                this.screen.getChildren().remove(enemyIdle.getView());
                this.enemyIdle = new AnimatedGif(gamelogics.enemy.getIdlePath(), gamelogics.enemy.getIdleSpeed());
                this.enemyIdle.setCycleCount(-1);
                this.enemyIdle.play();
                this.enemyIdle.imageView = setImageSettings(this.enemyIdle.imageView, false);
                this.screen.getChildren().add(enemyIdle.getView());
            }
        }
    }

    private void recoverClicked() {
        if (gamelogics.hero.isAlive()) {
            if (gamelogics.RecoverClicked()) {
                enemyTurn();
            }
        }
    }

    private void menuClicked() {
        System.exit(0);
    }

    private void animateHeroQuick() {
        this.screen.getChildren().remove(heroIdle.getView());
        this.screen.getChildren().add(heroQuick.getView());
        this.heroQuick.play();
        this.heroQuick.setOnFinished(attEnds -> {
            this.screen.getChildren().remove(heroQuick.getView());
            this.screen.getChildren().add(heroIdle.getView());
        });

    }

    private void animateHeroHeavy() {
        this.screen.getChildren().remove(heroIdle.getView());
        this.screen.getChildren().add(heroHeavy.getView());
        this.heroHeavy.play();
        this.heroHeavy.setOnFinished(attEnds -> {
            this.screen.getChildren().remove(heroHeavy.getView());
            this.screen.getChildren().add(heroIdle.getView());
        });

    }

    public void animateEnemyQuick() {
        this.screen.getChildren().remove(enemyIdle.getView());
        this.screen.getChildren().add(enemyQuick.getView());
        this.enemyQuick.play();
        this.enemyQuick.setOnFinished(attEnds -> {
            this.screen.getChildren().remove(enemyQuick.getView());
            this.screen.getChildren().add(enemyIdle.getView());
        });
    }

    public void animateEnemyHeavy() {
        this.screen.getChildren().remove(enemyIdle.getView());
        this.screen.getChildren().add(enemyHeavy.getView());
        this.enemyHeavy.play();
        this.enemyHeavy.setOnFinished(attEnds -> {
            this.screen.getChildren().remove(enemyHeavy.getView());
            this.screen.getChildren().add(enemyIdle.getView());
        });
    }

    private void enemyTurn() {
        int action = gamelogics.enemyTurn();
        if (action == 0) {
            animateEnemyQuick();
            gamelogics.enemyAttack(true);
        } else if (action == 1) {
            animateEnemyHeavy();
            gamelogics.enemyAttack(false);
        }
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
