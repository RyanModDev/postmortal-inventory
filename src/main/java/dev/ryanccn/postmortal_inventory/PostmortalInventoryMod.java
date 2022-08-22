package dev.ryanccn.postmortal_inventory;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostmortalInventoryMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod name as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("Postmortal Inventory");

    @Override
    public void onInitialize(ModContainer mod) {
        LOGGER.info("Voila");

        ServerPlayerEvents.ALLOW_DEATH.register((ServerPlayerEntity player, DamageSource damageSource, float damageAmount) -> {
            return Handler.allowPlayerDeath(player);
        });
    }
}
