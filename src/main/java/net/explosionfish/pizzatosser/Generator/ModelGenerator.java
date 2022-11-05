package net.explosionfish.pizzatosser.Generator;

import net.explosionfish.pizzatosser.PizzaTosser;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        // items
        itemModelGenerator.register(PizzaTosser.DOUGHBALL_ITEM, Models.GENERATED);
        itemModelGenerator.register(PizzaTosser.PIZZADOUGH_ITEM, Models.GENERATED);
        itemModelGenerator.register(PizzaTosser.FERMENTED_MILK_BLOB, Models.GENERATED);
        itemModelGenerator.register(PizzaTosser.CHEESE, Models.GENERATED);

        //pizzas raw


        //pizzas


        //calzones
    }
}
