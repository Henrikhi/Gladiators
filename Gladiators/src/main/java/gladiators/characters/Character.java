package gladiators.characters;

import java.util.Random;

public class Character {

    private String name;
    private int hitpoints;
    private int maxHP;
    private int accuracy;
    private int strength;
    private Random random;
    private String idlePath;
    private String quickPath;
    private String heavyPath;
    private String entryPath;
    private String deathPath;
    private int idleSpeed;
    private int quickSpeed;
    private int heavySpeed;
    private int entrySpeed;
    private int deathSpeed;

    public Character(String name, int maxHP, int accuracy, int strength,
            String idlePath, int idleSpeed,
            String quickPath, int quickSpeed,
            String heavyPath, int heavySpeed,
            String entryPath, int entrySpeed,
            String deathPath, int deathSpeed) {
        this.name = name;
        this.maxHP = maxHP;
        this.hitpoints = maxHP;
        this.accuracy = accuracy;
        this.strength = strength;
        this.random = new Random();
        this.idlePath = idlePath;
        this.quickPath = quickPath;
        this.heavyPath = heavyPath;
        this.entryPath = entryPath;
        this.deathPath = deathPath;
        this.idleSpeed = idleSpeed;
        this.quickSpeed = quickSpeed;
        this.heavySpeed = heavySpeed;
        this.entrySpeed = entrySpeed;
        this.deathSpeed = deathSpeed;
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

    public String getEntryPath() {
        return entryPath;
    }

    public String getDeathPath() {
        return deathPath;
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

    public int getEntrySpeed() {
        return entrySpeed;
    }

    public int getDeathSpeed() {
        return deathSpeed;
    }

    public String getName() {
        return name;
    }

    public void heal(int howMuch) {
        this.hitpoints += 50;
        if (this.hitpoints > this.maxHP) {
            this.hitpoints = this.maxHP;
        }
    }

    public void gotDamaged(int dealtDamage) {
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
        return text.toUpperCase();
    }

}
