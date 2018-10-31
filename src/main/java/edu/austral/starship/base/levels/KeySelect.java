package edu.austral.starship.base.levels;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.engines.Engine;
import edu.austral.starship.base.levels.screen.KeySelectScreen;
import edu.austral.starship.base.levels.screen.Screen;
import edu.austral.starship.base.player.Player;
import edu.austral.starship.base.player.controls.*;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class KeySelect implements Level {
    private List<Screen> screens;
    private Stage stage;
    private List<Engine> engines;
    private List<Player> players;
    private LevelsController levelsController;
    private int currentScreen;
    private int currentPlayer;
    private int currentKeyWait;

    public KeySelect(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void draw(PGraphics graphics, Set<Integer> keySet) {
        stage.update(graphics);
        for (Engine engine : engines) {
            engine.execute(stage);
        }

        Iterator<Integer> iterator = keySet.iterator();
        if(iterator.hasNext() && currentKeyWait >= CustomGameFramework.TIME_BETWEEN_KEYS) {
            assignKey(iterator.next());
            changeScreen();
            currentKeyWait = 0;
        }
        currentKeyWait ++;
        if(currentPlayer >= players.size()) {
            stage.resetStage();
            levelsController.nextLevel(players);
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
        this.levelsController = levelsController;
        this.engines = engines;
    }

    @Override
    public void init(List<Player> players) {
        currentScreen = 0;
        currentPlayer = 0;
        currentKeyWait = 0;
        this.screens = new ArrayList<>();
        stage.resetStage();
        this.players = players;
        for (Player player : players) {
            String playerName = player.getPlayerNumber().getName();
            screens.add(new KeySelectScreen("Choose key to accelerate", playerName));
            screens.add(new KeySelectScreen("Choose key to slow down", playerName));
            screens.add(new KeySelectScreen("Choose key to turn right", playerName));
            screens.add(new KeySelectScreen("Choose key to turn left", playerName));
            screens.add(new KeySelectScreen("Choose key to shoot", playerName));
        }
        changeScreen();
    }

    private void assignKey(Integer key) {
        Player player = players.get(currentPlayer);
        switch (currentScreen) {
            case 0:
                player.addKeyToController(key, new Accelerate());
                currentScreen++;
                break;
            case 1:
                player.addKeyToController(key, new Stop());
                currentScreen++;
                break;
            case 2:
                player.addKeyToController(key, new TurnRight());
                currentScreen++;
                break;
            case 3:
                player.addKeyToController(key, new TurnLeft());
                currentScreen++;
                break;
            default:
                player.addKeyToController(key, new Shoot());
                currentScreen = 0;
                currentPlayer++;
                break;
        }
    }

    private void changeScreen() {
        stage.resetStage();
        int index = currentScreen + 5  * currentPlayer;
        if(index < screens.size()) stage.addGameObjects(screens.get(index).draw());
    }
}
