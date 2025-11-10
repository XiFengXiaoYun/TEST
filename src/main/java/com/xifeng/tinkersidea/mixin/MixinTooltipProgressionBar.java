package com.xifeng.tinkersidea.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.xifeng.tinkersidea.Weapons.wizardry.SpellBladeHelper;
import electroblob.wizardry.client.DrawingUtils;
import electroblob.wizardry.client.gui.GuiArcaneWorkbench;
import electroblob.wizardry.constants.Tier;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "electroblob.wizardry.client.gui.GuiArcaneWorkbench$TooltipElementProgressionBar")
public abstract class MixinTooltipProgressionBar {

    @Shadow(remap = false)
    @Final
    GuiArcaneWorkbench this$0;

    @Unique
    private static boolean tEST$isSpellBlade(ItemStack stack) {
        return stack.getTagCompound() != null && stack.getTagCompound().hasKey("WizardryData");
    }

    @ModifyReturnValue(
            method = "isVisible",
            at = @At("RETURN"),
            remap = false
    )
    private boolean canVisible(boolean original, ItemStack stack) {
        if(!original) {
            return tEST$isSpellBlade(stack);
        }
        return true;
    }

    @Inject(
            method = "drawBackground",
            at = @At("HEAD"),
            remap = false,
            cancellable = true
    )
    private void mixinDrawBackground(int x, int y, ItemStack stack, float partialTicks, int mouseX, int mouseY, CallbackInfo ci) {
        if(tEST$isSpellBlade(stack)) {
            ci.cancel();
            y += 3 + 1;
            NBTTagCompound nbt = SpellBladeHelper.getWizardryData(stack);

            Tier tier = Tier.valueOf(nbt.getString("tier"));

            float progressFraction = 1;

            if(tier != Tier.MASTER){
                progressFraction = (float) SpellBladeHelper.getProgression(stack) / Tier.values()[tier.level + 1].getProgression();
            }

            DrawingUtils.drawTexturedRect(x, y, 176, 220 + 3, 131, 3, 512, 512);
            int width = (int)(131 * progressFraction);
            DrawingUtils.drawTexturedRect(x, y, 176, 220, width, 3, 512, 512);
        }
    }

    @Inject(
            method = "drawForeground",
            at = @At("HEAD"),
            remap = false,
            cancellable = true
    )
    private void mixinDrawForeground(int x, int y, ItemStack stack, int mouseX, int mouseY, CallbackInfo ci) {
        if(tEST$isSpellBlade(stack)) {
            ci.cancel();
            NBTTagCompound nbt = SpellBladeHelper.getWizardryData(stack);
            Tier tier = Tier.valueOf(nbt.getString("tier"));
            FontRenderer fontRenderer = this$0.mc.fontRenderer;
            fontRenderer.drawStringWithShadow(tier.getDisplayNameWithFormatting(), x, y, 0);

            if(tier != Tier.MASTER){
                Tier nextTier = Tier.values()[tier.level + 1];
                String s = TextFormatting.DARK_GRAY + nextTier.getDisplayName();
                if(SpellBladeHelper.getProgression(stack) >= nextTier.getProgression()) s = nextTier.getDisplayNameWithFormatting();
                fontRenderer.drawStringWithShadow(s, x + 144 - 6 * 2 - fontRenderer.getStringWidth(s), y, 0);
            }
        }
    }
}
