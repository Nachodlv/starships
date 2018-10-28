package edu.austral.starship.base.gameobjects.animations;

import edu.austral.starship.base.gameobjects.Drawable;

public interface Animation extends Drawable {
    int getCurrentState();
    int getWidth();
    int getHeight();
}
