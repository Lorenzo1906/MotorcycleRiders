package net.urbanleyend.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AfterAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import net.urbanleyend.helpers.AssetLoader;
import net.urbanleyend.motorcycleriders.MotorcycleRiders;

public class SplashScreen implements Screen {

    private SpriteBatch batcher;
    private Sprite sprite;
    private Stage stage;
    private MotorcycleRiders game;

    public SplashScreen(MotorcycleRiders game) {
        this.game = game;
        this.stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }

    @Override
    public void show() {
        sprite = new Sprite(AssetLoader.logo);
        sprite.setColor(1, 1, 1, 0);

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        float desiredWidth = width * .7f;
        float scale = desiredWidth / sprite.getWidth();

        sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
        sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2) - (sprite.getHeight() / 2));
        setupActions();
        batcher = new SpriteBatch();
    }

    private void setupActions() {
        TextureRegion splashRegion = new TextureRegion(sprite);
        Image splashImage = new Image(splashRegion);
        splashImage.getColor().a = 0f;

        splashImage.setWidth(.5f * Gdx.graphics.getWidth());
        splashImage.setScaling(Scaling.fillX);

        splashImage.setWidth(.5f * Gdx.graphics.getHeight());
        splashImage.setScaling(Scaling.fillY);

        AfterAction loadNextScreen = new AfterAction();
        loadNextScreen.setAction(new Action() {
                                     @Override
                                     public boolean act(float delta) {
                                         game.setScreen( new GameScreen() );
                                         return true;
                                     }
                                 }
        );

        SequenceAction actions = new SequenceAction();
        actions.addAction(Actions.fadeIn(0.75f));
        actions.addAction(Actions.delay(1.75f));
        actions.addAction(Actions.fadeOut(0.75f));
        actions.addAction(loadNextScreen);

        splashImage.addAction(actions);

        stage.addActor(splashImage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
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
