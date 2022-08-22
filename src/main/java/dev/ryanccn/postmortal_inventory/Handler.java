package dev.ryanccn.postmortal_inventory;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;

public class Handler {
    public static boolean allowPlayerDeath(ServerPlayerEntity player) {
        if (player.getStackInHand(Hand.MAIN_HAND).getItem().equals(Items.TOTEM_OF_UNDYING)) {
            return true;
        }

        if (player.getStackInHand(Hand.OFF_HAND).getItem().equals(Items.TOTEM_OF_UNDYING)) {
            return true;
        }

        Inventory inv = player.getInventory();
        if (inv == null) {
            return true;
        }

        ItemStack stackOfTotems = null;
        for (int i = 0; i < inv.size(); i++) {
            ItemStack curIS = inv.getStack(i);
            if (curIS.getItem().equals(Items.TOTEM_OF_UNDYING)) {
                stackOfTotems = curIS;
                break;
            }
        }

        if (stackOfTotems == null) {
            return true;
        }

        player.increaseStat(Stats.USED.getOrCreateStat(Items.TOTEM_OF_UNDYING), 1);
        Criteria.USED_TOTEM.trigger(player, stackOfTotems);

        player.setHealth(1.0F);
        player.clearStatusEffects();
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 1));

        player.getWorld().sendEntityStatus(player, (byte) 35);

        stackOfTotems.decrement(1);

        return false;
    }
}
