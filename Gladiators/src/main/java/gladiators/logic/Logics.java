package gladiators.logic;

import gladiators.characters.Hero;
import gladiators.characters.Enemies;
import gladiators.characters.Character;
import gladiators.ui.Buttons;
import javafx.geometry.Insets;
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
    private boolean heroTurn;

    public Logics(String heroName) {
        this.hero = new Hero(heroName);
        this.heroTurn = true;
        this.enemies = new Enemies();
        this.enemy = enemies.randomEnemy();
        this.buttons = new Buttons();
        this.heroText = new TextArea();
        this.enemyText = new TextArea();
        this.infoText = new TextArea();
        createEnemyText();
        createInfoText();
        createHeroText();
    }

    private void createEnemyText() {
        this.enemyText.setTranslateX(570);
        this.enemyText.setTranslateY(350);
        this.enemyText.setPrefSize(220, 90);
        this.enemyText.setText(this.enemy.getText());
        this.enemyText.setFont(new Font("Font.ARIEL", 25));
        this.enemyText.setEditable(false);
    }

    private void createHeroText() {
        this.heroText.setText(this.hero.getText());
        this.heroText.setTranslateX(340);
        this.heroText.setTranslateY(350);
        this.heroText.setPrefSize(220, 90);
        this.heroText.setFont(new Font("Font.ARIEL", 25));
        this.heroText.setEditable(false);
    }

    private void createInfoText() {
        this.infoText.setTranslateX(488);
        this.infoText.setTranslateY(452);
        this.infoText.setPrefSize(310, 147);
        this.infoText.setFont(new Font("Font.ARIEL", 15));
        this.infoText.setWrapText(true);
        this.infoText.setEditable(false);
        this.infoText.setText("Welcome to the arena " + this.hero.getName() + "!\n"
                + "Your first opponent is " + this.enemy.getName() + ".");
    }

    public void quickClicked() {
        if (!this.heroTurn) {
            return;
        }
//        this.heroTurn = false;

        int damage = this.hero.quickAttack();
        if (damage == 0) {
            this.infoText.setText(this.infoText.getText() + "\n\nYou tried to attack your best, but missed your attack.");
            this.infoText.appendText("");
        } else {
            this.infoText.setText(this.infoText.getText() + "\n\nYou attack the " + this.enemy.getName() + " swiftly, "
                    + "and manage to deal " + damage + " damage!");
            this.infoText.appendText("");
            this.enemy.getHit(damage);
            if (this.enemy.isAlive()) {
                this.enemyText.setText(this.enemy.getText());
            } else {
                enemyDied();
            }
        }

    }

    private void enemyDied() {
        this.infoText.setText(this.infoText.getText() + "\n\n" + this.enemy.getName()
                + " did not survive your final blow.");
        this.infoText.appendText("");

        this.enemy = this.enemies.randomEnemy();
        this.enemyText.setText(this.enemy.getText());
        this.infoText.setText(this.infoText.getText() + "\n\nA new opponent "
                + this.enemy.getName() + " has accepted your challenge!");
        this.infoText.appendText("");

    }

}
