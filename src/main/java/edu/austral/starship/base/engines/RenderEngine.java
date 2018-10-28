package edu.austral.starship.base.engines;

import edu.austral.starship.base.framework.FontLoader;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.HUE.Text;
import edu.austral.starship.base.gameobjects.animations.Explosion;
import edu.austral.starship.base.gameobjects.rigid_bodies.Asteroid;
import edu.austral.starship.base.gameobjects.rigid_bodies.Bullet;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.levels.Stage;
import edu.austral.starship.base.renders.*;
import processing.core.PFont;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RenderEngine implements Engine {
    private Stage stage;
    private ShipRender shipRender;
    private BulletRender bulletRender;
    private AsteroidRender asteroidRender;
    private AnimationRender explosionRender;
    private TextRender textRender;

    public RenderEngine(ImageLoader imageLoader) {
        PImage image = imageLoader.load("assets/sprites/ship3.png");
        List<PImage> list = Collections.singletonList(image);
        this.shipRender = new ShipRender(list);

        PImage bullet = imageLoader.load("assets/sprites/laser.png");
        this.bulletRender = new BulletRender(bullet);

        PImage asteroid = imageLoader.load("assets/sprites/asteroid.png");
        this.asteroidRender = new AsteroidRender(asteroid);

        List<PImage> explosions = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            explosions.add(imageLoader.load("assets/sprites/explosion/explosion-" + i + ".png"));
        }
        explosionRender = new AnimationRender(explosions);

        textRender = new TextRender();
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

    @Override
    public void acceptsExplosion(Explosion explosion) {
        explosionRender.render(stage.getpGraphics(), explosion);
    }

    @Override
    public void acceptsText(Text text) {
        textRender.render(stage.getpGraphics(), text);
    }
}
