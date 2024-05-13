package net.rumo.egapplemod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.data.server.advancement.AdvancementProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class EnchantedGappleModDataGenerator implements DataGeneratorEntrypoint {

	private static class EnchantedGappleModRecipeGenerator extends FabricRecipeProvider {
		private EnchantedGappleModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output);
		}

		@Override
		public void generate(Consumer<RecipeJsonProvider> exporter) {
			ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.ENCHANTED_GOLDEN_APPLE)
					.pattern("ggg")
					.pattern("gag")
					.pattern("ggg")
					.input('g', Items.GOLD_BLOCK)
					.input('a', Items.APPLE)
					.criterion(FabricRecipeProvider.hasItem(Items.GOLD_BLOCK),
							FabricRecipeProvider.conditionsFromItem(Items.GOLD_BLOCK))
					.criterion(FabricRecipeProvider.hasItem(Items.APPLE),
							FabricRecipeProvider.conditionsFromItem(Items.APPLE))
					.offerTo(exporter);
		}
	}

//	static class EnchantedGappleModAdvancementsProvider extends FabricAdvancementProvider {
//		protected EnchantedGappleModAdvancementsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
//			super(output, registriesFuture);
//		}
//
//		@Override
//		public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
//			Advancement.Builder.create().parent().display(
//							Items.ENCHANTED_GOLDEN_APPLE,
//							Text.literal("Overpowered"),
//							Text.literal("Eat an Enchanted Apple"),
//							new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
//							AdvancementFrame.TASK,
//							true,
//							true,
//							false
//					)
//					.build(consumer, "egapplemod" + "/root");
//		}
//	}

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(EnchantedGappleModRecipeGenerator::new);
		// pack.addProvider(EnchantedGappleModAdvancementsProvider::new);
	}
}
