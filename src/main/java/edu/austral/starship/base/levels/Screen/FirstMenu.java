package edu.austral.starship.base.levels.Screen;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.levels.GameObjectFactory.GameObjectFactory;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;

public class FirstMenu implements Screen {
    @Override
    public List<GameObject> draw() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(GameObjectFactory.createGenericText("StarShip", 50,
                Vector2.vector((float) CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 200), PConstants.CENTER));
        gameObjects.add(GameObjectFactory.createGenericText("Select quantity of players (1-4)", 30,
                Vector2.vector((float) CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 100), PConstants.CENTER));
        return gameObjects;
    }
}
