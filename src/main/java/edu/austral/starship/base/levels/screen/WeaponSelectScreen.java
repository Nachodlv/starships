package edu.austral.starship.base.levels.screen;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.WeaponType;
import edu.austral.starship.base.levels.gameObjectFactory.GameObjectFactory;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;

public class WeaponSelectScreen implements Screen{
    private String player;

    public WeaponSelectScreen(String player) {
        this.player = player;
    }

    @Override
    public List<GameObject> draw() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(GameObjectFactory.createGenericText(player, 50,
                Vector2.vector((float) CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 200), PConstants.CENTER));
        gameObjects.add(GameObjectFactory.createGenericText("Choose a weapon", 50,
                Vector2.vector((float) CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 100), PConstants.CENTER));

        for (int i = 0; i < WeaponType.values().length; i++) {
            WeaponType weaponType = WeaponType.values()[i];
            String text = (i + 1) + ". " + weaponType.getName();
            gameObjects.add(GameObjectFactory.createGenericText(text, 30,
                    Vector2.vector((float) CustomGameFramework.WIDTH/2,
                            (float)CustomGameFramework.HEIGHT/2 + (50 * i)), PConstants.CENTER));
        }

        return gameObjects;
    }
}
