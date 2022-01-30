package com.zach2039.oldguns.init;

import java.util.function.Supplier;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.entity.monster.MusketeerSkeleton;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
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
	
}
