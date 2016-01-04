package net.urbanleyend.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Car extends Scrollable {

    private Rectangle body;

    private float centerX;

    public Car(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);

        body = new Rectangle();
        centerX = width /2;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        getBody().set(position.x, position.y, width, height);
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

    public boolean collides(Bike bike) {
        if (position.x <= bike.getX() + bike.getWidth()) {
            return (Intersector.overlaps(bike.getBody(), body));
        }
        return false;
    }

    public float getCenterX() {
        return centerX;
    }

    public Rectangle getBody() {
        return body;
    }
}
