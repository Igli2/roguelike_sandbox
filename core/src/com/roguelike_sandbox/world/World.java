package com.roguelike_sandbox.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.Random;

public class World {
    public static int TILE_SIZE = 32;
    private final int seed;

    OrthographicCamera camera;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    TileTextureProvider textureProvider;

    public World() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        seed = new Random().nextInt(Integer.MAX_VALUE);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();

        tiledMap = new TiledMap();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        textureProvider = new TileTextureProvider();
    }

    public void setCameraPos(double x, double y) {
        camera.position.x = (float) x;
        camera.position.y = (float) y;
    }

    public void generateTileMap() {
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
                double noiseValue  = getNoiseValue(i, j);
                if (noiseValue < -0.3) {
                    layer1.setCell(i, j, lavaCell);
                } else {
                    layer1.setCell(i, j, dirtCell);
                }
            }
        }

        layers.add(layer1);
    }

    private double getNoiseValue(int x, int y) {
        return ImprovedNoise.noise((float) x / 20, (float) y / 20, seed / 10);
    }

    public void render() {
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    public void dispose() {
        tiledMap.dispose();
        textureProvider.dispose();
    }
}
