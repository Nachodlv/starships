package edu.austral.starship.base.levels;

import edu.austral.starship.base.Color;
import edu.austral.starship.base.gameobjects.HUE.Text;
import edu.austral.starship.base.gameobjects.rigid_bodies.BulletType;
import edu.austral.starship.base.gameobjects.rigid_bodies.Ship;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.BulletFactoryImpl;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.Weapon;
import edu.austral.starship.base.gameobjects.rigid_bodies.weapon.WeaponImpl;
import edu.austral.starship.base.player.Player;
import edu.austral.starship.base.player.PlayerNumber;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GameObjectFactory {

    private GameObjectFactory(){}

    static Ship createShip(Player player) {
        Weapon weapon = createWeapon(player);
        Ship ship = new Ship(Vector2.vector(0, -1), player.getPlayerNumber().getShipPosition(),
                10, weapon, 25, 25, 0.05F, 5, player.getPlayerNumber().getColor());
        player.setShip(ship);
        return ship;
    }

    private static Weapon createWeapon(Player player) {
        List<BulletType> bullets = new ArrayList<>();
        bullets.add(new BulletType(1, 15, 10, 10, Vector2.vector(0, -1)
                , 0, Vector2.vector(0, 0)));
        bullets.add(new BulletType(1, 15, 10, 10, Vector2.vector(0, -1)
                , 0, Vector2.vector(-15, 0)));
        bullets.add(new BulletType(1, 15, 10, 10, Vector2.vector(0, -1)
                , 0, Vector2.vector(15, 0)));


        BulletFactoryImpl bulletFactory = new BulletFactoryImpl(bullets, player);
        return new WeaponImpl(bulletFactory, 10);
    }

    static Text createLifeText(Player player) {
        PlayerNumber playerNumber = player.getPlayerNumber();
        return new Text(10, 10, playerNumber.getColor(), "Life: ", player.getPlayerNumber().getLifePosition(),
                player.getShip(), 20, PConstants.LEFT);
    }

    static Text createScoreText(Player player) {
        PlayerNumber playerNumber = player.getPlayerNumber();
        return new Text(10, 10, playerNumber.getColor(), "Score: ", player.getPlayerNumber().getScorePosition(),
                player, 20,  PConstants.LEFT);
    }

    static Text createGenericText(String text, int size, Vector2 position, int align) {
        return new Text(10, 10, Color.GREEN, text,position,size, align);
    }

    static List<Text> createLeaderBoard(List<Player> players, Vector2 position, int size) {
        List<Text> texts = new ArrayList<>(createRow(Arrays.asList(
                "Position",
                "Name",
                "Score"),
                position, size, 200));
        for (int i = 0; i < players.size(); i++) {
            texts.addAll(
                    createRow(Arrays.asList(String.valueOf(i + 1),
                            players.get(i).getPlayerNumber().getName(),
                            String.valueOf(players.get(i).getScore())),
                    position.add(Vector2.vector(0, 50 * (i + 1))), size, 200));
        }
        return texts;
    }

    private static List<Text> createRow(List<String> strings, Vector2 position, int size, int tabs) {
        List<Text> texts = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            texts.add(createGenericText(strings.get(i), size, position.add(Vector2.vector(tabs * i, 0)),
                    PConstants.CENTER));
        }
        return texts;
    }
}
