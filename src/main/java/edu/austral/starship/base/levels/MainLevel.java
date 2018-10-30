package edu.austral.starship.base.levels;

import edu.austral.starship.base.collision.CollisionEngine;
import edu.austral.starship.base.engines.*;
import edu.austral.starship.base.framework.FontLoader;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.player.Player;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainLevel implements Level {

    private static final double MAX_SCORE = 5000;

    private List<Engine> engines;
    private Stage stage;
    private List<Player> players;
    private LevelsController levelsController;
    private boolean gameEnded;

    public MainLevel(Stage stage) {
        this.engines = new ArrayList<>();
        this.stage = stage;
    }

    @Override
    public void draw(PGraphics graphics, Set<Integer> keySet) {
        stage.update(graphics);
        for (Engine engine :
                engines) {
            engine.execute(stage);
        }
        if(gameEnded()) {
            stage.resetStage();
            levelsController.nextLevel(players);
        }
        keyController(keySet);

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
    public void setup(ImageLoader imageLoader, LevelsController levelsController) {
        this.levelsController = levelsController;
        createEngines(imageLoader);
    }

    @Override
    public void init(List<Player> players) {
        this.players = players;
        stage.resetStage();
        for (Player player : players) {
            player.resetScore();
            stage.addGameObject(GameObjectFactory.createShip(player));
            stage.addGameObject(GameObjectFactory.createLifeText(player));
            stage.addGameObject(GameObjectFactory.createScoreText(player));
        }
    }

    private void createEngines(ImageLoader imageLoader) {
        engines.add(new MoveEngine());
        engines.add(new RenderEngine(imageLoader));
        engines.add(new SpawnerEngine(30 ,5));
        engines.add(new DeleteEngine());
        engines.add(new CollisionEngineContainer(new CollisionEngine<>()));
    }

    /*
    * Return true when there are less than two players active if the total player is more than one
    * Return true when there are no players active if the total player is equals to one
    * */
    private boolean gameEnded() {
        int activePlayers = 0;
        for (Player player : players) {
            if(player.getShip().isActive()) activePlayers ++;
            if(player.getScore() >= MAX_SCORE) return true;
        }
        if(players.size() >= 2) return activePlayers <= 1;
        else return activePlayers == 0;
    }

    private void keyController(Set<Integer> keySet) {
        for(Integer key: keySet) {
            for(Player player: players) {
                if(player.hasKey(key)) player.keyPressed(key);
            }
        }
    }

}
