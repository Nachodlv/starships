package edu.austral.starship.base.renders;

import edu.austral.starship.base.gameobjects.animations.Animation;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.List;

public class AnimationRender {
    List<PImage> images;

    public AnimationRender(List<PImage> images) {
        this.images = images;
    }

    public void render(PGraphics graphics, Animation animation) {
        graphics.pushMatrix();
        Vector2 asteroidPosition = animation.getPosition();
        graphics.imageMode(PConstants.CENTER);
        graphics.translate(asteroidPosition.getX(), asteroidPosition.getY());
        graphics.image(images.get(animation.getCurrentState()), 0, 0, animation.getWidth(), animation.getHeight());
        graphics.popMatrix();
    }
}
