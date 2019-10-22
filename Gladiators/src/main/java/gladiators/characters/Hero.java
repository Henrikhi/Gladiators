package gladiators.characters;

public class Hero extends Character {

    private int kills;

    public Hero(String name) {
        super(name, 100, 70, 30, "file:../Files/Images/Characters/Hero/hero1.png");
        this.kills = 0;
        setHeroImageSettings(true);
        giveHealthPotions(3);
    }

    public int getKills() {
        return this.kills;
    }

    public void addKill() {
        this.kills++;
    }

}
