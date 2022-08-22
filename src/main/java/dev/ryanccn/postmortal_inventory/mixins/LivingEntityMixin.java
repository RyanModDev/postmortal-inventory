package dev.ryanccn.postmortal_inventory.mixins;

import dev.ryanccn.postmortal_inventory.PostmortalInventoryMod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @ModifyVariable(method = "tryUseTotem", ordinal = 0, at = @At(value = "LOAD", ordinal = 0))
    public ItemStack mixin(ItemStack orig) {
        PostmortalInventoryMod.LOGGER.info("mixin called");

        if (orig == null && ((Object) this) instanceof ServerPlayerEntity player) {
            PostmortalInventoryMod.LOGGER.info("checking for totems in inventory");

            Inventory inv = player.getInventory();

            for (int i = 0; i < inv.size(); i++) {
                ItemStack stk = inv.getStack(i);
                if (stk.equals(Items.TOTEM_OF_UNDYING)) {
                    return stk;
                }
            }

            PostmortalInventoryMod.LOGGER.info("not found");
        }

        return orig;
    }
}
