package gladiators.characters;

import java.util.Random;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Character {

    private String name;
    private int hitpoints;
    private int maxHP;
    private int healthPotions;
    private int accuracy;
    private int strength;
    private Random random;
    private String imagePath;
    private ImageView imageview;

    public Character(String name, int maxHP, int accuracy, int strength, String imagePath) {
        this.name = name;
        this.maxHP = maxHP;
        this.hitpoints = maxHP;
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

    public int getMaxHP() {
        return maxHP;
    }

    public boolean drinkHealthPotion() {
        if (this.healthPotions == 0) {
            return false;
        }
        this.healthPotions--;
        this.hitpoints += 50;
        if (this.hitpoints > this.maxHP) {
            this.hitpoints = this.maxHP;
        }
        return true;
    }

    public void getHit(int dealtDamage) {
        this.hitpoints -= dealtDamage;
    }

    public boolean isAlive() {
        return this.hitpoints > 0;
    }

    public int quickAttack() {
        int randomNumber = this.random.nextInt(101);
        if (randomNumber > this.accuracy) {
            return 0;
        }
        return this.strength;
    }

    public int heavyAttack() {
        int randomNumber = this.random.nextInt(51);
        if (randomNumber > (this.accuracy / 2)) {
            return 0;
        }
        return this.strength * 2;
    }

    private void createImage() {
        Image image = new Image(this.imagePath);
        this.imageview = new ImageView(image);
    }

    public void setHeroImageSettings(boolean isHero) {
        if (isHero) {
            this.imageview.setTranslateX(10);
            this.imageview.setTranslateY(120);
        } else {
            this.imageview.setTranslateX(450);
            this.imageview.setTranslateY(0);
        }
    }

    public String getText() {
        String text = "" + this.name + "\nHealth: " + this.hitpoints + "/" + this.maxHP;
        return text;
    }

}
