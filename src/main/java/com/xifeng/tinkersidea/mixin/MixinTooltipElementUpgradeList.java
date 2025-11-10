package com.xifeng.tinkersidea.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "electroblob.wizardry.client.gui.GuiArcaneWorkbench$TooltipElementUpgradeList")
public abstract class MixinTooltipElementUpgradeList {
    @ModifyReturnValue(
            method = "isVisible",
            at = @At("RETURN"),
            remap = false
    )
    private boolean canVisible(boolean original, ItemStack stack) {
        if(!original) {
            return stack.getTagCompound() != null && stack.getTagCompound().hasKey("WizardryData");
        }
        return true;
    }
}
