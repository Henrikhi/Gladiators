package gladiators.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class TextBoxes {

    private TextArea heroText;
    private TextArea enemyText;
    private TextArea infoText;
    private TextArea heroTextOld;
    private TextArea enemyTextOld;
    private TextArea infoTextOld;
    private Font fontCharacters;
    private Font fontInfo;

    public TextBoxes() {
        this.heroText = new TextArea();
        this.enemyText = new TextArea();
        this.infoText = new TextArea();
        this.heroTextOld = new TextArea();
        this.enemyTextOld = new TextArea();
        this.infoTextOld = new TextArea();

        this.fontCharacters = createFont("Adonais.ttf", 24);
        this.fontInfo = createFont("Minecraft.ttf", 16);
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
        this.enemyText.setTranslateY(360);
        this.enemyText.setPrefSize(220, 80);
        this.enemyText.setEditable(false);
        this.enemyText.setText(enemyText);
        this.enemyText.setFont(fontCharacters);
        this.enemyTextOld.setText(enemyText);
    }

    public void createHeroText(String heroText) {
        this.heroText.setTranslateX(340);
        this.heroText.setTranslateY(360);
        this.heroText.setPrefSize(220, 80);
        this.heroText.setEditable(false);
        this.heroText.setText(heroText);
        this.heroText.setFont(fontCharacters);
        this.heroTextOld.setText(heroText);
    }

    public void createInfoText(String heroName, String enemyName) {
        this.infoText.setTranslateX(488);
        this.infoText.setTranslateY(452);
        this.infoText.setPrefSize(310, 147); //310, 147
        this.infoText.setWrapText(true);
        this.infoText.setEditable(false);
        this.infoText.setFont(fontInfo);
        this.infoTextOld.setText(this.infoText.getText());
    }

    public void updateEnemyTextOld(String enemyText) {
        this.enemyTextOld.setText(enemyText);
    }

    public void updateHeroTextOld(String heroText) {
        this.heroTextOld.setText(heroText);
    }

    public void addInfoTextOld(String text) {
        if (this.infoTextOld.getText().equals("")) {
            this.infoTextOld.setText(text);
        } else {
            this.infoTextOld.setText(this.infoTextOld.getText() + "\n\n" + text);
            this.infoTextOld.appendText("");
        }
    }

    public void updateHeroText() {
        this.heroText.setText(this.heroTextOld.getText());
    }

    public void updateEnemyText() {
        this.enemyText.setText(this.enemyTextOld.getText());
    }

    public void updateInfoText() {
        this.infoText.setText(this.infoTextOld.getText());
        this.infoText.appendText("");
    }

    private Font createFont(String fontName, int size) {
        try {
            final Font f = Font.loadFont(new FileInputStream(new File("../Files/Fonts/" + fontName)), size);
            return f;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
