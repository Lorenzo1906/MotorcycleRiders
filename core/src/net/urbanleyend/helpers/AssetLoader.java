package net.urbanleyend.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture logoTexture, buildingTextureA, buildingTextureB, buildingTextureC, streetTextureA, streetTextureB;
    public static TextureRegion logo, buildingA, buildingB, buildingC, streetA, streetB;

    public static void load() {
        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        buildingTextureA = new Texture(Gdx.files.internal("data/buildingA.png"));
        buildingTextureA.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        buildingTextureB = new Texture(Gdx.files.internal("data/buildingB.png"));
        buildingTextureB.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        buildingTextureC = new Texture(Gdx.files.internal("data/buildingC.png"));
        buildingTextureC.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        streetTextureA = new Texture(Gdx.files.internal("data/streetA.png"));
        streetTextureA.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        streetTextureB = new Texture(Gdx.files.internal("data/streetB.png"));
        streetTextureB.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);
        buildingA = new TextureRegion(buildingTextureA, 0, 0, 34, 150);
        buildingB = new TextureRegion(buildingTextureB, 0, 0, 34, 150);
        buildingC = new TextureRegion(buildingTextureC, 0, 0, 34, 150);
        streetA = new TextureRegion(streetTextureA, 0, 0, 68, 204);
        streetB = new TextureRegion(streetTextureB, 0, 0, 68, 204);
    }

    public static void dispose() {
        logoTexture.dispose();
        buildingTextureA.dispose();
        buildingTextureB.dispose();
        buildingTextureC.dispose();
        streetTextureA.dispose();
        streetTextureB.dispose();
    }
}
