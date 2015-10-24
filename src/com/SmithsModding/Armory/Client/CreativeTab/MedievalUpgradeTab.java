package com.SmithsModding.Armory.Client.CreativeTab;

import com.SmithsModding.Armory.Common.Registry.GeneralRegistry;
import com.SmithsModding.Armory.Util.Client.TranslationKeys;
import com.SmithsModding.Armory.Util.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

/**
 * Created by Orion
 * Created on 26.05.2015
 * 16:26
 * <p/>
 * Copyrighted according to Project specific license
 */
public class MedievalUpgradeTab extends CreativeTabs {
    private ItemStack tIconStack;

    public MedievalUpgradeTab() {
        super(CreativeTabs.getNextID(), "Armory - Medieval Armor Upgrades Tab");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        if (tIconStack == null) {
            tIconStack = new ItemStack(GeneralRegistry.Items.iMedievalUpgrades, 1);
            NBTTagCompound pIconCompound = new NBTTagCompound();
            pIconCompound.setString(References.NBTTagCompoundData.Material, References.InternalNames.Materials.Vanilla.OBSIDIAN);
            pIconCompound.setString(References.NBTTagCompoundData.Armor.ArmorID, References.InternalNames.Armor.MEDIEVALHELMET);
            pIconCompound.setString(References.NBTTagCompoundData.Addons.AddonID, References.InternalNames.Upgrades.Helmet.TOP + "-" + References.InternalNames.Materials.Vanilla.OBSIDIAN);

            tIconStack.setTagCompound(pIconCompound);
        }

        return tIconStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel() {
        return StatCollector.translateToLocal(TranslationKeys.CreativeTabs.MedievalUpgrade);
    }

    @Override
    public Item getTabIconItem() {
        return null;
    }
}