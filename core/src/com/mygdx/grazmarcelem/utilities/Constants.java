package com.mygdx.grazmarcelem.utilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {
    ///wielkos swiata
    public static final float WORLD_SIZE = 128;

    ////Nazwy tekstur w atlasie
    public static final String TEXTURE_ATLAS = "rawAssets-packed/pack.atlas";
    public static final String ALIEN_BLUE_RIGHT_ONE = "alienblue_walk1right";
    public static final String ALIEN_BLUE_LEFT_ONE = "alienblue_walk1left";
    public static final String ALIEN_BLUE_RIGHT_TWO = "alienblue_walk2right";
    public static final String ALIEN_BLUE_LEFT_TWO = "alienblue_walk2left";
    public static final String ALIEN_JUMP_RIGHT = "alienBlue_jumpright";
    public static final String ALIEN_JUMP_LEFT = "alienBlue_jumpleft";
    public static final String ALIEN_STANDING = "alienblue";


    ////pozycja
    public static final Vector2 PLAYER_POSITION = new Vector2(16, 24);
    public static final float PLAYER_HEIGHT = 16.0f;

    ////Fizyka
    public static final float MOVEMENT_SPEED = 64;
    public static final float JUMP_SPEED = 250;
    public static final float MAX_JUMP_DURATION = .15f;
    public static final float GRAVITY_ACCELERATION = 1000;

    ///Animacja
    public static final float LOOP_DURATION = .25f;
}
