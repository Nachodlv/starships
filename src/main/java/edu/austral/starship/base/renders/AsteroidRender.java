package edu.austral.starship.base.renders;

import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

public class AsteroidRender {
    private PImage image;

    public AsteroidRender(PImage image) {
        this.image = image;
    }

    public void render(PGraphics graphics, Asteroid asteroid) {
        graphics.pushMatrix();
        Vector2 asteroidPosition = asteroid.getPosition();
        graphics.imageMode(PConstants.CENTER);
        graphics.translate(asteroidPosition.getX(), asteroidPosition.getY());
        graphics.rotate(1);
        graphics.image(image, 0, 0, asteroid.getWidth(), asteroid.getHeight());
        graphics.popMatrix();
    }
}
