package net.explosionfish.pizzatosser;

import net.explosionfish.pizzatosser.Block.PizzaBlock;
import net.explosionfish.pizzatosser.Entity.DoughballEntity;
import net.explosionfish.pizzatosser.Item.DoughballItem;
import net.explosionfish.pizzatosser.Item.PizzaBlockItem;
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
					.maxCount(1),
					1)
	);

	// steps of doughball

	public static final DoughballItem DOUGHBALL_ITEM2 = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "doughball2"),
			new DoughballItem(new FabricItemSettings()
					.maxCount(1),
					2)
	);

	public static final DoughballItem DOUGHBALL_ITEM3 = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "doughball3"),
			new DoughballItem(new FabricItemSettings()
					.maxCount(1),
					3)
	);

	public static final Item PIZZADOUGH_ITEM = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "pizzadough"),
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

	// Ingredients
	// Fermented Milk + Cheese
	public static final Item FERMENTED_MILK_BLOB = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "fermented_milk_blob"),
			new Item(new FabricItemSettings()
					.food(new FoodComponent.Builder()
							.hunger(0)
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
							.snack()
							.build()))
	);

	// Hunger values for each pizza/calzone (pizza gives hunger*6 while calzones only give hunger*4 but are portable)
	// hunger put in based on cooked ingredients
	public static final Integer cheese_hunger = 2; // 2 hunger put in
	public static final Integer pepperoni_hunger = 3; // 10 hunger put in
	public static final Integer cheeselover_hunger = 3; // 4 hunger put in
	public static final Integer cbr_hunger  = 6; // 21 hunger put in
	public static final Integer threemeat_hunger = 6; // 24 hunger put in
	public static final Integer vegan_hunger = 1; // 1 hunger put in
	public static final Integer fish_hunger = 5; // 20 hunger put in
	public static final Integer las_hunger = 6; // 22 hunger put in

	// current values mean that cheese and cheeselover is the most hunger efficient (12:1 and 6:1) but only provide 12-18 hunger compared to the average 30-36 hunger
	// pepperoni and cheeselover being equal might be balanced by the fact cheese takes a lot of effort to make?
	// cbr is the least hunger efficinet (3:2 or 1:1 for calzone)

	// Pizzas (cake + item + raw)
	// Cheese Pizza --------------------------------------------------
	public static final PizzaBlock CHEESE_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "cheese_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), cheese_hunger)
	);

	public static final Item CHEESE_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cheese_pizza"),
			new PizzaBlockItem(CHEESE_CAKE, new FabricItemSettings().maxCount(1), cheese_hunger)
	);

	public static final Item CHEESE_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cheese_pizza_raw"),
			new Item(new FabricItemSettings().maxCount(1))
	);

	// Pepperoni Pizza --------------------------------------------------
	public static final PizzaBlock PEPPERONI_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "pepperoni_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), pepperoni_hunger)
	);

	public static final Item PEPPERONI_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "pepperoni_pizza"),
			new PizzaBlockItem(PEPPERONI_CAKE, new FabricItemSettings().maxCount(1), pepperoni_hunger)
	);

	public static final Item PEPPERONI_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "pepperoni_pizza_raw"),
			new Item(new FabricItemSettings().maxCount(1))
	);

	// Cheese Lovers --------------------------------------------------
	public static final PizzaBlock CHEESELOVER_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "cheeselover_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), cheeselover_hunger)
	);

	public static final Item CHEESELOVER_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cheeselover_pizza"),
			new PizzaBlockItem(CHEESELOVER_CAKE, new FabricItemSettings().maxCount(1), cheeselover_hunger)
	);

	public static final Item CHEESELOVER_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cheeselover_pizza_raw"),
			new Item(new FabricItemSettings().maxCount(1))
	);

	// Chicken Bacon Rabbit --------------------------------------------------
	public static final PizzaBlock CBR_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "cbr_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), cbr_hunger)
	);

	public static final Item CBR_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cbr_pizza"),
			new PizzaBlockItem(CBR_CAKE, new FabricItemSettings().maxCount(1), cbr_hunger)
	);

	public static final Item CBR_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cbr_pizza_raw"),
			new Item(new FabricItemSettings().maxCount(1))
	);

	// Three Meat Treat --------------------------------------------------
	public static final PizzaBlock THREEMEAT_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "threemeat_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), threemeat_hunger)
	);

	public static final Item THREEMEAT_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "threemeat_pizza"),
			new PizzaBlockItem(THREEMEAT_CAKE, new FabricItemSettings().maxCount(1), threemeat_hunger)
	);

	public static final Item THREEMEAT_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "threemeat_pizza_raw"),
			new Item(new FabricItemSettings().maxCount(1))
	);

	// Vegan Pizza --------------------------------------------------
	public static final PizzaBlock VEGAN_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "vegan_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), vegan_hunger)
	);

	public static final Item VEGAN_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "vegan_pizza"),
			new PizzaBlockItem(VEGAN_CAKE, new FabricItemSettings().maxCount(1), vegan_hunger)
	);

	public static final Item VEGAN_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "vegan_pizza_raw"),
			new Item(new FabricItemSettings().maxCount(1))
	);

	// Fisherman's Delight --------------------------------------------------
	public static final PizzaBlock FISH_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "fish_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), fish_hunger)
	);

	public static final Item FISH_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "fish_pizza"),
			new PizzaBlockItem(FISH_CAKE, new FabricItemSettings().maxCount(1), fish_hunger)
	);

	public static final Item FISH_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "fish_pizza_raw"),
			new Item(new FabricItemSettings().maxCount(1))
	);

	// Land Air and Sea --------------------------------------------------
	public static final PizzaBlock LAS_CAKE = Registry.register(
			Registry.BLOCK,
			new Identifier(ModID, "las_cake"),
			new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE), las_hunger)
	);

	public static final Item LAS_PIZZA = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "las_pizza"),
			new PizzaBlockItem(LAS_CAKE, new FabricItemSettings().maxCount(1), las_hunger)
	);

	public static final Item LAS_PIZZA_RAW = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "las_pizza_raw"),
			new Item(new FabricItemSettings().maxCount(1))
	);

	// Calzones! Pizza snacks :)
	public static final Item CHEESE_CALZONE = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cheese_calzone"),
			new Item(new FabricItemSettings().food(new FoodComponent.Builder()
					.hunger(cheese_hunger)
					.build()))
	);

	public static final Item PEPPERONI_CALZONE = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "pepperoni_calzone"),
			new Item(new FabricItemSettings().food(new FoodComponent.Builder()
					.hunger(pepperoni_hunger)
					.build()))
	);

	public static final Item CHEESELOVER_CALZONE = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cheeselover_calzone"),
			new Item(new FabricItemSettings().food(new FoodComponent.Builder()
					.hunger(cheeselover_hunger)
					.build()))
	);

	public static final Item THREEMEAT_CALZONE = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "threemeat_calzone"),
			new Item(new FabricItemSettings().food(new FoodComponent.Builder()
					.hunger(threemeat_hunger)
					.build()))
	);

	public static final Item CBR_CALZONE = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "cbr_calzone"),
			new Item(new FabricItemSettings().food(new FoodComponent.Builder()
					.hunger(cbr_hunger)
					.build()))
	);

	public static final Item VEGAN_CALZONE = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "vegan_calzone"),
			new Item(new FabricItemSettings().food(new FoodComponent.Builder()
					.hunger(vegan_hunger)
					.build()))
	);

	public static final Item FISH_CALZONE = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "fish_calzone"),
			new Item(new FabricItemSettings().food(new FoodComponent.Builder()
					.hunger(fish_hunger)
					.build()))
	);

	public static final Item LAS_CALZONE = Registry.register(
			Registry.ITEM,
			new Identifier(ModID, "las_calzone"),
			new Item(new FabricItemSettings().food(new FoodComponent.Builder()
					.hunger(las_hunger)
					.build()))
	);

	// Item group for all the items
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
				stacks.add(new ItemStack(PEPPERONI_PIZZA_RAW));
				stacks.add(new ItemStack(CHEESELOVER_PIZZA_RAW)); //3
				stacks.add(new ItemStack(THREEMEAT_PIZZA_RAW));
				stacks.add(new ItemStack(CBR_PIZZA_RAW));
				stacks.add(new ItemStack(VEGAN_PIZZA_RAW)); //6
				stacks.add(new ItemStack(FISH_PIZZA_RAW));
				stacks.add(new ItemStack(LAS_PIZZA_RAW));
				stacks.add(ItemStack.EMPTY); //9
				// third row
				stacks.add(new ItemStack(CHEESE_PIZZA));
				stacks.add(new ItemStack(PEPPERONI_PIZZA));
				stacks.add(new ItemStack(CHEESELOVER_PIZZA)); //3
				stacks.add(new ItemStack(THREEMEAT_PIZZA));
				stacks.add(new ItemStack(CBR_PIZZA));
				stacks.add(new ItemStack(VEGAN_PIZZA)); //6
				stacks.add(new ItemStack(FISH_PIZZA));
				stacks.add(new ItemStack(LAS_PIZZA));
				stacks.add(ItemStack.EMPTY); //9
				// fourth row
				stacks.add(new ItemStack(CHEESE_CALZONE));
				stacks.add(new ItemStack(PEPPERONI_CALZONE));
				stacks.add(new ItemStack(CHEESELOVER_CALZONE)); //3
				stacks.add(new ItemStack(THREEMEAT_CALZONE));
				stacks.add(new ItemStack(CBR_CALZONE));
				stacks.add(new ItemStack(VEGAN_CALZONE)); //6
				stacks.add(new ItemStack(FISH_CALZONE));
				stacks.add(new ItemStack(LAS_CALZONE));
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
