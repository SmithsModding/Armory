package com.smithsmodding.armory.common.fluid;

import com.smithsmodding.armory.api.material.core.IMaterial;
import com.smithsmodding.armory.api.util.client.Textures;
import com.smithsmodding.armory.api.util.client.TranslationKeys;
import com.smithsmodding.armory.api.util.references.References;
import com.smithsmodding.armory.util.MaterialHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * Created by Marc on 19.12.2015.
 */
public class FluidMoltenMetal extends Fluid {

    public FluidMoltenMetal() {
        super(References.InternalNames.Fluids.MOLTENMETAL, new ResourceLocation(Textures.Blocks.LiquidMetalStill.getPrimaryLocation()), new ResourceLocation(Textures.Blocks.LiquidMetalFlow.getPrimaryLocation()));
    }

    @Override
    public int getColor(@Nullable FluidStack stack) {
        if (stack == null || stack.tag == null)
            return getColor();

        IMaterial material = MaterialHelper.getMaterialFromRegistries(new ResourceLocation(stack.tag.getString(References.NBTTagCompoundData.Fluids.MoltenMetal.MATERIAL)));

        if (material == null)
            return getColor();

        return material.getRenderInfo().getLiquidColor().getRGB();
    }

    @Override
    public int getTemperature(@Nullable FluidStack stack) {
        if (stack == null || stack.tag == null)
            return 20;

        IMaterial material = MaterialHelper.getMaterialFromRegistries(new ResourceLocation(stack.tag.getString(References.NBTTagCompoundData.Fluids.MoltenMetal.MATERIAL)));

        if (material == null)
            return 20;

        return Math.round(material.getMeltingPoint());
    }


    @Nonnull
    @Override
    public String getLocalizedName(@Nullable FluidStack stack) {
        if (stack == null || stack.tag == null)
            return "Undefined molten Metal.";

        IMaterial material = MaterialHelper.getMaterialFromRegistries(new ResourceLocation(stack.tag.getString(References.NBTTagCompoundData.Fluids.MoltenMetal.MATERIAL)));

        if (material == null)
            return "Undefined molten Metal.";

        return I18n.format(TranslationKeys.Fluids.MOLTEN) + " " + I18n.format(material.getTranslationKey());
    }
}
