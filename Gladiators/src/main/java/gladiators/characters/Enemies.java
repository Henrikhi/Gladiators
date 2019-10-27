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
                "file:../Files/Images/Characters/Cyclop/cyclopIdle.gif", 1000, //1000
                "file:../Files/Images/Characters/Cyclop/cyclopQuick.gif", 1000, //1000
                "file:../Files/Images/Characters/Cyclop/cyclopHeavy.gif", 1000, //1000
                "file:../Files/Images/Characters/Cyclop/cyclopHeavy.gif", 1000, //1000
                "file:../Files/Images/Characters/Cyclop/cyclopHeavy.gif", 1000); //1000
        Character goblin = new Character("Goblin", 75, 80, 20,
                "file:../Files/Images/Characters/Goblin/goblinIdle.gif", 1000, //1000
                "file:../Files/Images/Characters/Goblin/goblinQuick.gif", 1000, //1000
                "file:../Files/Images/Characters/Goblin/goblinHeavy.gif", 2500, //2500
                "file:../Files/Images/Characters/Goblin/goblinHeavy.gif", 1000, //2500
                "file:../Files/Images/Characters/Goblin/goblinHeavy.gif", 1000); //2500
        Character gnome = new Character("Gnome", 50, 100, 15,
                "file:../Files/Images/Characters/Gnome/gnomeIdle.gif", 1500, //1500
                "file:../Files/Images/Characters/Gnome/gnomeQuick.gif", 1500, //1500
                "file:../Files/Images/Characters/Gnome/gnomeHeavy.gif", 3000, //3000
                "file:../Files/Images/Characters/Gnome/gnomeEntry.gif", 3000, //3000
                "file:../Files/Images/Characters/Gnome/gnomeDeath.gif", 8000); //3000
        Character slime = new Character("Slime", 100, 60, 25,
                "file:../Files/Images/Characters/Slime/slimeIdle.gif", 1000, //1000
                "file:../Files/Images/Characters/Slime/slimeQuick.gif", 1000, //1000
                "file:../Files/Images/Characters/Slime/slimeHeavy.gif", 1000, //1000
                "file:../Files/Images/Characters/Slime/slimeHeavy.gif", 1000, //1000
                "file:../Files/Images/Characters/Slime/slimeHeavy.gif", 1000); //1000

//        this.enemies.add(cyclop);
        this.enemies.add(goblin);
        this.enemies.add(gnome);
//        this.enemies.add(slime);
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
