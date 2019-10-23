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

        Character cyclop = new Character("Cyclop", 150, 40, 40,
                "file:../Files/Images/Characters/Cyclop/cyclopIdle.gif", 400,
                "file:../Files/Images/Characters/Cyclop/cyclopQuick.gif", 400,
                "file:../Files/Images/Characters/Cyclop/cyclopHeavy.gif", 400);
        Character goblin = new Character("Goblin", 75, 80, 20,
                "file:../Files/Images/Characters/Goblin/goblinIdle.gif", 400,
                "file:../Files/Images/Characters/Goblin/goblinQuick.gif", 400,
                "file:../Files/Images/Characters/Goblin/goblinHeavy.gif", 400);
        Character gnome = new Character("Gnome", 50, 100, 15,
                "file:../Files/Images/Characters/Gnome/gnomeIdle.gif", 1000,
                "file:../Files/Images/Characters/Gnome/gnomeQuick.gif", 1000,
                "file:../Files/Images/Characters/Gnome/gnomeHeavy.gif", 400);
        Character slime = new Character("Slime", 100, 60, 25,
                "file:../Files/Images/Characters/Slime/slimeIdle.gif", 400,
                "file:../Files/Images/Characters/Slime/slimeQuick.gif", 400,
                "file:../Files/Images/Characters/Slime/slimeHeavy.gif", 400);

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
