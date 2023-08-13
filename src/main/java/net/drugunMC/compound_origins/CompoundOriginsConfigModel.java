package net.drugunMC.compound_origins;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RestartRequired;



@Config(name = "compound-origins-config", wrapperName = "CompoundOriginsConfig")
@Modmenu(modId = CompoundOrigins.ModID)
public class CompoundOriginsConfigModel {

    @RestartRequired
    public boolean addOrbRecipe = false;

    //@RestartRequired
    //public boolean overrideIcarusConfig = true;



}


