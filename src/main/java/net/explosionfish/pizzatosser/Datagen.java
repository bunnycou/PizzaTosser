package net.explosionfish.pizzatosser;

import net.explosionfish.pizzatosser.Generator.EnglishLangProvider;
import net.explosionfish.pizzatosser.Generator.ModelGenerator;
import net.explosionfish.pizzatosser.Generator.RecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class Datagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(EnglishLangProvider::new);
        // haven't gotten either of these to work
        //fabricDataGenerator.addProvider(RecipeGenerator::new);
        //fabricDataGenerator.addProvider(ModelGenerator::new);
    }
}

