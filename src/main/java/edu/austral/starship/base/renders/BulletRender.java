package edu.austral.starship.base.renders;

import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

public class BulletRender {
    private PImage image;

    public BulletRender(PImage image) {
        this.image = image;
    }

    public void render(PGraphics graphics, Bullet bullet) {
        graphics.pushMatrix();
        Vector2 bulletPosition = bullet.getPosition();
        graphics.imageMode(PConstants.CENTER);
        graphics.translate(bulletPosition.getX(), bulletPosition.getY());
        graphics.rotate(bullet.getAngle());
        graphics.image(image, 0, 0, bullet.getWidth(), bullet.getHeight());
        graphics.popMatrix();
    }

}
