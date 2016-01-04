package net.urbanleyend.gameworld;

import net.urbanleyend.gameobjects.Bike;
import net.urbanleyend.gameobjects.GameState;
import net.urbanleyend.gameobjects.ScrollHandler;

public class GameWorld {

    private Bike bike;
    private ScrollHandler scroller;
    private GameRenderer renderer;
    private GameState currentState;
    private boolean isAlive = true;

    private int midPointX;
    private int score = 0;
    private float runTime = 0;

    public GameWorld(int midPointX) {
        //currentState = GameState.MENU;
        currentState = GameState.READY;
        this.midPointX = midPointX;

        bike = new Bike(midPointX - 6, 22, 12, 36);

        scroller = new ScrollHandler(this, getMidPointX());
    }

    public void update(float delta) {
        runTime += delta;

        switch (currentState) {
            case READY:
            case MENU:
                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }
    }

    private void updateReady(float delta) {
        bike.updateReady(delta);
        scroller.updateReady(delta);
    }

    public void updateRunning(float delta) {
        bike.update(delta);
        scroller.update(delta);

        if (scroller.collides(bike)) {
            scroller.stop();
            bike.die();
            isAlive = false;
        }
    }

    public void setRenderer(GameRenderer renderer) {
        this.renderer = renderer;
    }

    public Bike getBike(){
        return bike;
    }

    public int getMidPointX() {
        return midPointX;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void ready() {

    }

    public void restart() {
        score = 0;
        scroller.onRestart();
        ready();
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }
}
