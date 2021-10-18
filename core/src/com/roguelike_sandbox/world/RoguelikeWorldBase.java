package com.roguelike_sandbox.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.roguelike_sandbox.audio.MusicEffect;
import com.roguelike_sandbox.audio.MusicPlayer;
import com.roguelike_sandbox.character.Player;
import com.roguelike_sandbox.game.GameSettings;

public class RoguelikeWorldBase {

    protected static final int TILE_SIZE = 32;

    private final OrthographicCamera camera;
    protected TiledMap tiledMap;
    protected TiledMapRenderer tiledMapRenderer;
    private final World box2DWorld;
    private final ExtendViewport viewport;
    private final GameSettings settings;
    private MusicPlayer musicPlayer;
    private Array<Body> bodies;

    public RoguelikeWorldBase(GameSettings settings) {
        this.settings = settings;

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
    }

    private static PolygonShape createPolygon(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2(rectangle.width, rectangle.height);
        polygon.setAsBox(size.x / 2f, size.y / 2f, new Vector2(rectangle.x + size.x / 2, rectangle.y + size.y / 2), 0.0f);
        return polygon;
    }

    public void setMusicPlayer(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    public void setCameraPos(Vector2 newPosition) {
        camera.position.x = newPosition.x;
        camera.position.y = newPosition.y;
    }

    public void render(Player player) {
        musicPlayer.loopInSegments();
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();


        //TODO: remove debug
        debug();


        box2DWorld.step(1f / 120f, 6, 2);
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
                // rectObj.getRectangle()
                PolygonShape shape = RoguelikeWorldBase.createPolygon(rectObj);

                BodyDef bd = new BodyDef();
                bd.type = BodyDef.BodyType.StaticBody;
                //bd.position = new Vector2(rectObj.getRectangle().x, rectObj.getRectangle().getY());
                Body body = box2DWorld.createBody(bd);
                body.createFixture(shape, 0f);
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
