package com.zach2039.oldguns.init;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class ModMaterials {
	// Solid
	public static final Material BLACK_POWDER = (new MaterialBuilder(MaterialColor.COLOR_BLACK).flammable()).build();
	
	public static final Material WET_BLACK_POWDER = (new MaterialBuilder(MaterialColor.COLOR_BLACK).destroyOnPush()).build();
	
	public static final Material BLACK_POWDER_CAKE = (new MaterialBuilder(MaterialColor.COLOR_BLACK).flammable().destroyOnPush()).build();
	
	// Liquid
	public static final Material LIQUID_NITER = fluid(MaterialColor.COLOR_LIGHT_GRAY).flammable().build();
	
	
	private static MaterialBuilder fluid(final MaterialColor materialColor) {
		return new MaterialBuilder(materialColor)
				.noCollider()
				.notSolidBlocking()
				.nonSolid()
				.destroyOnPush()
				.replaceable()
				.liquid();
	}
			
	/**
	 * Extension of {@link Material.Builder} that allows access to the private and protected methods from the Vanilla class.
	 */
	private static class MaterialBuilder extends Material.Builder {
		private static final Method NOT_SOLID_BLOCKING = ObfuscationReflectionHelper.findMethod(Material.Builder.class, /* notSolidBlocking */ "m_76360_");

		public MaterialBuilder(final MaterialColor color) {
			super(color);
		}

		@Override
		public MaterialBuilder liquid() {
			return (MaterialBuilder) super.liquid();
		}

		@Override
		public MaterialBuilder nonSolid() {
			return (MaterialBuilder) super.nonSolid();
		}

		@Override
		public MaterialBuilder noCollider() {
			return (MaterialBuilder) super.noCollider();
		}

		public MaterialBuilder notSolidBlocking() {
			try {
				NOT_SOLID_BLOCKING.invoke(this);
			} catch (final IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException("Failed to call Material.Builder#notOpaque", e);
			}

			return this;
		}

		@Override
		public MaterialBuilder flammable() {
			return (MaterialBuilder) super.flammable();
		}

		@Override
		public MaterialBuilder replaceable() {
			return (MaterialBuilder) super.replaceable();
		}

		@Override
		public MaterialBuilder destroyOnPush() {
			return (MaterialBuilder) super.destroyOnPush();
		}

		@Override
		public MaterialBuilder notPushable() {
			return (MaterialBuilder) super.notPushable();
		}
	}
}
