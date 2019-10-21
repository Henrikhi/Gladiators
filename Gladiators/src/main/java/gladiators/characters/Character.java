package gladiators.characters;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Character {

    private String name;
    private int hitpoints;
    private int healthPotions;
    private int accuracy;
    private int strength;
    private Random random;
    private String imagePath;
    private ImageView imageview;

    public Character(String name, int hitpoints, int accuracy, int strength, String imagePath) {
        this.name = name;
        this.hitpoints = hitpoints;
        this.healthPotions = 0;
        this.accuracy = accuracy;
        this.strength = strength;
        this.random = new Random();
        this.imagePath = imagePath;
        createImage();
    }

    
    public ImageView getImageview() {
        return imageview;
    }
    
    

    public String getName() {
        return name;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getStrength() {
        return strength;
    }

    public int getHealthPotions() {
        return healthPotions;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public boolean drinkHealthPotion() {
        if (this.healthPotions == 0) {
            return false;
        }
        this.healthPotions--;
        this.hitpoints += 50;
        return true;
    }

    public void getHit(int dealtDamage) {
        this.hitpoints -= dealtDamage;
    }

    public boolean isAlive() {
        return this.hitpoints > 0;
    }

    public int attack() {
        int randomNumber = this.random.nextInt(101);
        if (randomNumber > this.accuracy) {
            return 0;
        }
        return this.strength;
    }

    private void createImage() {
        Image image = new Image(this.imagePath);
        this.imageview = new ImageView(image);
    }
    
    public void setHeroImageSettins(boolean isHero) {
        if (isHero) {
            this.imageview.setTranslateX(50);
            this.imageview.setTranslateY(120);
        } else {
            this.imageview.setTranslateX(450);
            this.imageview.setTranslateY(-50);
        }
    }

}
