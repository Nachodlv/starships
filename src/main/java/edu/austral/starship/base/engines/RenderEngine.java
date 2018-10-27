package edu.austral.starship.base.engines;

import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.levels.Stage;
import edu.austral.starship.base.renders.ShipRender;
import processing.core.PImage;

import java.util.Collections;
import java.util.List;

public class RenderEngine implements Engine {
    private Stage stage;
    private ShipRender shipRender;

    public RenderEngine(ImageLoader imageLoader) {
        PImage image = imageLoader.load("assets/sprites/ship3.png");
        List<PImage> list = Collections.singletonList(image);
        this.shipRender = new ShipRender(list);
    }

    @Override
    public void execute(Stage stage) {
        this.stage = stage;
        List<GameObject> gameObjects = stage.getGameObjects();
        for (GameObject gameObject: gameObjects) {
            gameObject.accepts(this);
        }
    }

    @Override
    public void acceptsAsteroid(Asteroid asteroid) {

    }

    @Override
    public void acceptsBullet(Bullet bullet) {

    }

    @Override
    public void acceptsShip(Ship ship) {
        shipRender.render(stage.getpGraphics(), ship);
    }
}
