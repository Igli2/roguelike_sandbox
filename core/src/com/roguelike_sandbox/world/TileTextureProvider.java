package com.roguelike_sandbox.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class TileTextureProvider {
    private final Map<String, Texture> textures;

    public TileTextureProvider() {
        textures = new HashMap<>();

        for (TileTexture tt : TileTexture.values()) {
            Texture texture = new Texture(Gdx.files.internal(tt.filename));
            textures.put(tt.filename, texture);
        }
    }

    public TextureRegion getTexture(TileTexture tileTexture) {
        return new TextureRegion(textures.get(tileTexture.filename), 0, 0, tileTexture.width, tileTexture.height);
    }

    public void dispose() {
        for (Texture t : textures.values()) {
            t.dispose();
        }
    }
}
