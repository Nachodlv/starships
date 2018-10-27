package edu.austral.starship;

import edu.austral.starship.base.engines.Engine;
import edu.austral.starship.base.engines.MoveEngine;
import edu.austral.starship.base.engines.RenderEngine;
import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.base.levels.*;
import edu.austral.starship.base.player.Player;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomGameFramework implements GameFramework {
    private List<Player> players;
    private int width = 1500;
    private int height = 1000;
    private LevelsController levelsController;

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings.setSize(width, height);
        List<Level> levels = new ArrayList<>();
        players = new ArrayList<>();
        Level mainLevel = new MainLevel(new Stage(height, width));
        levels.add(mainLevel);

        players.add(new Player());
        mainLevel.setup(players, imageLoader);

        levelsController = new LevelsControllerImpl(levels);
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        for(Integer key: keySet) {
            for(Player player: players) {
                if(player.hasKey(key)) player.keyPressed(key);
            }
        }
        levelsController.getCurrentLevel().draw(graphics);
    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }
}
