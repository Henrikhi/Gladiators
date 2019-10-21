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
        
        Logics gamelogics = new Logics();
        gamelogics.newHero("Jukka");
        BackGround bg = new BackGround();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the arena!");
        
        Pane screen = new Pane();
        screen.setPrefSize(800, 600);
        screen.getChildren().addAll(bg.getImageview(), gamelogics.hero.getImageview(), gamelogics.enemies.randomEnemy().getImageview());
        
        Button button = new Button();
        screen.getChildren().add(button);
        button.setTranslateY(350);
        button.setTranslateX(50);
        button.setScaleX(0.2);
        button.setScaleY(0.2);
        button.setGraphic(new ImageView(new Image("file:../Files/Images/Characters/PlayerFinished.png")));
        button.setPrefSize(10, 10);
        
        
        stage.setScene(new Scene(screen));
        stage.show();
    }
    
}
