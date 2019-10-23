package gladiators.characters;

import java.util.ArrayList;
import java.util.Random;

public class Enemies {

    private ArrayList<Character> enemies;
    private Random random;

    public Enemies() {
        this.enemies = new ArrayList<>();
        init();
        this.random = new Random();
    }

    private void init() {

        Character cyclop = new Character("Cyclop", 150, 40, 40, "file:../Files/Images/Characters/Cyclop/cyclopGif1.gif");
        Character goblin = new Character("Goblin", 75, 80, 20, "file:../Files/Images/Characters/Goblin/goblinGif1.gif");
        Character gnome = new Character("Gnome", 50, 100, 15, "file:../Files/Images/Characters/Gnome/gnomeGif1.gif");
        Character slime = new Character("Slime", 100, 60, 25, "file:../Files/Images/Characters/Slime/slimeGif1.gif");

        this.enemies.add(cyclop);
        this.enemies.add(goblin);
        this.enemies.add(gnome);
        this.enemies.add(slime);
    }

    public Character randomEnemy() {
        if (this.enemies.isEmpty()) {
            init();
        }
        Character enemy = this.enemies.get(random.nextInt(this.enemies.size()));
        this.enemies.remove(enemy);
        return enemy;

    }

}
