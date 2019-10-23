package gladiators.ui;

import gladiators.logic.Logics;
import gladiators.music.Music;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GladiatorMain extends Application {

    private Logics gamelogics;
    private Pane screen;
    private Music music;
    private Animations animations;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        this.gamelogics = new Logics("Jukka");
        this.animations = new Animations();
        animationInit();
        this.screen = initScreen();
        this.music = new Music();

//        this.music.playRandom();
        this.gamelogics.getButtons().getQuickButton().setOnMouseClicked(click -> {
            quickClicked();
        });

        this.gamelogics.getButtons().getHeavyButton().setOnMouseClicked(click -> {
            heavyClicked();
        });

        this.gamelogics.getButtons().getRecoverButton().setOnMouseClicked(click -> {
            recoverClicked();
        });

        this.gamelogics.getButtons().getMenuButton().setOnMouseClicked(click -> {
            menuClicked();
        });

        stage.setScene(new Scene(screen));
        stage.setTitle("Gladiator");
        stage.setResizable(false);
        stage.show();
    }

    private Pane initScreen() {
        Pane screen = new Pane();
        BackGround bg = new BackGround();

        screen.setPrefSize(800, 600);

        screen.getChildren().add(bg.getImageview());
        this.gamelogics.getButtons().getAll().forEach(button -> {
            screen.getChildren().add(button);
        });
        screen.getChildren().add(this.gamelogics.getTextboxes().getHeroText());
        screen.getChildren().add(this.gamelogics.getTextboxes().getEnemyText());
        screen.getChildren().add(this.gamelogics.getTextboxes().getInfoText());

        screen.getChildren().add(this.animations.getHeroIdle().getView());
        screen.getChildren().add(this.animations.getEnemyIdle().getView());

        return screen;
    }

    private void animationInit() {
        this.animations.createHeroIdle(this.gamelogics.getHero().getIdlePath(), this.gamelogics.getHero().getIdleSpeed());
        this.animations.createHeroQuick(this.gamelogics.getHero().getQuickPath(), this.gamelogics.getHero().getQuickSpeed());
        this.animations.createHeroHeavy(this.gamelogics.getHero().getHeavyPath(), this.gamelogics.getHero().getHeavySpeed());
        this.animations.createEnemyIdle(this.gamelogics.getEnemy().getIdlePath(), this.gamelogics.getEnemy().getIdleSpeed());
        this.animations.createEnemyQuick(this.gamelogics.getEnemy().getQuickPath(), this.gamelogics.getEnemy().getQuickSpeed());
        this.animations.createEnemyHeavy(this.gamelogics.getEnemy().getHeavyPath(), this.gamelogics.getEnemy().getHeavySpeed());

        this.animations.getHeroIdle().play();
        this.animations.getEnemyIdle().play();
    }

    private void quickClicked() {
        if (this.gamelogics.getHero().isAlive()) {
            animateHeroQuick();

            int damage = this.gamelogics.getHero().quickAttack();
            if (damage == 0) {
                this.gamelogics.missed(true);
            } else {
                this.gamelogics.hit(damage, true, " swiftly");
            }

            if (this.gamelogics.getEnemy().isAlive()) {
                enemyTurn();
            } else {
                this.gamelogics.enemyDied();
                this.screen.getChildren().remove(this.animations.getEnemyIdle().getView());
                this.animations.createEnemyIdle(this.gamelogics.getEnemy().getIdlePath(), this.gamelogics.getEnemy().getIdleSpeed());
                this.animations.getEnemyIdle().play();
                this.screen.getChildren().add(this.animations.getEnemyIdle().getView());
            }
        }
    }

    private void heavyClicked() {
        if (this.gamelogics.getHero().isAlive()) {
            animateHeroHeavy();
            int damage = this.gamelogics.getHero().heavyAttack();
            if (damage == 0) {
                this.gamelogics.missed(true);
            } else {
                this.gamelogics.hit(damage, true, " with great power");
            }

            if (this.gamelogics.getEnemy().isAlive()) {
                enemyTurn();
            } else {
                this.gamelogics.enemyDied();
                this.screen.getChildren().remove(this.animations.getEnemyIdle().getView());
                this.animations.createEnemyIdle(this.gamelogics.getEnemy().getIdlePath(), this.gamelogics.getEnemy().getIdleSpeed());
                this.animations.getEnemyIdle().play();
                this.screen.getChildren().add(this.animations.getEnemyIdle().getView());
            }
        }
    }

    private void recoverClicked() {
        if (this.gamelogics.getHero().isAlive()) {
            if (this.gamelogics.RecoverClicked()) {
                enemyTurn();
            }
        }
    }

    private void menuClicked() {
        System.exit(0);
    }

    private void animateHeroQuick() {
        this.screen.getChildren().remove(this.animations.getHeroIdle().getView());
//        createHeroQuick();
        this.screen.getChildren().add(this.animations.getHeroQuick().getView());
        this.animations.getHeroQuick().play();
        this.animations.getHeroQuick().setOnFinished(attEnds -> {
            this.screen.getChildren().remove(this.animations.getHeroQuick().getView());
            this.screen.getChildren().add(this.animations.getHeroIdle().getView());
        });

    }

    private void animateHeroHeavy() {
        this.screen.getChildren().remove(this.animations.getHeroIdle().getView());
//        createHeroHeavy();
        this.screen.getChildren().add(this.animations.getHeroHeavy().getView());
        this.animations.getHeroHeavy().play();
        this.animations.getHeroHeavy().setOnFinished(attEnds -> {
            this.screen.getChildren().remove(this.animations.getHeroHeavy().getView());
            this.screen.getChildren().add(this.animations.getHeroIdle().getView());
        });

    }

    public void animateEnemyQuick() {
        this.screen.getChildren().remove(this.animations.getEnemyIdle().getView());
        this.animations.createEnemyQuick(this.gamelogics.getEnemy().getQuickPath(), this.gamelogics.getEnemy().getQuickSpeed());
        this.screen.getChildren().add(this.animations.getEnemyQuick().getView());
        this.animations.getEnemyQuick().play();
        this.animations.getEnemyQuick().setOnFinished(attEnds -> {
            this.screen.getChildren().remove(this.animations.getEnemyQuick().getView());
            this.screen.getChildren().add(this.animations.getEnemyIdle().getView());
        });
    }

    public void animateEnemyHeavy() {
        this.screen.getChildren().remove(this.animations.getEnemyIdle().getView());
        this.animations.createEnemyHeavy(this.gamelogics.getEnemy().getHeavyPath(), this.gamelogics.getEnemy().getHeavySpeed());
        this.screen.getChildren().add(this.animations.getEnemyHeavy().getView());
        this.animations.getEnemyHeavy().play();
        this.animations.getEnemyHeavy().setOnFinished(attEnds -> {
            this.screen.getChildren().remove(this.animations.getEnemyHeavy().getView());
            this.screen.getChildren().add(this.animations.getEnemyIdle().getView());
        });
    }

    private void enemyTurn() {
        int action = this.gamelogics.enemyTurn();
        if (action == 0) {
            animateEnemyQuick();
            this.gamelogics.enemyAttack(true);
        } else if (action == 1) {
            animateEnemyHeavy();
            this.gamelogics.enemyAttack(false);
        }
    }

}
