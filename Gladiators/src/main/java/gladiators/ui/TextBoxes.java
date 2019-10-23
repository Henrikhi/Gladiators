package gladiators.ui;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class TextBoxes {

    private TextArea heroText;
    private TextArea enemyText;
    private TextArea infoText;

    public TextBoxes() {
        this.heroText = new TextArea();
        this.enemyText = new TextArea();
        this.infoText = new TextArea();
    }

    public TextArea getInfoText() {
        return infoText;
    }

    public TextArea getHeroText() {
        return heroText;
    }

    public TextArea getEnemyText() {
        return enemyText;
    }
    
    

    public void createEnemyText(String enemyText) {
        this.enemyText.setTranslateX(570);
        this.enemyText.setTranslateY(350);
        this.enemyText.setPrefSize(220, 90);
        this.enemyText.setFont(new Font("Font.ARIEL", 25));
        this.enemyText.setEditable(false);
        this.enemyText.setText(enemyText);
    }

    public void createHeroText(String heroText) {
        this.heroText.setTranslateX(340);
        this.heroText.setTranslateY(350);
        this.heroText.setPrefSize(220, 90);
        this.heroText.setFont(new Font("Font.ARIEL", 25));
        this.heroText.setEditable(false);
        this.heroText.setText(heroText);
    }

    public void createInfoText(String heroName, String enemyName) {
        this.infoText.setTranslateX(488);
        this.infoText.setTranslateY(452);
        this.infoText.setPrefSize(310, 147);
        this.infoText.setFont(new Font("Font.ARIEL", 15));
        this.infoText.setWrapText(true);
        this.infoText.setEditable(false);
        this.infoText.setText("Welcome to the arena " + heroName + "!\n"
                + "Your first challenger is " + enemyName + ".");
    }

    public void updateEnemyText(String enemyText) {
        this.enemyText.setText(enemyText);
    }
    
    public void updateHeroText(String heroText) {
        this.heroText.setText(heroText);
    }
    
    public void addInfoText(String text) {
        this.infoText.setText(this.infoText.getText() + text);
        this.infoText.appendText("");
    }

}
