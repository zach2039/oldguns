package com.zach2039.oldguns.text;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

/**
 * Adapted from Mekanism's APILang under the following license:
 * <p>
 * MIT License
 * <p>
 * Copyright (c) 2017-2020 Aidan C. Brady
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public enum OldGunsLang {
	MESSAGE_DEATH_FIREARM("death.attack.oldguns.firearm"),
	MESSAGE_DEATH_FIREARM_PLAYER("death.attack.oldguns.firearm.player"),
	MESSAGE_DEATH_ARTILLERY("death.attack.oldguns.artillery"),
	MESSAGE_DEATH_ARTILLERY_PLAYER("death.attack.oldguns.artillery.player")
	;

	private final String key;

	OldGunsLang(final String type, final String path) {
		this(Util.makeDescriptionId(type, new ResourceLocation(OldGuns.MODID, path)));
	}

	OldGunsLang(final String key) {
		this.key = key;
	}

	public String getTranslationKey() {
		return key;
	}

	private static String prefix() {
		return OldGuns.MODID + ".";
	}
}
