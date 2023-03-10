package net.drugunMC.compound_origins;

import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.util.Identifier;

public final class CompOriginsJsonCondProvider {

    public static final Identifier READCONFIG = new Identifier(CompoundOrigins.ModID + ":readconfig");

    public static ConditionJsonProvider readconfig(String key) {
        return CompOriginsJsonCondImpl.config(READCONFIG, key);
    }

    static {

        ResourceConditions.register(READCONFIG, CompOriginsJsonCondImpl::readConfig);

    }

    static void init() {
    }

    public CompOriginsJsonCondProvider() {
    }









}



