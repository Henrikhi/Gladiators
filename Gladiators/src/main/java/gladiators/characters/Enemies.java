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
        Character cyclop = new Character("Cyclop", 150, 40, 40);
        Character goblin = new Character("Goblin", 75, 80, 20);

        this.enemies.add(cyclop);
        this.enemies.add(goblin);
    }

    public Character randomEnemy() {
        if (this.enemies.isEmpty()) {
            return null;
        }
        Character enemy = this.enemies.get(random.nextInt(this.enemies.size()));
        this.enemies.remove(enemy);
        return enemy;

    }

}
