package com.mygdx.grazmarcelem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.grazmarcelem.utilities.Assets;
import com.mygdx.grazmarcelem.utilities.Constants;

import javax.print.attribute.Size2DSyntax;


class Play extends ScreenAdapter{

//    private TiledMap map;
//    private OrthogonalTiledMapRenderer renderer;
//    private OrthographicCamera camera;
//    Player player;
    //ninepatch
//    FitViewport viewport;
//    Texture platformTexture;
//    NinePatch platformNinePatch;
//    private static float SIZE1 = 20, SIZE2 = 40, WORLD = 100;
//
//
//    private static final String ATLAS = "rawAssets-packed/pack.atlas";
//    private static final String ALIENBLUE = "badlogic";
//
//    SpriteBatch batch;
//    AssetManager assetManager;
//
//    TextureAtlas.AtlasRegion alienBlueRightOne;
    Level level;
    SpriteBatch batch;
    ExtendViewport viewport;

    @Override
    public void show() {
        Assets.instance.init();
        level = new Level();
        batch = new SpriteBatch();
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);


    }


    @Override
    public void render(float delta) {
        level.update(delta);

        viewport.apply();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        batch.setProjectionMatrix(viewport.getCamera().combined);





        batch.begin();
        level.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }





    @Override
    public void dispose() {
        Assets.instance.dispose();

        batch.dispose();
    }


}
