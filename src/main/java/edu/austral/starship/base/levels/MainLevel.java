package edu.austral.starship.base.levels;

import edu.austral.starship.base.engines.Engine;
import edu.austral.starship.base.levels.gameObjectFactory.GameObjectFactory;
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
    public void setup(List<Engine> engines, LevelsController levelsController) {
        this.engines = engines;
        this.levelsController = levelsController;
    }

    @Override
    public void init(List<Player> players) {
        this.players = players;
        stage.resetStage();
        for (Player player : players) {
            player.resetScore();
            stage.addGameObject(player.getShip());
            stage.addGameObject(GameObjectFactory.createLifeText(player));
            stage.addGameObject(GameObjectFactory.createScoreText(player));
        }
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
