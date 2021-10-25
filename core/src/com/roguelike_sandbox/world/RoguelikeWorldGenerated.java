package com.roguelike_sandbox.world;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.roguelike_sandbox.game.GameClass;

public class RoguelikeWorldGenerated extends AbstractRoguelikeWorld {

    private final TileTextureProvider textureProvider;
    private final int seed;

    public RoguelikeWorldGenerated(GameClass game, String worldType, int seed) {
        super(game);

        this.seed = seed;

        textureProvider = new TileTextureProvider(worldType);
        tiledMap = new TiledMap();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        generateTileMap();
        setCameraPositions();
    }

    public void generateTileMap() {
        float w = 1000;
        float h = 1000;

        MapLayers layers = tiledMap.getLayers();
        TiledMapTileLayer layer1 = new TiledMapTileLayer(1000, 800, AbstractRoguelikeWorld.TILE_SIZE, AbstractRoguelikeWorld.TILE_SIZE);
        layer1.setName("Ground");

        TiledMapTileLayer.Cell dirtCell = new TiledMapTileLayer.Cell();
        dirtCell.setTile(new StaticTiledMapTile(textureProvider.getTexture(TileTexture.GROUND)));
        TiledMapTileLayer.Cell rocksCell = new TiledMapTileLayer.Cell();
        rocksCell.setTile(new StaticTiledMapTile(textureProvider.getTexture(TileTexture.ROCKS)));
        TiledMapTileLayer.Cell sandCell = new TiledMapTileLayer.Cell();
        sandCell.setTile(new StaticTiledMapTile(textureProvider.getTexture(TileTexture.SAND)));
        TiledMapTileLayer.Cell lakeCell = new TiledMapTileLayer.Cell();
        lakeCell.setTile(new StaticTiledMapTile(textureProvider.getTexture(TileTexture.LAKE)));

        for (int i = 0; i < w / AbstractRoguelikeWorld.TILE_SIZE; i++) {
            for (int j = 0; j < h / AbstractRoguelikeWorld.TILE_SIZE; j++) {
                double noiseValue = getNoiseValue(i, j);
                if (noiseValue < -0.30) {
                    layer1.setCell(i, j, lakeCell);
                } else if (noiseValue < -0.24) {
                    layer1.setCell(i, j, sandCell);
                } else if (noiseValue < 0.25) {
                    layer1.setCell(i, j, dirtCell);
                } else {
                    layer1.setCell(i, j, rocksCell);
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
