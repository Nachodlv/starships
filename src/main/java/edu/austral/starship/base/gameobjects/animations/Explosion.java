package edu.austral.starship.base.gameobjects.animations;

import edu.austral.starship.base.engines.Visitor;
import edu.austral.starship.base.vector.Vector2;

public class Explosion implements Animation {

    private boolean active;
    private Vector2 position;
    private int currentState;
    private int currentStateTime;
    private int quantityOfStates;
    private int timeBetweenStates;
    private int height;
    private int width;

    public Explosion(Vector2 position, int quantityOfStates, int timeBetweenStates, int height, int width) {
        this.position = position;
        this.quantityOfStates = quantityOfStates;
        this.timeBetweenStates = timeBetweenStates;
        this.currentState = 0;
        this.currentStateTime = 0;
        this.active = true;
        this.height = height;
        this.width = width;
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
        visitor.acceptsExplosion(this);
    }

    @Override
    public int getCurrentState() {
        if(currentState >= quantityOfStates) {
            active = false;
            return quantityOfStates -1 ;
        }
        if(currentStateTime >= timeBetweenStates) {
            currentState ++;
            currentStateTime = 0;
            return currentState - 1;
        } else {
            currentStateTime ++;
            return currentState;
        }

    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
