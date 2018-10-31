package edu.austral.starship.base.levels;

import edu.austral.starship.base.engines.Engine;
import edu.austral.starship.base.levels.screen.FirstMenu;
import edu.austral.starship.base.player.Player;
import edu.austral.starship.base.player.PlayerNumber;
import processing.core.PGraphics;

import java.awt.event.KeyEvent;
import java.util.*;

public class Menu implements Level {
    private Stage stage;
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
    public void setup(List<Engine> engines, LevelsController levelsController) {
        this.engines = engines;
        this.levelsController = levelsController;
    }

    @Override
    public void init(List<Player> players) {
        this.players = new ArrayList<>();
        stage.resetStage();
        stage.addGameObjects(new FirstMenu().draw());
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
