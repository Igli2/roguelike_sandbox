package com.roguelike_sandbox.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class RoguelikeWorld {

    private static final int TILE_SIZE = 32;

    // private final int seed;

    private final OrthographicCamera camera;
    private final TiledMap tiledMap;
    private final TiledMapRenderer tiledMapRenderer;
    private final World box2DWorld;
    private Array<Body> bodies;

    public RoguelikeWorld() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        // seed = new Random().nextInt(Integer.MAX_VALUE);

        camera = new OrthographicCamera();
        camera.zoom -= 0.5f;
        camera.setToOrtho(false, w, h);
        camera.update();

        box2DWorld = new World(new Vector2(0, 0), true);
        tiledMap = new TmxMapLoader().load("tilemaps/lobby.tmx");
        createBodies();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    private static PolygonShape createPolygon(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / RoguelikeWorld.TILE_SIZE, (rectangle.y + rectangle.height * 0.5f) / RoguelikeWorld.TILE_SIZE);
        polygon.setAsBox(rectangle.width * 0.5f / RoguelikeWorld.TILE_SIZE, rectangle.height * 0.5f / RoguelikeWorld.TILE_SIZE, size, 0.0f);
        return polygon;
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

    public void setCameraPos(Vector2 newPosition) {
        camera.position.x = newPosition.x;
        camera.position.y = newPosition.y;
    }

    public void render() {
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    public void createBodies() {
        MapObjects objects = tiledMap.getLayers().get("Colliders").getObjects();
        bodies = new Array<>();

        for (MapObject obj : objects) {
            if (obj instanceof RectangleMapObject) {
                RectangleMapObject rectObj = (RectangleMapObject) obj;
                // rectObj.getRectangle()
                PolygonShape shape = RoguelikeWorld.createPolygon(rectObj);

                BodyDef bd = new BodyDef();
                bd.type = BodyDef.BodyType.StaticBody;
                Body body = box2DWorld.createBody(bd);
                body.createFixture(shape, 1);

                bodies.add(body);
            }
        }
    }

    public void dispose() {
        tiledMap.dispose();
    }

    public World getBox2DWorld() {
        return box2DWorld;
    }
}
