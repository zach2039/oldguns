package com.zach2039.oldguns.util;

import java.util.Random;

public class ModRandomUtil {
	public static int getRandMinMax(Random rand, int min, int max) {
	    return (int) ((rand.nextDouble() * (max - min)) + min);
	}
}
