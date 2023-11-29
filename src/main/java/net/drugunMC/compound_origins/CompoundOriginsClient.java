package net.drugunMC.compound_origins;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

import static net.drugunMC.compound_origins.CompoundOrigins.TEMPORARY_LEAVES;

public class CompoundOriginsClient implements ClientModInitializer {







	@Environment(EnvType.CLIENT)
	@Override
	public void onInitializeClient() {

		//LOGGER.info("Hello Fabric world!");




		EntityRendererRegistry.register(CompoundOrigins.AFFINITY_FIRE_PROJECTILE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(CompoundOrigins.AFFINITY_FIRE_PROJECTILE_LAVA, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(CompoundOrigins.AFFINITY_EARTH_PROJECTILE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(CompoundOrigins.AFFINITY_NATURE_PROJECTILE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(CompoundOrigins.AFFINITY_WATER_PROJECTILE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(CompoundOrigins.TELEPORT_PROJECTILE, FlyingItemEntityRenderer::new);


		BlockRenderLayerMap.INSTANCE.putBlock(TEMPORARY_LEAVES, RenderLayer.getCutout());


		ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
			return view != null && pos != null ? BiomeColors.getFoliageColor(view, pos) : FoliageColors.getDefaultColor();
		}, TEMPORARY_LEAVES);





	}





}
