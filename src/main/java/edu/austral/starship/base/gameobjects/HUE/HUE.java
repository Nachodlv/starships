package edu.austral.starship.base.gameobjects.HUE;

import edu.austral.starship.base.Color;
import edu.austral.starship.base.gameobjects.Drawable;

public interface HUE extends Drawable {
    int getHeight();
    int getWidth();
    Color getTextColor();
    String getText();
    int getTextSize();
}
