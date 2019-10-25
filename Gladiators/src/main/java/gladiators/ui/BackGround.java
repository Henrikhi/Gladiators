package gladiators.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BackGround {

    private String path;
    private ImageView imageview;

    public BackGround() {
        //backgrounds must be 600 x 800 pixels

        this.path = "file:../Files/Images/Backgrounds/arena1.png";
        Image image = new Image(this.path);
        this.imageview = new ImageView(image);
    }

    public ImageView getImageview() {
        return imageview;
    }

}
