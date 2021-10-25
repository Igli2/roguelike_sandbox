package com.roguelike_sandbox.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class TileTextureProvider {
    private final Map<String, Texture> textures;
    private final String worldType;

    public TileTextureProvider(String worldType) {
        textures = new HashMap<>();
        this.worldType = worldType;

        for (TileTexture tt : TileTexture.values()) {
            String filePath = "images/world_generated_tilesets/" + worldType + "/" + tt.filename;
            Texture texture = new Texture(Gdx.files.internal(filePath));
            textures.put(filePath, texture);
        }
    }

    public TextureRegion getTexture(TileTexture tileTexture) {
        return new TextureRegion(textures.get("images/world_generated_tilesets/" + worldType + "/" + tileTexture.filename), 0, 0, tileTexture.width, tileTexture.height);
    }

    public void dispose() {
        for (Texture t : textures.values()) {
            t.dispose();
        }
    }
}
