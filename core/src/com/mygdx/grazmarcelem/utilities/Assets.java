package com.mygdx.grazmarcelem.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

public class Assets implements Disposable, AssetErrorListener {

    public static final String tag = Assets.class.getName();

    //Instancja klasy

    public static final Assets instance = new Assets();

    public PlayerAssets playerAssets;
    private AssetManager assetManager;

    public Assets() {

    }

    public void init() {
        this.assetManager = new AssetManager();
        assetManager.setErrorListener(this);
        //ladowanie atlasu
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        playerAssets = new PlayerAssets(atlas);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(tag, "nie zaladowalo asseta: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
    ///klasa z teksturami
    public class PlayerAssets {

        public final TextureAtlas.AtlasRegion alienBlueRightOne;
        public final TextureAtlas.AtlasRegion alienBlueLeftOne;
        public final TextureAtlas.AtlasRegion alienBlueJumpRight;
        public final TextureAtlas.AtlasRegion alienBlueJumpLeft;
        public final TextureAtlas.AtlasRegion alienStanding;

        public final Animation walkRightAnimation;
        public final Animation walkLeftAnimation;


        public PlayerAssets(TextureAtlas atlas) {
            alienBlueRightOne = atlas.findRegion(Constants.ALIEN_BLUE_RIGHT_ONE);
            alienBlueLeftOne = atlas.findRegion(Constants.ALIEN_BLUE_LEFT_ONE);
            alienBlueJumpLeft = atlas.findRegion(Constants.ALIEN_JUMP_LEFT);
            alienBlueJumpRight = atlas.findRegion(Constants.ALIEN_JUMP_RIGHT);
            alienStanding = atlas.findRegion(Constants.ALIEN_STANDING);

            Array<TextureAtlas.AtlasRegion> walkRightFrames = new Array<TextureAtlas.AtlasRegion>();
            walkRightFrames.add(atlas.findRegion(Constants.ALIEN_BLUE_RIGHT_ONE));
            walkRightFrames.add(atlas.findRegion(Constants.ALIEN_BLUE_RIGHT_TWO));

            walkRightAnimation = new Animation(Constants.LOOP_DURATION, walkRightFrames , Animation.PlayMode.LOOP);

            Array<TextureAtlas.AtlasRegion> walkLeftFrames = new Array<TextureAtlas.AtlasRegion>();
            walkLeftFrames.add(atlas.findRegion(Constants.ALIEN_BLUE_LEFT_ONE));
            walkLeftFrames.add(atlas.findRegion(Constants.ALIEN_BLUE_LEFT_TWO));

            walkLeftAnimation = new Animation(Constants.LOOP_DURATION, walkLeftFrames, Animation.PlayMode.LOOP);

        }

    }
}
