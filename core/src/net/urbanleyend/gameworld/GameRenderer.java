package net.urbanleyend.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.urbanleyend.gameobjects.Bike;
import net.urbanleyend.gameobjects.Building;
import net.urbanleyend.gameobjects.Car;
import net.urbanleyend.gameobjects.ScrollHandler;
import net.urbanleyend.gameobjects.Street;
import net.urbanleyend.helpers.AssetLoader;

public class GameRenderer {

    private GameWorld world;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;

    private int midPointX;

    // Game Objects
    private Bike bike;
    private ScrollHandler scroller;
    private Building rightBuildingA, rightBuildingB, rightBuildingC, leftBuildingA, leftBuildingB, leftBuildingC;
    private Street streetA, streetB;
    private Car carA, carB, carC, carD;

    // Game Assets
    private TextureRegion buildingA, buildingB, buildingC, streetTextureA, streetTextureB, bikeTexture, ready, carATexture, carBTexture;

    public GameRenderer(GameWorld world, int gameHeight, int midPointX) {
        this.world = world;
        this.midPointX = midPointX;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 136, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
        initAssets();
    }

    private void initGameObjects() {
        scroller = world.getScroller();
        bike = world.getBike();

        rightBuildingA = scroller.getRightBuildingA();
        rightBuildingB = scroller.getRightBuildingB();
        rightBuildingC = scroller.getRightBuildingC();

        leftBuildingA = scroller.getLeftBuildingA();
        leftBuildingB = scroller.getLeftBuildingB();
        leftBuildingC = scroller.getLeftBuildingC();

        streetA = scroller.getStreetA();
        streetB = scroller.getStreetB();

        carA = scroller.getCarA();
        carB = scroller.getCarB();
        carC = scroller.getCarC();
        carD = scroller.getCarD();
    }

    private void initAssets() {
        buildingA = AssetLoader.buildingA;
        buildingB = AssetLoader.buildingB;
        buildingC = AssetLoader.buildingC;
        streetTextureA = AssetLoader.streetA;
        streetTextureB = AssetLoader.streetB;
        bikeTexture = AssetLoader.bike;
        ready = AssetLoader.ready;
        carATexture = AssetLoader.carA;
        carBTexture = AssetLoader.carB;
    }

    public void render(float delta, float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        //shapeRenderer.rect(0, midPointY + 66, 136, 11);

        //shapeRenderer.end();

        batcher.begin();

        drawBuildings();
        drawStreet();

        if (world.isRunning()) {
            drawBike(delta);
            drawCars(delta);
        } else if (world.isReady()) {
            drawBike(delta);
            drawReady();
        } else if (world.isMenu()) {
            drawBikeCentered(delta);
        } else if (world.isGameOver()) {

        } else if (world.isHighScore()) {

        }

        batcher.end();
    }

    private void drawBuildings() {
        batcher.draw(buildingA, rightBuildingA.getX(), rightBuildingA.getY(), rightBuildingA.getWidth(), rightBuildingA.getHeight());
        batcher.draw(buildingB, rightBuildingB.getX(), rightBuildingB.getY(), rightBuildingB.getWidth(), rightBuildingB.getHeight());
        batcher.draw(buildingC, rightBuildingC.getX(), rightBuildingC.getY(), rightBuildingC.getWidth(), rightBuildingC.getHeight());

        batcher.draw(buildingA, leftBuildingA.getX(), leftBuildingA.getY(), leftBuildingA.getWidth(), leftBuildingA.getHeight());
        batcher.draw(buildingB, leftBuildingB.getX(), leftBuildingB.getY(), leftBuildingB.getWidth(), leftBuildingB.getHeight());
        batcher.draw(buildingC, leftBuildingC.getX(), leftBuildingC.getY(), leftBuildingC.getWidth(), leftBuildingC.getHeight());
    }

    private void drawStreet() {
        batcher.draw(streetTextureA, streetA.getX(), streetA.getY(), streetA.getWidth(), streetA.getHeight());
        batcher.draw(streetTextureB, streetB.getX(), streetB.getY(), streetB.getWidth(), streetB.getHeight());
    }

    private void drawBike(float runTime) {
        batcher.draw(bikeTexture, bike.getX(), bike.getY(), bike.getWidth(), bike.getHeight());
    }

    private void drawCars(float runTime) {
        batcher.draw(carATexture, carA.getX(), carA.getY(), carA.getWidth(), carA.getHeight());
        batcher.draw(carBTexture, carB.getX(), carB.getY(), carB.getWidth(), carB.getHeight());
        batcher.draw(carATexture, carC.getX(), carC.getY(), carC.getWidth(), carC.getHeight());
        batcher.draw(carBTexture, carD.getX(), carD.getY(), carD.getWidth(), carD.getHeight());
    }

    private void drawBikeCentered(float runTime) {
        batcher.draw(bikeTexture, bike.getX(), bike.getY(), bike.getWidth(), bike.getHeight());
    }

    private void drawReady() {
        batcher.draw(ready, midPointX - 18, 156, 36, 12);
    }
}
