package net.urbanleyend.gameobjects;

import net.urbanleyend.gameworld.GameWorld;

public class ScrollHandler {

    private Building rightBuildingA, rightBuildingB, rightBuildingC, leftBuildingA, leftBuildingB, leftBuildingC;
    private Street streetA;
    private Street streetB;
    public static final int SCROLL_SPEED = -59;

    public ScrollHandler(GameWorld gameWorld, float xPos){

        rightBuildingA = new Building(0, 0, 34, 150, SCROLL_SPEED);
        rightBuildingB = new Building(0, rightBuildingA.getTailY(), 34, 150, SCROLL_SPEED);
        rightBuildingC = new Building(0, rightBuildingB.getTailY(), 34, 150, SCROLL_SPEED);

        float leftX = xPos * 2;

        leftBuildingA = new Building(leftX - 34, 0, 34, 150, SCROLL_SPEED);
        leftBuildingB = new Building(leftX - 34, leftBuildingA.getTailY(), 34, 150, SCROLL_SPEED);
        leftBuildingC = new Building(leftX - 34, leftBuildingB.getTailY(), 34, 150, SCROLL_SPEED);

        streetA = new Street(34, 0, 68, 204, SCROLL_SPEED);
        streetB = new Street(34, getStreetA().getTailY(), 68, 204, SCROLL_SPEED);
    }

    public void updateReady(float delta) {
        updateLeftBuildings(delta);
        updateRightBuildings(delta);
        updateStreet(delta);
    }

    public void update(float delta) {
        updateLeftBuildings(delta);
        updateRightBuildings(delta);
        updateStreet(delta);
    }

    private void updateStreet(float delta) {
        getStreetA().update(delta);
        streetB.update(delta);

        if (getStreetA().isScrolledDown()) {
            getStreetA().reset(streetB.getTailY());
        } else if (streetB.isScrolledDown()) {
            streetB.reset(getStreetA().getTailY());
        }
    }

    private void updateLeftBuildings(float delta){
        leftBuildingA.update(delta);
        leftBuildingB.update(delta);
        leftBuildingC.update(delta);

        if (leftBuildingA.isScrolledDown()) {
            leftBuildingA.reset(leftBuildingC.getTailY());
        } else if (leftBuildingB.isScrolledDown()) {
            leftBuildingB.reset(leftBuildingA.getTailY());
        } else if (leftBuildingC.isScrolledDown()) {
            leftBuildingC.reset(leftBuildingB.getTailY());
        }
    }

    private void updateRightBuildings(float delta){
        rightBuildingA.update(delta);
        rightBuildingB.update(delta);
        rightBuildingC.update(delta);

        if (rightBuildingA.isScrolledDown()) {
            rightBuildingA.reset(rightBuildingC.getTailY());
        } else if (rightBuildingB.isScrolledDown()) {
            rightBuildingB.reset(rightBuildingA.getTailY());
        } else if (rightBuildingC.isScrolledDown()) {
            rightBuildingC.reset(rightBuildingB.getTailY());
        }
    }

    public void stop() {
        rightBuildingA.stop();
        rightBuildingB.stop();
        rightBuildingC.stop();

        leftBuildingA.stop();
        leftBuildingB.stop();
        leftBuildingC.stop();
    }

    //public boolean collides(Bird bird) {

    //}

    public void onRestart() {
        rightBuildingA.onRestart(0, SCROLL_SPEED);
        rightBuildingB.onRestart(rightBuildingA.getTailY(), SCROLL_SPEED);
        rightBuildingC.onRestart(rightBuildingB.getTailY(), SCROLL_SPEED);

        leftBuildingA.onRestart(0, SCROLL_SPEED);
        leftBuildingB.onRestart(leftBuildingA.getTailY(), SCROLL_SPEED);
        leftBuildingC.onRestart(leftBuildingB.getTailY(), SCROLL_SPEED);
    }

    public Building getRightBuildingA() {
        return rightBuildingA;
    }

    public Building getRightBuildingB() {
        return rightBuildingB;
    }

    public Building getRightBuildingC() {
        return rightBuildingC;
    }

    public Building getLeftBuildingA() {
        return leftBuildingA;
    }

    public Building getLeftBuildingB() {
        return leftBuildingB;
    }

    public Building getLeftBuildingC() {
        return leftBuildingC;
    }

    public Street getStreetA() {
        return streetA;
    }

    public Street getStreetB() {
        return streetB;
    }
}