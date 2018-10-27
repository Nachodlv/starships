package edu.austral.starship.base.renders;

import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.List;


public class ShipRender {

    private List<PImage> images;

    public ShipRender(List<PImage> images) {
        this.images = images;
    }

    public void render(PGraphics graphics, Ship ship) {
        graphics.pushMatrix();
        Vector2 shipPosition = ship.getPosition();
        graphics.tint(200, 0 ,0);
        graphics.imageMode(PConstants.CENTER);
        graphics.translate(shipPosition.getX(), shipPosition.getY());
        graphics.rotate(ship.getAngle());
        graphics.image(images.get(0), 0, 0, ship.getWidth(), ship.getHeight());
        graphics.popMatrix();
    }

}
