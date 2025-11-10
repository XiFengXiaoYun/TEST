package com.xifeng.tinkersidea.mixin;

import com.xifeng.tinkersidea.Weapons.wizardry.SpellBladeHelper;
import electroblob.wizardry.client.DrawingUtils;
import electroblob.wizardry.client.gui.GuiArcaneWorkbench;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "electroblob.wizardry.client.gui.GuiArcaneWorkbench$TooltipElementUpgrades")
public abstract class MixinTooltipElementUpgrades {
    @Shadow(remap = false)
    @Final
    GuiArcaneWorkbench this$0;

    @Unique
    private static boolean tEST$isSpellBlade(ItemStack stack) {
        return stack.getTagCompound() != null && stack.getTagCompound().hasKey("WizardryData");
    }

    @Inject(
            method = "getHeight",
            at = @At("HEAD"),
            remap = false,
            cancellable = true
    )
    private void mixinGetHeight(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if(tEST$isSpellBlade(stack)) {
            int rows = 1 + (SpellBladeHelper.getTotalUpgrades(stack) * (16 + 2) - 2)
                    / (144 - 6 * 2);
            cir.setReturnValue(rows * (16 + 2) - 2);
        }
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
            GlStateManager.enableDepth();
            int x1 = 0;
            for(Item item : SpellBladeHelper.getSpecialUpgrades()){
                int level = SpellBladeHelper.getUpgradeLevel(stack, item);
                if(level > 0){
                    ItemStack upgrade = new ItemStack(item, level);
                    RenderItem itemRender = this$0.mc.getRenderItem();
                    itemRender.renderItemAndEffectIntoGUI(upgrade, x + x1, y);
                    itemRender.renderItemOverlayIntoGUI(this$0.mc.fontRenderer, upgrade, x + x1, y, null);
                    x1 += 16 + 2;
                    if(x1 + 16 > 144 - 6 * 2){
                        x1 = 0;
                        y += 16 + 2;
                    }
                }
            }
            GlStateManager.disableDepth();
            GlStateManager.disableLighting();
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
            int x1 = 0;
            for(Item item : SpellBladeHelper.getSpecialUpgrades()){
                int level = SpellBladeHelper.getUpgradeLevel(stack, item);
                if(level > 0){
                    if(DrawingUtils.isPointInRegion(x + x1, y, 16, 16, mouseX, mouseY)){
                        ItemStack upgrade = new ItemStack(item, level);
                        //this$0.renderToolTip(upgrade, mouseX - this$0.guiLeft, mouseY - this$0.guiTop);
                    }
                    x1 += 16 + 2;
                    if(6 * 2 + x1 + 16 > 144){
                        x1 = 0;
                        y += 16 + 2;
                    }
                }
            }
        }
    }
}
