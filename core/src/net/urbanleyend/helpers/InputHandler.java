package net.urbanleyend.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import net.urbanleyend.gameobjects.Bike;
import net.urbanleyend.gameworld.GameWorld;

import java.util.ArrayList;

public class InputHandler implements InputProcessor {

    private Bike bike;
    private GameWorld world;

    private float scaleFactorX;
    private float scaleFactorY;

    public InputHandler(GameWorld world, float scaleFactorX, float scaleFactorY) {
        this.world = world;
        bike = world.getBike();

        int midPointX = world.getMidPointX();

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.LEFT) {
            bike.changeToNextLeftLine();
        }

        if (keycode == Input.Keys.RIGHT) {
            bike.changeToNextLeftRight();
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (world.isMenu()) {
            //playButton.isTouchDown(screenX, screenY);
        } else if (world.isReady()) {
            world.start();
        } else if (world.isRunning()) {
            //myBird.onClick();
        }

        if (world.isGameOver() || world.isHighScore()) {
            //myWorld.restart();
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }
}
