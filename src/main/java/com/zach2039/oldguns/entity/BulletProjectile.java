package com.zach2039.oldguns.entity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModDamageSources;
import com.zach2039.oldguns.init.ModDamageSources.DamageType;
import com.zach2039.oldguns.init.ModEntities;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.init.ModSoundEvents;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class BulletProjectile extends ArrowEntity implements IEntityAdditionalSpawnData {

	private static final DataParameter<Float> PROJECTILE_SIZE = EntityDataManager.defineId(BulletProjectile.class, DataSerializers.FLOAT);
	private static final DataParameter<BlockPos> START_POS = EntityDataManager.defineId(BulletProjectile.class, DataSerializers.BLOCK_POS);
	private static final DataParameter<Float> EFFECTIVE_RANGE = EntityDataManager.defineId(BulletProjectile.class, DataSerializers.FLOAT);
	private static final DataParameter<CompoundNBT> SHOOTING_ENTITY = EntityDataManager.defineId(BulletProjectile.class, DataSerializers.COMPOUND_TAG);
	private static final DataParameter<Float> DAMAGE = EntityDataManager.defineId(BulletProjectile.class, DataSerializers.FLOAT);
	private static final DataParameter<Byte> CRITICAL = EntityDataManager.defineId(BulletProjectile.class, DataSerializers.BYTE);
	private static final DataParameter<Integer> ID_EFFECT_COLOR = EntityDataManager.defineId(BulletProjectile.class, DataSerializers.INT);
	private static final DataParameter<Byte> ID_FLAGS = EntityDataManager.defineId(BulletProjectile.class, DataSerializers.BYTE);
	private static final DataParameter<Byte> PIERCE_LEVEL = EntityDataManager.defineId(BulletProjectile.class, DataSerializers.BYTE);

	@Nullable
	private UUID ownerUUID;
	@Nullable
	private Entity cachedOwner;
	private boolean leftOwner;
	private boolean hasBeenShot;

	@Nullable
	private BlockState lastState;
	public PickupStatus pickup = PickupStatus.DISALLOWED;
	private int life;
	private double baseDamage = 2.0D;
	private int knockback;
	private SoundEvent soundEvent = ModSoundEvents.BULLET_HIT_BLOCK.get();
	@Nullable
	private IntOpenHashSet piercingIgnoreEntityIds;
	@Nullable
	private List<Entity> piercedAndKilledEntities;

	//private static final byte EVENT_POTION_PUFF = 0;
	private Potion potion = Potions.EMPTY;
	private final Set<EffectInstance> effects = Sets.newHashSet();
	private boolean fixedColor;
	private int totalInGroundTime;


	public BulletProjectile(EntityType<? extends BulletProjectile> entityType, World world) {
		super(entityType, world);
		this.pickup = PickupStatus.DISALLOWED;
		this.totalInGroundTime = 0;
	}

	public BulletProjectile(World world, double x, double y, double z) {
		this(ModEntities.BULLET_PROJECTILE.get(), world);
		this.pickup = PickupStatus.DISALLOWED;
		this.moveTo(x, y, z, this.xRotO, this.yRotO);
		this.setPos(x, y, z);
	}

	public BulletProjectile(World world, LivingEntity shooter) {
		this(world, shooter.getX(), shooter.getEyeY() - (double)0.1F, shooter.getZ());		
		this.setOwner(shooter);
	}

	@Override
	public EntityType<?> getType() {
		return ModEntities.BULLET_PROJECTILE.get();
	}

	@Override
	public void setEffectsFromItem(final ItemStack stackIn) {
		super.setEffectsFromItem(stackIn);
	}

	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(ModItems.SMALL_IRON_MUSKET_BALL.get());
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void writeSpawnData(PacketBuffer buf) {
		final Entity shooter = getOwner();
		buf.writeVarInt(shooter == null ? 0 : shooter.getId());
	}

	@Override
	public void readSpawnData(PacketBuffer buf) {
		final Entity shooter = level.getEntity(buf.readVarInt());
		if (shooter != null)
			setOwner(shooter);
	}

	@Override
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
		this.entityData.define(SHOOTING_ENTITY, new CompoundNBT());

		/* Register storage for damage. */
		this.entityData.define(DAMAGE, 0.0f);

		/* Register projectile size for rendering. */
		this.entityData.define(PROJECTILE_SIZE, 1f);

		/* Register CRITICAL flag for critical damage application. */
		this.entityData.define(CRITICAL, (byte)0);
	}

	@Override
	public void setPierceLevel(byte p_36768_) {
		this.entityData.set(PIERCE_LEVEL, p_36768_);
	}

	@Override
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

	@Override
	public boolean shotFromCrossbow() {
		byte b0 = this.entityData.get(ID_FLAGS);
		return (b0 & 4) != 0;
	}

	@Override
	public byte getPierceLevel() {
		return this.entityData.get(PIERCE_LEVEL);
	}	

	@Override
	public int getColor() {
		return this.entityData.get(ID_EFFECT_COLOR);
	}

	@Override
	public void setShotFromCrossbow(boolean isShotFromCrossbow) {
		this.setFlag(4, isShotFromCrossbow);
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
	public void addAdditionalSaveData(CompoundNBT compound) {
		if (this.ownerUUID != null) {
			compound.putUUID("Owner", this.ownerUUID);
		}

		if (this.leftOwner) {
			compound.putBoolean("LeftOwner", true);
		}

		compound.putBoolean("HasBeenShot", this.hasBeenShot);

		if (this.potion != Potions.EMPTY) {
			compound.putString("Potion", Registry.POTION.getKey(this.potion).toString());
		}

		if (this.fixedColor) {
			compound.putInt("Color", this.getColor());
		}

		if (!this.effects.isEmpty()) {
			ListNBT listtag = new ListNBT();

			for(EffectInstance mobeffectinstance : this.effects) {
				listtag.add(mobeffectinstance.save(new CompoundNBT()));
			}

			compound.put("CustomPotionEffects", listtag);
		}

		compound.putShort("life", (short)this.life);
		if (this.lastState != null) {
			compound.put("inBlockState", NBTUtil.writeBlockState(this.lastState));
		}

		compound.putByte("shake", (byte)this.shakeTime);
		compound.putBoolean("inGround", this.inGround);
		compound.putInt("totalInGroundTime", this.totalInGroundTime);
		compound.putByte("pickup", (byte)this.pickup.ordinal());
		compound.putDouble("damage", this.baseDamage);
		compound.putBoolean("crit", this.isCritArrow());
		compound.putByte("PierceWorld", this.getPierceLevel());
		compound.putString("SoundEvent", Registry.SOUND_EVENT.getKey(this.soundEvent).toString());
		compound.putBoolean("ShotFromCrossbow", this.shotFromCrossbow());
		compound.putFloat("projectileSize", getProjectileSize());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		if (compound.hasUUID("Owner")) {
			this.ownerUUID = compound.getUUID("Owner");
		}

		this.leftOwner = compound.getBoolean("LeftOwner");
		this.hasBeenShot = compound.getBoolean("HasBeenShot");


		if (compound.contains("Potion", 8)) {
			this.potion = PotionUtils.getPotion(compound);
		}

		for(EffectInstance mobeffectinstance : PotionUtils.getCustomEffects(compound)) {
			this.addEffect(mobeffectinstance);
		}

		if (compound.contains("Color", 99)) {
			this.setFixedColor(compound.getInt("Color"));
		} else {
			this.updateColor();
		}

		this.life = compound.getShort("life");
		if (compound.contains("inBlockState", Constants.NBT.TAG_COMPOUND)) {
			this.lastState = NBTUtil.readBlockState(compound.getCompound("inBlockState"));
		}

		this.shakeTime = compound.getByte("shake") & 255;
		this.inGround = compound.getBoolean("inGround");
		this.totalInGroundTime = compound.getInt("totalInGroundTime");
		if (compound.contains("damage", Constants.NBT.TAG_DOUBLE)) {
			this.baseDamage = compound.getDouble("damage");
		}

		this.pickup = AbstractArrowEntity.PickupStatus.byOrdinal(compound.getByte("pickup"));
		this.setCritArrow(compound.getBoolean("crit"));
		this.setPierceLevel(compound.getByte("PierceLevel"));
		if (compound.contains("SoundEvent", Constants.NBT.TAG_STRING)) {
			this.soundEvent = Registry.SOUND_EVENT.getOptional(new ResourceLocation(compound.getString("SoundEvent"))).orElse(this.getDefaultHitGroundSoundEvent());
		}

		this.setShotFromCrossbow(compound.getBoolean("ShotFromCrossbow"));

		this.setProjectileSize(compound.getFloat("projectileSize"));
	}

	private boolean checkLeftOwner() {
		Entity entity = this.getOwner();
		if (entity != null) {
			for(Entity entity1 : this.level.getEntities(this, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), (ent) -> {
				return !ent.isSpectator() && ent.isPickable();
			})) {
				if (entity1.getRootVehicle() == entity.getRootVehicle()) {
					return false;
				}
			}
		}

		return true;
	}

	private boolean shouldFall() {
		return this.inGround && this.level.noCollision((new AxisAlignedBB(this.position(), this.position())).inflate(0.06D));
	}

	private void startFalling() {
		this.inGround = false;
		Vector3d vec3 = this.getDeltaMovement();
		this.setDeltaMovement(vec3.multiply((double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F)));
	}

	/**
	 * Returns true if projectile's current location is within the specified range.
	 * @param x
	 * @param y
	 * @param z
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
		double distance = current.distSqr(launch);

		/* Return based on distance value. */
		return (distance < effectiveRange) ? true : false;
	}

	protected DamageType getDamageType() {
		return DamageType.FIREARM;
	}

	@Override
	protected void onHitEntity(EntityRayTraceResult result) {
		Entity entity = result.getEntity();

		int i = MathHelper.ceil(this.baseDamage);

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
				this.remove();
				return;
			}

			this.piercingIgnoreEntityIds.add(entity.getId());
		}

		if (this.isCritArrow()) {
			long j = (long)this.random.nextInt(i / 2 + 2);
			i = (int)Math.min(j + (long)i, 2147483647L);
		}

		Entity entity1 = this.getOwner();
		DamageSource damagesource;
		if (entity1 == null) {
			damagesource = ModDamageSources.causeBulletDamage(getDamageType(), this, this);
		} else {
			damagesource = ModDamageSources.causeBulletDamage(getDamageType(), this, entity1);
			if (entity1 instanceof LivingEntity) {
				((LivingEntity)entity1).setLastHurtMob(entity);
			}
		}

		boolean flag = entity.getType() == EntityType.ENDERMAN;
		int k = entity.getRemainingFireTicks();
		if (this.isOnFire() && !flag) {
			entity.setSecondsOnFire(5);
		}


		if (entity.hurt(damagesource, (float)i)) {
			if (flag) {
				return;
			}

			if (entity instanceof LivingEntity) {
				LivingEntity livingentity = (LivingEntity)entity;
				if (!this.level.isClientSide && this.getPierceLevel() <= 0) {
					//livingentity.setArrowCount(livingentity.getArrowCount() + 1);
				}

				if (this.knockback > 0) {
					Vector3d vec3 = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)this.knockback * 0.6D);
					if (vec3.lengthSqr() > 0.0D) {
						livingentity.push(vec3.x, 0.1D, vec3.z);
					}
				}

				if (!this.level.isClientSide && entity1 instanceof LivingEntity) {
					EnchantmentHelper.doPostHurtEffects(livingentity, entity1);
					EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity);
				}

				this.doPostHurtEffects(livingentity);
				if (entity1 != null && livingentity != entity1 && livingentity instanceof PlayerEntity && entity1 instanceof ServerPlayerEntity && !this.isSilent()) {
					((ServerPlayerEntity)entity1).connection.send(new SChangeGameStatePacket(SChangeGameStatePacket.ARROW_HIT_PLAYER, 0.0F));
				}

				if (!entity.isAlive() && this.piercedAndKilledEntities != null) {
					this.piercedAndKilledEntities.add(livingentity);
				}

				if (!this.level.isClientSide && entity1 instanceof ServerPlayerEntity) {
					//ServerPlayer serverplayer = (ServerPlayer)entity1;
					if (this.piercedAndKilledEntities != null && this.shotFromCrossbow()) {
						//CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayer, this.piercedAndKilledEntities);
					} else if (!entity.isAlive() && this.shotFromCrossbow()) {
						//CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayer, Arrays.asList(entity));
					}
				}

				// Reset inv time to allow multiple pellets to damage entities
				entity.invulnerableTime = 0;
			}

			this.playSound(ModSoundEvents.BULLET_HIT_MOB.get(), 0.3F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
			if (this.getPierceLevel() <= 0) {
				this.remove();
			}
		} else {
			entity.setRemainingFireTicks(k);
			this.setDeltaMovement(this.getDeltaMovement().scale(-0.1D));
			this.yRot += 180.0F;
			this.yRotO += 180.0F;
			if (!this.level.isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7D) {

				this.remove();
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
		return true;
	}

	@SuppressWarnings("deprecation")
	protected boolean canCollideWithBlockState(BlockState state)
	{
		if (!state.isAir())
			return true;

		return false;
	}

	@Override
	protected void onHitBlock(BlockRayTraceResult result) {
		// Allow ricochet for projectiles

		Vector3i hitNormal = result.getDirection().getNormal();
		boolean isShallowAngle = MathHelper.abs((float) this.getDeltaMovement().normalize().dot(new Vector3d(hitNormal.getX(), hitNormal.getY(), hitNormal.getZ()).normalize())) < 0.4;
		boolean ricochet = result.getDirection().getAxis().isVertical() && (isShallowAngle) && (this.getVelocityMagnitude() > 1f);

		//OldGuns.LOGGER.info("ricochet: " + ricochet);
		//OldGuns.LOGGER.info("vertical: " + result.getDirection().getAxis().isVertical());
		//OldGuns.LOGGER.info("math: " + MathHelper.abs((float) this.getDeltaMovement().normalize().dot(new Vector3d(hitNormal.getX(), hitNormal.getY(), hitNormal.getZ()).normalize())));
		//OldGuns.LOGGER.info("velMag: " + (this.getVelocityMagnitude() > 1f));

		this.lastState = this.level.getBlockState(result.getBlockPos());
		BlockState blockstate = this.level.getBlockState(result.getBlockPos());

		if (!ricochet) {
			if (this.allowBlockHitSlowdown()) {
				Vector3d vec3 = result.getLocation().subtract(this.getX(), this.getY(), this.getZ());
				this.setDeltaMovement(vec3);
				Vector3d vec31 = vec3.normalize().scale((double)0.05F);
				this.setPosRaw(this.getX() - vec31.x, this.getY() - vec31.y, this.getZ() - vec31.z);

			} else {
				this.setDeltaMovement(this.getDeltaMovement().scale(0.5f));
			}

			if (canCollideWithBlockState(blockstate)) {
				blockstate.onProjectileHit(this.level, blockstate, result, this);
				if (!this.isInWater() && this.getVelocityMagnitude() > 1.0f) {
					this.playSound(ModSoundEvents.BULLET_HIT_BLOCK.get(), 0.7F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
				} else {
					this.playSound(SoundEvents.ANVIL_HIT, 0.7F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
				}
				this.inGround = true;
				this.shakeTime = 7;
				this.setCritArrow(false);
				this.setPierceLevel((byte)0);
				this.setShotFromCrossbow(false);
				this.resetPiercedEntities();
			}
		} else {
			this.inGround = false;
			double xMotMod = 0.70f + (this.random.nextFloat() * 0.15f);
			double yMotMod = -0.70f;
			double zMotMod = 0.70f + (this.random.nextFloat() * 0.15f);
			this.setDeltaMovement(this.getDeltaMovement().multiply(xMotMod, yMotMod, zMotMod));

			for(int i = 0; i < 3; ++i) {
				BlockParticleData blockPart = new BlockParticleData(ParticleTypes.BLOCK, blockstate);
				this.level.addParticle(blockPart, 
						this.xo - this.getDeltaMovement().x * 0.25D, this.yo - this.getDeltaMovement().y * 0.25, this.zo - this.getDeltaMovement().z * 0.25,
						0, 0, 0);
			}

			this.playSound(ModSoundEvents.BULLET_RICOCHET.get(), 0.3F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));

			// Add to totalInGroundTime to reduce damage after ricochet
			++this.totalInGroundTime;
		}
	}

	@Override
	protected void onHit(RayTraceResult result) {
		RayTraceResult.Type hitresult$type = result.getType();
		if (hitresult$type == RayTraceResult.Type.ENTITY) {
			this.onHitEntity((EntityRayTraceResult)result);
		} else if (hitresult$type == RayTraceResult.Type.BLOCK) {
			this.onHitBlock((BlockRayTraceResult)result);
		}
	}


	@Override
	protected void tickDespawn() {
		++this.life;
		if (this.life >= 1200) {
			this.remove();
		}
	}

	@Override
	public boolean isNoPhysics() {
		if (!this.level.isClientSide) {
			return this.noPhysics;
		} else {
			return (this.entityData.get(ID_FLAGS) & 2) != 0;
		}
	}

	@Override
	public void playerTouch(PlayerEntity p_70100_1_) {}
	
	@Override
	public void tick() {
		if (!this.hasBeenShot) {
			this.hasBeenShot = true;
		}

		if (!this.leftOwner) {
			this.leftOwner = this.checkLeftOwner();
		}

		super.baseTick();

		boolean flag = this.isNoPhysics();
		Vector3d vector3d = this.getDeltaMovement();
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			float d0 = MathHelper.sqrt(getHorizontalDistanceSqr(vector3d));
			this.yRot = (float)(MathHelper.atan2(vector3d.x, vector3d.z) * (double)(180F / (float)Math.PI));
			this.xRot = (float)(MathHelper.atan2(vector3d.y, (double)d0) * (double)(180F / (float)Math.PI));
			this.yRotO = this.yRot;
			this.xRotO = this.xRot;
		}

		BlockPos blockpos = this.blockPosition();
		BlockState blockstate = this.level.getBlockState(blockpos);
		if (!this.canCollideWithBlockState(blockstate) && !flag) {
			VoxelShape voxelshape = blockstate.getCollisionShape(this.level, blockpos);
			if (!voxelshape.isEmpty()) {
				Vector3d vec31 = this.position();

				for(AxisAlignedBB aabb : voxelshape.toAabbs()) {
					if (aabb.move(blockpos).contains(vec31)) {
						this.inGround = true;
						break;
					}
				}
			}
		}

		if (this.shakeTime > 0) {
			--this.shakeTime;
		}

		if (this.isInWaterOrRain()) {	         
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
			Vector3d vec32 = this.position();
			Vector3d vec33 = vec32.add(vector3d);
			RayTraceResult hitresult = this.level.clip(new RayTraceContext(vec32, vec33, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
			if (hitresult.getType() != RayTraceResult.Type.MISS) {
				vec33 = hitresult.getLocation();
			}

			while(this.isAlive()) {
				EntityRayTraceResult entityhitresult = this.findHitEntity(vec32, vec33);
				if (entityhitresult != null) {
					hitresult = entityhitresult;
				}

				if (hitresult != null && hitresult.getType() == RayTraceResult.Type.ENTITY) {
					Entity entity = ((EntityRayTraceResult)hitresult).getEntity();
					Entity entity1 = this.getOwner();
					if (entity instanceof PlayerEntity && entity1 instanceof PlayerEntity && !((PlayerEntity)entity1).canHarmPlayer((PlayerEntity)entity)) {
						hitresult = null;
						entityhitresult = null;
					}
				}

				if (hitresult != null && hitresult.getType() != RayTraceResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
					this.onHit(hitresult);
					this.hasImpulse = true;
				}

				if (entityhitresult == null || this.getPierceLevel() <= 0) {
					break;
				}

				hitresult = null;
			}


			vector3d = this.getDeltaMovement();
			double d5 = vector3d.x;
			double d6 = vector3d.y;
			double d1 = vector3d.z;
			if (this.isCritArrow()) {
				for(int i = 0; i < 4; ++i) {
					this.level.addParticle(ParticleTypes.CRIT, this.getX() + d5 * (double)i / 4.0D, this.getY() + d6 * (double)i / 4.0D, this.getZ() + d1 * (double)i / 4.0D, -d5, -d6 + 0.2D, -d1);
				}
			}

			double d7 = this.getX() + d5;
			double d2 = this.getY() + d6;
			double d3 = this.getZ() + d1;
			float d4 = MathHelper.sqrt(getHorizontalDistanceSqr(vector3d));
			if (flag) {
				this.yRot = (float)(MathHelper.atan2(-d5, -d1) * (double)(180F / (float)Math.PI));
			} else {
				this.yRot = (float)(MathHelper.atan2(d5, d1) * (double)(180F / (float)Math.PI));
			}

			this.xRot = (float)(MathHelper.atan2(d6, (double)d4) * (double)(180F / (float)Math.PI));
			this.xRot = lerpRotation(this.xRotO, this.xRot);
			this.yRot = lerpRotation(this.yRotO, this.yRot);
			float f = (this.isInsideEffectiveRange()) ? 1.0f : 0.98F;
			//float f1 = 0.05F;
			if (this.isInWater()) {
				for(int j = 0; j < 4; ++j) {
					//float f2 = 0.25F;
					this.level.addParticle(ParticleTypes.BUBBLE, d7 - d5 * 0.25D, d2 - d6 * 0.25D, d3 - d1 * 0.25D, d5, d6, d1);
				}

				f = this.getWaterInertia();
			}

			this.setDeltaMovement(vector3d.scale((double)f));
			if (!this.isNoGravity() && !flag) {
				Vector3d vec34 = this.getDeltaMovement();
				double gravityForce = (this.isInsideEffectiveRange() && this.totalInGroundTime == 0) ? 0.0d : 0.05d;
				this.setDeltaMovement(vec34.x, vec34.y - gravityForce, vec34.z);
			}

			this.setPos(d7, d2, d3);
			this.checkInsideBlocks();
		}

		if (this.level.isClientSide) {
			if (this.inGround) {
				if (this.inGroundTime % 5 == 0) {
					this.makeParticle(1);
				}
			} else {
				this.makeParticle(2);
			}
		} else if (this.inGround && this.inGroundTime != 0 && !this.effects.isEmpty() && this.inGroundTime >= 600) {
			this.level.broadcastEntityEvent(this, (byte)0);
			this.potion = Potions.EMPTY;
			this.effects.clear();
			this.entityData.set(ID_EFFECT_COLOR, -1);
		}

		this.tickDespawn();

		if (this.totalInGroundTime >= 100 && ((this.life % (20 + this.random.nextInt(10) - 5)) == 0)) {
			this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0d, 0d, 0d);
		} else if (this.totalInGroundTime == 0 && (this.life % 2) == 0) {
			this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0d, 0d, 0d);
		}
	}

	private void makeParticle(int particleCount) {
		int i = this.getColor();
		if (i != -1 && particleCount > 0) {
			double d0 = (double)(i >> 16 & 255) / 255.0D;
			double d1 = (double)(i >> 8 & 255) / 255.0D;
			double d2 = (double)(i >> 0 & 255) / 255.0D;

			for(int j = 0; j < particleCount; ++j) {
				this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), d0, d1, d2);
			}	
		}
	}	

	public void shoot(double x, double y, double z, float vel, float acc) {
		Vector3d vector3d = (new Vector3d(x, y, z)).normalize().add(this.random.nextGaussian() * (double)0.0075F * (double)acc, this.random.nextGaussian() * (double)0.0075F * (double)acc, this.random.nextGaussian() * (double)0.0075F * (double)acc).scale((double)vel);
		this.setDeltaMovement(vector3d);
		float d0 = MathHelper.sqrt(getHorizontalDistanceSqr(vector3d));
		this.yRot = (float)(MathHelper.atan2(vector3d.x, vector3d.z) * (double)(180F / (float)Math.PI));
		this.xRot = (float)(MathHelper.atan2(vector3d.y, (double)d0) * (double)(180F / (float)Math.PI));
		this.yRotO = this.yRot;
		this.xRotO = this.xRot;
		this.inGroundTime = 0;
		this.life = 0;
		this.baseDamage = this.entityData.get(DAMAGE);
	}

	public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy)
	{
		float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		float f1 = -MathHelper.sin(pitch * 0.017453292F);
		float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
		this.setDeltaMovement(this.getDeltaMovement().add(shooter.getDeltaMovement()));
	}

	public void shoot(double posX, double posY, double posZ, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy)
	{
		float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		float f1 = -MathHelper.sin(pitch * 0.017453292F);
		float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
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

	/**
	 * Set the start location for this entity. Will be used to calculate damage falloff.
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setLaunchLocation(Vector3d pos)
	{
		this.entityData.set(START_POS, new BlockPos(pos.x, pos.y, pos.z));
	} 

	/**
	 * Set the start location for this entity. Will be used to calculate damage falloff.
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setLaunchLocation(double x, double y, double z)
	{
		this.entityData.set(START_POS, new BlockPos(x, y, z));
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
	 * @param position
	 */
	public BlockPos getLaunchLocation()
	{
		return ((BlockPos)this.entityData.get(START_POS));
	}


	/**
	 * Set the size of this projectile.
	 * @param range
	 */
	public void setProjectileSize(float size)
	{
		this.entityData.set(PROJECTILE_SIZE, size);
	}

	/**
	 * Get the shooting entity for this entity.
	 * @param range
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
