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
        this.enemy.gotDamaged(damage);
        if (this.enemy.isAlive()) {
            this.textboxes.updateEnemyTextOld(enemy.getText());
        }
    }

    public void enemyDied() {
        this.hero.addKill();
        this.textboxes.addInfoTextOld(this.enemy.getName()
                + " did not survive your final blow.");

        if (this.random.nextInt(2) == 1) {
            enemyDroppedPotion();
        }
        updateTextBoxes();
    }

    public void newEnemy() {
        this.enemy = this.enemies.randomEnemy();
        this.textboxes.updateEnemyTextOld(this.enemy.getText());
        this.textboxes.addInfoTextOld("A new opponent "
                + this.enemy.getName() + " has accepted your challenge!");
    }

    public boolean RecoverClicked() {
        if (this.hero.drinkHealthPotion()) {
            this.textboxes.addInfoTextOld("You drank a magical "
                    + "potion and now you feel refreshed, hydrated and ready to "
                    + "continue the fight! You have "
                    + numberToString(this.hero.getHealthPotions())
                    + " magical potions left.");
            this.textboxes.updateHeroTextOld(this.hero.getText());
            return true;
        } else {
            this.textboxes.addInfoTextOld("You do not have "
                    + "any magical potions left. What a shame.");
            updateTextBoxes();
            return false;
        }
    }

    public int enemyTurn() {
        return random.nextInt(2);
    }

    private void heroDied() {
        this.textboxes.addInfoTextOld("You died. You managed to defeat " + numberToString(this.hero.getKills())
                + " opponents before you died. Better luck next time!.");
    }

    public void missed(boolean heroMissed) {
        if (heroMissed) {
            this.textboxes.addInfoTextOld("You tried to attack your best, but missed your attack.");
        } else {
            this.textboxes.addInfoTextOld("" + this.enemy.getName() + " tried to attack you, but " + this.enemy.getName() + " missed.");
        }
    }

    public void hit(int damage, boolean heroAttacked, String how) {
        if (heroAttacked) {
            this.textboxes.addInfoTextOld("You attacked the " + this.enemy.getName() + how + ", "
                    + "dealing " + damage + " damage!");
            enemyGetsHit(damage);
        } else {
            this.textboxes.addInfoTextOld("" + this.enemy.getName() + " attacked you" + how + ", "
                    + "dealing " + damage + " damage!");
            this.hero.gotDamaged(damage);
            this.textboxes.updateHeroTextOld(this.hero.getText());
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
        this.textboxes.addInfoTextOld("The " + this.enemy.getName() + " dropped "
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

    public void activateButtons() {
        this.buttons.getAll().forEach(button -> {
            button.setDisable(false);
        });
    }

    public void disactivateButtons() {
        this.buttons.getAll().forEach(button -> {
            button.setDisable(true);
        });
    }

    public void updateTextBoxes() {
        this.textboxes.updateHeroText();
        this.textboxes.updateEnemyText();
        this.textboxes.updateInfoText();
    }

    public void heroEntryDone() {
        this.textboxes.addInfoTextOld("Welcome to the arena " + this.hero.getName() + "!");
    }

    public void enemyEntryDone() {
        this.textboxes.addInfoTextOld("Your first challenger is " + this.enemy.getName() + ".");
    }

}
