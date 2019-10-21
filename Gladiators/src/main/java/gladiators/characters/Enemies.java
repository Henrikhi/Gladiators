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
        Character cyclop = new Character("Cyclop", 150, 40, 40, "file:../Files/Images/Characters/Cyclop/cyclop1.png");
        Character goblin = new Character("Goblin", 75, 80, 20, "file:../Files/Images/Characters/Goblin/goblin1.png");
        Character gnome = new Character("Gnome", 50, 100, 15, "file:../Files/Images/Characters/Gnome/gnome1.png");
        Character slime = new Character("Slime", 100, 60, 25, "file:../Files/Images/Characters/Slime/slime1.png");
        
        this.enemies.add(cyclop);
        this.enemies.add(goblin);
        this.enemies.add(gnome);
        this.enemies.add(slime);
        
        this.enemies.forEach(enemy -> {
            enemy.setHeroImageSettings(false);
            
        });
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
