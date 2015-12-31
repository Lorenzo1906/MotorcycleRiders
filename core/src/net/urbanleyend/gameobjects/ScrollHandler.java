package net.urbanleyend.gameobjects;

import net.urbanleyend.gameworld.GameWorld;
import net.urbanleyend.helpers.Constants;

import java.util.Random;

public class ScrollHandler {

    private Building rightBuildingA, rightBuildingB, rightBuildingC, leftBuildingA, leftBuildingB, leftBuildingC;
    private Street streetA, streetB;
    private Car carA, carB, carC, carD;
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

        carA = new Car(Constants.LEFT_LINE - 12, 300, 24, 72, SCROLL_SPEED);
        carB = new Car(Constants.RIGHT_LINE - 12, 300, 24, 72, SCROLL_SPEED);
        carC = new Car(Constants.CENTER_LINE - 12, carA.getTailY() + Constants.SPACE_CAR, 24, 72, SCROLL_SPEED);
        carD = new Car(Constants.RIGHT_LINE - 12, carB.getTailY() + Constants.SPACE_CAR, 24, 72, SCROLL_SPEED);
    }

    public void updateReady(float delta) {
        updateLeftBuildings(delta);
        updateRightBuildings(delta);
        updateStreet(delta);
        updateCars(delta);
    }

    public void update(float delta) {
        updateLeftBuildings(delta);
        updateRightBuildings(delta);
        updateStreet(delta);
        updateCars(delta);
    }

    private void updateCars(float delta) {
        carA.update(delta);
        carB.update(delta);
        carC.update(delta);
        carD.update(delta);

        if (carA.isScrolledDown()) {
            carA.reset(carC.getTailY() + Constants.SPACE_CAR, getRandomLane() - carA.getCenterX());
        }

        if (carB.isScrolledDown()) {
            carB.reset(carD.getTailY() + Constants.SPACE_CAR, getRandomLane() - carB.getCenterX());
        }

        if (carC.isScrolledDown()) {
            carC.reset(carA.getTailY() + Constants.SPACE_CAR, getRandomLane() - carC.getCenterX());
        }

        if (carD.isScrolledDown()) {
            carD.reset(carB.getTailY() + Constants.SPACE_CAR, getRandomLane() - carD.getCenterX());
        }
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

    private int getRandomLane(){
        Random random = new Random();

        int val = random.nextInt(3);
        int result = 0;

        switch (val) {
            case 0:
                result = Constants.LEFT_LINE;
                break;
            case 1:
                result = Constants.CENTER_LINE;
                break;
            case 2:
                result = Constants.RIGHT_LINE;
                break;
        }

        return result;
    }

    public void stop() {
        rightBuildingA.stop();
        rightBuildingB.stop();
        rightBuildingC.stop();

        leftBuildingA.stop();
        leftBuildingB.stop();
        leftBuildingC.stop();

        streetA.stop();
        streetB.stop();

        carA.stop();
        carB.stop();
        carC.stop();
        carD.stop();
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

        streetA.onRestart(0, SCROLL_SPEED);
        streetB.onRestart(streetA.getTailY(), SCROLL_SPEED);

        carA.onRestart(200, getRandomLane() - carA.getCenterX(), SCROLL_SPEED);
        carB.onRestart(200, getRandomLane() - carB.getCenterX(), SCROLL_SPEED);
        carC.onRestart(carA.getTailY(), getRandomLane() - carC.getCenterX(), SCROLL_SPEED);
        carD.onRestart(carB.getTailY(), getRandomLane() - carD.getCenterX(), SCROLL_SPEED);
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

    public Car getCarA() {
        return carA;
    }

    public Car getCarB() {
        return carB;
    }

    public Car getCarC() {
        return carC;
    }

    public Car getCarD() {
        return carD;
    }
}
