package com.zach2039.oldguns.data.lang;

public class OldGunsCompendiumLang {

	public static final String LANDING = "Antique weapons for that colonial feel. Old Guns is a mod that adds early black powder weapons, and methods to produce and supply them.";
	
	public static class Basics {

		public static final String NAME = "The Basics";
		
		public static final String DESCRIPTION = "A general overview of mechanics of Old Guns. For more details on contained topics, refer to their respective sections.";
		
		public static class Welcome {
			
			public static final String ENTRY = "Old Guns";
			
			public static final String PAGE0 = "Old Guns adds a collection of mid-15th century to early 19th century black powder arms and materials. "
					+ "This compendium should assist any up-and-coming gunsmith or gunner with these arms and materials, and the processes to manufacture said items.";
			
			public static final String PAGE1 = "Contained within this book are guides to various concepts of Old Guns, like firearms, reloading, ammo manufactory, artillery, and other miscellaneous details; "
					+ "these guides are organized by category, so be sure to check the index for a fast reference to any topic.";
			
		}
		
		public static class GunsmithsBench {
			
			public static final String ENTRY = "Gunsmith's Bench";
			
			public static final String PAGE0 = "To create firearms, tools, and some materials, a Gunsmith's Bench must be used. $(br)$(br)"
					+ "The Gunsmith's Bench can be crafted as follows.";
			
			public static final String PAGE1 = "The Gunsmith's Bench can also create crafting tools like the $(l:basics/mortar_and_pestle)Mortar and Pestle$(/l).";
		
		}
		
		public static class MortarAndPestle {
			
			public static final String ENTRY = "Mortar and Pestle";
			
			public static final String PAGE0 = "The Mortar and Pestle is a crafting tool which grinds reagents into various dusts.";
			
			public static final String PAGE1 = "The dusts produced are used in the production of $(l:basics/black_powder)Black Powder$(/l).";
		
		}
		
		public static class BlackPowder {
			
			public static final String ENTRY = "Black Powder";
			
			public static final String PAGE0 = "Black Powder is an important alchemical output used in $(l:basics/firearms)Firearms$(/l), $(l:basics/artillery)Artillery$(/l), and other explosive tools.$(br)$(br)"
					+ "Black Powder has grades: low, medium, and high. Normal gunpowder is low grade. Firearms and artillery can require certain grades.$(br)$(br)"
					+ "See $(l:black_powder/types)Black Powder Production$(/l) for more details.";
			
			public static final String PAGE1_HEADING = "Medium Grade";
			public static final String PAGE1_HEADING2 = "High Grade";

		}
	}
	
}
