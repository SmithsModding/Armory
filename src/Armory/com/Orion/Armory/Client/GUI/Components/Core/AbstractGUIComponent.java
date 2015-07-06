package com.Orion.Armory.Client.GUI.Components.Core;

import codechicken.lib.vec.Rectangle4i;
import com.Orion.Armory.Client.GUI.ArmoryBaseGui;
import com.Orion.Armory.Client.GUI.Components.ToolTips.IToolTip;
import com.Orion.Armory.Client.GUI.Components.ToolTips.IToolTipProvider;
import com.Orion.Armory.Util.Core.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

/**
 * Created by Orion
 * Created on 25.04.2015
 * 12:34
 * <p/>
 * Copyrighted according to Project specific license
 */
public abstract class AbstractGUIComponent extends Gui implements IGUIComponent
{
    protected ArmoryBaseGui iGui;

    public int iHeight = 0;
    public int iWidth = 0;
    public int iLeft = 0;
    public int iTop = 0;

    String iInternalName;

    @Override
    public Rectangle getOccupiedArea() {
        return new Rectangle(iLeft,0, iTop, iWidth, iHeight);
    }

    @Override
    public ArmoryBaseGui getBaseGui() {
        return iGui;
    }

    @Override
    public Rectangle getToolTipVisibileArea() {
        return new Rectangle(-1,0,-1,1,1);
    }

    @Override
    public ArrayList<IToolTip> getToolTipLines() {
        return new ArrayList<IToolTip>();
    }

    @Override
    public void setToolTipVisibleArea(Rectangle pNewArea) {
        return;
    }

    public AbstractGUIComponent(ArmoryBaseGui pGui, String pInternalName, int pLeft, int pTop, int pWidth, int pHeight)
    {
        super();

        iGui = pGui;

        iInternalName = pInternalName;

        iHeight = pHeight;
        iWidth = pWidth;
        iLeft = pLeft;
        iTop = pTop;
    }

    public String getInternalName()
    {
        return iInternalName;
    }

    public abstract void onUpdate();

    public int getHeight()
    {
        return iHeight;
    }

    public int getWidth() {return iWidth;}

    public void draw(int pX, int pY)
    {
        drawBackGround(pX, pY);
        drawForeGround(pX, pY);
    }

    public abstract void drawForeGround(int pX, int pY);

    public abstract void drawBackGround(int pX, int pY);

    public abstract void drawToolTips(int pMouseX, int pMouseY);

    public boolean checkIfPointIsInComponent(int pTargetX, int pTargetY)
    {
        Rectangle tComponentBounds = new Rectangle(iLeft, 0, iTop, iWidth, iHeight);

        return tComponentBounds.contains(pTargetX, pTargetY);
    }

    public abstract boolean handleMouseClicked(int pMouseX, int pMouseY, int pMouseButton);

    public boolean handleKeyTyped(char pKey, int pParaa)
    {
        return false;
    }

    public boolean requiresForcedInput()
    {
        return false;
    }
}
