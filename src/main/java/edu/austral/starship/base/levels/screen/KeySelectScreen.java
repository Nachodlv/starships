package edu.austral.starship.base.levels.screen;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.levels.gameObjectFactory.GameObjectFactory;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;

public class KeySelectScreen implements Screen {

    private String text;
    private String player;

    public KeySelectScreen(String text, String player) {
        this.text = text;
        this.player = player;
    }

    @Override
    public List<GameObject> draw() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(GameObjectFactory.createGenericText(player, 50,
                Vector2.vector((float) CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 200), PConstants.CENTER));
        gameObjects.add(GameObjectFactory.createGenericText(text, 50,
                Vector2.vector((float) CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 100), PConstants.CENTER));
        return gameObjects;
    }
}
