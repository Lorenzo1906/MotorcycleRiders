package net.urbanleyend.gameobjects;

public class Building  extends Scrollable{

    public Building(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }

    public void onRestart(float y, float scrollSpeed) {
        position.y = y;
        velocity.y =  scrollSpeed;
    }
}
