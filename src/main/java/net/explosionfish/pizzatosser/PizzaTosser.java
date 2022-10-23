package net.explosionfish.pizzatosser;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.Material;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
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

	public static final CakeBlock PIZZA_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "pizza_cake"),
			new CakeBlock(FabricBlockSettings.copyOf(Blocks.CAKE))
	);

	public static final Item PIZZA_ITEM = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "pizza"),
			new BlockItem(PIZZA_CAKE, new FabricItemSettings()
					.group(ItemGroup.FOOD)
					.food(new FoodComponent.Builder()
							.hunger(4)
							.build()))
	);

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
