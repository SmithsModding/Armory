/*
 * Copyright (c) 2015.
 *
 * Copyrighted by SmithsModding according to the project License
 */

package com.SmithsModding.Armory.API.Knowledge;

import com.SmithsModding.Armory.Common.Item.Knowledge.*;
import net.minecraft.item.*;

import java.util.*;

public interface IBluePrintContainerItem {

    ArrayList<LabelledBlueprintGroup> getBlueprintGroups (ItemStack pStack);

    void writeBlueprintGroupsToStack (ItemStack pStack, ArrayList<LabelledBlueprintGroup> pGroups);

    void initializeContainer (ItemStack pStack);
}