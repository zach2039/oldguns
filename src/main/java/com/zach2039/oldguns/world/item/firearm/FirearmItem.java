package com.zach2039.oldguns.world.item.firearm;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.Ammo;
import com.zach2039.oldguns.api.ammo.ProjectileType;
import com.zach2039.oldguns.api.firearm.Firearm;
import com.zach2039.oldguns.api.firearm.FirearmCondition;
import com.zach2039.oldguns.api.firearm.FirearmEffect;
import com.zach2039.oldguns.api.firearm.FirearmReloadType;
import com.zach2039.oldguns.api.firearm.FirearmSize;
import com.zach2039.oldguns.api.firearm.FirearmWaterResiliency;
import com.zach2039.oldguns.api.firearm.FirearmTypes;
import com.zach2039.oldguns.api.firearm.FirearmTypes.MechanismType;
import com.zach2039.oldguns.api.firearm.util.FirearmItemHelper;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.api.firearm.util.FirearmStackHelper;
import com.zach2039.oldguns.api.firearm.util.FirearmTooltipHelper;
import com.zach2039.oldguns.capability.SerializableCapabilityProvider;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmpty;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.network.FirearmEffectMessage;
import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.item.ammo.firearm.FirearmAmmoItem;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.TargetPoint;

public class FirearmItem extends BowItem implements Firearm {

	private final Enchantment [] VALID_ENCHANTMENTS = {
			Enchantments.PUNCH_ARROWS, Enchantments.PIERCING, 
			Enchantments.UNBREAKING, Enchantments.MENDING
	};

	private Multimap<Attribute, AttributeModifier> mainhandModifiers;
	private Multimap<Attribute, AttributeModifier> offhandModifiers;

	private FirearmItem(FirearmProperties builder) {
		super((Properties) builder);
		this.ammoCapacity = builder.ammoCapacity;
		this.projectileSpeed = builder.projectileSpeed;
		this.effectiveRangeModifier = builder.effectiveRangeModifier;
		this.deviationModifier = builder.deviationModifier;
		this.damageModifier = builder.damageModifier;
		this.requiredReloadTicks = builder.requiredReloadTicks;
		this.mechanismType = builder.mechanismType;
		this.defaultProjectileType = builder.defaultProjectileType;
		this.reloadType = builder.reloadType;
		this.firearmSize = builder.firearmSize;
		this.firearmWaterResiliency = builder.firearmWaterResiliency;
		this.firesAllLoadedAmmoAtOnce = builder.firesAllLoadedAmmoAtOnce;
		initAttributes();
	}
	
	public FirearmItem(FirearmTypes.Muzzleloaders entry) {
		this((FirearmProperties) new FirearmProperties()
				.ammoCapacity(entry.getAmmoCapacity())
				.firesAllLoadedAmmoAtOnce(entry.firesAllAtOnce())
				.mechanismType(entry.getMechanismType())
				.defaultProjectileType(entry.getDefaultProjectileType())
				.firearmSize(entry.getFirearmSize())
				.firearmWaterResiliency(entry.getFirearmWaterResiliency())
				.reloadType(entry.getFirearmReloadType())
				.effectiveRangeModifier(entry.getAttributes().effectiveRangeModifier.get().floatValue())
				.damageModifier(entry.getAttributes().shotDamageModifier.get().floatValue())
				.deviationModifier(entry.getAttributes().shotDeviationModifier.get().floatValue())
				.projectileSpeed(entry.getAttributes().projectileSpeed.get().floatValue())
				.defaultDurability(entry.getAttributes().durability.get())
				.setNoRepair()
				.tab(OldGuns.CREATIVE_MODE_TAB)
				);
	}

	@Override
	public ICapabilityProvider initCapabilities(final ItemStack stack, @Nullable final CompoundTag nbt) {
		return new SerializableCapabilityProvider<>(FirearmEmptyCapability.FIREARM_EMPTY_CAPABILITY, FirearmEmptyCapability.DEFAULT_FACING, new FirearmEmpty(true));
	}

	@Override
	public void appendHoverText(ItemStack stackIn, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stackIn, level, tooltip, flagIn);

