package edu.austral.starship.base.gameobjects.HUE;

import edu.austral.starship.base.Color;
import edu.austral.starship.base.engines.Visitor;
import edu.austral.starship.base.vector.Vector2;

import java.util.Optional;

public class Text implements HUE {
    private int height;
    private int width;
    private Color textColor;
    private String text;
    private boolean active;
    private Vector2 position;
    private int textSize;
    private Optional<Valuable> valuable;
    private int textAlign;

    public Text(int height, int width, Color textColor, String text, Vector2 position, Valuable valuable,
                int textSize, int textAlign) {
        this.height = height;
        this.width = width;
        this.textColor = textColor;
        this.text = text;
        this.position = position;
        this.textSize = textSize;
        this.valuable = Optional.of(valuable);
        this.active = true;
        this.textAlign = textAlign;
    }

    public Text(int height, int width, Color textColor, String text, Vector2 position, int textSize, int textAlign) {
        this.height = height;
        this.width = width;
        this.textColor = textColor;
        this.text = text;
        this.position = position;
        this.valuable = Optional.empty();
        this.active = true;
        this.textSize = textSize;
        this.textAlign = textAlign;
    }


        @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public Color getTextColor() {
        return textColor;
    }

    @Override
    public String getText() {
        return valuable.map(valuable1 -> text + valuable1.getValue()).orElseGet(() -> text);
    }

    @Override
    public int getTextSize() {
        return textSize;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void setActive(boolean newStatus) {
        active = newStatus;
    }

    @Override
    public void accepts(Visitor visitor) {
        visitor.acceptsText(this);
    }

    public int getTextAlign() {
        return textAlign;
    }
}
