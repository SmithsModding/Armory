package com.SmithsModding.Armory.Client.GUI.Components.MultiComponents;

import com.SmithsModding.Armory.Client.GUI.Components.ComponentBorder;
import com.SmithsModding.Armory.Client.GUI.Components.ComponentSlot;
import com.SmithsModding.Armory.Client.GUI.Components.Core.AbstractGUIMultiComponent;
import com.SmithsModding.Armory.Client.GUI.Components.Core.IComponentHost;
import com.SmithsModding.Armory.Util.Client.Colors;
import com.SmithsModding.Armory.Util.Client.Textures;

/**
 * Created by Orion
 * Created on 01.05.2015
 * 15:39
 * <p/>
 * Copyrighted according to Project specific license
 */
public class ComponentPlayerInventory extends AbstractGUIMultiComponent {

    public static final int WIDTH = 176;
    public static final int HEIGTH = 90;

    public ComponentPlayerInventory(IComponentHost pHost, String pInternalName, int pLeft, int pTop, int pPlayerInventoryStartSlotIndex, ComponentBorder.CornerTypes pConnectionType) {
        super(pHost, pInternalName, pLeft, pTop, WIDTH, HEIGTH);

        getComponents().add(new ComponentBorder(pHost, pInternalName + ".Background", 0, 0, 175, 90, Colors.DEFAULT, pConnectionType));

        for (int tSlotIndex = 0; tSlotIndex < 36; tSlotIndex++) {
            int tRowIndex = ((tSlotIndex) / 9);
            int tColumnIndex = (tSlotIndex) % 9;

            if (tRowIndex < 3) {
                addComponent(new ComponentSlot(pHost, pInternalName + ".Slot." + tSlotIndex, tSlotIndex, 18, 18, 7 + tColumnIndex * 18, 7 + tRowIndex * 18, Textures.Gui.Basic.Slots.DEFAULT, Colors.DEFAULT));
            } else {
                addComponent(new ComponentSlot(pHost, pInternalName + ".Slot." + tSlotIndex, tSlotIndex, 18, 18, 7 + tColumnIndex * 18, 11 + tRowIndex * 18, Textures.Gui.Basic.Slots.DEFAULT, Colors.DEFAULT));
            }
        }
    }

    @Override
    public boolean handleMouseClicked(int pMouseX, int pMouseY, int pMouseButton) {
        return false;
    }
}