package gladiators.characters;

public class Hero extends Character {

    private int kills;

    public Hero(String name) {
        super(name, 100, 70, 30,
                "file:../Files/Images/Characters/Hero/heroIdle.gif", 2000,
                "file:../Files/Images/Characters/Hero/heroQuick.gif", 1000,
                "file:../Files/Images/Characters/Hero/heroHeavy.gif", 1000);
        this.kills = 0;
        giveHealthPotions(3);
    }

    public int getKills() {
        return this.kills;
    }

    public void addKill() {
        this.kills++;
    }

}
