package com.roguelike_sandbox.world;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.roguelike_sandbox.game.GameClass;

public class RoguelikeWorldStatic extends AbstractRoguelikeWorld {
    public RoguelikeWorldStatic(GameClass game, String tilemapFile) {
        super(game);

        tiledMap = new TmxMapLoader().load("tilemaps/lobby.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        setCameraPositions();
        createBodies();
    }
}
