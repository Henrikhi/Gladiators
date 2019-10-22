package gladiators.ui;

import gladiators.logic.Logics;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GladiatorMain extends Application {

    static Logics gamelogics;
    static Pane screen;

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

    private Pane initScreen() {
        Pane screen = new Pane();
        BackGround bg = new BackGround();
        screen.setPrefSize(800, 600);
        screen.getChildren().addAll(bg.getImageview(),
                gamelogics.hero.getImageview(),
                gamelogics.enemy.getImageview());

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
            screen.getChildren().remove(gamelogics.enemy.getImageview());
            gamelogics.quickClicked();
            screen.getChildren().add(gamelogics.enemy.getImageview());
        }
    }

    private void heavyClicked() {
        if (gamelogics.hero.isAlive()) {
            screen.getChildren().remove(gamelogics.enemy.getImageview());
            gamelogics.heavyClicked();
            screen.getChildren().add(gamelogics.enemy.getImageview());
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

}
