package zach2039.oldguns.common.item.firearm;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityEquipment;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zach2039.oldguns.api.firearm.FirearmType.FirearmCondition;
import zach2039.oldguns.api.firearm.FirearmType.FirearmEffect;
import zach2039.oldguns.api.firearm.FirearmType.FirearmReloadType;
import zach2039.oldguns.api.firearm.FirearmType.FirearmSize;
import zach2039.oldguns.api.firearm.FirearmType.FirearmWaterResiliency;
import zach2039.oldguns.api.firearm.impl.IFirearm;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.entity.EntityProjectile;
import zach2039.oldguns.common.init.ModItems;
import zach2039.oldguns.common.item.ammo.ItemFirearmAmmo;
import zach2039.oldguns.common.item.crafting.BreechloadingReloadRecipe;
import zach2039.oldguns.common.item.util.FirearmNBTHelper;
import zach2039.oldguns.common.item.util.FirearmRecipeHelper;
import zach2039.oldguns.common.item.util.FirearmStackHelper;
import zach2039.oldguns.common.item.util.FirearmTooltipHelper;
import zach2039.oldguns.common.network.MessageFirearmEffect;

public abstract class ItemFirearm extends ItemBow implements IFirearm
{
	/**
	 * Ammo capacity of this firearm item instance.
	 */
	protected int AmmoCapacity = 1;
	
	/**
	 * Projectile speed of this firearm item instance.
	 */
	protected float ProjectileSpeed = 2.5f;
	
	/**
	 * Effective range of this firearm item instance.
	 */
	protected float EffectiveRange = 10f;
	
	/**
	 * Deviation modifier of this firearm item instance.
	 */
	protected float deviationModifier = 1f;
	
	/**
	 * Damage modifier of this firearm item instance.
	 */
	protected float damageModifier = 1f;
	
	/**
	 * Required reload ticks of this firearm item instance.
	 */
	protected int requiredReloadTicks = 80;
	
	/**
	 * Reload type of the firearm.
	 */
	protected FirearmReloadType reloadType = FirearmReloadType.MUZZLELOADER;
	
	/**
	 * Size of the firearm.
	 */
	protected FirearmSize firearmSize = FirearmSize.MEDIUM;
	
	/**
	 * Water resiliency of the firearm.
	 */
	protected FirearmWaterResiliency firearmWaterResiliency = FirearmWaterResiliency.FAIR;
	
