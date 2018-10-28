package edu.austral.starship.base.engines;

import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.levels.Stage;
import edu.austral.starship.base.renders.AsteroidRender;
import edu.austral.starship.base.renders.BulletRender;
import edu.austral.starship.base.renders.ShipRender;
import processing.core.PImage;

import java.util.Collections;
import java.util.List;

public class RenderEngine implements Engine {
    private Stage stage;
    private ShipRender shipRender;
    private BulletRender bulletRender;
    private AsteroidRender asteroidRender;

    public RenderEngine(ImageLoader imageLoader) {
        PImage image = imageLoader.load("assets/sprites/ship3.png");
        List<PImage> list = Collections.singletonList(image);
        this.shipRender = new ShipRender(list);

        PImage bullet = imageLoader.load("assets/sprites/laser.png");
        this.bulletRender = new BulletRender(bullet);

        PImage asteroid = imageLoader.load("assets/sprites/asteroid.png");
        this.asteroidRender = new AsteroidRender(asteroid);
    }

    @Override
    public void execute(Stage stage) {
        this.stage = stage;
        for (GameObject gameObject: stage.getGameObjects()) {
            gameObject.accepts(this);
        }
    }

    @Override
    public void acceptsAsteroid(Asteroid asteroid) {
        asteroidRender.render(stage.getpGraphics(), asteroid);
    }

    @Override
    public void acceptsBullet(Bullet bullet) {
        bulletRender.render(stage.getpGraphics(), bullet);
    }

    @Override
    public void acceptsShip(Ship ship) {
        shipRender.render(stage.getpGraphics(), ship);
    }
}
