package com.zach2039.oldguns.init;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;

public class ModSpawnPlacements {
	
	static {
		//SpawnPlacements.register(ModEntities.MUSKETEER_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		//SpawnPlacements.register(ModEntities.HARQUEBUSIER_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Monster::checkMonsterSpawnRules);
	}
	
	public static void register() {}
	
}