	public ItemFirearm(String name)
	{
		setRegistryName(OldGuns.MODID, name);
		setUnlocalizedName(name);
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
		setNoRepair();
        addPropertyOverride(new ResourceLocation(OldGuns.MODID, "empty"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
            	if (entityIn == null) return 0.0F;
            	if (stack.isEmpty() || !(stack.getItem() instanceof ItemFirearm)) return 0.0F;
            	int ammoCount = FirearmNBTHelper.peekNBTTagAmmoCount(stack);
            	//OldGuns.logger.info("ammoCount : " + ammoCount);
            	return (ammoCount > 0) ? 0.0F : 1.0F;
            }
        });
        addPropertyOverride(new ResourceLocation(OldGuns.MODID, "reload_state"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
            	if (entityIn == null) return 1.0F;
            	if (stack.isEmpty() || !(stack.getItem() instanceof ItemFirearm)) return 1.0F;
            	
            	/* Check if this firearm is a breechloader and has a valid reloading recipe. */
				boolean isReloadableBreechloader = (getReloadType() == FirearmReloadType.BREECHLOADER && 
						!FirearmRecipeHelper.getBreechloadingReloadRecipes(stack).isEmpty());
				boolean hasAmmoLoaded = (FirearmNBTHelper.peekNBTTagAmmoCount(stack) > 0);
				boolean isLoadable = (!hasAmmoLoaded && isReloadableBreechloader);
            	
				if (!isReloadableBreechloader)
					return 1.0f;
				if (!isLoadable)
					return 0.0f;
				if (hasAmmoLoaded)
					return 1.0f;
				
            	float progress = FirearmStackHelper.getReloadProgress(entityIn, getRequiredReloadTicks());
            	//OldGuns.logger.info("reloadProgress : " + progress);
            	
                return (entityIn.getActiveItemStack() == stack) ? progress : 0.0f;
            }
        });
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		
		/* Make sure item has NBT tag. */
		if (!stack.hasTagCompound())
			initNBTTags(stack);
		
		/* Process reloading animations based on active use ticks, if this item is a breechloader. */
		if (entityIn instanceof EntityLivingBase) 
		{
			EntityLivingBase entLiving = (EntityLivingBase) entityIn;
		
			/* Check if this firearm is a breechloader and has a valid reloading recipe. */
			boolean isReloadableBreechloader = (getReloadType() == FirearmReloadType.BREECHLOADER && 
					!FirearmRecipeHelper.getBreechloadingReloadRecipes(stack).isEmpty());
			boolean hasAmmoLoaded = (FirearmNBTHelper.peekNBTTagAmmoCount(stack) > 0);
			boolean isLoading = (!hasAmmoLoaded && isReloadableBreechloader);
			
			if (entLiving.getActiveItemStack() == stack && isLoading)
			{
				OldGuns.logger.debug("hasAmmoLoaded : " + hasAmmoLoaded);
				OldGuns.logger.debug("isLoading : " + isLoading);
				float progress = FirearmStackHelper.getReloadProgress(entLiving, getRequiredReloadTicks());
				
				if (progress > 0.09f && progress < 0.11f)
					entLiving.playSound(SoundEvents.BLOCK_LEVER_CLICK, 0.25F, 1.0F / (Item.itemRand.nextFloat() * 0.2F + 0.9F));
            	
				if (progress > 0.49f && progress < 0.51f)
					entLiving.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 0.25F, 0.7F / (Item.itemRand.nextFloat() * 0.2F + 0.9F));
				
				if (progress > 0.69f && progress < 0.71f)
					entLiving.playSound(SoundEvents.BLOCK_LEVER_CLICK, 0.25F, 1.0F / (Item.itemRand.nextFloat() * 0.2F + 0.9F));
				
				if (progress > 0.95f && progress < 0.99f)
					entLiving.playSound(SoundEvents.BLOCK_LEVER_CLICK, 0.35F, 1.7F / (Item.itemRand.nextFloat() * 0.2F + 0.9F));
			}
		}
    }

	@Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
    	super.onCreated(stack, worldIn, playerIn);
    	
    	/* Make sure item has NBT tag. */
    	if (!stack.hasTagCompound())
			initNBTTags(stack);
    	
    	/* Recalculate firearm condition. */
    	FirearmNBTHelper.refreshFirearmCondition(stack);
    }
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
		/* Populate tooltip info from NBT data. */
		FirearmTooltipHelper.populateTooltipInfo(this, stack, tooltip);
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)
    {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

        /* Attack vars. */
        double baseAttackDamage = 1f;
        double baseAttackSpeed = 1f;
        
        /* Get attack speed and base from size. */
        switch (getFirearmSize())
        {
        	case SMALL:
        		baseAttackDamage = 1f;
        		baseAttackSpeed = 1f;
        		break;
        	case MEDIUM:
        		baseAttackDamage = 2f;
        		baseAttackSpeed = 0.7f;
        		break;
        	case LARGE:
        		baseAttackDamage = 3f;
        		baseAttackSpeed = 0.4f;
        		break;
        	default:
        		break;
        }
        
        if (slot == EntityEquipmentSlot.MAINHAND)
        {
        	multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", baseAttackDamage, 0));
        	multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", baseAttackSpeed, 0));
        }
        else if (slot == EntityEquipmentSlot.OFFHAND)
        {
        	multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", baseAttackSpeed, 0));
        }
        
        return multimap;
    }
	
	public static float getSnapshotDeviationMultiplier(int ticksInUse)
	{
		float devMulti = (ticksInUse < 5) ? 3.0f : ((ticksInUse < 10) ? 2.0f : 1.0f); 
		return devMulti; 
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack firearmStack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
	{
		if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            //boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack ammoStack = FirearmNBTHelper.peekNBTTagAmmo(firearmStack);

            int ticksUsed = this.getMaxItemUseDuration(firearmStack) - timeLeft;
            ticksUsed = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(firearmStack, worldIn, entityplayer, ticksUsed, !ammoStack.isEmpty());
            if (ticksUsed < 0) return;

            /* Check if this firearm is a breechloader and has a valid reloading recipe. */
            BreechloadingReloadRecipe reloadRecipe = null;
            boolean isReloadableBreechloader = (getReloadType() == FirearmReloadType.BREECHLOADER && 
    				!FirearmRecipeHelper.getBreechloadingReloadRecipes(firearmStack).isEmpty());
    		boolean canReloadBreechloader = false;
    		if (isReloadableBreechloader)
    		{
    			/* Allow action to continue on the first reload recipe that is valid for the player's inventory. */
    			for (BreechloadingReloadRecipe recipe : FirearmRecipeHelper.getBreechloadingReloadRecipes(firearmStack))
    			{
    				canReloadBreechloader = recipe.isValid(entityplayer.inventory);
    				if (canReloadBreechloader)
    				{
    					reloadRecipe = recipe;
    					break;
    				}
    			}
    		}
    		
    		/* Only allow firing if no cooldown and ammo loaded. */
            boolean readyToFire = (entityplayer.getCooledAttackStrength(0f) >= 0.99f);
    		boolean hasAmmoLoaded = (FirearmNBTHelper.peekNBTTagAmmoCount(firearmStack) > 0);
    		boolean notBroken = FirearmNBTHelper.getNBTTagCondition(firearmStack) != FirearmCondition.BROKEN;
    		
    		boolean holdingLargeFirearmOffhand = isCarryingLargeFirearmInOtherHand(worldIn, entityplayer, EnumHand.MAIN_HAND);
    		//boolean canAim = (hasAmmoLoaded && readyToFire && notBroken && !holdingLargeFirearmOffhand);
    		boolean canReload = (!hasAmmoLoaded && canReloadBreechloader && readyToFire && notBroken && !holdingLargeFirearmOffhand);
            
    		OldGuns.logger.debug("readyToFire : " + readyToFire);
    		OldGuns.logger.debug("hasAmmoLoaded : " + hasAmmoLoaded);
    		OldGuns.logger.debug("notBroken : " + notBroken);
    		OldGuns.logger.debug("canReload : " + canReload);
    		OldGuns.logger.debug("holdingLargeFirearmOffhand : " + holdingLargeFirearmOffhand);
    		OldGuns.logger.debug("isReloadableBreechloader : " + isReloadableBreechloader);
    		
            if (isReloadableBreechloader && canReload)
            {
            	float progress = FirearmStackHelper.getReloadProgress(entityplayer, getRequiredReloadTicks());
            	if (progress >= 0.99f && reloadRecipe != null)
            	{
            		ItemStack reloadRecipeAmmoStack = reloadRecipe.getAmmoItemStack().copy();
            		if (reloadRecipe.consumeIngredients(entityplayer.inventory, entityplayer.capabilities.isCreativeMode))
            		{
	            		FirearmNBTHelper.pushNBTTagAmmo(firearmStack, reloadRecipeAmmoStack);
	            		//OldGuns.logger.info("reloadRecipeAmmoStack : " + reloadRecipeAmmoStack);
	            		//OldGuns.logger.info("reloadProgress : " + progress);
	            		entityplayer.swingArm(entityplayer.getActiveHand());
            		}
            		
            		if (!worldIn.isRemote)
            		{
            			EntityPlayerMP playerMP = (EntityPlayerMP) entityplayer;
            			SPacketEntityEquipment pkt = new SPacketEntityEquipment(
            					entityplayer.getEntityId(),
            					((entityplayer.getActiveHand() == EnumHand.MAIN_HAND) ? EntityEquipmentSlot.MAINHAND : EntityEquipmentSlot.OFFHAND), 
            					firearmStack);
            			playerMP.connection.sendPacket(pkt);
            		}
            		return;
            	}
            }
            
            float snapshotDevMulti = getSnapshotDeviationMultiplier(ticksUsed);
            
            if (!ammoStack.isEmpty())
            {
  
                boolean flag1 = entityplayer.capabilities.isCreativeMode || (ammoStack.getItem() instanceof ItemArrow && ((ItemArrow) ammoStack.getItem()).isInfinite(ammoStack, firearmStack, entityplayer));

                if (!worldIn.isRemote)
                {
                	/* Calculate stuff based on firearm condition. */
                    boolean failure = checkConditionForEffect(worldIn, entityplayer, firearmStack);
        	            
        	        //OldGuns.logger.info(String.format("Failure: %d", failure ? 1 : 0));
        	            
                    /* If firearm broke or misfired, do nothing. */
                    if (failure)
                    {
                    	/* Refresh condition. */
                        FirearmNBTHelper.refreshFirearmCondition(firearmStack);
                        if (!worldIn.isRemote)
                		{
                			EntityPlayerMP playerMP = (EntityPlayerMP) entityplayer;
                			SPacketEntityEquipment pkt = new SPacketEntityEquipment(
                					entityplayer.getEntityId(),
                					((entityplayer.getActiveHand() == EnumHand.MAIN_HAND) ? EntityEquipmentSlot.MAINHAND : EntityEquipmentSlot.OFFHAND), 
                					firearmStack);
                			playerMP.connection.sendPacket(pkt);
                		}
                    	return;
                    }
                    
                    ItemFirearmAmmo itemFirearmAmmo = (ItemFirearmAmmo)(ammoStack.getItem() instanceof ItemFirearmAmmo ? ammoStack.getItem() : ModItems.SMALL_IRON_MUSKET_BALL);
                    List<EntityProjectile> entityProjectiles = itemFirearmAmmo.createProjectiles(worldIn, ammoStack, entityplayer);
                    
                    /* Fire all projectiles from ammo item. */
                    float finalVelocity = getProjectileSpeed();
                    float finalEffectiveRange = itemFirearmAmmo.getProjectileEffectiveRange() * getEffectiveRangeModifier();
                    float finalDeviation = getFirearmDeviation() * snapshotDevMulti * itemFirearmAmmo.getProjectileDeviationModifier();
                    
                    OldGuns.logger.info("AmmoEffectiveRange  : " + itemFirearmAmmo.getProjectileEffectiveRange());
                    OldGuns.logger.info("FirearmEffectiveMod : " + getEffectiveRangeModifier());
                    OldGuns.logger.info("FinalEffectiveRange : " + finalEffectiveRange);
                    
                    OldGuns.logger.info("FirearmDeviation   : " + getFirearmDeviation());
                    OldGuns.logger.info("AmmoDeviationMod   : " + itemFirearmAmmo.getProjectileDeviationModifier());
                    OldGuns.logger.info("AimingDeviationMod : " + snapshotDevMulti);
                    OldGuns.logger.info("FinalDeviation     : " + finalDeviation);
                    
                    entityProjectiles.forEach((t) ->
                    {
                    	/* Set location-based data. */
                    	t.setEffectiveRange(finalEffectiveRange);
                    	t.setLaunchLocation(t.getPosition());
                    	
                    	/* Launch projectile. */
                    	t.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, finalVelocity, finalDeviation);
                    	
                    	int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, firearmStack);

                        if (j > 0)
                        {
                            t.setDamage((t.getDamage() * getDamageModifier()) + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, firearmStack);

                        if (k > 0)
                        {
                            t.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, firearmStack) > 0)
                        {
                            t.setFire(100);
                        }
                        
                        worldIn.spawnEntity(t);
                    });
                    
                    firearmStack.damageItem(1, entityplayer);
                    
                    /* Do firing effects. */
                    doFiringEffect(worldIn, entityplayer, firearmStack);        
                    
                    if (!flag1 && !entityplayer.capabilities.isCreativeMode && (firearmStack != null))
                    {
                    	/* Remove ammo from firearm. */
                        FirearmNBTHelper.popNBTTagAmmo(firearmStack);
                    }
                }

                /* Refresh condition. */
                FirearmNBTHelper.refreshFirearmCondition(firearmStack);
                
                entityplayer.addStat(StatList.getObjectUseStats(this));
            }
            if (!worldIn.isRemote)
    		{
    			EntityPlayerMP playerMP = (EntityPlayerMP) entityplayer;
    			SPacketEntityEquipment pkt = new SPacketEntityEquipment(
    					entityplayer.getEntityId(),
    					((entityplayer.getActiveHand() == EnumHand.MAIN_HAND) ? EntityEquipmentSlot.MAINHAND : EntityEquipmentSlot.OFFHAND), 
    					firearmStack);
    			playerMP.connection.sendPacket(pkt);
    		}
        }
		
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		if (FirearmNBTHelper.peekNBTTagAmmoCount(stack) == 0)
			return EnumAction.BLOCK;
		
		return EnumAction.BOW;
	}
	
	private boolean isCarryingLargeFirearmInOtherHand(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		boolean isCarryingLargeFirearm = false;
		
		/* Item in other hand. */
		ItemStack itemstackOther = playerIn.getHeldItem((handIn == EnumHand.MAIN_HAND) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
		
		/* If item in other hand is instance of ItemFirearm, return true if firearm is large size. */
		if (itemstackOther.getItem() instanceof ItemFirearm)
		{
			isCarryingLargeFirearm = ((ItemFirearm)itemstackOther.getItem()).getFirearmSize() == FirearmSize.LARGE;
		}
		
		return isCarryingLargeFirearm;
	}
	
	private boolean hasLoadedFirearmInOtherHand(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		boolean isLoadedFirearmInOtherHand = false;
		
		/* Item in other hand. */
		ItemStack itemstackOther = playerIn.getHeldItem((handIn == EnumHand.MAIN_HAND) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
		
		/* If item in other hand is instance of ItemFirearm, return true if firearm is large size. */
		if (itemstackOther.getItem() instanceof ItemFirearm)
		{
			isLoadedFirearmInOtherHand = (FirearmNBTHelper.peekNBTTagAmmoCount(itemstackOther) > 0);
		}
		
		return isLoadedFirearmInOtherHand;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		/* Check if this firearm is a breechloader and has a valid reloading recipe. */
		boolean isReloadableBreechloader = (getReloadType() == FirearmReloadType.BREECHLOADER && 
				!FirearmRecipeHelper.getBreechloadingReloadRecipes(itemstack).isEmpty());
		boolean canReloadBreechloader = false;
		if (isReloadableBreechloader)
		{
			/* Allow action to continue on the first reload recipe that is valid for the player's inventory. */
			for (BreechloadingReloadRecipe recipe : FirearmRecipeHelper.getBreechloadingReloadRecipes(itemstack))
			{
				canReloadBreechloader = recipe.isValid(playerIn.inventory);
				if (canReloadBreechloader)
					break;
			}
		}
		
		/* Only allow firing if no cooldown and ammo loaded. */
        boolean readyToFire = (playerIn.getCooledAttackStrength(0f) >= 0.99f);
		boolean hasAmmoLoaded = (FirearmNBTHelper.peekNBTTagAmmoCount(itemstack) > 0);
		boolean notBroken = FirearmNBTHelper.getNBTTagCondition(itemstack) != FirearmCondition.BROKEN;
		
		boolean holdingLargeFirearmOffhand = isCarryingLargeFirearmInOtherHand(worldIn, playerIn, handIn);
		/* Need this to allow firing of an offhand firearm. */
		boolean holdingLoadedFirearmOffhand = hasLoadedFirearmInOtherHand(worldIn, playerIn, handIn);
		boolean canAim = (hasAmmoLoaded && readyToFire && notBroken && !holdingLargeFirearmOffhand);
		boolean canReload = (!hasAmmoLoaded && canReloadBreechloader && !holdingLoadedFirearmOffhand && readyToFire && notBroken && !holdingLargeFirearmOffhand);
		
		if (!worldIn.isRemote) {
			OldGuns.logger.debug("itemstack                : " + itemstack);
			OldGuns.logger.debug("isReloadableBreechloader : " + isReloadableBreechloader);
			OldGuns.logger.debug("canAim                   : " + canAim);
			OldGuns.logger.debug("canReload                : " + canReload);
		}
		
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, canAim);
		if (ret != null) return ret;
		
		if (!canAim && !canReload)
		{
			/* Play empty sound if no ammo, or broken soundif broken. */
			if (!notBroken)
			{
				playerIn.playSound(SoundEvents.BLOCK_LEVER_CLICK, 0.25F, 0.7F / (Item.itemRand.nextFloat() * 0.2F + 0.9F));
				playerIn.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.10F, 0.7F / (Item.itemRand.nextFloat() * 0.2F + 0.9F));
			}
			else if (!hasAmmoLoaded)
			{
                playerIn.playSound(SoundEvents.BLOCK_LEVER_CLICK, 0.25F, 0.7F / (Item.itemRand.nextFloat() * 0.2F + 0.9F));		 
			}
			
		    return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}
		else if (canAim)
		{
		    playerIn.setActiveHand(handIn);
		    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}
		else 
		{
			 playerIn.setActiveHand(handIn);
			 return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
		}
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
        return !oldStack.equals(newStack); //!ItemStack.areItemStacksEqual(oldStack, newStack);
    }
	
	@Override
	public int getItemEnchantability()
	{
		// TODO Auto-generated method stub
		return super.getItemEnchantability();
	}
	
	/**
	 * Does NBT stuff for firearm conditions, and will cause certain things to happen based on said conditions.
	 * @param worldIn
	 * @param entityShooter
	 * @param stackIn
	 * @return 
	 */
	protected boolean checkConditionForEffect(World worldIn, Entity shooter, ItemStack stackIn)
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
			case POOR:
				misfireWaterChance += 0.25f;
			case FAIR:
				misfireWaterChance += 0.25f;
			default:
				break;
		}
		
		/* Check for explosion, and then for misfire if no explosion. */
		if (worldIn.rand.nextFloat() < explosionChance)
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
			float handX = -MathHelper.sin(((shooter.rotationYaw + 23) / 180F) * 3.141593F) * MathHelper.cos((shooter.rotationPitch / 180F) * 3.141593F);
			float handY = -MathHelper.sin((shooter.rotationPitch / 180F) * 3.141593F) - 0.1F;
			float handZ = MathHelper.cos(((shooter.rotationYaw + 23) / 180F) * 3.141593F) * MathHelper.cos((shooter.rotationPitch / 180F) * 3.141593F);
			
			/* Calculate the actual position of the particles. */
			double expX = shooter.posX + handX;
			double expY = shooter.posY + shooter.getEyeHeight() + handY;
			double expZ = shooter.posZ + handZ;
			
			/* Explode for real. */
			worldIn.newExplosion((Entity)null, expX, expY, expZ, strength, false, false);
			
			/* Empty firearm and set damage to max. */
            FirearmNBTHelper.emptyNBTTagAmmo(stackIn);
            stackIn.setItemDamage(stackIn.getMaxDamage());
            
            NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(
					shooter.dimension, shooter.posX, shooter.posY, shooter.posZ, 64d);
			
			OldGuns.network.sendToAllAround(
					new MessageFirearmEffect((EntityLivingBase)shooter, FirearmEffect.BREAK, shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ,
							shooter.rotationPitch, shooter.rotationYaw, ((EntityPlayer)shooter).getActiveHand().ordinal()),
					point
					);
		}
		else if (worldIn.rand.nextFloat() < misfireChance)
		{
			/* Misfire. */
			failure = true;
			
			NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(
					shooter.dimension, shooter.posX, shooter.posY, shooter.posZ, 64d);
			
			OldGuns.network.sendToAllAround(
					new MessageFirearmEffect((EntityLivingBase)shooter, FirearmEffect.MISFIRE, shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ,
							shooter.rotationPitch, shooter.rotationYaw, ((EntityPlayer)shooter).getActiveHand().ordinal()),
					point
					);
		}
		else if (this.getFirearmWaterResiliency() != FirearmWaterResiliency.GOOD)
		{
			/* Check for rain or water, if firearm is vulnerable to it. */
			
			boolean misfire = false;
			
			/* If poor water resiliency and wet or submerged, misfire. */
			if (shooter.isWet() && !shooter.isInWater())
			{
				misfire = ((worldIn.rand.nextFloat() < misfireWaterChance) ||
						(this.getFirearmWaterResiliency() == FirearmWaterResiliency.POOR)) ? true : false;
			}
			else if (shooter.isInWater())
			{
				misfire = true;
			}
			
			/* Misfire and flag as failure if needed. */
			if (misfire)
			{
				failure = true;
				
				NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(
						shooter.dimension, shooter.posX, shooter.posY, shooter.posZ, 64d);
				
				OldGuns.network.sendToAllAround(
						new MessageFirearmEffect((EntityLivingBase)shooter, FirearmEffect.MISFIRE_WET, shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ,
								shooter.rotationPitch, shooter.rotationYaw, ((EntityPlayer)shooter).getActiveHand().ordinal()),
						point
						);
			}
		}
		
		return failure;
	}
	
	public int getAmmoCapacity()
	{
		return AmmoCapacity;
	}

	public void setAmmoCapacity(int ammoCapacity)
	{
		AmmoCapacity = ammoCapacity;
	}

	public float getProjectileSpeed()
	{
		return ProjectileSpeed;
	}

	public void setProjectileSpeed(float projectileSpeed)
	{
		ProjectileSpeed = projectileSpeed;
	}

	public float getEffectiveRangeModifier()
	{
		return EffectiveRange;
	}

	public void setEffectiveRangeModifier(float effectiveRange)
	{
		EffectiveRange = effectiveRange;
	}

	public float getFirearmDeviation()
	{
		return this.deviationModifier;
	}

	public void setFirearmDeviation(float deviationModifier)
	{
		this.deviationModifier = deviationModifier;
	}
	
	public float getDamageModifier()
	{
		return this.damageModifier;
	}

	public void setDamageModifier(float damageModifier)
	{
		this.damageModifier = damageModifier;
	}
	
	public int getRequiredReloadTicks()
	{
		return this.requiredReloadTicks;
	}

	public void setRequiredReloadTicks(int requiredReloadTicks)
	{
		this.requiredReloadTicks = requiredReloadTicks;
	}
	
	public FirearmReloadType getReloadType()
	{
		return this.reloadType;
	}

	public void setReloadType(FirearmReloadType reloadType)
	{
		this.reloadType = reloadType;
	}
	
	public FirearmSize getFirearmSize()
	{
		return this.firearmSize;
	}

	public void setFirearmSize(FirearmSize size) 
	{
		this.firearmSize = size;
	}
	
	public FirearmWaterResiliency getFirearmWaterResiliency()
	{
		return this.firearmWaterResiliency;
	}
	
	public void setFirearmWaterResiliency(FirearmWaterResiliency waterResiliency) 
	{
		this.firearmWaterResiliency = waterResiliency;
	}
}
