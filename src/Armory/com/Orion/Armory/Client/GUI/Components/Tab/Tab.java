/*
 * Copyright (c) 2015.
 *
 * Copyrighted by SmithsModding according to the project License
 */

package com.Orion.Armory.Client.GUI.Components.Tab;

import com.Orion.Armory.Client.GUI.Components.Core.IComponentHost;
import com.Orion.Armory.Client.GUI.Components.Core.StandardComponentManager;
import com.Orion.Armory.Client.GUI.Components.Ledgers.LedgerManager;
import com.Orion.Armory.Client.GUI.Components.ToolTips.IToolTip;
import com.Orion.Armory.Client.GUI.Components.ToolTips.IToolTipProvider;
import com.Orion.Armory.Client.GUI.Components.ToolTips.StandardToolTip;
import com.Orion.Armory.Util.Core.Rectangle;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.ArrayList;


public abstract class Tab implements IToolTipProvider {
    protected StandardComponentManager iComponents;
    protected LedgerManager iLedgers;
    protected int iXSize;
    protected int iYSize;
    protected TabManager iTabManager;
    private String iInternalID;
    private ItemStack iIconStack;
    private String iToolTipText;

    public Tab(String pInternalID, ItemStack pIconStack, String pToolTipText) {
        this.iInternalID = pInternalID;
        this.iIconStack = pIconStack;

        this.iXSize = 120;
        this.iYSize = 120;

        this.iToolTipText = pToolTipText;
    }

    public String getInternalID() {
        return iInternalID;
    }

    public ItemStack getIconStack() {
        return iIconStack;
    }

    public StandardComponentManager getComponents() {
        return iComponents;
    }

    public int getXSize() {
        return iXSize;
    }

    public int getYSize() {
        return iYSize;
    }

    public String getToolTipText() {
        return iToolTipText;
    }

    public LedgerManager getLedgerManager() {
        return iLedgers;
    }

    public abstract void initializeTab(ITabbedHost pHost);

    @Override
    public IComponentHost getComponentHost() {
        return iTabManager;
    }

    @Override
    public String getInternalName() {
        return iInternalID;
    }

    @Override
    public Rectangle getToolTipVisibileArea() {
        int iTabIndex = iTabManager.getAllRegisteredTabs().indexOf(this);

        return new Rectangle(iTabIndex * TabManager.TABSIZEX, 0, 0, TabManager.TABSIZEX, TabManager.TABSIZEY);
    }

    @Override
    public void setToolTipVisibleArea(Rectangle pNewArea) {
        return;
    }

    @Override
    public Rectangle getOccupiedArea() {
        return new Rectangle(0, 0, 30, iXSize, iYSize);
    }

    @Override
    public ArrayList<IToolTip> getToolTipLines() {
        ArrayList<IToolTip> iToolTips = new ArrayList<IToolTip>();

        iToolTips.add(new StandardToolTip(this, StatCollector.translateToLocal(iToolTipText)));

        return iToolTips;
    }
}
