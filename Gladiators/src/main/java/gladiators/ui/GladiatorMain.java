package gladiators.ui;

import gladiators.logic.Logics;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GladiatorMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Logics gamelogics = new Logics("Jukka");
        BackGround bg = new BackGround();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the arena!");

        Pane screen = new Pane();
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

        gamelogics.buttons.getQuickButton().setOnMouseClicked(click -> {
            screen.getChildren().remove(gamelogics.enemy.getImageview());
            gamelogics.quickClicked();
            screen.getChildren().add(gamelogics.enemy.getImageview());
            
        });

        stage.setScene(new Scene(screen));
        stage.setTitle("Gladiator");
        stage.setResizable(false);
        stage.show();
    }

}
