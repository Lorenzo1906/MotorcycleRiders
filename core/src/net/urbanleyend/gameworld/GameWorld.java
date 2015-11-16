package net.urbanleyend.gameworld;

import net.urbanleyend.gameobjects.GameState;
import net.urbanleyend.gameobjects.ScrollHandler;

public class GameWorld {

    private ScrollHandler scroller;
    private GameRenderer renderer;
    private GameState currentState;

    private int midPointX;
    private int score = 0;
    private float runTime = 0;

    public GameWorld(int midPointX) {
        currentState = GameState.MENU;
        this.midPointX = midPointX;

        scroller = new ScrollHandler(this, midPointX);
    }

    public void update(float delta) {
        runTime += delta;

        switch (currentState) {
            case READY:
            case MENU:
                updateReady(delta);
                break;
            default:
                break;
        }
    }

    private void updateReady(float delta) {
        scroller.updateReady(delta);
    }

    public void setRenderer(GameRenderer renderer) {
        this.renderer = renderer;
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
        //currentState = GameState.READY;
        //renderer.prepareTransition(0, 0, 0, 1f);
    }

    public void restart() {
        score = 0;
       // bird.onRestart(midPointY - 5);
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
