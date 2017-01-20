package com.smithsmodding.armory.common.item;

import com.smithsmodding.armory.api.heatable.IHeatableObjectType;
import com.smithsmodding.armory.api.util.references.ModCreativeTabs;
import com.smithsmodding.armory.api.util.references.ModHeatedObjectTypes;
import com.smithsmodding.armory.api.util.references.References;

/**
 * Created by Orion
 * Created on 17.05.2015
 * 14:41
 * <p>
 * Copyrighted according to Project specific license
 */
public class ItemHeatablePlate extends ItemHeatableResource{

    public ItemHeatablePlate() {
        this.setMaxStackSize(64);
        this.setCreativeTab(ModCreativeTabs.componentsTab);
        this.setUnlocalizedName(References.InternalNames.Items.ItemMetalPlate);
        this.setRegistryName(References.InternalNames.Items.ItemMetalPlate);
    }

    @Override
    public IHeatableObjectType getHeatableObjectType() {
        return ModHeatedObjectTypes.PLATE;
    }
}
