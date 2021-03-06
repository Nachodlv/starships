package edu.austral.starship.base.levels;

import edu.austral.starship.base.gameobjects.GameObject;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;

public class Stage {
    private PGraphics pGraphics;
    private int height;
    private int width;
    private List<GameObject> gameObjects;

    public Stage(int height, int width) {
        this.height = height;
        this.width = width;
        gameObjects = new ArrayList<>();
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    void addGameObjects(List<GameObject> gameObjects) {
        this.gameObjects.addAll(gameObjects);
    }

    public void deleteGameObject(GameObject gameObject){
        gameObjects.remove(gameObject);
    }

    void update(PGraphics pGraphics) {
        this.pGraphics = pGraphics;
    }

    public PGraphics getpGraphics() {
        return pGraphics;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    void resetStage() {
        gameObjects = new ArrayList<>();
    }
}
