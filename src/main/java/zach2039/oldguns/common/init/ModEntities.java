package zach2039.oldguns.common.init;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.entity.EntityArtilleryCannon;
import zach2039.oldguns.common.entity.EntityProjectile;

@ObjectHolder(OldGuns.MODID)
public class ModEntities
{
	@ObjectHolder("entity_projectile")
	public static final EntityEntry ENTITY_PROJECTILE = null;
	
	@ObjectHolder("entity_artillery_cannon")
	public static final EntityEntry ENTITY_ARTILLERY_CANNON = null;
	
	@Mod.EventBusSubscriber(modid = OldGuns.MODID)
	public static class RegistrationHandler 
	{
		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityEntry> event) {
			final EntityEntry[] entries = {
					createBuilder("entity_projectile")
							.entity(EntityProjectile.class)
							.tracker(500, 1, true)
							.build(),
					createBuilder("entity_artillery_cannon")
							.entity(EntityArtilleryCannon.class)
							.tracker(500, 10, true)
							.build(),
			};
			
			event.getRegistry().registerAll(entries);
		}
		
		private static int entityID = 0;
		
		/**
		 * Create an {@link EntityEntryBuilder} with the specified unlocalized/registry name and an automatically-assigned network ID.
		 *
		 * @param name The name
		 * @param <E>  The entity type
		 * @return The builder
		 */
		private static <E extends Entity> EntityEntryBuilder<E> createBuilder(final String name) 
		{
			final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
			final ResourceLocation registryName = new ResourceLocation(OldGuns.MODID, name);
			return builder.id(registryName, entityID++).name(registryName.toString());
		}
	}
}
