package edu.austral.starship.base.levels.screen;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.levels.gameObjectFactory.GameObjectFactory;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;

public class PauseScreen implements Screen {
    @Override
    public List<GameObject> draw() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(GameObjectFactory.createGenericText("Paused", 50,
                Vector2.vector((float) CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 200), PConstants.CENTER));
        gameObjects.add(GameObjectFactory.createGenericText("Press R to resume. Press M to go to the menu", 30,
                Vector2.vector((float)CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 50), PConstants.CENTER));


        return gameObjects;
    }
}
