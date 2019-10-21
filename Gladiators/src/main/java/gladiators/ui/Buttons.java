package gladiators.ui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Buttons {

    private Button quick;
    private Button recover;
    private Button menu;
    private Button heavy;

    public Buttons() {
        createQuick();
        createRecover();
        createHeavy();
        createMenu();
    }

    private void createQuick() {
        this.quick = new Button();
        this.quick.setGraphic(new ImageView(new Image("file:../Files/Images/Buttons/quick1.png")));
        this.quick.setTranslateX(50);
        this.quick.setTranslateY(450);
    }

    public Button getQuickButton() {
        return quick;
    }

    private void createRecover() {
        this.recover = new Button();
        this.recover.setGraphic(new ImageView(new Image("file:../Files/Images/Buttons/recover1.png")));
        this.recover.setTranslateX(300);
        this.recover.setTranslateY(450);
    }

    public Button getRecoverButton() {
        return recover;
    }

    public void createMenu() {
        this.menu = new Button();
        this.menu.setGraphic(new ImageView(new Image("file:../Files/Images/Buttons/menu1.png")));
        this.menu.setTranslateX(300);
        this.menu.setTranslateY(525);
    }

    public Button getMenuButton() {
        return menu;
    }

    public void createHeavy() {
        this.heavy = new Button();
        this.heavy.setGraphic(new ImageView(new Image("file:../Files/Images/Buttons/heavy1.png")));
        this.heavy.setTranslateX(50);
        this.heavy.setTranslateY(525);
    }

    public Button getHeavyButton() {
        return heavy;
    }

}
