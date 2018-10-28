package edu.austral.starship.base.levels;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.engines.*;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.gameobjects.HUE.Text;
import edu.austral.starship.base.player.Player;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameOver implements Level {

    private Stage stage;
    private List<Engine> engines;
    private LevelsController levelsController;
    private boolean leaderBoardLoaded;
    private List<Player> players;

    public GameOver(Stage stage) {
        this.stage = stage;
        this.engines = new ArrayList<>();
        this.leaderBoardLoaded = false;
    }

    @Override
    public void draw(PGraphics graphics, Set<Integer> keySet) {
        stage.update(graphics);
        for (Engine engine :
                engines) {
            engine.execute(stage);
        }
        for (Integer integer : keySet) {
            if(integer == KeyEvent.VK_R) {
                levelsController.previousLevel();
                resetLevel();
                return;
            } else if(integer == KeyEvent.VK_M) {
                levelsController.setLevel(0);
                resetLevel();
                return;
            }
        }
        if(!leaderBoardLoaded) {
            drawScreen();
            leaderBoardLoaded = true;
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
    public void setup(List<Player> players, ImageLoader imageLoader, LevelsController levelsController) {
        engines.add(new RenderEngine(imageLoader));
        this.levelsController = levelsController;
        this.players = players;
    }

    private void drawScreen() {
        stage.addGameObject(GameObjectFactory.createGenericText("Game Over", 50,
                Vector2.vector((float)CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 200), PConstants.CENTER));
        stage.addGameObject(GameObjectFactory.createGenericText("Press R to restart. Press M to go to the menu", 30,
                Vector2.vector((float)CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 50), PConstants.CENTER));
        if(players.size() <= 1) soloPlayer(players.get(0));
        else createLeaderBoard();
    }

    private void soloPlayer(Player player) {
        stage.addGameObject(GameObjectFactory.createGenericText("Your score is" + player.getScore(),
                30, Vector2.vector((float)CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 100), PConstants.CENTER));
    }

    private void createLeaderBoard() {
        sortPlayers(players);
        stage.addGameObject(GameObjectFactory.createGenericText("The winner is " + players.get(0).getPlayerNumber().getName(),
                30, Vector2.vector((float)CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 100), PConstants.CENTER));
        List<Text> texts = GameObjectFactory.createLeaderBoard(players,
                Vector2.vector((float)CustomGameFramework.WIDTH/2 - 200, (float)CustomGameFramework.HEIGHT/2 + 50), 30);
        for (Text text : texts) {
            stage.addGameObject(text);
        }

    }
    private void sortPlayers(List<Player> players) {
        players.sort((player, t1) -> Double.compare(t1.getScore(), player.getScore()));
    }

    private void resetLevel() {
        stage.resetStage();
        leaderBoardLoaded = false;
        for (Player player : players) {
            player.resetScore();
        }
    }

}
