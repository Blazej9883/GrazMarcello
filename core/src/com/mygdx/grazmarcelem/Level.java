package com.mygdx.grazmarcelem;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.grazmarcelem.entities.Player;

public class Level {
    Player player;

    public Level() {
        player = new Player();

    }

    public void update(float delta) {
        player.update(delta);
    }

    public void render(SpriteBatch batch) {
        player.render(batch);
    }
}
