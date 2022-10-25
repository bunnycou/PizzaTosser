package net.explosionfish.pizzatosser;

import net.explosionfish.pizzatosser.Block.PizzaBlock;
import net.explosionfish.pizzatosser.Entity.DoughballEntity;
import net.explosionfish.pizzatosser.Item.DoughballItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PizzaTosser implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String ModID = "pizzatoss";
	public static final Logger LOGGER = LoggerFactory.getLogger(ModID);


	// basic dough items
	public static final DoughballItem DOUGHBALL_ITEM = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "doughball"),
			new DoughballItem(new FabricItemSettings()
					.maxCount(1)
					.group(ItemGroup.FOOD))
	);

	public static final Item PIZZADOUGH_ITEM = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "pizzadough"),
			new Item(new FabricItemSettings().group(ItemGroup.FOOD))
	);

	// Pizzas (cake + item)
	// Cheese Pizza
	public static final PizzaBlock CHEESE_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "cheese_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), 2)
	);

	public static final Item CHEESE_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cheese_pizza"),
			new BlockItem(CHEESE_CAKE, new FabricItemSettings()
					.group(ItemGroup.FOOD))
	);

	// Pepperoni Pizza
	public static final PizzaBlock PEPPERONI_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "pepperoni_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), 3)
	);

	public static final Item PEPPERONI_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "pepperoni_pizza"),
			new BlockItem(PEPPERONI_CAKE, new FabricItemSettings()
					.group(ItemGroup.FOOD))
	);

	// tossed dough entity
	public static final EntityType<DoughballEntity> DOUGHBALL_ENTITY = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(ModID, "doughball_entity"),
			FabricEntityTypeBuilder.<DoughballEntity>create(SpawnGroup.MISC, DoughballEntity::new)
					.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
					.trackRangeBlocks(4).trackedUpdateRate(10)
					.build());

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Ready to toss pizzas!");
	}
}
