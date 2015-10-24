package com.SmithsModding.Armory.Client.GUI.Components;

import com.SmithsModding.Armory.Client.GUI.Components.Core.AbstractGUIComponent;
import com.SmithsModding.Armory.Client.GUI.Components.Core.IComponentHost;
import com.SmithsModding.Armory.Util.Client.CustomResource;
import com.SmithsModding.Armory.Util.Client.GUI.GuiHelper;
import org.lwjgl.opengl.GL11;

/**
 * Created by Orion
 * Created on 03.05.2015
 * 12:12
 * <p/>
 * Copyrighted according to Project specific license
 */
public class ComponentImage extends AbstractGUIComponent {

    CustomResource iTargetResource;

    public ComponentImage(IComponentHost pHost, String pInternalName, int pLeft, int pTop, CustomResource pTargetResource) {
        super(pHost, pInternalName, pLeft, pTop, pTargetResource.getWidth(), pTargetResource.getHeight());
        iTargetResource = pTargetResource;
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void drawForeGround(int pX, int pY) {

    }

    @Override
    public void drawBackGround(int pX, int pY) {
        GL11.glPushMatrix();
        GL11.glColor4f(iTargetResource.getColor().getColorRedFloat(), iTargetResource.getColor().getColorGreenFloat(), iTargetResource.getColor().getColorBlueFloat(), iTargetResource.getColor().getAlphaFloat());

        GuiHelper.bindTexture(iTargetResource.getPrimaryLocation());
        drawTexturedModalRect(iLeft, iTop, iTargetResource.getU(), iTargetResource.getV(), iTargetResource.getWidth(), iTargetResource.getHeight());

        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glPopMatrix();
    }

    @Override
    public boolean handleMouseClicked(int pMouseX, int pMouseY, int pMouseButton) {
        return false;
    }
}