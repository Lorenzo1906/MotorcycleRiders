package net.urbanleyend.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import net.urbanleyend.gameworld.GameRenderer;
import net.urbanleyend.gameworld.GameWorld;
import net.urbanleyend.helpers.InputHandler;

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;

    public GameScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        Gdx.app.log(this.getClass().getName(), "Width: " + screenWidth);
        Gdx.app.log(this.getClass().getName(), "Height: " + screenHeight);

        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        int midPointX = (int) (gameWidth / 2);

        world = new GameWorld(midPointX);
        Gdx.input.setInputProcessor(new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight));
        renderer = new GameRenderer(world, (int) gameHeight, midPointX);
        world.setRenderer(renderer);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(delta, runTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
