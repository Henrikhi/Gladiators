package gladiators.logic;

import gladiators.characters.Hero;
import gladiators.characters.Enemies;
import gladiators.characters.Character;
import gladiators.ui.Buttons;
import java.util.Random;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class Logics {

    public Hero hero;
    public Enemies enemies;
    public Character enemy;
    public Buttons buttons;
    public TextArea heroText;
    public TextArea enemyText;
    public TextArea infoText;
    private Random random;

    public Logics(String heroName) {
        this.hero = new Hero(heroName);
        this.enemies = new Enemies();
        this.enemy = enemies.randomEnemy();
        this.buttons = new Buttons();
        this.heroText = new TextArea();
        this.enemyText = new TextArea();
        this.infoText = new TextArea();
        createEnemyText();
        createInfoText();
        createHeroText();
        this.random = new Random();
    }

    private void createEnemyText() {
        this.enemyText.setTranslateX(570);
        this.enemyText.setTranslateY(350);
        this.enemyText.setPrefSize(220, 90);
        this.enemyText.setFont(new Font("Font.ARIEL", 25));
        this.enemyText.setEditable(false);
        this.enemyText.setText(this.enemy.getText());
    }

    private void createHeroText() {
        this.heroText.setTranslateX(340);
        this.heroText.setTranslateY(350);
        this.heroText.setPrefSize(220, 90);
        this.heroText.setFont(new Font("Font.ARIEL", 25));
        this.heroText.setEditable(false);
        this.heroText.setText(this.hero.getText());
    }

    private void createInfoText() {
        this.infoText.setTranslateX(488);
        this.infoText.setTranslateY(452);
        this.infoText.setPrefSize(310, 147);
        this.infoText.setFont(new Font("Font.ARIEL", 15));
        this.infoText.setWrapText(true);
        this.infoText.setEditable(false);
        this.infoText.setText("Welcome to the arena " + this.hero.getName() + "!\n"
                + "Your first challenger is " + this.enemy.getName() + ".");
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
            this.enemyText.setText(this.enemy.getText());
        }
    }

    public void enemyDied() {
        this.hero.addKill();
        this.infoText.setText(this.infoText.getText() + "\n\n" + this.enemy.getName()
                + " did not survive your final blow.");
        this.infoText.appendText("");

        if (this.random.nextInt(2) == 1) {
            enemyDroppedPotion();
        }
        this.enemy = this.enemies.randomEnemy();
        this.enemyText.setText(this.enemy.getText());
        this.infoText.setText(this.infoText.getText() + "\n\nA new opponent "
                + this.enemy.getName() + " has accepted your challenge!");
        this.infoText.appendText("");

    }

    public boolean RecoverClicked() {
        if (this.hero.drinkHealthPotion()) {
            this.infoText.setText(this.infoText.getText() + "\n\nYou drank a magical "
                    + "potion and now you feel refreshed, hydrated and ready to "
                    + "continue the fight!");
            this.infoText.appendText("");
            this.heroText.setText(this.hero.getText());
            return true;
        } else {
            this.infoText.setText(this.infoText.getText() + "\n\nYou do not have "
                    + "any magical potions left. What a shame.");
            this.infoText.appendText("");
            return false;
        }
    }

    public int enemyTurn() {
        return random.nextInt(2);
    }

    private void heroDied() {
        this.infoText.setText(this.infoText.getText() + "\n\nYou died. You managed to defeat " + numberToString(this.hero.getKills())
                + " opponents before you died. Better luck next time!.");
        this.infoText.appendText("");
    }

    public void missed(boolean heroMissed) {
        if (heroMissed) {
            this.infoText.setText(this.infoText.getText() + "\n\nYou tried to attack your best, but missed your attack.");
            this.infoText.appendText("");
        } else {
            this.infoText.setText(this.infoText.getText() + "\n\n" + this.enemy.getName() + " tried to attack you, but " + this.enemy.getName() + " missed.");
            this.infoText.appendText("");
        }
    }

    public void hit(int damage, boolean heroAttacked, String how) {
        if (heroAttacked) {
            this.infoText.setText(this.infoText.getText() + "\n\nYou attacked the " + this.enemy.getName() + how + ", "
                    + "dealing " + damage + " damage!");
            this.infoText.appendText("");
            enemyGetsHit(damage);
        } else {
            this.infoText.setText(this.infoText.getText() + "\n\n" + this.enemy.getName() + " attacked you" + how + ", "
                    + "dealing " + damage + " damage!");
            this.infoText.appendText("");
            this.hero.getHit(damage);
            this.heroText.setText(this.hero.getText());
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
        this.infoText.setText(this.infoText.getText() + "\n\nThe " + this.enemy.getName() + " dropped "
                + "a health potion. Now you have " + numberToString(this.hero.getHealthPotions()) + " health potions!");
        this.infoText.appendText("");
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
