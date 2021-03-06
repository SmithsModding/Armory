package com.smithsmodding.armory.api.client.textures.types;

/*
  A BIG NOTE UPFRONT. Due to the similarities between TiC ToolSystem and Armories armor system this is a near repackage.
  Most of this code falls under their license, although some changes are made to fit the system in with Armories used
  of Wrapper classes instead of direct access.
 */

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MetalTextureTexture extends MetalColoredTexture {

    protected TextureColoredTexture texture2;

    public MetalTextureTexture(String addTextureLocation, @NotNull TextureAtlasSprite baseTexture, String spriteName, int baseColor, float shinyness, float brightness, float hueshift) {
        super(baseTexture, spriteName, baseColor, shinyness, brightness, hueshift);
        texture2 = new TextureColoredTexture(addTextureLocation, baseTexture, spriteName);
    }

    public MetalTextureTexture(@NotNull TextureAtlasSprite addTexture, @NotNull TextureAtlasSprite baseTexture, String spriteName, int baseColor, float shinyness, float brightness, float hueshift) {
        super(baseTexture, spriteName, baseColor, shinyness, brightness, hueshift);
        texture2 = new TextureColoredTexture(addTexture, baseTexture, spriteName);
    }

    @Override
    public boolean load (IResourceManager manager, ResourceLocation location) {
        // at frist do the metal texture
        texture2.load(manager, location);
        return super.load(manager, location);
    }

    @Override
    protected void processData (int[][] data) {
        // go over the base texture and color it
        for (int mipmap = 0; mipmap < data.length; mipmap++) {
            if (data[mipmap] == null) {
                continue;
            }
            for (int pxCoord = 0; pxCoord < data[mipmap].length; pxCoord++) {
                // getCreationRecipe input from metal
                data[mipmap][pxCoord] = colorPixel(texture2.getFrameTextureData(0)[mipmap][pxCoord], mipmap, pxCoord);
            }
        }
    }
}