		/* Populate tooltip info from NBT data. */
		FirearmTooltipHelper.populateTooltipInfo(this, stackIn, tooltip);	
	}

	@Override
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> stackList) {
		if (this.allowdedIn(tab)) {
			ItemStack firearmStack = new ItemStack(this);
			initNBTTags(firearmStack);
			for (int i = 0; i < this.ammoCapacity; i++) {
				FirearmNBTHelper.pushNBTTagAmmo(firearmStack, getDefaultProjectileForFirearm());
			}
			stackList.add(firearmStack);
		}
	}


	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		for (Enchantment enchantmentToCheck : VALID_ENCHANTMENTS) {
			if (enchantmentToCheck == enchantment) return true;
		}
		return false;
	}

	@Override
	public int getEnchantmentValue() {
		return 9;
	}

	private void initAttributes() {
		double baseAttackDamage;
		double baseAttackSpeed;


		/* Get attack speed and base from size. */
		switch (getFirearmSize())
		{
		case SMALL:
			baseAttackDamage = 0f;
			baseAttackSpeed = -1f;
			break;
		default:
		case MEDIUM:
			baseAttackDamage = 1f;
			baseAttackSpeed = -2f;
			break;
		case LARGE:
			baseAttackDamage = 2f;
			baseAttackSpeed = -3f;
			break;
		case HUGE:
			baseAttackDamage = 3f;
			baseAttackSpeed = -3.5f;
			break;
		}

		{            
			Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
			builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)baseAttackDamage, AttributeModifier.Operation.ADDITION));
			builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)baseAttackSpeed, AttributeModifier.Operation.ADDITION));
			this.mainhandModifiers = builder.build();
		}

		{
			Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
			builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)baseAttackSpeed, AttributeModifier.Operation.ADDITION));
			this.offhandModifiers = builder.build();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		switch(slot) {
		case MAINHAND:
			return this.mainhandModifiers;
		case OFFHAND:
			return this.offhandModifiers;
		default:
			return super.getDefaultAttributeModifiers(slot);
		}
	}

	@Override
	public void onCraftedBy(ItemStack stackIn, Level worldIn, Player playerIn)	{
		super.onCraftedBy(stackIn, worldIn, playerIn);

		initNBTTags(stackIn);
	}

	@Override
	public ItemStack getDefaultInstance() {
		ItemStack stackIn = new ItemStack(this);

		initNBTTags(stackIn);

		return stackIn;
	}

	@Override	
	public UseAnim getUseAnimation(ItemStack stackIn) {
		if (FirearmNBTHelper.peekNBTTagAmmoCount(stackIn) == 0)
			return UseAnim.BLOCK;

		return UseAnim.BOW;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
	{
		return !oldStack.equals(newStack); //!ItemStack.areItemStacksEqual(oldStack, newStack);
	}

	@Override
	public void inventoryTick(ItemStack stackIn, Level levelIn, Entity entityIn, int slot, boolean par5) {

		if (OldGunsConfig.SERVER.firearmSettings.hugeFirearmDebuffs.get()) {
			// Apply huge firearm debuffs for balancing reasons
			if (entityIn instanceof Player)
			{
				Player player = (Player) entityIn;
				if (player.isCreative())
					return;
				if (ItemStack.isSame(player.getMainHandItem(), stackIn) || ItemStack.isSame(player.getOffhandItem (), stackIn)) {
					if (getFirearmSize() == FirearmSize.HUGE) {
						addEffect(player, MobEffects.MOVEMENT_SLOWDOWN, 0);
					}
				}
			}
		}
	}

	private void addEffect(Player player, MobEffect effect, int amplifier)
	{
		MobEffectInstance effectIn = player.getEffect(effect);
		if (effectIn == null || effectIn.getDuration() < 20) {
			player.addEffect(new MobEffectInstance(effect, 20 * 3, amplifier));
		}
	}
	
	/**
	 * Used by both mobs and player to launch projectiles from the firearm
	 * @param level
	 * @param livingEntity
	 * @param firearmStack
	 * @param ammoStack
	 * @param snapshotDevMulti
	 */
	public void fireProjectiles(Level level, LivingEntity livingEntity, ItemStack firearmStack, ItemStack ammoStack, float snapshotDevMulti) {
				
		int maxShots = (firesAllLoadedAmmoAtOnce() && !ammoStack.isEmpty()) ? FirearmNBTHelper.peekNBTTagAmmoCount(firearmStack) : 1; 
		for (int i = 0; i < maxShots; i++) {
			ammoStack = FirearmNBTHelper.peekNBTTagAmmo(firearmStack);
			Ammo itemFirearmAmmo = (Ammo)(ammoStack.getItem() instanceof Ammo ? ammoStack.getItem() : this.getDefaultAmmoItem());
			List<BulletProjectile> entityProjectiles = itemFirearmAmmo.createProjectiles(level, ammoStack, livingEntity);

			/* Fire all projectiles from ammo item. */
			float finalVelocity = getProjectileSpeed();
			float finalEffectiveRange = itemFirearmAmmo.getProjectileEffectiveRange() * getEffectiveRangeModifier();
			float finalDeviation = getFirearmDeviation() * snapshotDevMulti * itemFirearmAmmo.getProjectileDeviationModifier();

			OldGuns.printDebug(this + "AmmoEffectiveRange  : " + itemFirearmAmmo.getProjectileEffectiveRange());
			OldGuns.printDebug(this + "FirearmEffectiveMod : " + getEffectiveRangeModifier());
			OldGuns.printDebug(this + "FinalEffectiveRange : " + finalEffectiveRange);
            
			OldGuns.printDebug(this + "FirearmDeviation   : " + getFirearmDeviation());
			OldGuns.printDebug(this + "AmmoDeviationMod   : " + itemFirearmAmmo.getProjectileDeviationModifier());
			OldGuns.printDebug(this + "AimingDeviationMod : " + snapshotDevMulti);
			OldGuns.printDebug(this + "FinalDeviation     : " + finalDeviation);

			entityProjectiles.forEach((t) -> {
				/* Set location-based data. */
				t.setEffectiveRange(finalEffectiveRange);
				t.setLaunchLocation(t.blockPosition());

				/* Launch projectile. */
				t.shoot(livingEntity, livingEntity.getXRot(), livingEntity.getYRot(), 0.0F, finalVelocity, finalDeviation);

				int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, firearmStack);

				if (j > 0)
				{
					t.setDamage((t.getDamage() * getDamageModifier()) + (double)j * 0.5D + 0.5D);
				}

				int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, firearmStack);

				if (k > 0)
				{
					t.setKnockback(k);
				}

				if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, firearmStack) > 0)
				{
					t.setRemainingFireTicks(100);
				}

				level.addFreshEntity(t);
			});

			/* Do firing effects. */
			doFiringEffect(level, livingEntity, firearmStack);        

			if (livingEntity instanceof Player) {
				Player player = (Player)livingEntity;

				if (!player.isCreative()) {
					firearmStack.hurt(1, level.random, (ServerPlayer) player);
					if (firearmStack != null)
						FirearmNBTHelper.popNBTTagAmmo(firearmStack);
				}
			} else {
				firearmStack.hurt(1, level.random, null);
			}
		}
		
		FirearmNBTHelper.refreshFirearmCondition(firearmStack);
	}
	
	@SuppressWarnings("unused")
	@Override
	public void releaseUsing(ItemStack stackIn, Level worldIn, LivingEntity livingEntityIn, int ticksRemaining) {
		if (livingEntityIn instanceof Player)
		{
			Player entityplayer = (Player)livingEntityIn;
			//boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack ammoStack = FirearmNBTHelper.peekNBTTagAmmo(stackIn);

			int ticksUsed = this.getUseDuration(stackIn) - ticksRemaining;
			ticksUsed = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stackIn, worldIn, entityplayer, ticksUsed, !ammoStack.isEmpty());
			if (ticksUsed < 0) 
				return;

			/* Check if this firearm is a breechloader and has a valid reloading recipe. */
			//            BreechloadingReloadRecipe reloadRecipe = null;
			//            boolean isReloadableBreechloader = (getReloadType() == FirearmReloadType.BREECHLOADER && 
			//    				!FirearmRecipeHelper.getBreechloadingReloadRecipes(firearmStack).isEmpty());
			//    		boolean canReloadBreechloader = false;
			//    		if (isReloadableBreechloader)
			//    		{
			//    			/* Allow action to continue on the first reload recipe that is valid for the player's inventory. */
			//    			for (BreechloadingReloadRecipe recipe : FirearmRecipeHelper.getBreechloadingReloadRecipes(firearmStack))
			//    			{
			//    				canReloadBreechloader = recipe.isValid(entityplayer.inventory);
			//    				if (canReloadBreechloader)
			//    				{
			//    					reloadRecipe = recipe;
			//    					break;
			//    				}
			//    			}
			//    		}
			boolean isReloadableBreechloader = false; //FIX
			boolean canReloadBreechloader = false; //FIX
			Object reloadRecipe = null; //FIX

			/* Only allow firing if no cooldown and ammo loaded. */
			boolean readyToFire = (entityplayer.getAttackStrengthScale(0f) >= 0.99f);
			boolean hasAmmoLoaded = (FirearmNBTHelper.peekNBTTagAmmoCount(stackIn) > 0);
			boolean notBroken = FirearmNBTHelper.getNBTTagCondition(stackIn) != FirearmCondition.BROKEN;

			boolean holdingSizableFirearmOffhand = isCarryingSizableFirearmInOtherHand(worldIn, entityplayer, InteractionHand.MAIN_HAND);
			//boolean canAim = (hasAmmoLoaded && readyToFire && notBroken && !holdingLargeFirearmOffhand);
			boolean canReload = (!hasAmmoLoaded && canReloadBreechloader && readyToFire && notBroken && !holdingSizableFirearmOffhand);

			//    		OldGuns.LOGGER.debug("readyToFire : " + readyToFire);
			//    		OldGuns.LOGGER.debug("hasAmmoLoaded : " + hasAmmoLoaded);
			//    		OldGuns.LOGGER.debug("notBroken : " + notBroken);
			//    		OldGuns.LOGGER.debug("canReload : " + canReload);
			//    		OldGuns.LOGGER.debug("holdingLargeFirearmOffhand : " + holdingLargeFirearmOffhand);
			//    		OldGuns.LOGGER.debug("isReloadableBreechloader : " + isReloadableBreechloader);

			if (isReloadableBreechloader && canReload)
			{
				float progress = FirearmStackHelper.getReloadProgress(entityplayer, ticksRemaining, getRequiredReloadTicks());
				if (progress >= 0.99f && reloadRecipe != null)
				{
					//            		ItemStack reloadRecipeAmmoStack = reloadRecipe.getAmmoItemStack().copy();
					//            		if (reloadRecipe.consumeIngredients(entityplayer.getInventory(), entityplayer.isCreative()))
					//            		{
					//	            		FirearmNBTHelper.pushNBTTagAmmo(stackIn, reloadRecipeAmmoStack);
					//	            		//OldGuns.logger.info("reloadRecipeAmmoStack : " + reloadRecipeAmmoStack);
					//	            		//OldGuns.logger.info("reloadProgress : " + progress);
					//	            		entityplayer.swing(entityplayer.getUsedItemHand());
					//            		}
					//            		
					//            		if (!worldIn.isClientSide())
					//            		{
					//            			ServerPlayer playerMP = (ServerPlayer) entityplayer;
					//            			List<Pair<EquipmentSlot, ItemStack>> slots;
					//            			slots.add(new Pair<EquipmentSlot, ItemStack>((entityplayer.getUsedItemHand() == InteractionHand.MAIN_HAND) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND, stackIn));
					//            			ClientboundSetEquipmentPacket pkt = new ClientboundSetEquipmentPacket(entityplayer.getId(),	slots);
					//            			playerMP.connection.send(pkt);
					//            		}
					//            		return;
				}
			}

			float snapshotDevMulti = getSnapshotDeviationMultiplier(ticksUsed);

			if (!ammoStack.isEmpty())
			{

				boolean flag1 = entityplayer.isCreative();

				if (!worldIn.isClientSide())
				{
					/* Calculate stuff based on firearm condition. */
					boolean failure = checkConditionForEffect(worldIn, entityplayer, stackIn);

					//OldGuns.logger.info(String.format("Failure: %d", failure ? 1 : 0));

					/* If firearm broke or misfired, do nothing. */
					if (failure)
					{
						/* Refresh condition. */
						FirearmNBTHelper.refreshFirearmCondition(stackIn);
						if (!worldIn.isClientSide())
						{
							ServerPlayer playerMP = (ServerPlayer) entityplayer;
							List<Pair<EquipmentSlot, ItemStack>> slots = new ArrayList<Pair<EquipmentSlot, ItemStack>>();
							slots.add(new Pair<EquipmentSlot, ItemStack>((entityplayer.getUsedItemHand() == InteractionHand.MAIN_HAND) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND, stackIn));
							ClientboundSetEquipmentPacket pkt = new ClientboundSetEquipmentPacket(entityplayer.getId(),	slots);
							playerMP.connection.send(pkt);
						}
						return;
					}

					fireProjectiles(worldIn, entityplayer, stackIn, ammoStack, snapshotDevMulti);
				}

				

				entityplayer.awardStat(Stats.ITEM_USED.get(this));
			}

			if (!worldIn.isClientSide())
			{
				ServerPlayer playerMP = (ServerPlayer) entityplayer;
				List<Pair<EquipmentSlot, ItemStack>> slots = new ArrayList<Pair<EquipmentSlot, ItemStack>>();
				slots.add(new Pair<EquipmentSlot, ItemStack>((entityplayer.getUsedItemHand() == InteractionHand.MAIN_HAND) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND, stackIn));
				ClientboundSetEquipmentPacket pkt = new ClientboundSetEquipmentPacket(entityplayer.getId(),	slots);
				playerMP.connection.send(pkt);

				
			}

			FirearmEmptyCapability.getFirearmEmpty(stackIn).ifPresent(isEmpty -> {
				isEmpty.setEmpty(FirearmNBTHelper.peekNBTTagAmmoCount(stackIn) == 0);
			});

		}


	}

	@Override
	public void onUseTick(Level worldIn, LivingEntity playerIn, ItemStack stackIn, int currentUseTicks) {
		/* Make sure item has NBT tag. */
		initNBTTags(stackIn);

		/* Process reloading animations based on active use ticks, if this item is a breechloader. */
		if (playerIn != null) 
		{		
			/* Check if this firearm is a breechloader and has a valid reloading recipe. */
			//boolean isReloadableBreechloader = (getReloadType() == FirearmReloadType.BREECHLOADER && 
			//		!FirearmRecipeHelper.getBreechloadingReloadRecipes(stack).isEmpty());
			boolean hasAmmoLoaded = (FirearmNBTHelper.peekNBTTagAmmoCount(stackIn) > 0);
			//boolean isLoading = (!hasAmmoLoaded && isReloadableBreechloader);
			boolean isLoading = (!hasAmmoLoaded && false);

			if (playerIn.getUseItem() == stackIn && isLoading)
			{
				OldGuns.printDebug(this + "hasAmmoLoaded : " + hasAmmoLoaded);
				OldGuns.printDebug(this + "isLoading : " + isLoading);
				
				float progress = FirearmStackHelper.getReloadProgress(playerIn, currentUseTicks, getRequiredReloadTicks());

				if (progress > 0.09f && progress < 0.11f)
					playerIn.playSound(SoundEvents.LEVER_CLICK, 0.25F, 1.0F / (worldIn.random.nextFloat() * 0.2F + 0.9F));

				if (progress > 0.49f && progress < 0.51f)
					playerIn.playSound(SoundEvents.CHICKEN_EGG, 0.25F, 0.7F / (worldIn.random.nextFloat() * 0.2F + 0.9F));

				if (progress > 0.69f && progress < 0.71f)
					playerIn.playSound(SoundEvents.LEVER_CLICK, 0.25F, 1.0F / (worldIn.random.nextFloat() * 0.2F + 0.9F));

				if (progress > 0.95f && progress < 0.99f)
					playerIn.playSound(SoundEvents.LEVER_CLICK, 0.35F, 1.7F / (worldIn.random.nextFloat() * 0.2F + 0.9F));
			}
		}

	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn)
	{
		InteractionResultHolder<ItemStack> result;
		ItemStack itemstack = playerIn.getItemInHand(handIn);

		//		/* Check if this firearm is a breechloader and has a valid reloading recipe. */
		//		boolean isReloadableBreechloader = (getReloadType() == FirearmReloadType.BREECHLOADER && 
		//				!FirearmRecipeHelper.getBreechloadingReloadRecipes(itemstack).isEmpty());
		boolean canReloadBreechloader = false;
		//		if (isReloadableBreechloader)
		//		{
		//			/* Allow action to continue on the first reload recipe that is valid for the player's inventory. */
		//			for (BreechloadingReloadRecipe recipe : FirearmRecipeHelper.getBreechloadingReloadRecipes(itemstack))
		//			{
		//				canReloadBreechloader = recipe.isValid(playerIn.getInventory());
		//				if (canReloadBreechloader)
		//					break;
		//			}
		//		}

		/* Only allow firing if no cooldown and ammo loaded. */
		boolean readyToFire = (playerIn.getAttackStrengthScale(0f) >= 0.99f);
		boolean hasAmmoLoaded = (FirearmNBTHelper.peekNBTTagAmmoCount(itemstack) > 0);
		boolean notBroken = FirearmNBTHelper.getNBTTagCondition(itemstack) != FirearmCondition.BROKEN;

		boolean holdingSizableFirearmOffhand = isCarryingSizableFirearmInOtherHand(worldIn, playerIn, handIn);
		/* Need this to allow firing of an offhand firearm. */
		boolean holdingLoadedFirearmOffhand = hasLoadedFirearmInOtherHand(worldIn, playerIn, handIn);
		boolean canAim = (hasAmmoLoaded && readyToFire && notBroken && !holdingSizableFirearmOffhand);
		boolean canReload = (!hasAmmoLoaded && canReloadBreechloader && !holdingLoadedFirearmOffhand && readyToFire && notBroken && !holdingSizableFirearmOffhand);

		//		if (!worldIn.isClientSide()) {
		//			OldGuns.LOGGER.info("itemstack                : " + itemstack);
		//			//OldGuns.LOGGER.debug("isReloadableBreechloader : " + isReloadableBreechloader);
		//			OldGuns.LOGGER.info("canAim                   : " + canAim);
		//			OldGuns.LOGGER.info("canReload                : " + canReload);
		//		}

		InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, canAim);
		if (ret != null) return ret;

		if (!canAim && !canReload) {
			/* Play empty sound if no ammo, or broken soundif broken. */
			if (!notBroken) {
				playerIn.playSound(SoundEvents.LEVER_CLICK, 0.25F, 0.7F / (worldIn.random.nextFloat() * 0.2F + 0.9F));
				playerIn.playSound(SoundEvents.SHIELD_BREAK, 0.10F, 0.7F / (worldIn.random.nextFloat() * 0.2F + 0.9F));
			} else if (!hasAmmoLoaded) {
				playerIn.playSound(SoundEvents.LEVER_CLICK, 0.25F, 0.7F / (worldIn.random.nextFloat() * 0.2F + 0.9F));		 
			}

			result = InteractionResultHolder.fail(itemstack);
		} else if (canAim) {
			playerIn.startUsingItem(handIn);
			result = InteractionResultHolder.pass(itemstack);
		} else {
			playerIn.startUsingItem(handIn);
			result = InteractionResultHolder.pass(itemstack);
		}

		return result;
	}

	@Override
	public void initNBTTags(ItemStack stackIn)
	{
		FirearmNBTHelper.refreshFirearmCondition(stackIn);
		
		FirearmNBTHelper.peekNBTTagAmmo(stackIn);
	}

	@Override
	public boolean canReload(ItemStack stackIn)
	{
		return (FirearmNBTHelper.peekNBTTagAmmoCount(stackIn) < this.getAmmoCapacity());
	}

	@Override
	public void doFiringEffect(Level worldIn, Entity shooter, ItemStack stackIn)
	{
		TargetPoint point = new PacketDistributor.TargetPoint(
				shooter.xo, shooter.yo, shooter.zo, 1600d, shooter.level.dimension());
		
		
		OldGuns.NETWORK.send(PacketDistributor.NEAR.with(() -> point), 
				new FirearmEffectMessage((LivingEntity)shooter, getFirearmEffectForSize(), shooter.xo, shooter.yo + shooter.getEyeHeight(), shooter.zo,
						shooter.xRotO, shooter.yRotO, ((LivingEntity)shooter).getUsedItemHand().ordinal())
				);
	}
	
	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return null;
	};
	
	@Override
	public ItemStack getDefaultProjectileForFirearm() {
		return new ItemStack(this.getDefaultAmmoItem());
	}
	
	@Override
	public Item getDefaultAmmoItem() {
		return FirearmItemHelper.getDefaultAmmoItemForFirearm(getFirearmSize(), getMechanismType(), getDefaultProjectileType());
	}
	
	private FirearmEffect getFirearmEffectForSize() {
		switch(this.firearmSize) {
		case HUGE:
			return FirearmEffect.LARGE_FIREARM_SHOOT;
		case LARGE:
			return FirearmEffect.LARGE_FIREARM_SHOOT;
		case MEDIUM:
			return FirearmEffect.MEDIUM_FIREARM_SHOOT;
		case SMALL:
			return FirearmEffect.SMALL_FIREARM_SHOOT;
		default:
			return FirearmEffect.SMALL_FIREARM_SHOOT;
		}		
	}
	
	/**
	 * Does NBT stuff for firearm conditions, and will cause certain things to happen based on said conditions.
	 * @param worldIn
	 * @param entityShooter
	 * @param stackIn
	 * @return 
	 */
	public boolean checkConditionForEffect(Level worldIn, Entity shooter, ItemStack stackIn)
	{		
		/* Save flag determining firearm failure. */
		boolean failure = false;

		/* Get explosion chance for current condition. */
		float explosionChance = 0.0f;
		switch (FirearmNBTHelper.getNBTTagCondition(stackIn))
		{
		case VERY_POOR:
			explosionChance += 0.05f;
		case POOR:
			explosionChance += 0.05f;
		default:
			break;
		}

		/* Get misfire chance for current condition. */
		float misfireChance = 0.0f;
		switch (FirearmNBTHelper.getNBTTagCondition(stackIn))
		{
		case VERY_POOR:
			misfireChance += 0.05f;
		case POOR:
			misfireChance += 0.05f;
		case FAIR:
			misfireChance += 0.05f;
		default:
			break;
		}

		/* Get misfire chance for water. */
		float misfireWaterChance = 0.0f;
		switch (this.getFirearmWaterResiliency())
		{
		case VERY_POOR:
			misfireWaterChance += 0.75f;
		case POOR:
			misfireWaterChance += 0.50f;
		case FAIR:
			misfireWaterChance += 0.25f;
		case GOOD:
		case VERY_GOOD:
		default:
			break;
		}

		/* Check for explosion, and then for misfire if no explosion. */
		if (worldIn.random.nextFloat() < explosionChance)
		{
			/* EXPLODE! */
			failure = true;

			/* Change strength of boom based on firearm size. */
			float strength = 1.5f;
			switch (this.getFirearmSize())
			{
			case LARGE:
				strength += 0.5f;
			case MEDIUM:
				strength += 0.5f;
			default:
				break;
			}
			/* Get the position of the player's front using trig. */
			float handX = -Mth.sin(((shooter.xRotO + 23) / 180F) * 3.141593F) * Mth.cos((shooter.yRotO / 180F) * 3.141593F);
			float handY = -Mth.sin((shooter.yRotO / 180F) * 3.141593F) - 0.1F;
			float handZ = Mth.cos(((shooter.xRotO + 23) / 180F) * 3.141593F) * Mth.cos((shooter.yRotO / 180F) * 3.141593F);

			/* Calculate the actual position of the particles. */
			double expX = shooter.xo + handX;
			double expY = shooter.yo + shooter.getEyeHeight() + handY;
			double expZ = shooter.zo + handZ;

			/* Explode for real. */
			worldIn.explode((Entity)null, expX, expY, expZ, strength, false, Explosion.BlockInteraction.NONE);			

			/* Empty firearm and set damage to max. */
			FirearmNBTHelper.emptyNBTTagAmmo(stackIn);
			stackIn.setDamageValue(stackIn.getMaxDamage());

			PacketDistributor.TargetPoint point = new PacketDistributor.TargetPoint(
					shooter.xo, shooter.yo, shooter.zo, 100d, shooter.level.dimension());

			OldGuns.NETWORK.send(PacketDistributor.NEAR.with(() -> point), 
					new FirearmEffectMessage((LivingEntity)shooter, FirearmEffect.BREAK, shooter.xo, shooter.yo + shooter.getEyeHeight(), shooter.zo,
							shooter.xRotO, shooter.yRotO, ((Player)shooter).getUsedItemHand().ordinal())
					);
		}
		else if (worldIn.random.nextFloat() < misfireChance)
		{
			/* Misfire. */
			failure = true;

			PacketDistributor.TargetPoint point = new PacketDistributor.TargetPoint(
					shooter.xo, shooter.yo, shooter.zo, 100d, shooter.level.dimension());

			OldGuns.NETWORK.send(PacketDistributor.NEAR.with(() -> point), 
					new FirearmEffectMessage((LivingEntity)shooter, FirearmEffect.MISFIRE, shooter.xo, shooter.yo + shooter.getEyeHeight(), shooter.zo,
							shooter.xRotO, shooter.yRotO, ((LivingEntity)shooter).getUsedItemHand().ordinal())
					);
		}
		else if (this.getFirearmWaterResiliency() != FirearmWaterResiliency.VERY_GOOD)
		{
			/* Check for rain or water, if firearm is vulnerable to it. */

			boolean misfire = false;

			/* If poor water resiliency and wet or submerged, misfire */
			if (shooter.isInWaterOrRain() && !shooter.isInWater())
			{
				misfire = ((worldIn.random.nextFloat() < misfireWaterChance) ||
						(this.getFirearmWaterResiliency() == FirearmWaterResiliency.VERY_POOR)) ? true : false;
			}
			else if (shooter.isInWater())
			{
				/* If good water resiliency, try misfire in water. Otherwise, always misfire */
				misfire = ((worldIn.random.nextFloat() < misfireWaterChance) &&
						(this.getFirearmWaterResiliency() != FirearmWaterResiliency.GOOD)) ? true : false;
			}

			/* Misfire and flag as failure if needed. */
			if (misfire)
			{
				failure = true;

				PacketDistributor.TargetPoint point = new PacketDistributor.TargetPoint(
						shooter.xo, shooter.yo, shooter.zo, 100d, shooter.level.dimension());

				OldGuns.NETWORK.send(PacketDistributor.NEAR.with(() -> point), 
						new FirearmEffectMessage((LivingEntity)shooter, FirearmEffect.MISFIRE_WET, shooter.xo, shooter.yo + shooter.getEyeHeight(), shooter.zo,
								shooter.xRotO, shooter.yRotO, ((LivingEntity)shooter).getUsedItemHand().ordinal())
						);
			}
		}

		return failure;
	}

	public static float getSnapshotDeviationMultiplier(int ticksInUse)
	{
		float devMulti = (ticksInUse < 5) ? 3.0f : ((ticksInUse < 10) ? 2.0f : 1.0f); 
		return devMulti; 
	}

	private boolean isCarryingSizableFirearmInOtherHand(Level worldIn, Player playerIn, InteractionHand handIn)
	{
		boolean isCarryingSizableFirearm = false;

		ItemStack itemstackOther = playerIn.getItemInHand((handIn == InteractionHand.MAIN_HAND) ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);

		if (itemstackOther.getItem() instanceof Firearm)
		{
			isCarryingSizableFirearm = ((Firearm)itemstackOther.getItem()).getFirearmSize().ordinal() >= FirearmSize.MEDIUM.ordinal();
		}

		return isCarryingSizableFirearm;
	}

	private boolean hasLoadedFirearmInOtherHand(Level worldIn, Player playerIn, InteractionHand handIn)
	{
		boolean isLoadedFirearmInOtherHand = false;

		/* Item in other hand. */
		ItemStack itemstackOther = playerIn.getItemInHand((handIn == InteractionHand.MAIN_HAND) ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);

		/* If item in other hand is instance of ItemFirearm, return true if firearm is large size. */
		if (itemstackOther.getItem() instanceof FirearmItem)
		{
			isLoadedFirearmInOtherHand = (FirearmNBTHelper.peekNBTTagAmmoCount(itemstackOther) > 0);
		}

		return isLoadedFirearmInOtherHand;
	}

	public static class FirearmProperties extends Properties {

		/**
		 * Ammo capacity of this firearm item instance.
		 */
		int ammoCapacity = 1;

		/**
		 * Projectile speed of this firearm item instance.
		 */
		float projectileSpeed = 2.5f;

		/**
		 * Effective range of this firearm item instance.
		 */
		float effectiveRangeModifier = 10f;

		/**
		 * Deviation modifier of this firearm item instance.
		 */
		float deviationModifier = 1f;

		/**
		 * Damage modifier of this firearm item instance.
		 */
		float damageModifier = 1f;

		/**
		 * Required reload ticks of this firearm item instance.
		 */
		int requiredReloadTicks = 80;

		/**
		 * Whether the firearm fires all loaded ammo at once. Used for nockguns and duckfoot firearms.
		 */
		private boolean firesAllLoadedAmmoAtOnce = false;

		/**
		 * Mechanism type of firearm
		 */
		MechanismType mechanismType = MechanismType.MATCHLOCK;
		
		/**
		 * Default projectile type of firearm
		 */
		ProjectileType defaultProjectileType = ProjectileType.MUSKET_BALL;
		
		/**
		 * Reload type of the firearm.
		 */
		FirearmReloadType reloadType = FirearmReloadType.MUZZLELOADER;

		/**
		 * Size of the firearm.
		 */
		FirearmSize firearmSize = FirearmSize.MEDIUM;

		/**
		 * Water resiliency of the firearm.
		 */
		FirearmWaterResiliency firearmWaterResiliency = FirearmWaterResiliency.FAIR;

		public FirearmProperties ammoCapacity(int ammoCapacity) {
			this.ammoCapacity = ammoCapacity;
			return this;
		}

		public FirearmProperties projectileSpeed(float projectileSpeed) {
			this.projectileSpeed = projectileSpeed;
			return this;
		}

		public FirearmProperties effectiveRangeModifier(float effectiveRangeModifier) {
			this.effectiveRangeModifier = effectiveRangeModifier;
			return this;
		}

		public FirearmProperties deviationModifier(float deviationModifier) {
			this.deviationModifier = deviationModifier;
			return this;
		}

		public FirearmProperties damageModifier(float damageModifier) {
			this.damageModifier = damageModifier;
			return this;
		}

		public FirearmProperties requiredReloadTicks(int requiredReloadTicks) {
			this.requiredReloadTicks = requiredReloadTicks;
			return this;
		}

		public FirearmProperties firesAllLoadedAmmoAtOnce(boolean firesAllLoadedAmmoAtOnce) {
			this.firesAllLoadedAmmoAtOnce = firesAllLoadedAmmoAtOnce;
			return this;
		}

		public FirearmProperties mechanismType(MechanismType mechanismType) {
			this.mechanismType = mechanismType;
			return this;
		}
		
		public FirearmProperties defaultProjectileType(ProjectileType defaultProjectileType) {
			this.defaultProjectileType = defaultProjectileType;
			return this;
		}
		
		public FirearmProperties reloadType(FirearmReloadType reloadType) {
			this.reloadType = reloadType;
			return this;
		}

		public FirearmProperties firearmSize(FirearmSize firearmSize) {
			this.firearmSize = firearmSize;
			return this;
		}

		public FirearmProperties firearmWaterResiliency(FirearmWaterResiliency firearmWaterResiliency) {
			this.firearmWaterResiliency = firearmWaterResiliency;
			return this;
		}

	}

	@Override
	public int getAmmoCapacity() {
		return this.ammoCapacity;
	}

	@Override
	public float getProjectileSpeed() {
		return this.projectileSpeed;
	}

	@Override
	public float getEffectiveRangeModifier() {
		return this.effectiveRangeModifier;
	}

	@Override
	public float getFirearmDeviation() {
		return this.deviationModifier;
	}

	@Override
	public float getDamageModifier() {
		return this.damageModifier;
	}

	@Override
	public int getRequiredReloadTicks() {
		return this.requiredReloadTicks;
	}

	@Override
	public boolean firesAllLoadedAmmoAtOnce() {
		return this.firesAllLoadedAmmoAtOnce;
	}

	@Override
	public MechanismType getMechanismType() {
		return this.mechanismType;
	}
	
	@Override
	public ProjectileType getDefaultProjectileType() {
		return this.defaultProjectileType;
	}
	
	@Override
	public FirearmReloadType getReloadType() {
		return this.reloadType;
	}

	@Override
	public FirearmSize getFirearmSize()	{
		return this.firearmSize;
	}

	@Override
	public FirearmWaterResiliency getFirearmWaterResiliency() {
		return this.firearmWaterResiliency;
	}

	/**
	 * Ammo capacity of this firearm item instance.
	 */
	int ammoCapacity = 1;

	/**
	 * Projectile speed of this firearm item instance.
	 */
	float projectileSpeed = 2.5f;

	/**
	 * Effective range of this firearm item instance.
	 */
	float effectiveRangeModifier = 10f;

	/**
	 * Deviation modifier of this firearm item instance.
	 */
	float deviationModifier = 1f;

	/**
	 * Damage modifier of this firearm item instance.
	 */
	float damageModifier = 1f;

	/**
	 * Required reload ticks of this firearm item instance.
	 */
	int requiredReloadTicks = 80;

	/**
	 * Whether the firearm fires all loaded ammo at once. Used for nockguns and duckfoot firearms.
	 */
	boolean firesAllLoadedAmmoAtOnce = false;

	/**
	 * Mechanism type of firearm
	 */
	MechanismType mechanismType = MechanismType.MATCHLOCK;
	
	/**
	 * Default projectile type of firearm
	 */
	ProjectileType defaultProjectileType = ProjectileType.MUSKET_BALL;
	
	/**
	 * Reload type of the firearm.
	 */
	FirearmReloadType reloadType = FirearmReloadType.MUZZLELOADER;

	/**
	 * Size of the firearm.
	 */
	FirearmSize firearmSize = FirearmSize.MEDIUM;

	/**
	 * Water resiliency of the firearm.
	 */
	FirearmWaterResiliency firearmWaterResiliency = FirearmWaterResiliency.FAIR;

}
