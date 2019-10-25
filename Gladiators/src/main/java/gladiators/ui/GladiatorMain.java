package gladiators.ui;

import gladiators.logic.Logics;
import gladiators.music.Music;
import gladiators.ui.Animations.Animation;
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
        this.screen = initScreen();
        this.music = new Music();

        this.music.playRandom();
        
        
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

        animateEntries();

        stage.setScene(new Scene(screen));
        stage.setTitle("Gladiator");
        stage.setResizable(false);
        stage.show();
    }

    private Pane initScreen() {
        Pane screen = new Pane();
        BackGround bg = new BackGround();

        screen.setPrefSize(790, 590); //this should be 800x600

        screen.getChildren().add(bg.getImageview());
        this.gamelogics.getButtons().getAll().forEach(button -> {
            screen.getChildren().add(button);
        });
        screen.getChildren().add(this.gamelogics.getTextboxes().getHeroText());
        screen.getChildren().add(this.gamelogics.getTextboxes().getEnemyText());
        screen.getChildren().add(this.gamelogics.getTextboxes().getInfoText());

        return screen;
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
        }
    }

    private void recoverClicked() {
        if (this.gamelogics.getHero().isAlive()) {
            if (this.gamelogics.RecoverClicked()) {
                animateHeroPotion();
            }
        }
    }

    private void menuClicked() {
        System.exit(0);
    }

    private void animateEntries() {
        this.gamelogics.disactivateButtons();
        this.animations.createHeroEntry(this.gamelogics.getHero().getEntryPath(), this.gamelogics.getHero().getEntrySpeed());
        this.animations.createHeroDeath(this.gamelogics.getHero().getDeathPath(), this.gamelogics.getHero().getDeathSpeed());
        this.animations.createHeroQuick(this.gamelogics.getHero().getQuickPath(), this.gamelogics.getHero().getQuickSpeed());
        this.animations.createHeroHeavy(this.gamelogics.getHero().getHeavyPath(), this.gamelogics.getHero().getHeavySpeed());
        this.animations.createHeroPotion(this.gamelogics.getHero().getPotionPath(), this.gamelogics.getHero().getPotionSpeed());

        this.animations.createHeroIdle(this.gamelogics.getHero().getIdlePath(), this.gamelogics.getHero().getIdleSpeed());
        this.screen.getChildren().add(this.animations.getHeroEntry().getView());
        this.animations.getHeroEntry().play();
        this.animations.getHeroEntry().setOnFinished(heroEntryDone -> {
            this.gamelogics.heroEntryDone();
            this.gamelogics.updateTextBoxes();
            this.screen.getChildren().remove(this.animations.getHeroEntry().getView());
            this.screen.getChildren().add(this.animations.getHeroIdle().getView());
            this.animations.getHeroIdle().play();
            this.animations.createEnemyEntry(this.gamelogics.getEnemy().getEntryPath(), this.gamelogics.getEnemy().getEntrySpeed());
            this.animations.createEnemyIdle(this.gamelogics.getEnemy().getIdlePath(), this.gamelogics.getEnemy().getIdleSpeed());
            this.screen.getChildren().add(this.animations.getEnemyEntry().getView());
            this.animations.getEnemyEntry().play();
            this.animations.getEnemyEntry().setOnFinished(enemyEntryDone -> {
                this.gamelogics.enemyEntryDone();
                this.gamelogics.updateTextBoxes();
                this.screen.getChildren().remove(this.animations.getEnemyEntry().getView());
                this.screen.getChildren().add(this.animations.getEnemyIdle().getView());
                this.animations.getEnemyIdle().play();
                this.gamelogics.activateButtons();
            });
        });
    }

    private void animateHero(Animation action, Animation idle) {
        this.gamelogics.disactivateButtons();
        this.screen.getChildren().add(action.getView());
        action.play();
        action.setOnFinished(actionEnds -> {
            this.screen.getChildren().remove(action.getView());
            this.screen.getChildren().add(idle.getView());
            idle.play();
            this.gamelogics.updateTextBoxes();
            enemyTurn();
        });
    }

    private void animateEnemy(Animation action, Animation idle) {
        this.screen.getChildren().add(action.getView());
        this.screen.getChildren().remove(this.animations.getHeroIdle().getView());
        this.screen.getChildren().add(this.animations.getHeroIdle().getView());
        action.play();
        action.setOnFinished(actionEnds -> {
            this.screen.getChildren().remove(action.getView());
            this.screen.getChildren().add(idle.getView());
            idle.play();
            this.gamelogics.activateButtons();
            this.gamelogics.updateTextBoxes();
        });
    }

    private void animateHeroPotion() {
        this.screen.getChildren().remove(this.animations.getHeroIdle().getView());
        animateHero(this.animations.getHeroPotion(), this.animations.getHeroIdle());
    }

    private void animateHeroQuick() {
        this.screen.getChildren().remove(this.animations.getHeroIdle().getView());
        animateHero(this.animations.getHeroQuick(), this.animations.getHeroIdle());
    }

    private void animateHeroHeavy() {
        this.screen.getChildren().remove(this.animations.getHeroIdle().getView());
        animateHero(this.animations.getHeroHeavy(), this.animations.getHeroIdle());
    }

    private void animateHeroDeath() {

    }

    private void animateEnemyQuick() {
        this.screen.getChildren().remove(this.animations.getEnemyIdle().getView());
        this.animations.createEnemyQuick(this.gamelogics.getEnemy().getQuickPath(), this.gamelogics.getEnemy().getQuickSpeed());
        animateEnemy(this.animations.getEnemyQuick(), this.animations.getEnemyIdle());
    }

    private void animateEnemyHeavy() {
        this.screen.getChildren().remove(this.animations.getEnemyIdle().getView());
        this.animations.createEnemyHeavy(this.gamelogics.getEnemy().getHeavyPath(), this.gamelogics.getEnemy().getHeavySpeed());
        animateEnemy(this.animations.getEnemyHeavy(), this.animations.getEnemyIdle());
    }

    private void animateEnemyEntry() {
        this.animations.createEnemyIdle(this.gamelogics.getEnemy().getIdlePath(), this.gamelogics.getEnemy().getIdleSpeed());
        this.animations.createEnemyEntry(this.gamelogics.getEnemy().getEntryPath(), this.gamelogics.getEnemy().getEntrySpeed());
        animateEnemy(this.animations.getEnemyEntry(), this.animations.getEnemyIdle());
    }

    private void animateEnemyDeath() {
        this.screen.getChildren().remove(this.animations.getEnemyIdle().getView());
        this.animations.createEnemyDeath(this.gamelogics.getEnemy().getDeathPath(), this.gamelogics.getEnemy().getDeathSpeed());
        this.screen.getChildren().add(this.animations.getEnemyDeath().getView());
        this.animations.getEnemyDeath().play();
        this.animations.getEnemyDeath().setOnFinished(actionEnds -> {
            this.screen.getChildren().remove(this.animations.getEnemyDeath().getView());
            this.gamelogics.newEnemy();
            animateEnemyEntry();
        });
    }

    private void enemyTurn() {
        if (this.gamelogics.getEnemy().isAlive()) {
            int action = this.gamelogics.enemyTurn();
            if (action == 0) {
                animateEnemyQuick();
                this.gamelogics.enemyAttack(true);
            } else if (action == 1) {
                animateEnemyHeavy();
                this.gamelogics.enemyAttack(false);
            }
        } else {
            this.gamelogics.enemyDied();
            animateEnemyDeath();
        }
    }

}
