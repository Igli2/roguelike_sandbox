package com.roguelike_sandbox.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.roguelike_sandbox.audio.MusicEffect;
import com.roguelike_sandbox.audio.MusicPlayer;
import com.roguelike_sandbox.character.EntityManager;
import com.roguelike_sandbox.game.GameClass;
import com.roguelike_sandbox.game.GameSettings;

public class RogueLikeWorld {

    protected static final int TILE_SIZE = 32;

    private final OrthographicCamera camera;
    private final World box2DWorld;
    private final ExtendViewport viewport;
    private final GameSettings settings;
    private final GameClass game;
    private final EntityManager entityManager;
    protected TiledMap tiledMap;
    protected TiledMapRenderer tiledMapRenderer;
    private Vector2 cam_minPosition;
    private Vector2 cam_maxPosition;
    private MusicPlayer musicPlayer;
    private Array<Body> bodies;

    public RogueLikeWorld(GameClass game) {
        this.game = game;
        settings = game.settings;

        //TODO: Create music system
        musicPlayer = new MusicPlayer(MusicEffect.WATER_THEME, settings.getMusicVolume(), true);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        camera.zoom -= 0.8f;
        camera.setToOrtho(false, w, h);
        camera.update();

        box2DWorld = new World(new Vector2(0, 0), true);
        entityManager = new EntityManager(game, this);
    }

    private static PolygonShape createPolygon(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2(rectangle.width, rectangle.height);
        polygon.setAsBox(size.x / 2f, size.y / 2f, new Vector2(rectangle.x + size.x / 2, rectangle.y + size.y / 2), 0.0f);
        return polygon;
    }

    protected void setCameraPositions() {
        int tilesX = ((TiledMapTileLayer) tiledMap.getLayers().get("Ground")).getWidth();
        int tilesY = ((TiledMapTileLayer) tiledMap.getLayers().get("Ground")).getHeight();
        int tilesOnScreenX = Gdx.graphics.getWidth() / TILE_SIZE;
        int tilesOnScreenY = Gdx.graphics.getHeight() / TILE_SIZE;

        cam_minPosition = new Vector2(tilesOnScreenX * TILE_SIZE / 2f * camera.zoom + 2f, tilesOnScreenY * TILE_SIZE / 2f * camera.zoom + 10f);
        cam_maxPosition = new Vector2(tilesX * TILE_SIZE - cam_minPosition.x, tilesY * TILE_SIZE - cam_minPosition.y);

    }

    public void setMusicPlayer(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    public void setCameraPos(Vector2 newPosition) {
        if (newPosition.x < cam_minPosition.x) {
            newPosition.x = cam_minPosition.x;
        } else if (newPosition.x > cam_maxPosition.x) {
            newPosition.x = cam_maxPosition.x;
        }
        if (newPosition.y < cam_minPosition.y) {
            newPosition.y = cam_minPosition.y;
        } else if (newPosition.y > cam_maxPosition.y) {
            newPosition.y = cam_maxPosition.y;

        }
        camera.position.set(newPosition, 0);
        camera.update();
    }

    public void update(float dt) {
        musicPlayer.loopInSegments();
        tiledMapRenderer.setView(camera);
        box2DWorld.step(1f / 120f, 6, 2);
        entityManager.update(dt);
    }

    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tiledMapRenderer.render();
        entityManager.render();
        //TODO: remove debug
        debug();
        box2DWorld.step(1f / 60f, 6, 2);
    }

    private void debug() {
        Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
        debugRenderer.render(box2DWorld, camera.combined);
    }

    public void createBodies() {
        MapObjects objects = tiledMap.getLayers().get("Colliders").getObjects();
        bodies = new Array<>();

        for (MapObject obj : objects) {
            if (obj instanceof RectangleMapObject) {
                RectangleMapObject rectObj = (RectangleMapObject) obj;

                BodyDef bd = new BodyDef();
                bd.type = BodyDef.BodyType.StaticBody;
                Body body = box2DWorld.createBody(bd);
                FixtureDef fixture = new FixtureDef();
                fixture.shape = RogueLikeWorld.createPolygon(rectObj);
                body.createFixture(fixture);
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

    public void resizeViewport(int width, int height) {
        viewport.update(width, height);
    }
}
