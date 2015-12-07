package com.SmithsModding.Armory.Client.Textures;

import com.SmithsModding.Armory.API.Materials.IArmorMaterial;
import com.SmithsModding.Armory.API.Materials.IMaterialRenderInfo;
import com.SmithsModding.Armory.API.Textures.AbstractColoredTexture;
import com.SmithsModding.Armory.API.Textures.GuiOutlineTexture;
import com.SmithsModding.Armory.Armory;
import com.SmithsModding.Armory.Common.Material.ArmorMaterial;
import com.SmithsModding.Armory.Common.Material.MaterialRegistry;
import com.SmithsModding.SmithsCore.Util.Client.ResourceHelper;
import com.google.common.collect.Maps;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Marc on 06.12.2015.
 * <p/>
 * TextureManager used to handle grayscale textures and color them for each material.
 * Modelled after parts of the TinkersConstruct CustomTextureCreator.
 */
public class MaterializedTextureCreator implements IResourceManagerReloadListener {

    private static final IArmorMaterial guiMaterial;
    //Variable containing the location of all grayscale base textures.
    private static ArrayList<ResourceLocation> baseTextures = new ArrayList<ResourceLocation>();
    //Variable that holds the colored end textures when the Creator has reloaded
    private static Map<String, Map<String, TextureAtlasSprite>> buildSprites = Maps.newHashMap();

    //Initializes the dummy gui material with a proper set of render info.
    static {
        guiMaterial = new ArmorMaterial("_internal_gui", "Internal-Gui", "GUI", EnumChatFormatting.WHITE, false, 0F, 0F, null);
        guiMaterial.setRenderInfo(new IMaterialRenderInfo.AbstractMaterialRenderInfo() {
            @Override
            public TextureAtlasSprite getTexture (TextureAtlasSprite baseTexture, String location) {
                return new GuiOutlineTexture(baseTexture, location);
            }
        });
    }

    /**
     * Static method to register a new GrayScale texture to the Creator.
     *
     * @param location The location of the Texture.
     */
    public static void registerBaseTexture (ResourceLocation location) {
        baseTextures.add(location);
    }

    /**
     * Static method to register more then one GrayScale texture to the Creator.
     *
     * @param locations The location of the Textures to register.
     */
    public static void registerBaseTexture (Collection<ResourceLocation> locations) {
        baseTextures.addAll(locations);
    }

    /**
     * Static method to get the builded textures.
     *
     * @return A map containing all the colored textures using the base texture and the materialname as keys.
     */
    public static Map<String, Map<String, TextureAtlasSprite>> getBuildSprites () {
        return buildSprites;
    }

    /**
     * Actual construction method is called from the ForgeEvent system.
     * This method kicks the creation of the textures of and provided a map to put the textures in.
     *
     * @param event The Event fired before the TextureSheet is stitched. TextureStitchEvent.Pre instance.
     */
    @SubscribeEvent(priority = EventPriority.LOW)
    public void createCustomTextures (TextureStitchEvent.Pre event) {
        //Only run the creation once, after all mods have been loaded.
        if (!Loader.instance().hasReachedState(LoaderState.POSTINITIALIZATION)) {
            return;
        }

        //Function is called so that all textures can be created.
        createMaterialTextures(event.map);
    }

    /**
     * Method used to create textures that are material dependend based on the given baseTextures list.
     *
     * @param map The map to register the textures to.
     */
    public void createMaterialTextures (TextureMap map) {
        for (ResourceLocation baseTexture : baseTextures) {
            if (baseTexture.toString().equals("minecraft:missingno")) {
                //A missing texture does not need coloring. Skipping.
                continue;
            }

            //Grabbing the base texture so that we can color a copy.
            TextureAtlasSprite base = map.getTextureExtry(baseTexture.toString());
            if (base == null) {
                //A missing texture does not need coloring. Skipping.
                Armory.getLogger().error("Missing base texture: " + baseTexture.toString());
                continue;
            }

            Map<String, TextureAtlasSprite> coloredSprites = Maps.newHashMap();
            for (IArmorMaterial material : MaterialRegistry.getInstance().getArmorMaterials().values()) {
                TextureAtlasSprite sprite = createTexture(material, baseTexture, base, map);
                if (sprite != null) {
                    coloredSprites.put(material.getInternalMaterialName(), sprite);
                }
            }

            /*
            This code is currently not used until I have a way to make Addons into items.

            if(belongsToToolPart(baseTexture)) {
                TextureAtlasSprite sprite = createTexture(guiMaterial, baseTexture, base, map);
                if(sprite != null) {
                    coloredSprites.put(guiMaterial.identifier, sprite);
                }
            }*/

            buildSprites.put(baseTexture.toString(), coloredSprites);
        }
    }

    private TextureAtlasSprite createTexture (IArmorMaterial material, ResourceLocation baseTexture, TextureAtlasSprite base, TextureMap map) {
        String location = baseTexture.toString() + "_" + material.getInternalMaterialName();
        TextureAtlasSprite sprite;

        if (ResourceHelper.exists(location)) {
            sprite = map.registerSprite(new ResourceLocation(location));
        } else {
            // material does not need a special generated texture
            if (material.getRenderInfo() == null) {
                return null;
            }

            TextureAtlasSprite matBase = base;

            // different base texture?
            if (material.getRenderInfo().getTextureSuffix() != null) {
                String loc2 = baseTexture.toString() + "_" + material.getRenderInfo().getTextureSuffix();
                TextureAtlasSprite base2 = map.getTextureExtry(loc2);
                // can we manually load it?
                if (base2 == null && ResourceHelper.exists(loc2)) {
                    base2 = new AbstractColoredTexture(loc2, loc2) {
                        @Override
                        protected int colorPixel (int pixel, int mipmap, int pxCoord) {
                            return pixel;
                        }
                    };

                    // save in the map so it's getting reused by the others and is available
                    map.setTextureEntry(loc2, base2);
                }
                if (base2 != null) {
                    matBase = base2;
                }
            }

            sprite = material.getRenderInfo().getTexture(matBase, location);
        }

        // stitch new textures
        if (sprite != null && material.getRenderInfo().isStitched()) {
            map.setTextureEntry(location, sprite);
        }
        return sprite;
    }

    /**
     * Method called when the resource manager reloads.
     * Clears all the sprites.
     *
     * @param resourceManager The resource manager that reloaded.
     */
    @Override
    public void onResourceManagerReload (IResourceManager resourceManager) {
        baseTextures.clear();
        for (Map map : buildSprites.values()) {
            map.clear();
        }
        buildSprites.clear();
    }
}