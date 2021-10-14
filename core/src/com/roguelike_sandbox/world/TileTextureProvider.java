package com.roguelike_sandbox.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileTextureProvider {
    Texture textureAtlas;

    public TileTextureProvider() {
        textureAtlas = new Texture(Gdx.files.internal("images/world/texture_atlas.png"));
    }

    public TextureRegion getTexture(TileTexture tileTexture) {
        TextureRegion textureRegion = new TextureRegion(textureAtlas, tileTexture.x, tileTexture.y, tileTexture.width, tileTexture.height);
        return textureRegion;
    }

    public void dispose() {
        textureAtlas.dispose();
    }
}
