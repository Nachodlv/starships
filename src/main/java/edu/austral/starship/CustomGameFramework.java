package edu.austral.starship;

import edu.austral.starship.base.framework.FontLoader;
import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.base.levels.*;
import edu.austral.starship.base.player.Player;
import edu.austral.starship.base.player.PlayerNumber;
import edu.austral.starship.base.player.controls.*;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.event.KeyEvent;


import java.util.*;

public class CustomGameFramework implements GameFramework {

    public static final int WIDTH = 1500;
    public static final int HEIGHT = 1000;

    private List<Player> players;
    private LevelsController levelsController;

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader, FontLoader fontLoader) {
        windowsSettings.setSize(WIDTH, HEIGHT);
        List<Level> levels = new ArrayList<>();
        players = new ArrayList<>();

        Level mainLevel = new MainLevel(new Stage(HEIGHT, WIDTH));
        levels.add(mainLevel);

        Level gameOver = new GameOver(new Stage(HEIGHT, WIDTH));
        levels.add(gameOver);

        levelsController = new LevelsControllerImpl(levels);

        players.add(new Player(PlayerNumber.PLAYER_ONE));
        players.add(new Player(controlsPlayerTwo(), PlayerNumber.PLAYER_TWO));

        mainLevel.setup(players, imageLoader, levelsController);
        gameOver.setup(players, imageLoader, levelsController);

    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        for(Integer key: keySet) {
            for(Player player: players) {
                if(player.hasKey(key)) player.keyPressed(key);
            }
        }
        levelsController.getCurrentLevel().draw(graphics, keySet);
    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    private Controls controlsPlayerTwo() {
        Map<Integer, KeyFunctions> keys = new HashMap<>();
        keys.put(java.awt.event.KeyEvent.VK_W, new Accelerate());
        keys.put(java.awt.event.KeyEvent.VK_S, new Stop());
        keys.put(java.awt.event.KeyEvent.VK_A, new TurnLeft());
        keys.put(java.awt.event.KeyEvent.VK_D, new TurnRight());
        keys.put(java.awt.event.KeyEvent.VK_SHIFT, new Shoot());
        return new Controls(keys);
    }
}
