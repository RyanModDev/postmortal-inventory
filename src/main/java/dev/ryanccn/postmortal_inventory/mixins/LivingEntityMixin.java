package dev.ryanccn.postmortal_inventory.mixins;

import dev.ryanccn.postmortal_inventory.PostmortalInventoryMod;

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

    @ModifyVariable(method = "tryUseTotem", index = 2, at = @At(value = "LOAD", ordinal = 0))
    public ItemStack inventoryTotemMixin(ItemStack orig) {

        if (orig == null && ((Object) this) instanceof ServerPlayerEntity player) {
            Inventory inv = player.getInventory();

            for (int i = 0; i < inv.size(); i++) {
                ItemStack stk = inv.getStack(i);

                if (stk.isOf(Items.TOTEM_OF_UNDYING)) {
                    ItemStack stk2 = stk.copy();
                    stk.decrement(1);
                    PostmortalInventoryMod.LOGGER.info("Inventory totem used");
                    return stk2;
                }
            }
        }

        return orig;
    }
}
