package gladiators.characters;

import java.util.Random;
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
    private String idlePath;
    private String quickPath;
    private String heavyPath;
    private int idleSpeed;
    private int quickSpeed;
    private int heavySpeed;

    public Character(String name, int maxHP, int accuracy, int strength, String idlePath, int idleSpeed, String quickPath, int quickSpeed, String heavyPath, int heavySpeed) {
        this.name = name;
        this.maxHP = maxHP;
        this.hitpoints = maxHP;
        this.healthPotions = 0;
        this.accuracy = accuracy;
        this.strength = strength;
        this.random = new Random();
        this.idlePath = idlePath;
        this.quickPath = quickPath;
        this.heavyPath = heavyPath;
        this.idleSpeed = idleSpeed;
        this.quickSpeed = quickSpeed;
        this.heavySpeed = heavySpeed;
    }

    public String getIdlePath() {
        return idlePath;
    }

    public String getQuickPath() {
        return quickPath;
    }

    public String getHeavyPath() {
        return heavyPath;
    }

    public int getIdleSpeed() {
        return idleSpeed;
    }

    public int getQuickSpeed() {
        return quickSpeed;
    }

    public int getHeavySpeed() {
        return heavySpeed;
    }

    public String getName() {
        return name;
    }

    public int getHealthPotions() {
        return healthPotions;
    }

    public void giveHealthPotions(int howMany) {
        if (howMany > 0) {
            this.healthPotions += howMany;
        }
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
        if (this.hitpoints < 0) {
            this.hitpoints = 0;
        }
    }

    public boolean isAlive() {
        return this.hitpoints > 0;
    }

    public int quickAttack() {
        int randomNumber = this.random.nextInt(101);
        if (randomNumber > this.accuracy) {
            return 0;
        }
        return (10 + this.random.nextInt(this.strength - 10));
    }

    public int heavyAttack() {
        int randomNumber = this.random.nextInt(51);
        if (randomNumber > (this.accuracy / 2)) {
            return 0;
        }
        return (10 + this.random.nextInt(this.strength - 10)) * 2;
    }

    public String getText() {
        String text = "" + this.name + "\nHealth: " + this.hitpoints + "/" + this.maxHP;
        return text;
    }

}
