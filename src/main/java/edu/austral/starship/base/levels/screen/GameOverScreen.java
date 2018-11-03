package edu.austral.starship.base.levels.screen;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.gameobjects.GameObject;
import edu.austral.starship.base.gameobjects.hue.Text;
import edu.austral.starship.base.levels.gameObjectFactory.GameObjectFactory;
import edu.austral.starship.base.player.Player;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;

public class GameOverScreen implements Screen {

    private List<Player> players;

    public GameOverScreen(List<Player> players) {
        this.players = players;
    }

    @Override
    public List<GameObject> draw() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(GameObjectFactory.createGenericText("Game Over", 50,
                Vector2.vector((float) CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 200), PConstants.CENTER));
        gameObjects.add(GameObjectFactory.createGenericText("Press R to restart. Press M to go to the menu", 30,
                Vector2.vector((float)CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 50), PConstants.CENTER));

        if(players.size() <= 1) soloPlayer(players.get(0), gameObjects);
        else createLeaderBoard(gameObjects);

        return gameObjects;
    }

    private void soloPlayer(Player player, List<GameObject> gameObjects) {
        gameObjects.add(GameObjectFactory.createGenericText("Your score is " + player.getScore(),
                30, Vector2.vector((float)CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 100), PConstants.CENTER));
    }

    private void createLeaderBoard(List<GameObject> gameObjects) {
        sortPlayers(players);
        gameObjects.add(GameObjectFactory.createGenericText("The winner is " + players.get(0).getPlayerNumber().getName(),
                30, Vector2.vector((float)CustomGameFramework.WIDTH/2, (float)CustomGameFramework.HEIGHT/2 - 100), PConstants.CENTER));
        List<Text> texts = GameObjectFactory.createLeaderBoard(players,
                Vector2.vector((float)CustomGameFramework.WIDTH/2 - 200, (float)CustomGameFramework.HEIGHT/2 + 50), 30);
        gameObjects.addAll(texts);

    }
    private void sortPlayers(List<Player> players) {
        players.sort((player, t1) -> Double.compare(t1.getScore(), player.getScore()));
    }
}
