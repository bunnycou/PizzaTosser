package net.explosionfish.pizzatosser;

import net.explosionfish.pizzatosser.Generator.EnglishLangProvider;
import net.explosionfish.pizzatosser.Generator.RecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class Datagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(EnglishLangProvider::new);
        fabricDataGenerator.addProvider(RecipeGenerator::new);
    }
}

