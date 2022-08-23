package dev.ryanccn.postmortal_inventory;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostmortalInventoryMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Postmortal Inventory");

    @Override
    public void onInitialize(ModContainer mod) {
        LOGGER.info("Voila");
    }
}
