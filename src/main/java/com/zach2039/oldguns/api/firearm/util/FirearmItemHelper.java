package com.zach2039.oldguns.api.firearm.util;

import com.zach2039.oldguns.api.ammo.ProjectileType;
import com.zach2039.oldguns.api.firearm.FirearmSize;
import com.zach2039.oldguns.api.firearm.FirearmTypes.MechanismType;
import com.zach2039.oldguns.init.ModItems;
import net.minecraft.world.item.Item;

public class FirearmItemHelper {

	public static Item getDefaultAmmoItemForFirearm(FirearmSize size, MechanismType mechanism, ProjectileType projectile) {
		Item ammoItem = null;
		
		switch(size) {
		case HUGE:
		case LARGE:
			switch(mechanism) {
			case MATCHLOCK:
				switch(projectile) {
				case BIRDSHOT:
					ammoItem = ModItems.LARGE_STONE_BIRDSHOT.get();
					break;
				case MUSKET_BALL:
					ammoItem = ModItems.LARGE_STONE_MUSKET_BALL.get();
					break;
				default:
					break;
				}
				break;
			case WHEELLOCK:
			case CAPLOCK:
			case FLINTLOCK:	
				switch(projectile) {
				case BIRDSHOT:
					ammoItem = ModItems.LARGE_IRON_BIRDSHOT.get();
					break;
				case BUCKSHOT:
					ammoItem = ModItems.LARGE_IRON_BUCKSHOT.get();
					break;
				case MUSKET_BALL:
					ammoItem = ModItems.LARGE_IRON_MUSKET_BALL.get();
					break;
				case CANNONBALL:
					ammoItem = ModItems.MEDIUM_IRON_CANISTER_SHOT.get();
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
			break;
		case MEDIUM:
			switch(mechanism) {
			case MATCHLOCK:
				switch(projectile) {
				case BIRDSHOT:
					ammoItem = ModItems.MEDIUM_STONE_BIRDSHOT.get();
					break;
				case MUSKET_BALL:
					ammoItem = ModItems.MEDIUM_STONE_MUSKET_BALL.get();
					break;
				default:
					break;
				}
				break;
			case WHEELLOCK:
			case CAPLOCK:
			case FLINTLOCK:	
				switch(projectile) {
				case BIRDSHOT:
					ammoItem = ModItems.MEDIUM_IRON_BIRDSHOT.get();
					break;
				case BUCKSHOT:
					ammoItem = ModItems.MEDIUM_IRON_BUCKSHOT.get();
					break;
				case MUSKET_BALL:
					ammoItem = ModItems.MEDIUM_IRON_MUSKET_BALL.get();
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
			break;
		case SMALL:
			switch(mechanism) {
			case MATCHLOCK:
				switch(projectile) {
				case BIRDSHOT:
					ammoItem = ModItems.SMALL_STONE_BIRDSHOT.get();
					break;
				case MUSKET_BALL:
					ammoItem = ModItems.SMALL_STONE_MUSKET_BALL.get();
					break;
				default:
					break;
				}
				break;
			case WHEELLOCK:
			case CAPLOCK:
			case FLINTLOCK:	
				switch(projectile) {
				case BIRDSHOT:
					ammoItem = ModItems.SMALL_IRON_BIRDSHOT.get();
					break;
				case BUCKSHOT:
					ammoItem = ModItems.SMALL_IRON_BUCKSHOT.get();
					break;
				case MUSKET_BALL:
					ammoItem = ModItems.SMALL_IRON_MUSKET_BALL.get();
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		
		assert (ammoItem != null);
		
		return ammoItem;
	}
}
