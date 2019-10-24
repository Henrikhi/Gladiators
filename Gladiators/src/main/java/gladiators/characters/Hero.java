package gladiators.characters;

public class Hero extends Character {

    private int kills;
    private int healthPotions;
    private String potionPath;
    private int potionSpeed;

    public Hero(String name) {
        super(name, 100, 70, 30,
                "file:../Files/Images/Characters/Hero/heroIdle.gif", 2000,
                "file:../Files/Images/Characters/Hero/heroQuick.gif", 1000,
                "file:../Files/Images/Characters/Hero/heroHeavy.gif", 1000);
        this.potionPath = "file:../Files/Images/Characters/Hero/heroPotion.gif";
        this.potionSpeed = 2000;
        this.kills = 0;
        this.healthPotions = 3;
    }

    public String getPotionPath() {
        return this.potionPath;
    }

    public int getPotionSpeed() {
        return this.potionSpeed;
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
        heal(50);
        return true;
    }

    public int getKills() {
        return this.kills;
    }

    public void addKill() {
        this.kills++;
    }

}
