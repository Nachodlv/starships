package edu.austral.starship.base.player;

import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.base.Color;
import edu.austral.starship.base.vector.Vector2;

public enum PlayerNumber {

    PLAYER_ONE(
            Color.RED,
            Vector2.vector(50, 50),
            Vector2.vector(10, 15),
            Vector2.vector(10, 30),
            "Player one"
    ),
    PLAYER_TWO(
            Color.GREEN,
            Vector2.vector(CustomGameFramework.WIDTH - 50 , CustomGameFramework.HEIGHT - 50),
            Vector2.vector(CustomGameFramework.WIDTH - 200, CustomGameFramework.HEIGHT - 15),
            Vector2.vector(CustomGameFramework.WIDTH - 200, CustomGameFramework.HEIGHT - 30),
            "Player two"

    ),
    PLAYER_THREE(
            Color.BLUE,
            Vector2.vector(0, CustomGameFramework.HEIGHT - 50),
            Vector2.vector(0, CustomGameFramework.HEIGHT - 10),
            Vector2.vector(0, CustomGameFramework.HEIGHT - 5),
            "Player three"
    ),
    PLAYER_FOUR(
            Color.WHITE,
            Vector2.vector(CustomGameFramework.WIDTH - 50, 0),
            Vector2.vector(CustomGameFramework.WIDTH - 10, 0),
            Vector2.vector(CustomGameFramework.WIDTH - 5, 0),
            "Player four"
    );

    private Color color;
    private Vector2 shipPosition;
    private Vector2 scorePosition;
    private Vector2 lifePosition;
    private String name;

    PlayerNumber(Color color, Vector2 shipPosition, Vector2 scorePosition, Vector2 lifePosition, String name) {
        this.color = color;
        this.shipPosition = shipPosition;
        this.scorePosition = scorePosition;
        this.lifePosition = lifePosition;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public Vector2 getShipPosition() {
        return shipPosition;
    }

    public Vector2 getScorePosition() {
        return scorePosition;
    }

    public Vector2 getLifePosition() {
        return lifePosition;
    }

    public String getName() {
        return name;
    }
}
