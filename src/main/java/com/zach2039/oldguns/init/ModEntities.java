package com.zach2039.oldguns.init;

import java.util.List;
import java.util.function.Supplier;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.GenericMobSettings;
import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.entity.monster.HarquebusierSkeleton;
import com.zach2039.oldguns.world.entity.monster.MusketeerSkeleton;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class ModEntities {
	private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, OldGuns.MODID);

	private static boolean isInitialized;

	// Artillery
	public static final RegistryObject<EntityType<BulletProjectile>> BULLET_PROJECTILE = registerEntityType("bullet_projectile",
			() -> EntityType.Builder.<BulletProjectile>of((BulletProjectile::new), MobCategory.MISC)
			.setUpdateInterval(1)
			.setTrackingRange(500)
			.clientTrackingRange(500)
			.sized(0.1f, 0.1f)
			);

	// Mobs
	public static final RegistryObject<EntityType<MusketeerSkeleton>> MUSKETEER_SKELETON = registerEntityType("musketeer_skeleton",
			() -> EntityType.Builder.of(MusketeerSkeleton::new, MobCategory.MONSTER)
			.sized(0.6F, 1.99F).clientTrackingRange(8)
			);
	
	public static final RegistryObject<EntityType<HarquebusierSkeleton>> HARQUEBUSIER_SKELETON = registerEntityType("harquebusier_skeleton",
			() -> EntityType.Builder.of(HarquebusierSkeleton::new, MobCategory.MONSTER)
			.sized(0.6F, 1.99F).clientTrackingRange(8)
			);

	/**
	 * Registers the {@link DeferredRegister} instance with the mod event bus.
	 * <p>
	 * This should be called during mod construction.
	 *
	 * @author choonster
	 * @param modEventBus The mod event bus
	 */
	public static void initialize(final IEventBus modEventBus) {
		if (isInitialized) {
			throw new IllegalStateException("Already initialized");
		}

		ENTITIES.register(modEventBus);

		isInitialized = true;
	}

	/**
	 * Registers an entity type.
	 *
	 * @author choonster
	 * @param name    The registry name of the entity type
	 * @param factory The factory used to create the entity type builder
	 * @return A RegistryObject reference to the entity type
	 */
	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(final String name, final Supplier<EntityType.Builder<T>> factory) {
		return ENTITIES.register(name,
				() -> factory.get().build(new ResourceLocation(OldGuns.MODID, name).toString())
				);
	}

	@Mod.EventBusSubscriber(modid = OldGuns.MODID, bus = Bus.MOD)
	public static class RegistrationHandler {
		@SubscribeEvent
		public static void registerAttributes(final EntityAttributeCreationEvent event) {
			event.put(MUSKETEER_SKELETON.get(), MusketeerSkeleton.registerAttributes().build());
			event.put(HARQUEBUSIER_SKELETON.get(), HarquebusierSkeleton.registerAttributes().build());
		}
	}

	@Mod.EventBusSubscriber(modid = OldGuns.MODID)
	public static class SpawnHandler {
		
		private static final OldGunsConfig.GenericMobSettings musketeerSkeletonSettings = OldGunsConfig.SERVER.mobSettings.musketeerSkeleton;
		private static final OldGunsConfig.GenericMobSettings harquebusierSkeletonSettings = OldGunsConfig.SERVER.mobSettings.harquebusierSkeleton;
		
		@SubscribeEvent(priority = EventPriority.LOW)
		public static void registerEntitySpawns(final BiomeLoadingEvent event) {
			if (event.getName() == null) {
				return;
			}

			if (SpawnHandler.canSpawnInBiomeOrBiomeCategory(musketeerSkeletonSettings, event)) {
				addSpawn(event, MUSKETEER_SKELETON.get(), 
						musketeerSkeletonSettings.spawnWeight.get(),
						musketeerSkeletonSettings.minCount.get(), 
						musketeerSkeletonSettings.maxCount.get(),
						MobCategory.MONSTER);
			}
			
			if (SpawnHandler.canSpawnInBiomeOrBiomeCategory(harquebusierSkeletonSettings, event)) {
				addSpawn(event, HARQUEBUSIER_SKELETON.get(), 
						harquebusierSkeletonSettings.spawnWeight.get(),
						harquebusierSkeletonSettings.minCount.get(), 
						harquebusierSkeletonSettings.maxCount.get(),
						MobCategory.MONSTER);
			}
			
		}

		public static boolean canSpawnInBiomeOrBiomeCategory(GenericMobSettings settings, BiomeLoadingEvent event) {
			boolean canSpawn = false;
			
			if (!settings.canSpawn.get())
				return canSpawn;
			
			canSpawn = settings.validBiomeCategories.get().contains(event.getCategory().toString());
			
			return (canSpawn) ? true : settings.validBiomes.get().contains(event.getName().toString()); 
		}
		
		/**
		 * Add a spawn entry for the supplied entity to the biome being loaded in {@link BiomeLoadingEvent}.
		 * <p>
		 * Adapted from Forge's {@code EntityRegistry.addSpawn} method in 1.12.2.
		 *
		 * @param event          The event
		 * @param entityType     The entity type
		 * @param itemWeight     The weight of the spawn list entry (higher weights have a higher chance to be chosen)
		 * @param minGroupCount  Min spawn count
		 * @param maxGroupCount  Max spawn count
		 * @param classification The entity classification
		 */
		private static void addSpawn(final BiomeLoadingEvent event, final EntityType<? extends Mob> entityType, final int itemWeight, final int minGroupCount, final int maxGroupCount, final MobCategory classification) {
			final List<MobSpawnSettings.SpawnerData> spawnersList = event.getSpawns()
					.getSpawner(classification);

			// Try to find an existing entry for the entity type
			spawnersList.stream()
			.filter(spawners -> spawners.type == entityType)
			.findFirst()
			.ifPresent(spawnersList::remove); // If there is one, remove it

			// Add a new one
			spawnersList.add(new MobSpawnSettings.SpawnerData(entityType, itemWeight, minGroupCount, maxGroupCount));
		}

		/**
		 * Add a spawn list entry for {@code entityTypeToAdd} to the biome being loaded in {@link BiomeLoadingEvent} with an entry for {@code entityTypeToCopy} using the same weight and group count.
		 *
		 * @param event                The event
		 * @param entityTypeToAdd      The entity type to add spawn entries for
		 * @param classificationToAdd  The entity classification to add spawn entries for
		 * @param entityTypeToCopy     The entity type to copy spawn entries from
		 * @param classificationToCopy The entity classification to copy spawn entries from
		 */
		private static void copySpawns(final BiomeLoadingEvent event, final EntityType<? extends Mob> entityTypeToAdd, final MobCategory classificationToAdd, final EntityType<? extends Mob> entityTypeToCopy, final MobCategory classificationToCopy) {
			event.getSpawns()
			.getSpawner(classificationToCopy)
			.stream()
			.filter(spawners -> spawners.type == entityTypeToCopy)
			.findFirst()
			.ifPresent(spawners ->
			event.getSpawns().getSpawner(classificationToAdd)
			.add(new MobSpawnSettings.SpawnerData(entityTypeToAdd, spawners.getWeight(), spawners.minCount, spawners.maxCount))
					);
		}
	}

}
