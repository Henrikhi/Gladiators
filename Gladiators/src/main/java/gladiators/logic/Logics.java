package gladiators.logic;

import gladiators.characters.Hero;
import gladiators.characters.Enemies;
import gladiators.characters.Character;
import gladiators.ui.Buttons;

public class Logics {

    public Hero hero;
    public Enemies enemies;
    public Character enemy;
    public Buttons buttons;

    public Logics() {
        this.hero = null;
        this.enemies = new Enemies();
        this.enemy = enemies.randomEnemy();
        this.buttons = new Buttons();
    }

    public void newHero(String name) {
        this.hero = new Hero(name);
    }

}
