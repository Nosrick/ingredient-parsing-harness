package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.block.CuttingBoardBlock;
import net.fabricmc.example.block.entity.CuttingBoardBlockEntity;
import net.fabricmc.example.recipe.CuttingBoardRecipe;
import net.fabricmc.example.recipe.CuttingBoardRecipeSerializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {

    public static final String MOD_ID = "json_parse";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Block CUTTING_BOARD = new CuttingBoardBlock();
    public static final BlockEntityType<CuttingBoardBlockEntity> CUTTING_BOARD_ENTITY = FabricBlockEntityTypeBuilder
            .create(CuttingBoardBlockEntity::new, CUTTING_BOARD)
            .build();

    public static final CuttingBoardRecipeSerializer CUTTING_RECIPES = new CuttingBoardRecipeSerializer();

    public static RecipeType<CuttingBoardRecipe> CUTTING_BOARD_RECIPE_TYPE;

    public static ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "main"), () -> new ItemStack(CUTTING_BOARD.asItem()));


    public static TranslatableText i18n(String key, Object... args) {
        return new TranslatableText(MOD_ID + "." + key, args);
    }

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Demoing custom recipe JSON parsing error.");

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "cutting_board"), CUTTING_BOARD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cutting_board"), new BlockItem(CUTTING_BOARD, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "cutting_board"), CUTTING_BOARD_ENTITY);
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(ExampleMod.MOD_ID, "cutting"), CUTTING_RECIPES);
        CUTTING_BOARD_RECIPE_TYPE = RecipeType.register(new Identifier(ExampleMod.MOD_ID, "cutting").toString());
    }
}
