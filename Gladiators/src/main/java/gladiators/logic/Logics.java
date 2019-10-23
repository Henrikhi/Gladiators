package gladiators.logic;

import gladiators.characters.Hero;
import gladiators.characters.Enemies;
import gladiators.characters.Character;
import gladiators.ui.Buttons;
import gladiators.ui.TextBoxes;
import java.util.Random;

public class Logics {

    private Hero hero;
    private Enemies enemies;
    private Character enemy;
    private Buttons buttons;
    private Random random;
    private TextBoxes textboxes;

    public Logics(String heroName) {
        this.hero = new Hero(heroName);
        this.enemies = new Enemies();
        this.enemy = enemies.randomEnemy();
        this.buttons = new Buttons();
        this.textboxes = new TextBoxes();
        this.random = new Random();
        this.textboxes.createInfoText(this.hero.getName(), this.enemy.getName());
        this.textboxes.createHeroText(this.hero.getText());
        this.textboxes.createEnemyText(this.enemy.getText());
    }

    public Buttons getButtons() {
        return buttons;
    }

    public TextBoxes getTextboxes() {
        return textboxes;
    }

    public Hero getHero() {
        return hero;
    }

    public Character getEnemy() {
        return enemy;
    }

    public void heavyClicked() {
        int damage = this.hero.heavyAttack();
        if (damage == 0) {
            missed(true);
        } else {
            hit(damage, true, " with great power");
        }
    }

    private void enemyGetsHit(int damage) {
        this.enemy.getHit(damage);
        if (this.enemy.isAlive()) {
            this.textboxes.updateEnemyText(enemy.getText());
        }
    }

    public void enemyDied() {
        this.hero.addKill();
        this.textboxes.addInfoText("\n\n" + this.enemy.getName()
                + " did not survive your final blow.");

        if (this.random.nextInt(2) == 1) {
            enemyDroppedPotion();
        }
        this.enemy = this.enemies.randomEnemy();
        this.textboxes.updateEnemyText(this.enemy.getText());
        this.textboxes.addInfoText("\n\nA new opponent "
                + this.enemy.getName() + " has accepted your challenge!");

    }

    public boolean RecoverClicked() {
        if (this.hero.drinkHealthPotion()) {
            this.textboxes.addInfoText("\n\nYou drank a magical "
                    + "potion and now you feel refreshed, hydrated and ready to "
                    + "continue the fight!");
            this.textboxes.updateHeroText(this.hero.getText());
            return true;
        } else {
            this.textboxes.addInfoText("\n\nYou do not have "
                    + "any magical potions left. What a shame.");
            return false;
        }
    }

    public int enemyTurn() {
        return random.nextInt(2);
    }

    private void heroDied() {
        this.textboxes.addInfoText("\n\nYou died. You managed to defeat " + numberToString(this.hero.getKills())
                + " opponents before you died. Better luck next time!.");
    }

    public void missed(boolean heroMissed) {
        if (heroMissed) {
            this.textboxes.addInfoText("\n\nYou tried to attack your best, but missed your attack.");
        } else {
            this.textboxes.addInfoText("\n\n" + this.enemy.getName() + " tried to attack you, but " + this.enemy.getName() + " missed.");
        }
    }

    public void hit(int damage, boolean heroAttacked, String how) {
        if (heroAttacked) {
            this.textboxes.addInfoText("\n\nYou attacked the " + this.enemy.getName() + how + ", "
                    + "dealing " + damage + " damage!");
            enemyGetsHit(damage);
        } else {
            this.textboxes.addInfoText("\n\n" + this.enemy.getName() + " attacked you" + how + ", "
                    + "dealing " + damage + " damage!");
            this.hero.getHit(damage);
            this.textboxes.updateHeroText(this.hero.getText());
        }
    }

    public void enemyAttack(boolean typeWasQuick) {
        int damage = -1;

        if (typeWasQuick) {
            damage = enemy.quickAttack();
        } else {
            damage = enemy.heavyAttack();
        }
        if (damage == 0) {
            missed(false);
            return;
        }

        if (typeWasQuick) {
            hit(damage, false, " swiftly");
        } else {
            hit(damage, false, " with great power");
        }

        if (!this.hero.isAlive()) {
            heroDied();
        }
    }

    private void enemyDroppedPotion() {
        this.hero.giveHealthPotions(1);
        this.textboxes.addInfoText("\n\nThe " + this.enemy.getName() + " dropped "
                + "a health potion. Now you have " + numberToString(this.hero.getHealthPotions()) + " health potions!");
    }

    private String numberToString(int number) {
        if (number >= 10) {
            return "" + number;
        }

        if (number == 9) {
            return "nine";
        }
        if (number == 8) {
            return "eight";
        }
        if (number == 7) {
            return "seven";
        }
        if (number == 6) {
            return "six";
        }
        if (number == 5) {
            return "five";
        }
        if (number == 4) {
            return "four";
        }
        if (number == 3) {
            return "three";
        }
        if (number == 2) {
            return "two";
        }
        if (number == 1) {
            return "one";
        }
        return "no";
    }

}
