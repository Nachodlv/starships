package edu.austral.starship.base.levels;

import edu.austral.starship.base.collision.CollisionEngine;
import edu.austral.starship.base.engines.*;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.player.Player;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;

public class MainLevel implements Level {

    private List<Engine> engines;
    private Stage stage;

    public MainLevel(Stage stage) {
        this.engines = new ArrayList<>();
        this.stage = stage;
    }

    @Override
    public void draw(PGraphics graphics) {
        stage.update(graphics);
        for (Engine engine :
                engines) {
            engine.execute(stage);
        }
    }

    @Override
    public List<Engine> getEngines() {
        return engines;
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void setup(List<Player> players, ImageLoader imageLoader) {
        createEngines(imageLoader);
        Ship ship = GameObjectFactory.createShip(GameObjectFactory.createWeapon(players.get(0)));
        players.get(0).setShip(ship);
        stage.addGameObject(ship);
        Ship ship2 = GameObjectFactory.createShip(GameObjectFactory.createWeapon(players.get(1)));
        players.get(1).setShip(ship2);
        stage.addGameObject(ship2);
    }

    private void createEngines(ImageLoader imageLoader) {
        engines.add(new MoveEngine());
        engines.add(new RenderEngine(imageLoader));
        engines.add(new SpawnerEngine(50 ,5));
        engines.add(new DeleteEngine());
        engines.add(new CollisionEngineContainer(new CollisionEngine<>()));
    }

}
