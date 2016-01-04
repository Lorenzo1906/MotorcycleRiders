package net.urbanleyend.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import net.urbanleyend.helpers.Constants;

public class Bike {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation;
    private int width;
    private float height;
    private float centerX;

    private float originalX;

    private boolean isAlive;

    private Rectangle frontTire;
    private Rectangle body;
    private Rectangle backTire;

    public Bike(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.originalX = x;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
        frontTire = new Rectangle();
        body = new Rectangle();
        backTire = new Rectangle();
        isAlive = true;

        centerX = width / 2;
    }

    public void update(float delta) {
        body.set(position.x, position.y, width, height);
    }

    public void updateReady(float runTime) {

    }

    public void changeToNextLeftLine() {
        if (!isAlive) {
            return;
        }

        if ( (position.x + centerX) == Constants.LEFT_LINE) {
            System.out.println("Let's suppose that it play a sound");
            //TODO: Play a fucking sound
            return;
        }

        position.x = position.x - Constants.DISTANCE;
    }

    public void changeToNextLeftRight() {
        if (!isAlive) {
            return;
        }

        if ( (position.x + centerX) == Constants.RIGHT_LINE) {
            System.out.println("Let's suppose that it play a sound");
            //TODO: Play a fucking sound
            return;
        }

        position.x = position.x + Constants.DISTANCE;
    }

    public void die() {
        isAlive = false;
        velocity.y = 0;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public float getRotation() {
        return rotation;
    }

    public int getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getOriginalX() {
        return originalX;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Rectangle getFrontTire() {
        return frontTire;
    }

    public Rectangle getBody() {
        return body;
    }

    public Rectangle getBackTire() {
        return backTire;
    }
}
