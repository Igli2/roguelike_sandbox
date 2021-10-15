package com.roguelike_sandbox.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class World {
    // private final int seed;

    OrthographicCamera camera;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;

    public World() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        // seed = new Random().nextInt(Integer.MAX_VALUE);

        camera = new OrthographicCamera();
        camera.zoom -= 0.5f;
        camera.setToOrtho(false, w, h);
        camera.update();

        tiledMap = new TmxMapLoader().load("tilemaps/lobby.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    public void setCameraPos(Vector2 newPosition) {
        camera.position.x = newPosition.x;
        camera.position.y = newPosition.y;
    }

    /* public void generateTileMap() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        MapLayers layers = tiledMap.getLayers();
        TiledMapTileLayer layer1 = new TiledMapTileLayer(1000, 800, World.TILE_SIZE, World.TILE_SIZE);

        TiledMapTileLayer.Cell dirtCell = new TiledMapTileLayer.Cell();
        dirtCell.setTile(new StaticTiledMapTile(textureProvider.getTexture(TileTexture.DIRT)));
        TiledMapTileLayer.Cell lavaCell = new TiledMapTileLayer.Cell();
        lavaCell.setTile(new StaticTiledMapTile(textureProvider.getTexture(TileTexture.LAVA)));

        for (int i = 0; i < w / World.TILE_SIZE; i++) {
            for (int j = 0; j < h / World.TILE_SIZE; j++) {
                double noiseValue = getNoiseValue(i, j);
                if (noiseValue < -0.3) {
                    layer1.setCell(i, j, lavaCell);
                } else {
                    layer1.setCell(i, j, dirtCell);
                }
            }
        }

        layers.add(layer1);
    } */

    /* private double getNoiseValue(int x, int y) {
        return ImprovedNoise.noise((float) x / 20, (float) y / 20, seed / 10);
    } */

    public void render() {
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    public void dispose() {
        tiledMap.dispose();
    }
}
