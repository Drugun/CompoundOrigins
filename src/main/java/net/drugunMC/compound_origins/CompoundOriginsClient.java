package net.drugunMC.compound_origins;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class CompoundOriginsClient implements ClientModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String ModID = "compound_origins";







	@Environment(EnvType.CLIENT)
	@Override
	public void onInitializeClient() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		//LOGGER.info("Hello Fabric world!");




		EntityRendererRegistry.register(CompoundOrigins.AFFINITY_FIRE_PROJECTILE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(CompoundOrigins.AFFINITY_EARTH_PROJECTILE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(CompoundOrigins.AFFINITY_NATURE_PROJECTILE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(CompoundOrigins.AFFINITY_WATER_PROJECTILE, FlyingItemEntityRenderer::new);







	}





}
