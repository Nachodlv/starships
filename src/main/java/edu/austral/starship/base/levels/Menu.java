package edu.austral.starship.base.levels;

import edu.austral.starship.base.engines.Engine;
import edu.austral.starship.base.engines.RenderEngine;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.levels.Screen.FirstMenu;
import edu.austral.starship.base.levels.Screen.Screen;
import edu.austral.starship.base.player.Player;
import edu.austral.starship.base.player.PlayerNumber;
import processing.core.PGraphics;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Menu implements Level {
    private Stage stage;
    private Screen mainMenu;
    private List<Player> players;
    private List<Engine> engines;
    private LevelsController levelsController;

    public Menu(Stage stage) {
        this.stage = stage;
        engines = new ArrayList<>();
    }

    @Override
    public void draw(PGraphics graphics, Set<Integer> keySet) {
       if(containsKey(keySet, KeyEvent.VK_1)) createPlayers(1);
       else if(containsKey(keySet, KeyEvent.VK_2)) createPlayers(2);
       else if(containsKey(keySet, KeyEvent.VK_3)) createPlayers(3);
       else if(containsKey(keySet, KeyEvent.VK_4)) createPlayers(4);

       stage.update(graphics);
        for (Engine engine : engines) {
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
    public void setup(ImageLoader imageLoader, LevelsController levelsController) {
        engines.add(new RenderEngine(imageLoader));
        this.levelsController = levelsController;
        mainMenu = new FirstMenu();
    }

    @Override
    public void init(List<Player> players) {
        this.players = new ArrayList<>();
        stage.resetStage();
        for (GameObject gameObject : mainMenu.draw()) {
            stage.addGameObject(gameObject);
        }
    }

    private boolean containsKey(Set<Integer> keySet, Integer key) {
        for (Integer integer : keySet) {
            if(integer.equals(key)) return true;
        }
        return false;
    }

    private void createPlayers(int quantity) {
        for (int i = 0; i < quantity; i++) {
            switch (i) {
                case 0:
                    players.add(new Player(PlayerNumber.PLAYER_ONE));
                    break;
                case 1:
                    players.add(new Player(PlayerNumber.PLAYER_TWO));
                    break;
                case 2:
                    players.add(new Player(PlayerNumber.PLAYER_THREE));
                    break;
                default:
                    players.add(new Player(PlayerNumber.PLAYER_FOUR));
                    break;
            }
        }
        stage.resetStage();
        levelsController.nextLevel(players);
    }
}
