package com.smithsmodding.armory.compatibility;

import com.smithsmodding.armory.api.util.references.ModBlocks;
import com.smithsmodding.armory.api.util.references.References;
import com.smithsmodding.armory.client.gui.implementations.blacksmithsanvil.GuiBlacksmithsAnvil;
import com.smithsmodding.armory.compatibility.recipes.anvil.BlackSmithsAnvilRecipeHandler;
import com.smithsmodding.armory.compatibility.recipes.anvil.BlackSmithsAnvilRecipeMaker;
import com.smithsmodding.armory.compatibility.recipes.anvil.BlacksmithsAnvilRecipeCategory;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

/**
 * Author Orion (Created on: 21.06.2016)
 */
@JEIPlugin
public class JEICompatMod extends BlankModPlugin {

    private static IJeiHelpers HELPERS;

    public static IJeiHelpers getJeiHelpers() {
        return HELPERS;
    }

    @Override
    public void register(@Nonnull IModRegistry registry) {
        HELPERS = registry.getJeiHelpers();

        registry.addRecipeCategories(new BlacksmithsAnvilRecipeCategory());
        registry.addRecipeHandlers(new BlackSmithsAnvilRecipeHandler());
        registry.addRecipes(BlackSmithsAnvilRecipeMaker.getRecipes());
        registry.addRecipeClickArea(GuiBlacksmithsAnvil.class, 17, 7, 30, 30, References.Compatibility.JEI.RecipeTypes.ANVIL);

        NonNullList<ItemStack> anvils = NonNullList.create();
        ModBlocks.BL_ANVIL.getSubBlocks(Item.getItemFromBlock(ModBlocks.BL_ANVIL), null, anvils);

        for (ItemStack stack : anvils)
            registry.addRecipeCategoryCraftingItem(stack, References.Compatibility.JEI.RecipeTypes.ANVIL);

/*        getJeiHelpers().getNbtIgnoreList().ignoreNbtTagNames(References.NBTTagCompoundData.HeatedObject.CURRENTTEMPERATURE);
        getJeiHelpers().getNbtIgnoreList().ignoreNbtTagNames(References.NBTTagCompoundData.HeatedObject.ORIGINALITEM);
        getJeiHelpers().getNbtIgnoreList().ignoreNbtTagNames(CoreReferences.NBT.IItemProperties.TARGET);*/
    }
}
