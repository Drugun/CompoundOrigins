package net.drugunMC.compound_origins;

import net.drugunMC.compound_origins.entity.projectile.EarthAffinityProjectile;
import net.drugunMC.compound_origins.entity.projectile.FireAffinityProjectile;
import net.drugunMC.compound_origins.entity.projectile.NatureAffinityProjectile;
import net.drugunMC.compound_origins.entity.projectile.WaterAffinityProjectile;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompoundOrigins implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	public static final String ModID = "compound_origins";


	public static final EntityType<FireAffinityProjectile> AFFINITY_FIRE_PROJECTILE = FabricEntityTypeBuilder.<FireAffinityProjectile>create(SpawnGroup.MISC, FireAffinityProjectile::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
	public static final EntityType<EarthAffinityProjectile> AFFINITY_EARTH_PROJECTILE = FabricEntityTypeBuilder.<EarthAffinityProjectile>create(SpawnGroup.MISC, EarthAffinityProjectile::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
	public static final EntityType<NatureAffinityProjectile> AFFINITY_NATURE_PROJECTILE = FabricEntityTypeBuilder.<NatureAffinityProjectile>create(SpawnGroup.MISC, NatureAffinityProjectile::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
	public static final EntityType<WaterAffinityProjectile> AFFINITY_WATER_PROJECTILE = FabricEntityTypeBuilder.<WaterAffinityProjectile>create(SpawnGroup.MISC, WaterAffinityProjectile::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();






	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		//LOGGER.info("Hello Fabric world!");


		Registry.register(Registry.ENTITY_TYPE, new Identifier(CompoundOrigins.ModID, "affinity_fire_projectile"), AFFINITY_FIRE_PROJECTILE);
		Registry.register(Registry.ENTITY_TYPE, new Identifier(CompoundOrigins.ModID, "affinity_earth_projectile"), AFFINITY_EARTH_PROJECTILE);
		Registry.register(Registry.ENTITY_TYPE, new Identifier(CompoundOrigins.ModID, "affinity_nature_projectile"), AFFINITY_NATURE_PROJECTILE);
		Registry.register(Registry.ENTITY_TYPE, new Identifier(CompoundOrigins.ModID, "affinity_water_projectile"), AFFINITY_WATER_PROJECTILE);

		EntityRendererRegistry.register(CompoundOrigins.AFFINITY_FIRE_PROJECTILE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(CompoundOrigins.AFFINITY_EARTH_PROJECTILE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(CompoundOrigins.AFFINITY_NATURE_PROJECTILE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(CompoundOrigins.AFFINITY_WATER_PROJECTILE, FlyingItemEntityRenderer::new);







	}
}
