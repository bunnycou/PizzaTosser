package net.explosionfish.pizzatosser.Generator;

import net.explosionfish.pizzatosser.PizzaTosser;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class EnglishLangProvider extends FabricLanguageProvider {
    public EnglishLangProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(PizzaTosser.DOUGHBALL_ITEM, "Dough Ball");
        translationBuilder.add(PizzaTosser.PIZZADOUGH_ITEM, "Pizza Dough");
        translationBuilder.add(PizzaTosser.FERMENTED_MILK_BLOB, "Fermented Milk Blob");
        translationBuilder.add(PizzaTosser.CHEESE, "Cheese");
        translationBuilder.add(PizzaTosser.CHEESE_PIZZA_RAW, "Uncooked Cheese Pizza");
        translationBuilder.add(PizzaTosser.CHEESE_PIZZA, "Cheese Pizza");
        translationBuilder.add(PizzaTosser.PEPPERONI_PIZZA_RAW, "Uncooked Pepperoni Pizza");
        translationBuilder.add(PizzaTosser.PEPPERONI_PIZZA, "Pepperoni Pizza");
        translationBuilder.add(PizzaTosser.CHEESELOVER_PIZZA_RAW, "Uncooked Cheese Lover's Pizza");
        translationBuilder.add(PizzaTosser.CHEESELOVER_PIZZA, "Cheese Lover's Pizza");
        translationBuilder.add(PizzaTosser.THREEMEAT_PIZZA_RAW, "Uncooked Three Meat Pizza");
        translationBuilder.add(PizzaTosser.THREEMEAT_PIZZA, "Three Meat Pizza");
        translationBuilder.add(PizzaTosser.CBR_PIZZA_RAW, "Uncooked Chicken Bacon Rabbit Pizza");
        translationBuilder.add(PizzaTosser.CBR_PIZZA, "Chicken Bacon Rabbit Pizza");
        translationBuilder.add(PizzaTosser.VEAGAN_PIZZA_RAW, "Uncooked Vegan pizza");
        translationBuilder.add(PizzaTosser.VEGAN_PIZZA, "Vegan Pizza");
        translationBuilder.add(PizzaTosser.FISH_PIZZA_RAW, "Uncooked Fisherman's Pizza");
        translationBuilder.add(PizzaTosser.FISH_PIZZA, "Fisherman's Pizza");
        translationBuilder.add(PizzaTosser.LAS_PIZZA_RAW, "Uncooked Land, Air, and Sea Pizza");
        translationBuilder.add(PizzaTosser.LAS_PIZZA, "Land, Air, and Sea Pizza");
        translationBuilder.add(PizzaTosser.PIZZA_GROUP, "PizzaTosser");
    }
}
