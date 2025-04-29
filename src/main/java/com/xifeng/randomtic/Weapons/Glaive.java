package com.xifeng.randomtic.Weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.SwordCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.tools.TinkerTools;

import javax.annotation.Nonnull;
import java.util.List;

public class Glaive extends SwordCore {

    public Glaive() {
        super(PartMaterialType.handle(TinkerTools.toughToolRod),
                PartMaterialType.head(TinkerTools.swordBlade),
                PartMaterialType.extra(TinkerTools.wideGuard));

        this.addCategory(Category.WEAPON);

        setTranslationKey("glaive").setRegistryName("glaive");
    }

    @Override
    public float damagePotential() {
        return 2.0F;
    }

    @Override
    public double attackSpeed() {
        return 1.0D;
    }

    @Override
    public float damageCutoff() {
        return 25F;
    }

    @Override
    public float knockback() {
        return 3F;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand hand) {
        ItemStack itemStackIn = playerIn.getHeldItem(hand);
        return ActionResult.newResult(EnumActionResult.SUCCESS, itemStackIn);
    }

    @Override
    public boolean dealDamage(ItemStack stack, EntityLivingBase player, Entity entity, float damage) {
        if (player instanceof EntityPlayer) {
            double reach = player.getDistanceSq(entity);
            AxisAlignedBB sweepRange = entity.getEntityBoundingBox().grow(1.0,0.25,1.0);
            for (Entity entity1 : player.world.getEntitiesWithinAABB(Entity.class, sweepRange)) {
                if (!(entity1 instanceof EntityPlayer) && entity1 != entity && player.getDistanceSq(entity1) <= reach) {
                    entity1.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), damage);
                }
            }
            return super.dealDamage(stack, player, entity, damage);
        }
        return super.dealDamage(stack, player, entity, damage);
    }

    @Override
    public int[] getRepairParts() {
        return new int[]{1};
    }

    @Override
    protected ToolNBT buildTagData(List<Material> materials) {
        HandleMaterialStats handle = materials.get(0).getStatsOrUnknown(MaterialTypes.HANDLE);
        HeadMaterialStats head = materials.get(1).getStatsOrUnknown(MaterialTypes.HEAD);
        ExtraMaterialStats binding = materials.get(2).getStatsOrUnknown(MaterialTypes.EXTRA);

        ToolNBT data = new ToolNBT();
        data.head(head);
        data.extra(binding);
        data.handle(handle);

        data.attack += 4;
        data.durability *= 1.6;
        return data;
    }
}
