package com.roguelike_sandbox.world;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.roguelike_sandbox.game.GameSettings;

public class RoguelikeWorldStatic extends RoguelikeWorldBase {
    public RoguelikeWorldStatic(GameSettings settings, String tilemapFile) {
        super(settings);

        tiledMap = new TmxMapLoader().load("tilemaps/lobby.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        createBodies();
    }
}
