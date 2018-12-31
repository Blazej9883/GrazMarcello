package com.mygdx.grazmarcelem.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.grazmarcelem.utilities.Assets;
import com.mygdx.grazmarcelem.utilities.Constants;

import java.lang.reflect.Member;

public class Player {
    public static final String tag = Assets.class.getName();

    Vector2 position;
    Vector2 velocity;

    Facing facing;
    JumpState jumpState;
    WalkState walkState;

    long jumpStartTime;
    long walkStartTime;

    public Player() {
        position = new Vector2(0,Constants.PLAYER_HEIGHT);
        facing = Facing.RIGHT;
        walkState = WalkState.STANDING;

        velocity = new Vector2();
        jumpState = JumpState.FALLING;
    }

    public void update(float delta) {
        velocity.y -= delta * Constants.GRAVITY_ACCELERATION;

        position.mulAdd(velocity, delta);


        if(jumpState != JumpState.JUMPING){
            jumpState = JumpState.FALLING;
        }

        if(position.y - Constants.PLAYER_HEIGHT < 0) {
            jumpState = JumpState.GROUNDED;
            position.y = Constants.PLAYER_HEIGHT;
            velocity.y = 0;
        }





        if(Gdx.input.isTouched()){
            if(Gdx.input.getX() > 1200){
                System.out.println("PRAWO" + Gdx.input.getX());
                moveRight(delta);
                switch (jumpState){
                    case GROUNDED:
                        startJump();
                        break;
                    case JUMPING:
                        continueJump();
                        break;
                    case FALLING:
                        break;
                }
            }else {
                endJump();
                walkState = WalkState.STANDING;
            }
            if(Gdx.input.getX() < 1200) {
                System.out.println("LEWo"  + Gdx.input.getX());
                moveLeft(delta);
            }else
                walkState = WalkState.STANDING;
        }

    }



    private void startJump() {
        jumpState = JumpState.JUMPING;
        jumpStartTime = TimeUtils.nanoTime();
        continueJump();
    }

    private void continueJump() {



        if(jumpState == JumpState.JUMPING){

            float jumpDuration = MathUtils.nanoToSec *  (TimeUtils.nanoTime() - jumpStartTime);

            if(jumpDuration < Constants.MAX_JUMP_DURATION) {
                velocity.y = Constants.JUMP_SPEED;
            }else {
                endJump();
            }
        }
        return;
    }

    private void endJump() {
        if(jumpState == JumpState.JUMPING){
            jumpState = JumpState.FALLING;
        }
    }




    private void moveRight(float delta) {
        if(jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        facing = Facing.RIGHT;
        position.x += Constants.MOVEMENT_SPEED * delta;
    }

    private void moveLeft(float delta) {
        if(jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        facing = Facing.LEFT;
        position.x -= Constants.MOVEMENT_SPEED * delta;
    }






    public void render(SpriteBatch batch) {

        TextureRegion region = Assets.instance.playerAssets.alienBlueRightOne;
        //skakac w prawo   isc w prawo    skakac w lewo    isc w lewo      stac w prawo      stac w lewo
        if(facing == Facing.RIGHT && jumpState != JumpState.GROUNDED) {
            region = Assets.instance.playerAssets.alienBlueJumpRight;
        }else if(facing == Facing.RIGHT && walkState == WalkState.WALKING) {
            float walkDuration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);



            region = (TextureRegion) Assets.instance.playerAssets.walkRightAnimation.getKeyFrame(walkDuration);
        }else if(facing == Facing.LEFT && jumpState != JumpState.GROUNDED) {
            region = Assets.instance.playerAssets.alienBlueJumpLeft;
        }else if(facing == Facing.LEFT && walkState == WalkState.WALKING) {
            float walkDuration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);


            region = (TextureRegion) Assets.instance.playerAssets.walkLeftAnimation.getKeyFrame(walkDuration);
        }else if(facing == Facing.RIGHT && walkState == WalkState.STANDING) {
            region = Assets.instance.playerAssets.alienStanding;
        }else if (facing == Facing.LEFT && walkState == WalkState.STANDING) {
            region = Assets.instance.playerAssets.alienStanding;
        }

        batch.draw(
                region.getTexture(),
                position.x - Constants.PLAYER_POSITION.x,
                position.y - Constants.PLAYER_POSITION.y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }



    enum JumpState {
        JUMPING,
        FALLING,
        GROUNDED
    }

    enum Facing {
        LEFT,
        RIGHT
    }

    enum WalkState {
        WALKING,
        STANDING,
    }
}
