package net.urbanleyend.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
    private Car carA, carB, carC, carD, carE, carF;

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
        carE = scroller.getCarE();
        carF = scroller.getCarF();
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


        //Debug code
        if(AssetLoader.debug){
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

            shapeRenderer.setColor(new Color(255, 0, 0, 0.5f));
            shapeRenderer.rect(bike.getBody().x, bike.getBody().y, bike.getBody().width, bike.getBody().height);

            shapeRenderer.setColor(new Color(0, 61, 245, 0.5f));
            shapeRenderer.rect(carA.getBody().x, carA.getBody().y, carA.getBody().width, carA.getBody().height);
            shapeRenderer.rect(carB.getBody().x, carB.getBody().y, carB.getBody().width, carB.getBody().height);
            shapeRenderer.rect(carC.getBody().x, carC.getBody().y, carC.getBody().width, carC.getBody().height);
            shapeRenderer.rect(carD.getBody().x, carD.getBody().y, carD.getBody().width, carD.getBody().height);
            shapeRenderer.rect(carE.getBody().x, carE.getBody().y, carE.getBody().width, carE.getBody().height);
            shapeRenderer.rect(carF.getBody().x, carF.getBody().y, carF.getBody().width, carF.getBody().height);

            shapeRenderer.end();
        }
        //End debug code


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
        batcher.draw(carATexture, carE.getX(), carE.getY(), carE.getWidth(), carE.getHeight());
        batcher.draw(carBTexture, carF.getX(), carF.getY(), carF.getWidth(), carF.getHeight());
    }

    private void drawBikeCentered(float runTime) {
        batcher.draw(bikeTexture, bike.getX(), bike.getY(), bike.getWidth(), bike.getHeight());
    }

    private void drawReady() {
        batcher.draw(ready, midPointX - 18, 156, 36, 12);
    }
}
