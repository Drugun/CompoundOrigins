package net.drugunMC.compound_origins;

import net.drugunMC.compound_origins.block.TemporaryBlock;
import net.drugunMC.compound_origins.block.TemporaryLavaBlock;
import net.drugunMC.compound_origins.block.TemporaryLeavesBlock;
import net.drugunMC.compound_origins.block.TemporaryRootsBlock;
import net.drugunMC.compound_origins.entity.projectile.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompoundOrigins implements ModInitializer {

	public static final String ModID = "compound_origins";
	public static final Logger LOGGER = LoggerFactory.getLogger(ModID);

	public static final CompoundOriginsConfig CONFIG = CompoundOriginsConfig.createAndLoad();


	public static final TemporaryBlock TEMPORARY_COBBLESTONE = new TemporaryBlock(FabricBlockSettings.of(Material.STONE).hardness(4.0F).resistance(6.0F).dropsNothing(), 300, 60, 4);
	public static final TemporaryBlock TEMPORARY_ICE = new TemporaryBlock(FabricBlockSettings.of(Material.STONE).strength(0.8F).dropsNothing().slipperiness(0.98F).sounds(BlockSoundGroup.GLASS), 300, 60, 4);
	public static final TemporaryLavaBlock TEMPORARY_LAVA = new TemporaryLavaBlock(FabricBlockSettings.of(Material.STONE).hardness(300.0F).resistance(6.0F).dropsNothing().sounds(BlockSoundGroup.HONEY).luminance((state) -> {return 15;}).solidBlock(CompoundOrigins::never).blockVision(CompoundOrigins::never).suffocates(CompoundOrigins::never), 300, 60, 4, 2);
	public static final TemporaryLeavesBlock TEMPORARY_LEAVES = new TemporaryLeavesBlock(FabricBlockSettings.of(Material.STONE).hardness(0.3F).resistance(3.0F).dropsNothing().sounds(BlockSoundGroup.GRASS).nonOpaque().solidBlock(CompoundOrigins::never).blockVision(CompoundOrigins::never).suffocates(CompoundOrigins::never), 300, 60, 4);
	public static final TemporaryRootsBlock TEMPORARY_ROOTS = new TemporaryRootsBlock(FabricBlockSettings.of(Material.STONE).strength(0.9F).dropsNothing().sounds(BlockSoundGroup.MANGROVE_ROOTS).nonOpaque().solidBlock(CompoundOrigins::never).blockVision(CompoundOrigins::never).suffocates(CompoundOrigins::never), 300, 60, 4);

	public static final EntityType<FireAffinityProjectile> AFFINITY_FIRE_PROJECTILE = FabricEntityTypeBuilder.<FireAffinityProjectile>create(SpawnGroup.MISC, FireAffinityProjectile::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeChunks(10).trackedUpdateRate(3).forceTrackedVelocityUpdates(true).build();
	public static final EntityType<FireAffinityProjectileLava> AFFINITY_FIRE_PROJECTILE_LAVA = FabricEntityTypeBuilder.<FireAffinityProjectileLava>create(SpawnGroup.MISC, FireAffinityProjectileLava::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeChunks(10).trackedUpdateRate(3).forceTrackedVelocityUpdates(true).build();
	public static final EntityType<EarthAffinityProjectile> AFFINITY_EARTH_PROJECTILE = FabricEntityTypeBuilder.<EarthAffinityProjectile>create(SpawnGroup.MISC, EarthAffinityProjectile::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeChunks(10).trackedUpdateRate(3).forceTrackedVelocityUpdates(true).build();
	public static final EntityType<NatureAffinityProjectile> AFFINITY_NATURE_PROJECTILE = FabricEntityTypeBuilder.<NatureAffinityProjectile>create(SpawnGroup.MISC, NatureAffinityProjectile::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeChunks(10).trackedUpdateRate(3).forceTrackedVelocityUpdates(true).build();
	public static final EntityType<WaterAffinityProjectile> AFFINITY_WATER_PROJECTILE = FabricEntityTypeBuilder.<WaterAffinityProjectile>create(SpawnGroup.MISC, WaterAffinityProjectile::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeChunks(10).trackedUpdateRate(3).forceTrackedVelocityUpdates(true).build();
	public static final EntityType<WaterAffinityProjectileWall> AFFINITY_WATER_PROJECTILE_WALL = FabricEntityTypeBuilder.<WaterAffinityProjectileWall>create(SpawnGroup.MISC, WaterAffinityProjectileWall::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeChunks(10).trackedUpdateRate(3).forceTrackedVelocityUpdates(true).build();
	public static final EntityType<EarthAffinityProjectileWall> AFFINITY_EARTH_PROJECTILE_WALL = FabricEntityTypeBuilder.<EarthAffinityProjectileWall>create(SpawnGroup.MISC, EarthAffinityProjectileWall::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeChunks(10).trackedUpdateRate(3).forceTrackedVelocityUpdates(true).build();
	public static final EntityType<NatureAffinityProjectileWall> AFFINITY_NATURE_PROJECTILE_WALL = FabricEntityTypeBuilder.<NatureAffinityProjectileWall>create(SpawnGroup.MISC, NatureAffinityProjectileWall::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeChunks(10).trackedUpdateRate(3).forceTrackedVelocityUpdates(true).build();
	public static final EntityType<TeleportProjectile> TELEPORT_PROJECTILE = FabricEntityTypeBuilder.<TeleportProjectile>create(SpawnGroup.MISC, TeleportProjectile::new).dimensions(EntityDimensions.fixed(0.05f, 0.05f)).trackRangeChunks(10).trackedUpdateRate(3).forceTrackedVelocityUpdates(true).build();



	public static final TagKey<Block> TEMPORARY_BLOCKS = TagKey.of(Registry.BLOCK_KEY, new Identifier(CompoundOrigins.ModID, "temporary_blocks"));



	static {
		CompOriginsJsonCondProvider.init();
	}



	@Override
	public void onInitialize() {

		//LOGGER.info("Hello Fabric world!");

		Registry.register(Registry.BLOCK, new Identifier(CompoundOrigins.ModID, "temporary_cobblestone"), TEMPORARY_COBBLESTONE);
		Registry.register(Registry.ITEM, new Identifier(CompoundOrigins.ModID, "temporary_cobblestone"), new BlockItem(TEMPORARY_COBBLESTONE, new FabricItemSettings()));
		Registry.register(Registry.BLOCK, new Identifier(CompoundOrigins.ModID, "temporary_ice"), TEMPORARY_ICE);
		Registry.register(Registry.ITEM, new Identifier(CompoundOrigins.ModID, "temporary_ice"), new BlockItem(TEMPORARY_ICE, new FabricItemSettings()));
		Registry.register(Registry.BLOCK, new Identifier(CompoundOrigins.ModID, "temporary_lava"), TEMPORARY_LAVA);
		Registry.register(Registry.ITEM, new Identifier(CompoundOrigins.ModID, "temporary_lava"), new BlockItem(TEMPORARY_LAVA, new FabricItemSettings()));
		Registry.register(Registry.BLOCK, new Identifier(CompoundOrigins.ModID, "temporary_leaves"), TEMPORARY_LEAVES);
		Registry.register(Registry.ITEM, new Identifier(CompoundOrigins.ModID, "temporary_leaves"), new BlockItem(TEMPORARY_LEAVES, new FabricItemSettings()));
		Registry.register(Registry.BLOCK, new Identifier(CompoundOrigins.ModID, "temporary_roots"), TEMPORARY_ROOTS);
		Registry.register(Registry.ITEM, new Identifier(CompoundOrigins.ModID, "temporary_roots"), new BlockItem(TEMPORARY_ROOTS, new FabricItemSettings()));

		Registry.register(Registry.ENTITY_TYPE, new Identifier(CompoundOrigins.ModID, "affinity_fire_projectile"), AFFINITY_FIRE_PROJECTILE);
		Registry.register(Registry.ENTITY_TYPE, new Identifier(CompoundOrigins.ModID, "affinity_fire_projectile_lava"), AFFINITY_FIRE_PROJECTILE_LAVA);
		Registry.register(Registry.ENTITY_TYPE, new Identifier(CompoundOrigins.ModID, "affinity_earth_projectile"), AFFINITY_EARTH_PROJECTILE);
		Registry.register(Registry.ENTITY_TYPE, new Identifier(CompoundOrigins.ModID, "affinity_nature_projectile"), AFFINITY_NATURE_PROJECTILE);
		Registry.register(Registry.ENTITY_TYPE, new Identifier(CompoundOrigins.ModID, "affinity_water_projectile"), AFFINITY_WATER_PROJECTILE);
		Registry.register(Registry.ENTITY_TYPE, new Identifier(CompoundOrigins.ModID, "affinity_water_projectile_wall"), AFFINITY_WATER_PROJECTILE_WALL);
		Registry.register(Registry.ENTITY_TYPE, new Identifier(CompoundOrigins.ModID, "affinity_earth_projectile_wall"), AFFINITY_EARTH_PROJECTILE_WALL);
		Registry.register(Registry.ENTITY_TYPE, new Identifier(CompoundOrigins.ModID, "affinity_nature_projectile_wall"), AFFINITY_NATURE_PROJECTILE_WALL);
		Registry.register(Registry.ENTITY_TYPE, new Identifier(CompoundOrigins.ModID, "teleport_projectile"), TELEPORT_PROJECTILE);









	}


	private static boolean never(BlockState state, BlockView world, BlockPos pos) {
		return false;
	}


}
