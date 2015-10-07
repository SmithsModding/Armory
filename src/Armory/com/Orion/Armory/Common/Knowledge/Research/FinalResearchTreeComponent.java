/*
 * Copyright (c) 2015.
 *
 * Copyrighted by SmithsModding according to the project License
 */

package com.Orion.Armory.Common.Knowledge.Research;

import com.Orion.Armory.Util.Client.TranslationKeys;
import com.Orion.Armory.Util.References;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public abstract class FinalResearchTreeComponent extends StandardResearchTreeComponent {

    public FinalResearchTreeComponent(ItemStack pTargetStack) {
        super(pTargetStack, References.InternalNames.InputHandlers.BookBinder.COMPLETE);
    }

    @Override
    public boolean isFinalComponentInBranch() {
        return true;
    }

    @Override
    public String getDisplayString() {
        return StatCollector.translateToFallback(TranslationKeys.GUI.BookBinder.Research.ResearchComplete);
    }
}