package edu.austral.starship.base.renders;

import edu.austral.starship.base.Color;
import edu.austral.starship.base.gameobjects.HUE.Text;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;

public class TextRender {


    public void render(PGraphics graphics, Text text) {
        graphics.pushMatrix();


        Vector2 textPosition = text.getPosition();
        graphics.imageMode(PConstants.CENTER);
        graphics.translate(textPosition.getX(), textPosition.getY());
        graphics.textSize(text.getTextSize());
        graphics.textAlign(text.getTextAlign());
        //graphics.textFont(font);

        Color textColor = text.getTextColor();
        graphics.fill(textColor.getR(), textColor.getG(), textColor.getB());

        graphics.text(text.getText(), 0, 0);

        graphics.popMatrix();
    }
}
