package com.zach2039.oldguns.world.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.ProjectileType;
import com.zach2039.oldguns.init.ModEntities;
import com.zach2039.oldguns.init.ModSoundEvents;
import com.zach2039.oldguns.world.damagesource.OldGunsDamageSourceIndirectEntity;
import com.zach2039.oldguns.world.damagesource.OldGunsDamageSources;
import com.zach2039.oldguns.world.damagesource.OldGunsDamageTypes;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BulletProjectile extends Projectile implements IEntityAdditionalSpawnData {

	protected static final EntityDataAccessor<Float> PROJECTILE_SIZE = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<BlockPos> START_POS = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.BLOCK_POS);
	protected static final EntityDataAccessor<Float> EFFECTIVE_RANGE = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<CompoundTag> SHOOTING_ENTITY = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.COMPOUND_TAG);
	protected static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Float> BYPASS_ARMOR_PERCENTAGE = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Byte> CRITICAL = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.BYTE);
	protected static final EntityDataAccessor<Integer> ID_EFFECT_COLOR = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.BYTE);
	protected static final EntityDataAccessor<Byte> PIERCE_LEVEL = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.BYTE);

	protected static final EntityDataAccessor<Integer> PROJECTILE_TYPE = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Float> EFFECT_STRENGTH = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Integer> EFFECT_TICKS = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.INT);

	@Nullable
	protected UUID ownerUUID;
	@Nullable
	protected Entity cachedOwner;
	protected boolean leftOwner;
	protected boolean hasBeenShot;

	@Nullable
	protected BlockState lastState;
	public AbstractArrow.Pickup pickup = AbstractArrow.Pickup.DISALLOWED;
	protected int life;
	protected int knockback;
	protected SoundEvent soundEvent = ModSoundEvents.BULLET_HIT_BLOCK.get();
	@Nullable
	protected IntOpenHashSet piercingIgnoreEntityIds;
	@Nullable
	protected List<Entity> piercedAndKilledEntities;

	//private static final byte EVENT_POTION_PUFF = 0;
	protected Potion potion = Potions.EMPTY;
	protected final Set<MobEffectInstance> effects = Sets.newHashSet();
	protected boolean fixedColor;
	protected int totalInGroundTime;
	protected boolean inGround;
	protected int inGroundTime;

	// Set true when projectile is used to simulate trajectories on the client
	public boolean isSimulated = false;
	
	public BulletProjectile(EntityType<? extends BulletProjectile> entityType, Level world) {
		super(entityType, world);
		this.pickup = Pickup.DISALLOWED;
		this.totalInGroundTime = 0;
	}

	public BulletProjectile(Level world, double x, double y, double z) {
		this(ModEntities.BULLET_PROJECTILE.get(), world);
		this.pickup = Pickup.DISALLOWED;
		this.moveTo(x, y, z, this.xRotO, this.yRotO);
		this.setPos(x, y, z);
	}

	public BulletProjectile(Level world, LivingEntity shooter) {
		this(world, shooter.getX(), shooter.getEyeY() - (double)0.1F, shooter.getZ());		
		this.setOwner(shooter);
	}

	public BulletProjectile(Level world, LivingEntity shooter, double x, double y, double z) {
		this(world, x, y, z);		
		this.setOwner(shooter);
	}

	@Override
	public EntityType<?> getType() {
		return ModEntities.BULLET_PROJECTILE.get();
	}

	@Override
	public boolean canHitEntity(Entity ent) {
		boolean canHit = false;
		if (!ent.isSpectator() && ent.isAlive() && ent.isPickable()) {
			Entity entity = this.getOwner();
			if (this.getDamageType() == OldGunsDamageTypes.ARTILLERY) {
				canHit = true;
			} else {
				canHit = entity == null || this.leftOwner || !entity.isPassengerOfSameVehicle(ent);
			}
		}
		
		return canHit && (this.piercingIgnoreEntityIds == null || !this.piercingIgnoreEntityIds.contains(ent.getId()));
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void writeSpawnData(FriendlyByteBuf buf) {
		final Entity shooter = getOwner();
		buf.writeVarInt(shooter == null ? 0 : shooter.getId());
	}

	@Override
	public void readSpawnData(FriendlyByteBuf buf) {
		final Entity shooter = level().getEntity(buf.readVarInt());
		if (shooter != null)
			setOwner(shooter);
	}

	public boolean isCritArrow() {
		byte b0 = this.entityData.get(CRITICAL);
		return (b0 & 1) != 0;
	}

	@Override
	protected void defineSynchedData() {	
		this.entityData.define(ID_FLAGS, (byte)0);

		this.entityData.define(PIERCE_LEVEL, (byte)0);

		this.entityData.define(ID_EFFECT_COLOR, -1);

		/* Register spawn coordinates to datamanager for later damage falloff calculation. */
		this.entityData.define(START_POS, this.blockPosition());

		/* Register effective range storage for damage calculation. */
		this.entityData.define(EFFECTIVE_RANGE, 1f);

		/* Register storage for shooting entity reference. */
		this.entityData.define(SHOOTING_ENTITY, new CompoundTag());

		/* Register storage for damage. */
		this.entityData.define(DAMAGE, 0.0f);
		
		/* Register storage for percentage of damage that bypasses armor. */
		this.entityData.define(BYPASS_ARMOR_PERCENTAGE, 0.0f);

		/* Register projectile size for rendering. */
		this.entityData.define(PROJECTILE_SIZE, 1f);

		/* Register CRITICAL flag for critical damage application. */
		this.entityData.define(CRITICAL, (byte)0);

		/* Register projectile type; used for effects like explosions and other things */
		this.entityData.define(PROJECTILE_TYPE, ProjectileType.MUSKET_BALL.ordinal());

		/* Register effect strength; used for effect potency for certain ammo types */
		this.entityData.define(EFFECT_STRENGTH, 0.0f);

		/* Register effect ticks; used to determine length of persistent effects */
		this.entityData.define(EFFECT_TICKS, 0);
	}

	public void setPierceLevel(byte p_36768_) {
		this.entityData.set(PIERCE_LEVEL, p_36768_);
	}

	public void setCritArrow(boolean p_36763_) {
		this.setFlag(1, p_36763_);
	}

	private void setFlag(int p_36738_, boolean p_36739_) {
		byte b0 = this.entityData.get(ID_FLAGS);
		if (p_36739_) {
			this.entityData.set(ID_FLAGS, (byte)(b0 | p_36738_));
		} else {
			this.entityData.set(ID_FLAGS, (byte)(b0 & ~p_36738_));
		}
	}

	public byte getPierceLevel() {
		return this.entityData.get(PIERCE_LEVEL);
	}	

	public int getColor() {
		return this.entityData.get(ID_EFFECT_COLOR);
	}

	private void setFixedColor(int color) {
		this.fixedColor = true;
		this.entityData.set(ID_EFFECT_COLOR, color);
	}

	private void updateColor() {
		this.fixedColor = false;
		if (this.potion == Potions.EMPTY && this.effects.isEmpty()) {
			this.entityData.set(ID_EFFECT_COLOR, -1);
		} else {
			this.entityData.set(ID_EFFECT_COLOR, PotionUtils.getColor(PotionUtils.getAllEffects(this.potion, this.effects)));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		if (this.ownerUUID != null) {
			compound.putUUID("Owner", this.ownerUUID);
		}

		if (this.leftOwner) {
			compound.putBoolean("LeftOwner", true);
		}

		compound.putBoolean("HasBeenShot", this.hasBeenShot);

		if (this.potion != Potions.EMPTY) {
			compound.putString("Potion", BuiltInRegistries.POTION.getKey(this.potion).toString());
		}

		if (this.fixedColor) {
			compound.putInt("Color", this.getColor());
		}

		if (!this.effects.isEmpty()) {
			ListTag listtag = new ListTag();

			for(MobEffectInstance mobeffectinstance : this.effects) {
				listtag.add(mobeffectinstance.save(new CompoundTag()));
			}

			compound.put("CustomPotionEffects", listtag);
		}

		compound.putShort("life", (short)this.life);
		if (this.lastState != null) {
			compound.put("inBlockState", NbtUtils.writeBlockState(this.lastState));
		}

		compound.putBoolean("inGround", this.inGround);
		compound.putInt("totalInGroundTime", this.totalInGroundTime);
		compound.putByte("pickup", (byte)this.pickup.ordinal());
		compound.putDouble("damage", this.getDamage());
		compound.putFloat("bypassArmorPercentage", this.getBypassArmorPercentage());
		compound.putBoolean("crit", this.isCritArrow());
		compound.putByte("PierceLevel", this.getPierceLevel());
		compound.putString("SoundEvent", BuiltInRegistries.SOUND_EVENT.getKey(this.soundEvent).toString());
		compound.putFloat("projectileSize", getProjectileSize());
		compound.putFloat("effectStrength", getEffectStrength());
		compound.putInt("effectTicks", getEffectTicks());
	}

	public void addEffect(MobEffectInstance pEffect) {
		this.effects.add(pEffect);
		this.getEntityData().set(ID_EFFECT_COLOR, PotionUtils.getColor(PotionUtils.getAllEffects(this.potion, this.effects)));
	}
	
	public void setKnockback(int knockbackStrength) {
		this.knockback = knockbackStrength;
	}
	
	protected SoundEvent getDefaultHitGroundSoundEvent() {
		return ModSoundEvents.BULLET_HIT_BLOCK.get();
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		if (compound.hasUUID("Owner")) {
			this.ownerUUID = compound.getUUID("Owner");
		}

		this.leftOwner = compound.getBoolean("LeftOwner");
		this.hasBeenShot = compound.getBoolean("HasBeenShot");


		if (compound.contains("Potion", 8)) {
			this.potion = PotionUtils.getPotion(compound);
		}

		for(MobEffectInstance mobeffectinstance : PotionUtils.getCustomEffects(compound)) {
			this.addEffect(mobeffectinstance);
		}

		if (compound.contains("Color", 99)) {
			this.setFixedColor(compound.getInt("Color"));
		} else {
			this.updateColor();
		}

		this.life = compound.getShort("life");
		if (compound.contains("inBlockState", Tag.TAG_COMPOUND)) {
			this.lastState = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), compound.getCompound("inBlockState"));
		}

		this.inGround = compound.getBoolean("inGround");
		this.totalInGroundTime = compound.getInt("totalInGroundTime");
		if (compound.contains("damage", Tag.TAG_DOUBLE)) {
			this.setDamage(compound.getDouble("damage"));
		}
		if (compound.contains("bypassArmorPercentage", Tag.TAG_FLOAT)) {
			this.setBypassArmorPercentage(compound.getFloat("bypassArmorPercentage"));
		}

		this.pickup = AbstractArrow.Pickup.byOrdinal(compound.getByte("pickup"));
		this.setCritArrow(compound.getBoolean("crit"));
		this.setPierceLevel(compound.getByte("PierceLevel"));
		if (compound.contains("SoundEvent", Tag.TAG_STRING)) {
			this.soundEvent = BuiltInRegistries.SOUND_EVENT.getOptional(new ResourceLocation(compound.getString("SoundEvent"))).orElse(this.getDefaultHitGroundSoundEvent());
		}

		setProjectileSize(compound.getFloat("projectileSize"));

		setEffectStrength(compound.getFloat("effectStrength"));

		setEffectTicks(compound.getInt("effectTicks"));
	}

	protected boolean checkLeftOwner() {
		Entity entity = this.getOwner();
		if (entity != null) {
			for(Entity entity1 : this.level().getEntities(this, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), (ent) -> {
				return !ent.isSpectator() && ent.isPickable();
			})) {
				if (entity1.getRootVehicle() == entity.getRootVehicle()) {
					return false;
				}
			}
		}

		return true;
	}

	protected boolean shouldFall() {
		return this.inGround && this.level().noCollision((new AABB(this.position(), this.position())).inflate(0.06D));
	}

	protected void startFalling() {
		this.inGround = false;
		Vec3 vec3 = this.getDeltaMovement();
		this.setDeltaMovement(vec3.multiply((double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F)));
	}

	/**
	 * Returns true if projectile's current location is within the specified range.
	 * @return
	 */
	protected boolean isInsideEffectiveRange()
	{
		/* Get effective range. */
		float effectiveRange = getEffectiveRange();

		/* Get launch position. */
		BlockPos launch = getLaunchLocation();

		/* Get current position. */
		BlockPos current = blockPosition();

		/* Get distance between launch and current position. */
		double distance = current.distManhattan(launch);

		/* Return based on distance value. */
		return (distance < effectiveRange) ? true : false;
	}

	protected ResourceKey<DamageType> getDamageType() {
		if (
				this.getProjectileType() == ProjectileType.CANNONBALL ||
				this.getProjectileType() == ProjectileType.CANISTER ||
				this.getProjectileType() == ProjectileType.GRAPESHOT ||
				this.getProjectileType() == ProjectileType.CARCASS ||
				this.getProjectileType() == ProjectileType.EXPLOSIVE_SHELL
				) {

			return OldGunsDamageTypes.ARTILLERY;
		}

		return OldGunsDamageTypes.FIREARM;
	}

	protected void doPostHurtEffects(LivingEntity pLiving) {
		Entity entity = this.getEffectSource();

		for(MobEffectInstance mobeffectinstance : this.potion.getEffects()) {
			pLiving.addEffect(new MobEffectInstance(mobeffectinstance.getEffect(), Math.max(mobeffectinstance.getDuration() / 8, 1), mobeffectinstance.getAmplifier(), mobeffectinstance.isAmbient(), mobeffectinstance.isVisible()), entity);
		}

		if (!this.effects.isEmpty()) {
			for(MobEffectInstance mobeffectinstance1 : this.effects) {
				pLiving.addEffect(mobeffectinstance1, entity);
			}
		}

	}
	
	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity entity = result.getEntity();

		int i = Mth.ceil(this.getDamage());

		/* Cut damage in half if outside effective range or low speed. */
		if ((this.getVelocityMagnitude() < 0.5f) || (this.totalInGroundTime > 0))
		{
			i = i / 4;
		}
		else if (!this.isInsideEffectiveRange())
		{
			i = i / 2;
		}

		if (this.getPierceLevel() > 0) {
			if (this.piercingIgnoreEntityIds == null) {
				this.piercingIgnoreEntityIds = new IntOpenHashSet(5);
			}

			if (this.piercedAndKilledEntities == null) {
				this.piercedAndKilledEntities = Lists.newArrayListWithCapacity(5);
			}

			if (this.piercingIgnoreEntityIds.size() >= this.getPierceLevel() + 1) {
				this.discard();
				return;
			}

			this.piercingIgnoreEntityIds.add(entity.getId());
		}

		if (this.isCritArrow()) {
			long j = (long)this.random.nextInt(i / 2 + 2);
			i = (int)Math.min(j + (long)i, 2147483647L);
		}

		Entity entity1 = this.getOwner();
		OldGunsDamageSourceIndirectEntity damagesource;
		if (entity1 == null) {
			damagesource = (OldGunsDamageSourceIndirectEntity) OldGunsDamageSources.projectile(this.getDamageType(), this, null, this.getBypassArmorPercentage());
		} else {
			damagesource = (OldGunsDamageSourceIndirectEntity) OldGunsDamageSources.projectile(this.getDamageType(), this, entity1, this.getBypassArmorPercentage());
			
			if (entity1 instanceof LivingEntity) {
				((LivingEntity)entity1).setLastHurtMob(entity);
			}
		}
		
		boolean isEnderman = entity.getType() == EntityType.ENDERMAN;
		int k = entity.getRemainingFireTicks();
		if (this.isOnFire() && !isEnderman) {
			entity.setSecondsOnFire(5);
		}		
	
		float armorBypassPercent = damagesource.getPercentBypassArmor(); 
		int damageNoBypass = Mth.ceil(i * (float)(1 - armorBypassPercent));
		int damageBypass = Mth.ceil(i * (float)(armorBypassPercent));
		
		// What the heck is going on here
		if (entity.hurt(damagesource, (float)damageNoBypass) || (damageBypass > 0f)) {
			if (isEnderman) {
				return;
			}
			
			// Reset hurt timer for some double damage type trickery
			entity.invulnerableTime = 0;
			//entity.hurt(damagesource.bypassArmor(), (float)damageBypass);
			entity.hurt(damagesource, (float)damageBypass);
			
			if (entity instanceof LivingEntity) {
				LivingEntity livingentity = (LivingEntity)entity;
				if (!this.level().isClientSide && this.getPierceLevel() <= 0) {
					//livingentity.setArrowCount(livingentity.getArrowCount() + 1);
				}

				if (this.knockback > 0) {
					Vec3 vec3 = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)this.knockback * 0.6D);
					if (vec3.lengthSqr() > 0.0D) {
						livingentity.push(vec3.x, 0.1D, vec3.z);
					}
				}

				if (!this.level().isClientSide && entity1 instanceof LivingEntity) {
					EnchantmentHelper.doPostHurtEffects(livingentity, entity1);
					EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity);
				}

				this.doPostHurtEffects(livingentity);
				if (entity1 != null && livingentity != entity1 && livingentity instanceof Player && entity1 instanceof ServerPlayer && !this.isSilent()) {
					((ServerPlayer)entity1).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
				}

				if (!entity.isAlive() && this.piercedAndKilledEntities != null) {
					this.piercedAndKilledEntities.add(livingentity);
				}

				// Reset inv time to allow multiple pellets to damage entities
				entity.invulnerableTime = 0;
			}

			doEffectOnEntityHit(entity);

			this.playSound(ModSoundEvents.BULLET_HIT_MOB.get(), 0.3F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
			if (this.getPierceLevel() <= 0) {
				this.discard();
			}
		} else {
			entity.setRemainingFireTicks(k);
			
			if (!this.level().isClientSide) {
				this.discard();
			}
		}
	}

	private void resetPiercedEntities() {
		if (this.piercedAndKilledEntities != null) {
			this.piercedAndKilledEntities.clear();
		}

		if (this.piercingIgnoreEntityIds != null) {
			this.piercingIgnoreEntityIds.clear();
		}
	}

	/**
	 * Gets the magnitude of the current velocity.
	 * @return
	 */
	public double getVelocityMagnitude()
	{
		return Math.sqrt(Math.pow(this.getDeltaMovement().x, 2) + Math.pow(this.getDeltaMovement().y, 2) + Math.pow(this.getDeltaMovement().z, 2));
	}

	protected boolean allowBlockHitSlowdown() {
		if (this.getProjectileType() == ProjectileType.CANNONBALL && this.getEffectStrength() > 0.1f)
		{
			return false;
		}
		return true;
	}

	protected boolean canCollideWithBlockState(BlockState blockstate)
	{
		if (!blockstate.isAir())
			return true;

		return false;
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		this.lastState = this.level().getBlockState(result.getBlockPos());
		BlockState blockstate = this.level().getBlockState(result.getBlockPos());
		Vec3i hitNormal = result.getDirection().getNormal();
		boolean isShallowAngle = Mth.abs((float) this.getDeltaMovement().normalize().dot(new Vec3(hitNormal.getX(), hitNormal.getY(), hitNormal.getZ()).normalize())) < 0.4;
		boolean ricochet = result.getDirection().getAxis().isVertical() && (isShallowAngle) && (this.getVelocityMagnitude() > 1f);
		
		// Allow ricochet for projectiles
		//		OldGuns.LOGGER.info("ricochet: " + ricochet);
		//		OldGuns.LOGGER.info("vertical: " + result.getDirection().getAxis().isVertical());
		//		OldGuns.LOGGER.info("math: " + Mth.abs((float) this.getDeltaMovement().normalize().dot(new Vec3(hitNormal.getX(), hitNormal.getY(), hitNormal.getZ()).normalize())));
		//		OldGuns.LOGGER.info("velMag: " + (this.getVelocityMagnitude() > 1f));

		doEffectOnBlockHit(result.getBlockPos());

		if (!ricochet) {
			if (this.allowBlockHitSlowdown()) {
				Vec3 vec3 = result.getLocation().subtract(this.getX(), this.getY(), this.getZ());
				this.setDeltaMovement(vec3);
				Vec3 vec31 = vec3.normalize().scale((double)0.05F);
				this.setPosRaw(this.getX() - vec31.x, this.getY() - vec31.y, this.getZ() - vec31.z);

			} else {
				this.setDeltaMovement(this.getDeltaMovement().scale(0.5f));
			}

			if (canCollideWithBlockState(blockstate)) {
				blockstate.onProjectileHit(this.level(), blockstate, result, this);
				if (!this.isInWater() && this.getVelocityMagnitude() > 1.0f) {
					this.playSound(ModSoundEvents.BULLET_HIT_BLOCK.get(), 0.7F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
				} else {
					this.playSound(SoundEvents.ANVIL_HIT, 0.7F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
				}
				this.inGround = true;
				this.setCritArrow(false);
				this.setPierceLevel((byte)0);
				this.resetPiercedEntities();
			}
		} else {			
			this.inGround = false;
			double xMotMod = 0.70f + (this.random.nextFloat() * 0.15f);
			double yMotMod = -0.70f;
			double zMotMod = 0.70f + (this.random.nextFloat() * 0.15f);
			this.setDeltaMovement(this.getDeltaMovement().multiply(xMotMod, yMotMod, zMotMod));

			if (!this.isSimulated) {
				for(int i = 0; i < 3; ++i) {
					BlockParticleOption blockPart = new BlockParticleOption(ParticleTypes.BLOCK, blockstate);
					this.level().addParticle(blockPart,
							this.xo - this.getDeltaMovement().x * 0.25D, this.yo - this.getDeltaMovement().y * 0.25, this.zo - this.getDeltaMovement().z * 0.25,
							0, 0, 0);
				}
	
				this.playSound(ModSoundEvents.BULLET_RICOCHET.get(), 0.3F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
			}

			// Add to totalInGroundTime to reduce damage after ricochet
			++this.totalInGroundTime;
		}
	}

	@Override
	protected void onHit(HitResult result) {
		HitResult.Type hitresult$type = result.getType();
		if (hitresult$type == HitResult.Type.ENTITY) {
			this.onHitEntity((EntityHitResult)result);
		} else if (hitresult$type == HitResult.Type.BLOCK) {
			this.onHitBlock((BlockHitResult)result);
		}

		if (hitresult$type != HitResult.Type.MISS) {
			this.gameEvent(GameEvent.PROJECTILE_LAND, this.getOwner());
		}
	}

	protected void doEffectOnTick() {
		switch (getProjectileType()) {
			case CANISTER:
				if (getEffectTicks() <= 0) {
					if (getEffectStrength() > 0.0f && !level().isClientSide()) {
						level().explode(this, this.getX(), this.getY(), this.getZ(), getEffectStrength() / 4, Level.ExplosionInteraction.NONE);
						for (int i = 0; i < Math.round(getEffectStrength()) + 1; i++) {
							// Do canister shot explosion after ticks required
							
							BulletProjectile entityBullet = new BulletProjectile(level(), xo, yo, zo);
							entityBullet.setDamage(getDamage());
							entityBullet.setProjectileSize(0.3f);
							entityBullet.setProjectileType(ProjectileType.BUCKSHOT);
							entityBullet.setEffectiveRange(20f);
							entityBullet.shoot(
									xo, yo, zo, 
									Mth.randomBetween(random, 0f, (this.inGround) ? 360f : 120f), Mth.randomBetween(random, 0f, 360f), 
									0.0F, 3f, 5f);
							
							level().addFreshEntity(entityBullet);
						}
					}
					discard();
				} else {
					if (!level().isClientSide())
						setEffectTicks(getEffectTicks() - 1);
				}
				break;
			default:
				break;
		}
	}
	
	protected void doEffectOnEntityHit(Entity entity) {
		switch (getProjectileType()) {
			case EXPLOSIVE_SHELL:
				if (getEffectStrength() > 0.0f)
				{
					if (!level().isClientSide()) {
						level().explode(this, entity.getX(), entity.getY(), entity.getZ(), getEffectStrength(), Level.ExplosionInteraction.BLOCK);
					}
					discard();
				}
				break;
			case CANISTER:
				if (getEffectStrength() > 0.0f && !level().isClientSide()) {
					level().explode(this, this.getX(), this.getY(), this.getZ(), getEffectStrength() / 4, Level.ExplosionInteraction.NONE);
					for (int i = 0; i < Math.round(getEffectStrength()) + 1; i++) {
						// Do canister shot explosion after ticks required
						
						BulletProjectile entityBullet = new BulletProjectile(level(), xo, yo, zo);
						entityBullet.setDamage(getDamage());
						entityBullet.setProjectileSize(0.3f);
						entityBullet.setProjectileType(ProjectileType.BUCKSHOT);
						entityBullet.setEffectiveRange(20f);
						entityBullet.shoot(
								xo, yo, zo, 
								Mth.randomBetween(random, 0f, 360f), Mth.randomBetween(random, 0f, 360f), 
								0.0F, 3f, 5f);
						
						level().addFreshEntity(entityBullet);
					}
				}
				discard();
				break;
			default:
				break;
		}
	}

	protected void doEffectOnBlockHit(BlockPos blockpos) {
		switch (getProjectileType()) {
			case EXPLOSIVE_SHELL:
				if (getEffectStrength() > 0.0f) {
					if (!level().isClientSide()) {
						level().explode(this, blockpos.getX(), blockpos.getY(), blockpos.getZ(), getEffectStrength(), Level.ExplosionInteraction.BLOCK);
					}
					discard();
				}
				break;
			case CANNONBALL:
				/* Default behavior is solid projectile. */
				if (getEffectStrength() > 0.0f) {
					BlockState hitBlock = level().getBlockState(blockpos);
					float hitBlockHardness = hitBlock.getDestroySpeed(level(), blockpos);
					
					OldGuns.printDebug(this + " target hardness : " + hitBlockHardness);
					OldGuns.printDebug(this + " effect before : " + getEffectStrength());
					
					if (hitBlockHardness < getEffectStrength() && 
							hitBlock.canEntityDestroy(level(), blockpos, getOwner()) &&
							hitBlock.getBlock() != Blocks.BEDROCK
							) {				
						if (!level().isClientSide()) {
							/* Punch through block if effectStrength of solid projectile is greater than block hardness, and keep going with reduced effectiveness. */
							level().destroyBlock(blockpos, level().random.nextBoolean());
						}
						float effectModifier = (!this.isInWater()) ? 0.70f : 0.45f;
						setEffectStrength(Math.max(0.1f, getEffectStrength() * effectModifier));
	
						OldGuns.printDebug(this + " broke : " + hitBlock.toString());
					} else {
						setEffectStrength(0.0f);
						OldGuns.printDebug(this + " stopped by, inWater : " + hitBlock.toString() + ", " + this.isInWater());
					}
					OldGuns.printDebug(this + " effect after : " + getEffectStrength());
				}
				break;
			default:
				break;
		}
	}

	protected void tickDespawn() {
		++this.life;
		if (this.life >= 1200) {
			this.discard();
		}
	}

	public boolean isNoPhysics() {
		if (!this.level().isClientSide) {
			return this.noPhysics;
		} else {
			return (this.entityData.get(ID_FLAGS) & 2) != 0;
		}
	}

	@Nullable
	protected EntityHitResult findHitEntity(Vec3 pStartVec, Vec3 pEndVec) {
		return ProjectileUtil.getEntityHitResult(this.level(), this, pStartVec, pEndVec, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), this::canHitEntity);
	}
	
	protected float getWaterInertia() {
		return 0.6F;
	}
	
	@Override
	public void tick() {
		if (!this.hasBeenShot) {
			this.gameEvent(GameEvent.PROJECTILE_SHOOT, this.getOwner());
			this.hasBeenShot = true;
		}

		if (!this.leftOwner) {
			this.leftOwner = this.checkLeftOwner();
		}

		super.baseTick();

		boolean flag = this.isNoPhysics();
		Vec3 vec3 = this.getDeltaMovement();
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			double d0 = vec3.horizontalDistance();
			this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
			this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
			this.yRotO = this.getYRot();
			this.xRotO = this.getXRot();
		}

		BlockPos blockpos = this.blockPosition();
		BlockState blockstate = this.level().getBlockState(blockpos);
		if (!this.canCollideWithBlockState(blockstate) && !flag) {
			VoxelShape voxelshape = blockstate.getCollisionShape(this.level(), blockpos);
			if (!voxelshape.isEmpty()) {
				Vec3 vec31 = this.position();

				for(AABB aabb : voxelshape.toAabbs()) {
					if (aabb.move(blockpos).contains(vec31)) {
						this.inGround = true;
						break;
					}
				}
			}
		}

		if (this.isInWaterOrRain() || blockstate.is(Blocks.POWDER_SNOW)) {	         
			this.clearFire();
		}

		if (this.inGround && !flag) {
			if (this.lastState != blockstate && this.shouldFall()) {
				this.startFalling();
			}

			++this.inGroundTime;
			++this.totalInGroundTime;
		} else {
			this.inGroundTime = 0;
			Vec3 vec32 = this.position();
			Vec3 vec33 = vec32.add(vec3);
			HitResult hitresult = this.level().clip(new ClipContext(vec32, vec33, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
			if (hitresult.getType() != HitResult.Type.MISS) {
				vec33 = hitresult.getLocation();
			}

			while(!this.isRemoved()) {
				EntityHitResult entityhitresult = this.findHitEntity(vec32, vec33);
				if (entityhitresult != null) {
					hitresult = entityhitresult;
				}

				if (hitresult != null && hitresult.getType() == HitResult.Type.ENTITY) {
					Entity entity = ((EntityHitResult)hitresult).getEntity();
					Entity entity1 = this.getOwner();
					if (entity instanceof Player && entity1 instanceof Player && !((Player)entity1).canHarmPlayer((Player)entity)) {
						hitresult = null;
						entityhitresult = null;
					}
				}

				if (hitresult != null && hitresult.getType() != HitResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
					this.onHit(hitresult);
					this.hasImpulse = true;
				}

				if (entityhitresult == null || this.getPierceLevel() <= 0) {
					break;
				}

				hitresult = null;
			}


			vec3 = this.getDeltaMovement();
			double d5 = vec3.x;
			double d6 = vec3.y;
			double d1 = vec3.z;
			if (this.isCritArrow()) {
				for(int i = 0; i < 4; ++i) {
					this.level().addParticle(ParticleTypes.CRIT, this.getX() + d5 * (double)i / 4.0D, this.getY() + d6 * (double)i / 4.0D, this.getZ() + d1 * (double)i / 4.0D, -d5, -d6 + 0.2D, -d1);
				}
			}

			double d7 = this.getX() + d5;
			double d2 = this.getY() + d6;
			double d3 = this.getZ() + d1;
			double d4 = vec3.horizontalDistance();
			if (flag) {
				this.setYRot((float)(Mth.atan2(-d5, -d1) * (double)(180F / (float)Math.PI)));
			} else {
				this.setYRot((float)(Mth.atan2(d5, d1) * (double)(180F / (float)Math.PI)));
			}

			this.setXRot((float)(Mth.atan2(d6, d4) * (double)(180F / (float)Math.PI)));
			this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
			this.setYRot(lerpRotation(this.yRotO, this.getYRot()));
			boolean noFriction = (this.isInsideEffectiveRange() && this.getDamageType() == OldGunsDamageTypes.FIREARM && !this.isInWater());
			float f = (noFriction) ? 1.0f : 0.98F;
			//float f1 = 0.05F;
			if (!this.isSimulated && this.isInWater()) {
				for(int j = 0; j < 4; ++j) {
					//float f2 = 0.25F;
					this.level().addParticle(ParticleTypes.BUBBLE, d7 - d5 * 0.25D, d2 - d6 * 0.25D, d3 - d1 * 0.25D, d5, d6, d1);
				}

				f = this.getWaterInertia();
			}

			this.setDeltaMovement(vec3.scale((double)f));
			if (!this.isNoGravity() && !flag) {
				Vec3 vec34 = this.getDeltaMovement();
				double gravityForce = (noFriction && this.totalInGroundTime == 0) ? 0.0D : 0.05D;
				this.setDeltaMovement(vec34.x, vec34.y - gravityForce, vec34.z);
			}

			this.setPos(d7, d2, d3);
			this.checkInsideBlocks();
		}

		if (this.level().isClientSide) {
			if (this.inGround) {
				if (this.inGroundTime % 5 == 0) {
					this.makeParticle(1);
				}
			} else {
				this.makeParticle(2);
			}
		} else if (this.inGround && this.inGroundTime != 0 && !this.effects.isEmpty() && this.inGroundTime >= 600) {
			this.level().broadcastEntityEvent(this, (byte)0);
			this.potion = Potions.EMPTY;
			this.effects.clear();
			this.entityData.set(ID_EFFECT_COLOR, -1);
		}

		doEffectOnTick();
		
		tickDespawn();

		if (!this.isSimulated && this.totalInGroundTime >= 100 && ((this.life % (20 + this.random.nextInt(10) - 5)) == 0)) {
			this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0d, 0d, 0d);
		} else if (!this.isSimulated && this.totalInGroundTime == 0 && (this.life % 2) == 0) {
			this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0d, 0d, 0d);
		}
	}

	protected void makeParticle(int particleCount) {
		int i = this.getColor();
		if (i != -1 && particleCount > 0) {
			double d0 = (double)(i >> 16 & 255) / 255.0D;
			double d1 = (double)(i >> 8 & 255) / 255.0D;
			double d2 = (double)(i >> 0 & 255) / 255.0D;

			for(int j = 0; j < particleCount; ++j) {
				this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), d0, d1, d2);
			}	
		}
	}	

	public void shoot(double x, double y, double z, float vel, float acc) {
		Vec3 vec3 = (new Vec3(x, y, z)).normalize().add(this.random.nextGaussian() * (double)0.0075F * (double)acc, this.random.nextGaussian() * (double)0.0075F * (double)acc, this.random.nextGaussian() * (double)0.0075F * (double)acc).scale((double)vel);
		this.setDeltaMovement(vec3);
		double d0 = vec3.horizontalDistance();
		this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
		this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
		this.yRotO = this.getYRot();
		this.xRotO = this.getXRot();
		this.inGroundTime = 0;
		this.life = 0;
	}

	public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy)
	{
		float f = -Mth.sin(yaw * 0.017453292F) * Mth.cos(pitch * 0.017453292F);
		float f1 = -Mth.sin(pitch * 0.017453292F);
		float f2 = Mth.cos(yaw * 0.017453292F) * Mth.cos(pitch * 0.017453292F);
		this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
		this.setDeltaMovement(this.getDeltaMovement().add(shooter.getDeltaMovement()));
	}

	public void shoot(double posX, double posY, double posZ, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy)
	{
		float f = -Mth.sin(yaw * 0.017453292F) * Mth.cos(pitch * 0.017453292F);
		float f1 = -Mth.sin(pitch * 0.017453292F);
		float f2 = Mth.cos(yaw * 0.017453292F) * Mth.cos(pitch * 0.017453292F);
		this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
	}

	//	/**
	//	 * Set the shooting entity for this entity.
	//	 * @param range
	//	 */
	//	public void setShootingEntity(LivingEntity shootingEntity)
	//	{
	//		this.entityData.set(SHOOTING_ENTITY, shootingEntity.serializeNBT());
	//	}

	public void setDamage(double damageIn)
	{
		this.entityData.set(DAMAGE, (float) damageIn);
	}

	public double getDamage()
	{
		return this.entityData.get(DAMAGE);
	}

	public void setBypassArmorPercentage(float bypassArmorPercentage)
	{
		this.entityData.set(BYPASS_ARMOR_PERCENTAGE, (float) bypassArmorPercentage);
	}

	public float getBypassArmorPercentage()
	{
		return this.entityData.get(BYPASS_ARMOR_PERCENTAGE);
	}
	
	/**
	 * Set the start location for this entity. Will be used to calculate damage falloff.
	 * @param pos
	 */
	public void setLaunchLocation(Vec3 pos)
	{
		this.entityData.set(START_POS, new BlockPos((int) pos.x, (int) pos.y, (int) pos.z));
	} 

	/**
	 * Set the start location for this entity. Will be used to calculate damage falloff.
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setLaunchLocation(double x, double y, double z)
	{
		this.entityData.set(START_POS, new BlockPos((int) x, (int) y, (int) z));
	}

	/**
	 * Set the start location for this entity. Will be used to calculate damage falloff.
	 * @param position
	 */
	public void setLaunchLocation(BlockPos position)
	{
		this.entityData.set(START_POS, position);
	}

	/**
	 * Get the start location for this entity. Will be used to calculate damage falloff.
	 */
	public BlockPos getLaunchLocation()
	{
		return ((BlockPos)this.entityData.get(START_POS));
	}

	/**
	 * Set the type of this projectile
	 * @param type
	 */
	public void setProjectileType(ProjectileType type)
	{
		this.entityData.set(PROJECTILE_TYPE, type.ordinal());
	}

	/**
	 * Get the type projectile type of this projectile
	 */
	public ProjectileType getProjectileType()
	{
		int ordinal = this.entityData.get(PROJECTILE_TYPE).intValue();
		return ProjectileType.values()[ordinal];
	}

	/**
	 * Set the effect strength of this projectile
	 * @param effectStrength
	 */
	public void setEffectStrength(float effectStrength)
	{
		this.entityData.set(EFFECT_STRENGTH, effectStrength);
	}

	/**
	 * Get the effect strength of this projectile
	 * @param
	 */
	public float getEffectStrength()
	{
		return this.entityData.get(EFFECT_STRENGTH).floatValue();
	}

	/**
	 * Set the effect length of this projectile
	 * @param effectTicks
	 */
	public void setEffectTicks(int effectTicks)
	{
		this.entityData.set(EFFECT_TICKS, effectTicks);
	}

	/**
	 * Get the effect lenght of this projectile
	 */
	public int getEffectTicks()
	{
		return this.entityData.get(EFFECT_TICKS).intValue();
	}

	/**
	 * Set the size of this projectile.
	 * @param size
	 */
	public void setProjectileSize(float size)
	{
		this.entityData.set(PROJECTILE_SIZE, size);
	}

	/**
	 * Get the shooting entity for this entity.
	 */
	public float getProjectileSize()
	{
		return this.entityData.get(PROJECTILE_SIZE).floatValue();
	}

	/**
	 * Set the effective range of this projectile. Damage to entities is halved when outside this range.
	 * @param range
	 */
	public void setEffectiveRange(float range)
	{
		this.entityData.set(EFFECTIVE_RANGE, Float.valueOf(range));
	}

	/**
	 * Get the effective range of this projectile. Damage to entities is halved when outside this range.
	 */
	public float getEffectiveRange()
	{
		return ((Float)this.entityData.get(EFFECTIVE_RANGE)).floatValue();
	}


}
