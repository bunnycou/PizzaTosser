package net.explosionfish.pizzatosser;

import net.explosionfish.pizzatosser.Block.PizzaBlock;
import net.explosionfish.pizzatosser.Entity.DoughballEntity;
import net.explosionfish.pizzatosser.Item.DoughballItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
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
					.maxCount(1))
	);

	public static final Item PIZZADOUGH_ITEM = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "pizzadough"),
			new Item(new FabricItemSettings())
	);

	// Ingredients
	// Fermented Milk + Cheese
	public static final Item FERMENTED_MILK_BLOB = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "fermented_milk_blob"),
			new Item(new FabricItemSettings()
					.food(new FoodComponent.Builder()
							.hunger(1)
							.statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 4*10, 5), 100f)
							.alwaysEdible()
							.build()
					)
			)
	);

	public static final Item CHEESE = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cheese"),
			new Item(new FabricItemSettings()
					.food(new FoodComponent.Builder()
							.hunger(1)
							.build()))
	);

	// Pizzas (cake + item + raw)
	// Cheese Pizza --------------------------------------------------
	public static final PizzaBlock CHEESE_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "cheese_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), 2)
	);

	public static final Item CHEESE_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cheese_pizza"),
			new BlockItem(CHEESE_CAKE, new FabricItemSettings())
	);

	public static final Item CHEESE_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cheese_pizza_raw"),
			new Item(new FabricItemSettings())
	);

	// Pepperoni Pizza --------------------------------------------------
	public static final PizzaBlock PEPPERONI_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "pepperoni_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), 3)
	);

	public static final Item PEPPERONI_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "pepperoni_pizza"),
			new BlockItem(PEPPERONI_CAKE, new FabricItemSettings())
	);

	public static final Item PEPPERONI_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "pepperoni_pizza_raw"),
			new Item(new FabricItemSettings())
	);

	// Cheese Lovers --------------------------------------------------
	public static final PizzaBlock CHEESELOVER_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "cheeselover_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), 4)
	);

	public static final Item CHEESELOVER_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cheeselover_pizza"),
			new BlockItem(CHEESELOVER_CAKE, new FabricItemSettings())
	);

	public static final Item CHEESELOVER_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cheeselover_pizza_raw"),
			new Item(new FabricItemSettings())
	);

	// Chicken Bacon Rabbit --------------------------------------------------
	public static final PizzaBlock CBR_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "cbr_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), 5)
	);

	public static final Item CBR_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cbr_pizza"),
			new BlockItem(CBR_CAKE, new FabricItemSettings())
	);

	public static final Item CBR_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cbr_pizza_raw"),
			new Item(new FabricItemSettings())
	);

	// Three Meat Treat --------------------------------------------------
	public static final PizzaBlock THREEMEAT_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "threemeat_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), 5)
	);

	public static final Item THREEMEAT_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "threemeat_pizza"),
			new BlockItem(THREEMEAT_CAKE, new FabricItemSettings())
	);

	public static final Item THREEMEAT_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "threemeat_pizza_raw"),
			new Item(new FabricItemSettings())
	);

	// Vegan Pizza --------------------------------------------------
	public static final PizzaBlock VEGAN_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "vegan_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), 1)
	);

	public static final Item VEGAN_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "vegan_pizza"),
			new BlockItem(VEGAN_CAKE, new FabricItemSettings())
	);

	public static final Item VEAGAN_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "vegan_pizza_raw"),
			new Item(new FabricItemSettings())
	);

	// Fisherman's Delight --------------------------------------------------
	public static final PizzaBlock FISH_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "fish_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), 3)
	);

	public static final Item FISH_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "fish_pizza"),
			new BlockItem(FISH_CAKE, new FabricItemSettings())
	);

	public static final Item FISH_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "fish_pizza_raw"),
			new Item(new FabricItemSettings())
	);

	// Land Air and Sea --------------------------------------------------
	public static final PizzaBlock LAS_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "las_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), 5)
	);

	public static final Item LAS_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "las_pizza"),
			new BlockItem(LAS_CAKE, new FabricItemSettings())
	);

	public static final Item LAS_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "las_pizza_raw"),
			new Item(new FabricItemSettings())
	);


	// tossed dough entity
	public static final EntityType<DoughballEntity> DOUGHBALL_ENTITY = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(ModID, "doughball_entity"),
			FabricEntityTypeBuilder.<DoughballEntity>create(SpawnGroup.MISC, DoughballEntity::new)
					.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
					.trackRangeBlocks(4).trackedUpdateRate(10)
					.build());

	// Item group for all of the items
	public static final ItemGroup PIZZA_GROUP = FabricItemGroupBuilder.create(
					new Identifier(ModID, "pizza_group"))
			.icon(() -> new ItemStack(PEPPERONI_PIZZA))
			.appendItems(stacks -> {
				// first row
				stacks.add(new ItemStack(DOUGHBALL_ITEM));
				stacks.add(new ItemStack(PIZZADOUGH_ITEM));
				stacks.add(new ItemStack(FERMENTED_MILK_BLOB)); //3
				stacks.add(new ItemStack(CHEESE));
				stacks.add(ItemStack.EMPTY);
				stacks.add(ItemStack.EMPTY); //6
				stacks.add(ItemStack.EMPTY);
				stacks.add(ItemStack.EMPTY);
				stacks.add(ItemStack.EMPTY); //9
				// second row
				stacks.add(new ItemStack(CHEESE_PIZZA_RAW));
				stacks.add(new ItemStack(CHEESE_PIZZA));
				stacks.add(new ItemStack(PEPPERONI_PIZZA_RAW));//3
				stacks.add(new ItemStack(PEPPERONI_PIZZA));
				stacks.add(new ItemStack(CHEESELOVER_PIZZA_RAW));
				stacks.add(new ItemStack(CHEESELOVER_PIZZA)); //6
				stacks.add(new ItemStack(THREEMEAT_PIZZA_RAW));
				stacks.add(new ItemStack(THREEMEAT_PIZZA));
				stacks.add(ItemStack.EMPTY); //9
				// third row
				stacks.add(new ItemStack(CBR_PIZZA_RAW));
				stacks.add(new ItemStack(CBR_PIZZA));
				stacks.add(new ItemStack(VEAGAN_PIZZA_RAW)); //3
				stacks.add(new ItemStack(VEGAN_PIZZA));
				stacks.add(new ItemStack(FISH_PIZZA_RAW));
				stacks.add(new ItemStack(FISH_PIZZA)); //6
				stacks.add(new ItemStack(LAS_PIZZA_RAW));
				stacks.add(new ItemStack(LAS_PIZZA));
				stacks.add(ItemStack.EMPTY); //9
			})
			.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Ready to toss pizzas!");
	}
}
