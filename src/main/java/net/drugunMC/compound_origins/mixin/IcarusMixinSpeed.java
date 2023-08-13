package net.drugunMC.compound_origins.mixin;

import dev.cammiescorner.icarus.common.items.WingItem;
import dev.cammiescorner.icarus.core.integration.IcarusConfig;
import dev.cammiescorner.icarus.core.network.c2s.DeleteHungerMessage;
import dev.cammiescorner.icarus.core.util.IcarusHelper;
import dev.cammiescorner.icarus.core.util.SlowFallEntity;
import dev.emi.trinkets.api.TrinketItem;
import net.drugunMC.compound_origins.CompoundOrigins;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(WingItem.class)
public abstract class IcarusMixinSpeed extends TrinketItem {


    public IcarusMixinSpeed(DyeColor primaryColour, DyeColor secondaryColour, WingItem.WingType wingType) {
        super((new Item.Settings()).maxCount(1).maxDamage(IcarusConfig.wingsDurability).rarity(wingType == WingItem.WingType.UNIQUE ? Rarity.EPIC : Rarity.RARE));
    }

    /*

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Ldev/cammiescorner/icarus/core/util/IcarusHelper;applySpeed(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)V"))
    private void injected1(PlayerEntity player, ItemStack stack) {
        if(CompoundOrigins.CONFIG.overrideIcarusConfig()){
            ((SlowFallEntity)player).setSlowFalling(false);
            Vec3d rotation = player.getRotationVector();
            Vec3d velocity = player.getVelocity();
            float modifier = 1.0F;
            float speed = 0.0065F * (player.getPitch() < -75.0F && player.getPitch() > -105.0F ? 2.75F : 1.0F) / modifier;
            player.setVelocity(velocity.add(rotation.x * (double)speed + (rotation.x * 1.5 - velocity.x) * (double)speed, rotation.y * (double)speed + (rotation.y * 1.5 - velocity.y) * (double)speed, rotation.z * (double)speed + (rotation.z * 1.5 - velocity.z) * (double)speed));
            if (!player.isCreative()) {
                DeleteHungerMessage.send();
            }
        }
        else{
            IcarusHelper.applySpeed(player, stack);
        }

    }

    */












}
