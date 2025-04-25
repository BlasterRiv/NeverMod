package net.blaster.nevermod.datagen;

import net.blaster.nevermod.Item.ModItems;
import net.blaster.nevermod.NeverMod;
import net.blaster.nevermod.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NeverRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public NeverRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NeverKey.get())
                .pattern("##&")
                .pattern("$$$")
                .pattern("###")
                .define('#',ModItems.AncientGold).define('&',ModItems.PowerGem).define('$', Ingredient.of(ModItems.TeleportGem,ModItems.LinkingGem,ModItems.DimensionGem))
                .unlockedBy("has_gem",has(ModItems.AncientGold))
                .save(pRecipeOutput);
        oreSmelting(pRecipeOutput,List.of(ModItems.RawAncientGold),RecipeCategory.MISC,ModItems.AncientGold,100f,100,"AncientGold");
        oreBlasting(pRecipeOutput,List.of(ModItems.RawAncientGold),RecipeCategory.MISC,ModItems.AncientGold,100f,50,"AncientGold");
    }


    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pSerializer, AbstractCookingRecipe.Factory<T> pRecipeFactory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pSuffix) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pSerializer, pRecipeFactory)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, NeverMod.MOD_ID +':'+ getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }
    }

}
