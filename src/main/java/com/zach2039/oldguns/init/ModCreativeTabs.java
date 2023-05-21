package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import com.zach2039.oldguns.world.item.tools.DesignNotesItem;
import com.zach2039.oldguns.world.item.tools.PowderHornItem;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * Registers {@link CreativeModeTab}s.
 *
 * @author Zach2039
 */
@Mod.EventBusSubscriber(modid = OldGuns.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTabs {
    @Nullable
    private static CreativeModeTab CREATIVE_MODE_TAB;

    @SubscribeEvent
    public static void register(final CreativeModeTabEvent.Register event) {
        final Supplier<ItemStack> firearm = () -> new ItemStack(ModItems.FLINTLOCK_PISTOL.get());

        CREATIVE_MODE_TAB = event.registerCreativeModeTab(
                new ResourceLocation(OldGuns.MODID, "tab"),
                builder -> builder
                        .title(Component.translatable("itemGroup.oldguns"))
                        .icon(firearm)
                        .displayItems((parameters, output) -> {
                            output.accept(firearm.get());
                            add(output, ModBlocks.orderedItems());
                            add(output, ModItems.orderedItems());
                            add(output, ModFluids.orderedItems());
                        })
        );
    }

    private static void add(final CreativeModeTab.Output output, final Collection<RegistryObject<Item>> items) {
        items.stream()
                .map(RegistryObject::get)
                .forEach(output::accept);
    }

    @SubscribeEvent
    public static void buildContents(final CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() != CREATIVE_MODE_TAB) {
            return;
        }

        final var entries = event.getEntries();

        // Painful
        //for (java.util.Map.Entry<ItemStack, CreativeModeTab.TabVisibility> entry : entries) {
        //    if (entry.getKey().getItem() instanceof FirearmItem item) {
        //        item.fillCreativeModeTab(entries);
        //   }
        //}

        //for (java.util.Map.Entry<ItemStack, CreativeModeTab.TabVisibility> entry : entries) {
        //    if (entry.getKey().getItem() instanceof PowderHornItem item) {
        //        item.fillCreativeModeTab(entries);
        //    }
        //}

        //for (java.util.Map.Entry<ItemStack, CreativeModeTab.TabVisibility> entry : entries) {
        //    if (entry.getKey().getItem() instanceof DesignNotesItem item) {
        //        item.fillCreativeModeTab(entries);
        //    }
        //}
    }
}
