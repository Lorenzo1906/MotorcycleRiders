package net.urbanleyend.gameobjects;

public class Street extends Scrollable {

    public Street(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }

    public void onRestart(float y, float scrollSpeed) {
        position.y = y;
        velocity.y =  scrollSpeed;
    }
}
