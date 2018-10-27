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
        Vector2 shipPosition = ship.getPosition();
        graphics.imageMode(PConstants.CENTER);
        graphics.tint(200, 0 ,0);
        graphics.translate(shipPosition.getX()/2, shipPosition.getY()/2);
        graphics.rotate(ship.getDirection().angle());
        graphics.image(images.get(0), shipPosition.getX(), shipPosition.getY(), ship.getWidth(), ship.getHeight());
    }

}
