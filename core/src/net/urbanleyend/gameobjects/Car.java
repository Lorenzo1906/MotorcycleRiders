package net.urbanleyend.gameobjects;

import com.badlogic.gdx.math.Rectangle;

public class Car extends Scrollable {

    private Rectangle body;

    private float centerX;

    public Car(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);

        body = new Rectangle();
        centerX = width /2;
    }

    public void onRestart(float y, float x, float scrollSpeed) {
        position.y = y;
        position.x = x;
        velocity.y =  scrollSpeed;
    }

    public void reset(float newY, float newX){
        position.y = newY;
        position.x = newX;
        isScrolledDown = false;
    }

    public float getCenterX() {
        return centerX;
    }
}
