package com.roguelike_sandbox.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.roguelike_sandbox.game.GameClass;
import com.roguelike_sandbox.game.GameSettings;

import java.util.Random;

public class RoguelikeWorldGenerated extends RogueLikeWorld {

    private final int seed;
    private final TileTextureProvider textureProvider;

    public RoguelikeWorldGenerated(GameClass game) {
        super(game);

        seed = new Random().nextInt(Integer.MAX_VALUE);

        textureProvider = new TileTextureProvider();

        tiledMap = new TiledMap();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        generateTileMap();
        setCameraPositions();
    }

    // TODO: add tileset as parameter for different biomes
    public void generateTileMap() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        MapLayers layers = tiledMap.getLayers();
        TiledMapTileLayer layer1 = new TiledMapTileLayer(1000, 800, RogueLikeWorld.TILE_SIZE, RogueLikeWorld.TILE_SIZE);

        TiledMapTileLayer.Cell dirtCell = new TiledMapTileLayer.Cell();
        dirtCell.setTile(new StaticTiledMapTile(textureProvider.getTexture(TileTexture.DIRT)));
        TiledMapTileLayer.Cell lavaCell = new TiledMapTileLayer.Cell();
        lavaCell.setTile(new StaticTiledMapTile(textureProvider.getTexture(TileTexture.PATH)));

        for (int i = 0; i < w / RogueLikeWorld.TILE_SIZE; i++) {
            for (int j = 0; j < h / RogueLikeWorld.TILE_SIZE; j++) {
                double noiseValue = getNoiseValue(i, j);
                if (noiseValue < -0.1) {
                    layer1.setCell(i, j, lavaCell);
                } else {
                    layer1.setCell(i, j, dirtCell);
                }
            }
        }

        layers.add(layer1);
    }

    private double getNoiseValue(int x, int y) {
        return ImprovedNoise.noise((float) x / 20, (float) y / 20, seed / 10f);
    }

    @Override
    public void dispose() {
        super.dispose();
        textureProvider.dispose();
    }
}
