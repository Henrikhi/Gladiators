package gladiators.logic;

import gladiators.characters.Hero;
import gladiators.characters.Enemies;
import gladiators.characters.Character;
import gladiators.ui.Buttons;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class Logics {

    public Hero hero;
    public Enemies enemies;
    public Character enemy;
    public Buttons buttons;
    public TextArea heroText;
    public TextArea enemyText;

    public Logics() {
        this.hero = null;
        this.enemies = new Enemies();
        this.enemy = enemies.randomEnemy();
        this.buttons = new Buttons();
        this.heroText = new TextArea();
        this.enemyText = new TextArea();
        createEnemyText();
    }

    public void newHero(String name) {
        this.hero = new Hero(name);
        createHeroText();
    }

    private void createEnemyText() {
        this.enemyText.setTranslateX(10);
        this.enemyText.setTranslateY(10);
        this.enemyText.setPrefSize(350, 100);
        this.enemyText.setText(this.enemy.getText());
        this.enemyText.setFont(new Font("Font.ARIEL", 25));
    }

    private void createHeroText() {
        this.heroText.setText(this.hero.getText());
        this.heroText.setTranslateX(400);
        this.heroText.setTranslateY(340);
        this.heroText.setPrefSize(350, 100);
        this.heroText.setFont(new Font("Font.ARIEL", 25));
    }

}
