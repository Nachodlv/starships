package edu.austral.starship.base.gameobjects.rigid_bodies;

import edu.austral.starship.base.gameobjects.Drawable;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public interface RigidBody extends Drawable {

    float getAngle();

    Vector2 getDirection();

    float getVelocity();

    int getWidth();

    int getHeight();

    Vector2 getPosition();

    void setShape(Shape shape);

    void setPosition(Vector2 position);

    default Vector2 getNextPosition(){
        return getPosition().add(getDirection().multiply(getVelocity()));
    }

    default void setNextPosition(Vector2 newPosition) {
        Shape newShape = new Rectangle2D.Float(0 - getWidth()/2F, 0 - getHeight()/2F, getWidth(), getHeight());
        AffineTransform tx = new AffineTransform();
        tx.translate(newPosition.getX() - getWidth()/2F, newPosition.getY() - getHeight()/2F);
        tx.rotate(getAngle());
        setShape(tx.createTransformedShape(newShape));
        setPosition(newPosition);
    }

}
