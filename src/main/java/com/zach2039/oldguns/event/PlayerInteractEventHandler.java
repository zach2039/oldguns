package com.zach2039.oldguns.event;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.WorldInteractionSettings;
import com.zach2039.oldguns.init.ModItems;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OldGuns.MODID)
public class PlayerInteractEventHandler {

	private static final WorldInteractionSettings WORLD_INTERACTION_SETTINGS = OldGunsConfig.SERVER.recipeSettings.worldInteractionSettings;
	
	@SubscribeEvent
	public static void onShearsUsedOnLogEvent(final PlayerInteractEvent.RightClickBlock event) {
		
		// Create bark strands when shears are used on a log, if enabled
		if (OldGunsConfig.SERVER.recipeSettings.worldInteractionSettings.allowShearsToStripBark.get()) {
			ItemStack toolStack = event.getItemStack();
			Level level = (Level) event.getWorld();
			Player player = event.getPlayer();
			InteractionHand hand = event.getHand();
			BlockPos blockpos = event.getPos();
			BlockState preState = level.getBlockState(blockpos);
			BlockHitResult blockHit = event.getHitVec();
			UseOnContext ctx = new UseOnContext(level, player, hand, toolStack, blockHit);

			Optional<@Nullable BlockState> optional = Optional.ofNullable(preState.getToolModifiedState(ctx, net.minecraftforge.common.ToolActions.AXE_STRIP, false));

			if (optional.isPresent() && toolStack.getItem() == Items.SHEARS) {
				if (player instanceof ServerPlayer) {
					CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockpos, toolStack);
				}

				level.playSound(player, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.setBlock(blockpos, optional.get(), 11);
				int harvestAmt = Math.max(1, WORLD_INTERACTION_SETTINGS.barkStrandHarvestAmount.get());
				Block.popResourceFromFace(level, blockpos, event.getFace(), new ItemStack(ModItems.BARK_STRANDS.get(), harvestAmt));
				
				if (player != null) {
					player.swing(player.getUsedItemHand());
					toolStack.hurtAndBreak(1, player, (p_150686_) -> {
						p_150686_.broadcastBreakEvent(LivingEntity.getEquipmentSlotForItem(toolStack));
					});
				}
			}
		}
	}
}
