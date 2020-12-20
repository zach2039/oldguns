package zach2039.oldguns.common.item.firearm;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
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
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.entity.EntityProjectile;
import zach2039.oldguns.common.init.ModItems;
import zach2039.oldguns.common.item.ammo.ItemFirearmAmmo;
import zach2039.oldguns.common.item.util.FirearmNBTHelper;
import zach2039.oldguns.common.item.util.FirearmTooltipHelper;
import zach2039.oldguns.common.item.util.FirearmType.FirearmCondition;
import zach2039.oldguns.common.item.util.FirearmType.FirearmEffect;
import zach2039.oldguns.common.item.util.FirearmType.FirearmReloadType;
import zach2039.oldguns.common.item.util.FirearmType.FirearmSize;
import zach2039.oldguns.common.item.util.FirearmType.FirearmWaterResiliency;
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
	 * Reload type of the firearm.
	 */
	protected FirearmReloadType ReloadType = FirearmReloadType.MUZZLELOADER;
	
	public ItemFirearm(String name)
	{
		setRegistryName(OldGuns.MODID, name);
		setUnlocalizedName(name);
		setCreativeTab(CreativeTabs.COMBAT);
		setNoRepair();
        addPropertyOverride(new ResourceLocation(OldGuns.MODID, "empty"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
            	if (entityIn == null) return 0.0F;
            	if (stack.isEmpty() || !(stack.getItem() instanceof ItemFirearm)) return 0.0F;
                return (FirearmNBTHelper.peekNBTTagAmmoCount(stack) > 0) ? 0.0F : 1.0F;
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
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
	{
		if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            //boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = FirearmNBTHelper.peekNBTTagAmmo(stack);

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, !itemstack.isEmpty());
            if (i < 0) return;

            /* Calculate deviation multiplier for shot, based on charge time. */
            float deviationMulti = (i < 5) ? 3.0f : ((i < 10) ? 2.0f : 1.0f); 
            
            if (!itemstack.isEmpty())
            {
                float f = getProjectileSpeed();
                
                boolean flag1 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow && ((ItemArrow) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer));

                if (!worldIn.isRemote)
                {
                	/* Calculate stuff based on firearm condition. */
                    boolean failure = checkConditionForEffect(worldIn, entityplayer, stack);
        	            
        	        //OldGuns.logger.info(String.format("Failure: %d", failure ? 1 : 0));
        	            
                    /* If firearm broke or misfired, do nothing. */
                    if (failure)
                    {
                    	/* Refresh condition. */
                        FirearmNBTHelper.refreshFirearmCondition(stack);
                    	return;
                    }
                    
                    ItemFirearmAmmo itemFirearmAmmo = (ItemFirearmAmmo)(itemstack.getItem() instanceof ItemFirearmAmmo ? itemstack.getItem() : ModItems.SMALL_IRON_MUSKET_BALL);
                    List<EntityProjectile> entityProjectiles = itemFirearmAmmo.createProjectiles(worldIn, itemstack, entityplayer);
                    
                    /* Fire all projectiles from ammo item. */
                    entityProjectiles.forEach((t) ->
                    {
                    	/* Set location-based data. */
                    	t.setEffectiveRange(getEffectiveRange());
                    	t.setLaunchLocation(t.getPosition());
                    	
                    	/* Launch projectile. */
                    	t.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f, deviationMulti * 5.0F);
                    	
                    	int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                        if (j > 0)
                        {
                            t.setDamage(t.getDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                        if (k > 0)
                        {
                            t.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
                        {
                            t.setFire(100);
                        }
                        
                        worldIn.spawnEntity(t);
                    });
                    
                    stack.damageItem(1, entityplayer);
                    
                    /* Do firing effects. */
                    doFiringEffect(worldIn, entityplayer, stack);        
                    
                    if (!flag1 && !entityplayer.capabilities.isCreativeMode && (stack != null))
                    {
                    	/* Remove ammo from firearm. */
                        FirearmNBTHelper.popNBTTagAmmo(stack);
                    }
                }

                /* Refresh condition. */
                FirearmNBTHelper.refreshFirearmCondition(stack);
                
                entityplayer.addStat(StatList.getObjectUseStats(this));
            }
        }
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		// TODO Auto-generated method stub
		return super.getMaxItemUseDuration(stack);
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
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		/* Only allow firing if no cooldown and ammo loaded. */
        boolean readyToFire = (playerIn.getCooledAttackStrength(0f) >= 0.99f);
		boolean hasAmmo = (FirearmNBTHelper.peekNBTTagAmmoCount(itemstack) > 0);
		boolean notBroken = FirearmNBTHelper.getNBTTagCondition(itemstack) != FirearmCondition.BROKEN;
		boolean canAim = (hasAmmo && readyToFire && notBroken && !isCarryingLargeFirearmInOtherHand(worldIn, playerIn, handIn));
		
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, canAim);
		if (ret != null) return ret;
		
		if (!canAim)
		{
			/* Play empty sound if no ammo, or broken soundif broken. */
			if (!notBroken)
			{
				playerIn.playSound(SoundEvents.BLOCK_LEVER_CLICK, 0.25F, 0.7F / (Item.itemRand.nextFloat() * 0.2F + 0.9F));
				playerIn.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.10F, 0.7F / (Item.itemRand.nextFloat() * 0.2F + 0.9F));
			}
			else if (!hasAmmo)
                playerIn.playSound(SoundEvents.BLOCK_LEVER_CLICK, 0.25F, 0.7F / (Item.itemRand.nextFloat() * 0.2F + 0.9F));		 
			
		    return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}
		else
		{
		    playerIn.setActiveHand(handIn);
		    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
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

	public float getEffectiveRange()
	{
		return EffectiveRange;
	}

	public void setEffectiveRange(float effectiveRange)
	{
		EffectiveRange = effectiveRange;
	}

	public FirearmReloadType getReloadType()
	{
		return ReloadType;
	}

	public void setReloadType(FirearmReloadType reloadType)
	{
		ReloadType = reloadType;
	}
}
