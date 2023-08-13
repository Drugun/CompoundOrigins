package net.drugunMC.compound_origins.mixin;

import dev.cammiescorner.icarus.core.network.c2s.DeleteHungerMessage;
import dev.cammiescorner.icarus.core.util.IcarusHelper;
import dev.cammiescorner.icarus.core.util.SlowFallEntity;
import net.drugunMC.compound_origins.CompoundOrigins;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(DeleteHungerMessage.class)
public abstract class IcarusMixinHunger {


    public IcarusMixinHunger() {

    }

    /*

    @Override
    private static void handle(PlayerEntity player, ItemStack stack) {
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
