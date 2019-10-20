package gladiators.logic;

import gladiators.characters.Hero;
import gladiators.characters.Enemies;
import gladiators.characters.Character;

public class Logics {

    public Hero hero;
    public Enemies enemies;
    public Character enemy;

    public Logics() {
        this.hero = null;
        this.enemies = new Enemies();
        this.enemy = enemies.randomEnemy();
    }

    public void newHero(String name) {
        this.hero = new Hero(name);
    }

}
