package gladiators.ui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Buttons {

    private Button quick;
    private Button recover;

    public Buttons() {
        createQuick();
        createRecover();
    }

    private void createQuick() {
        this.quick = new Button();
        this.quick.setGraphic(new ImageView(new Image("file:../Files/Images/Buttons/quick0000.png")));
        this.quick.setTranslateX(50);
        this.quick.setTranslateY(450);
    }

    public Button getQuickButton() {
        return quick;
    }

    private void createRecover() {
        this.recover = new Button();
        this.recover.setGraphic(new ImageView(new Image("file:../Files/Images/Buttons/recover0000.png")));
        this.recover.setTranslateX(300);
        this.recover.setTranslateY(450);
    }

    public Button getRecoverButton() {
        return recover;
    }

}
