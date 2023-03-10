package net.drugunMC.compound_origins;

import com.google.gson.*;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.Objects;

public final class CompOriginsJsonCondImpl {

    public static ConditionJsonProvider config(Identifier id, String key) {

        return new ConditionJsonProvider() {
            @Override
            public Identifier getConditionId() {
                return id;
            }

            @Override
            public void writeParameters(JsonObject object) {
                JsonPrimitive o = new JsonPrimitive(key);

                object.add("value", o);
            }
        };
    }

    public static boolean readConfig(JsonObject object) {
        String key = JsonHelper.getString(object, "value");

        if(Objects.equals(key, "addOrbRecipe")){
            return CompoundOrigins.CONFIG.addOrbRecipe();
        }

        return false;
    }

}
