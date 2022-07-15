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
					+ "The Gunsmith's Bench can also create crafting tools like the $(l:basics/mortar_and_pestle)Mortar and Pestle$(/l).";
		
		}
		
		public static class MortarAndPestle {
			
			public static final String ENTRY = "Mortar and Pestle";
			
			public static final String PAGE0 = "The Mortar and Pestle is a crafting tool which grinds reagents into various dusts.$(br)$(br)"
					+ "The dusts produced are used in the production of $(l:basics/black_powder)Black Powder$(/l).";

		}
		
		public static class BlackPowder {
			
			public static final String ENTRY = "Black Powder";
			
			public static final String PAGE0 = "Black Powder is an important alchemical output used in $(l:basics/firearms)Firearms$(/l), $(l:basics/artillery)Artillery$(/l), and other explosive tools.$(br)$(br)"
					+ "Black Powder has grades of low, medium, and high. Normal gunpowder is low grade. Firearms and artillery can require certain grades.$(br)$(br)"
					+ "See $(l:black_powder_production/low_grade)Black Powder Production$(/l) for more details.";
	
		}
		
		public static class Firearms {
			
			public static final String ENTRY = "Firearms";
			
			public static final String PAGE0 = "Firearms are ranged, black powder weapons that have high damage and long reload time.$(br)$(br)"
					+ "Various types of firearm mechanisms exist: Matchlock, Wheellock, Flintlock, Caplock; these types have increasing "
					+ "damage and reliability, as well as variants that alter shot spread, ammo capacity, and other attributes.";

		}
		
		public static class Artillery {
			
			public static final String ENTRY = "Artillery";
			
			public static final String PAGE0 = "Artillery includes various gun and missle emplacements that are used to lay siege "
					+ "to fortifications or deal massive damage to groups of mobs or players.$(br)$(br)"
					+ "While more suited to medium to large-scale engagements, artillery pieces can still find use as home"
					+ " defense for the more adventurous artilleryman.";
			
		}
		
		public static class DesignNotes {
			
			public static final String ENTRY = "Design Notes";
			
			public static final String PAGE0 = "Certain recipes in the Gunsmith's Bench require Design Notes to craft. "
					+ "These notes can be found within dungeons or shipwrecks.$(br)$(br)"
					+ "Some parts like advanced mechanisms for firearms require notes to craft, but can also be found "
					+ "as loot.";

		}
	}
	
	public static class Firearms {

		public static final String NAME = "Firearms";
		
		public static final String DESCRIPTION = "Details on handheld ranged weapons that employ black powder to launch projectiles.";
		
		public static class General {
			
			public static final String ENTRY = "General Information";
			
			public static final String PAGE0 = "Matchlock and Wheellock firearms are craftable from the outset of spawn, but more advanced types "
					+ "and exotic firearms require finding $(l:basics/design_notes)Design Notes$(/l) that can be used in the Gunsmith's Bench to enable crafting.";
			
			public static final String PAGE1 = "While most firearms are reloaded in the user's inventory, modifications can be applied to allow breechloading, "
					+ "which allows reloading in a manner similar to the crossbow.";
			
			public static final String PAGE2 = "Firearms are also limited to the type of propellant that can be used, depending on the type.$(br)$(br)"
					+ "Matchlock weapons can reload with low or medium grade black powder. $(br)Wheellock weapons can reload with medium or high grade "
					+ "black powder.";
			
			public static final String PAGE3 = "Flintlock weapons can reload with medium or high grade black powder. $(br)Caplock weapons can reload with high grade "
					+ "black powder.$(br)$(br)$(l:firearms/paper_cartridges)Paper Cartridges$(/l) can also be used instead of loose powder and shot.";
		}
		
		public static class PaperCartridges {
			
			public static final String ENTRY = "Paper Cartridges";
			
			public static final String STONE_PAPER_CARTRIDGE = "Stone Paper Cartridge";
			public static final String IRON_PAPER_CARTRIDGE = "Iron Paper Cartridge";
			public static final String LEAD_PAPER_CARTRIDGE = "Lead Paper Cartridge";
			
			public static final String PAGE0 = "Paper Cartridges are prepared shot and powder enclosed within a waxed paper casing. Using paper cartridges "
					+ "allows more efficient ammo storage and faster reloading of muzzleloaders.";
		}
		
		public static class Recipes {
			
			public static final String ENTRY = "Recipes";
			
		}
	}
	

	public static class Artillery {

		public static final String NAME = "Artillery";
		
		public static final String DESCRIPTION = "Details on siege weapons that launch destructive projectiles.";
		
		public static class General {
			
			public static final String ENTRY = "General Information";
			
			public static final String PAGE0 = "Artillery pieces, whether they be cannons, field guns, rockets, or other types, are used to do "
					+ "strong damage to structures and mobs.$(br)$(br)"
					+ "Artillery is normally expensive to create and load when compared to firearms, but provide more utility in terms of projectile, like "
					+ "Canister Shot, Grapeshot, and other types.";
		}
		
		public static class ArtilleryTools {
			
			public static final String ENTRY = "Artillery Tools";
			
			public static final String POWDER_CHARGE = "Powder Charge";
			
			public static final String PAGE0 = "Operating artillery requires various tools to prep ammo and charges during loading.";
			
			public static final String PAGE1 = "The Powder Charge contains propellant used to launch any loaded ammunition.$(br)$(br)"
					+ "Using smaller or larger charges will decrease or increase the launch velocity of projectiles, respectively.";
			
			public static final String PAGE2 = "The Ram Rod is used to push charges or ammunition down the barrel of an artillery piece "
					+ "after it has been placed inside.";
			
			public static final String PAGE3 = "The Long Match is used to fire an artillery piece after both the charge and ammuntion has been "
					+ "loaded and rammed.";
			
			public static final String PAGE4 = "The Gunner's Quadrant can be used via right-clicking the artillery piece to display "
					+ "information about contents and loading state.$(br)"
					+ "When a artillery piece is loaded, the Gunner's Quadrant can also give a trajectory preview when held.";
		}
		
		public static class LoadingAndFiring {
			
			public static final String ENTRY = "Loading and Firing";
			
			public static final String PAGE0 = "To load a conventional artillery piece, like a cannon:$(br)$(br)"
					+ "1) Right-click with a Powder Charge.$(br)$(br)"
					+ "2) Right-click with a Ram Rod.$(br)$(br)"
					+ "3) Right-click with a projectile, like a cannonball.$(br)$(br)"
					+ "4) Right-click with a Long Match to fire.$(br)$(br)";
			
		}
		
		public static class AmmoTypes {
			
			public static final String ENTRY = "Ammo Types";
			
			public static final String CANNONBALL = "Cannonball";
			public static final String GRAPESHOT = "Grapeshot";
			public static final String CANISTER_SHOT = "Canister Shot";
			
			public static final String PAGE0 = "Artillery can be loaded with varying types of ammo, providing different projectile effects.$(br)$(br)"
					+ "Some types available are listed in the following pages.";
			
			public static final String PAGE1 = "Cannonballs are the most simple of projectiles. It breaks blocks on impact and does major damage "
					+ "to any mobs hit.";
			
			public static final String PAGE2 = "Grapeshot functions as a short-range anti-personnel shot, similar to a blunderbuss blast.";
			
			public static final String PAGE3 = "Canister Shot is a fused explosive shot that bursts after a short time, showering the battlefield with "
					+ "small projectiles.";
		}
		
		public static class Recipes {
			
			public static final String ENTRY = "Recipes";
			
		}
	}
	
	public static class BlackPowderProduction {

		public static final String NAME = "Black Powder Production";
		
		public static final String DESCRIPTION = "Details on the production of black powder.";
		
		public static class LowGrade {
			
			public static final String ENTRY = "Lo-grade Blk Powder";
			
			public static final String PAGE0 = "Low-grade Black Powder is the most basic type of black powder available for firearms. "
					+ "Normal gunpowder found in the world can be considered low-grade; it has weaker explosive properties, "
					+ "making it suitable for more crude weapons like the Matchlock firearm.";
			
			public static final String PAGE1 = "Low-grade Black Powder can be acquired via conventional means.";
			
		}
		
		public static class MediumGrade {
			
			public static final String ENTRY = "Med-grade Blk Powder";
			
			public static final String PAGE0 = "Medium-grade Black Powder is more potent black powder for firearms; while production is more involved "
					+ "than with Low-grade Black Powder, this propellant offers higher performance and is required in mid-tier firearms";
			
			public static final String PAGE1 = "Creating Medium-grade Black Powder requires Niter, Sulfur, and Charcoal. While charcoal can be gathered "
					+ "by conventional means, Niter requires more elaborate setups to produce and Sulfur must be extracted from Nether items.";
			
		}
		
		public static class HighGrade {
			
			public static final String ENTRY = "Hi-grade Blk Powder";
			
			public static final String PRESSING = "Corning Press";
			
			public static final String HIGH_GRADE_CAKE = "Black Powder Cake";
			
			public static final String PAGE0 = "High-grade Black Powder is the most refined and potent form of black powder, and is required in high-tier firearms.$(br)$(br)" +
					"High-grade Black Powder is derived from wet medium-grade black powder via a process called \"Corning\". " +
					"Corning provides more weight per volume, a more durable powder, and a reliable burn rate."; 
			
			public static final String PAGE1 = "Corning involves crushing wet powder into a cake and drying it in the sun.";
			
			public static final String PAGE3 = "To create a Wet Cake of High-grade Black Powder, one must crush a Block of Wet Medium-grade Black Powder "
					+ "using a piston and an Obsidian block as a crushing surface.";
			
			public static final String PAGE4 = "The cake is broken into powder after drying.";
			
		}
		
		public static class Niter {
			
			public static final String ENTRY = "Niter";
			
			public static final String CRYSTALIZATION = "Crystalization";
			
			public static final String PAGE0 = "Niter (or Saltpeter) is an oxidizer used in black powder. Niter must be crystalized from Liquid Niter in a "
					+ "boiling Cauldron. $(br)$(br)A Cauldron of Liquid Niter is boiled by placing a suitable heat source beneath the Cauldron; when the "
					+ "process begins, an audible boiling noise can be heard and bubbles will appear.";
			
			public static final String PAGE2 = "To collect Liquid Niter, once must brew Nitrated Soil gathed from Niter Bedding in a brewing stand with "
					+ "Water Bottles beneath, leaching the Niter from the soil.";
			
			public static final String PAGE3 = "Nitrated Soil can be harvested from Niter Bedding by right-clicking when a white efflorescence is present on the block.";
			
			public static final String PAGE4 = "Niter Bedding can accure efflorescence under farm animals or slowly from dripstone droplets.";
			
		}
		
		public static class Sulfur {
			
			public static final String ENTRY = "Sulfur";
			
			public static final String PAGE0 = "Sulfur is used as fuel and a ignition temperature reducer in black powder.";
			
			public static final String PAGE1 = "Sulfur can be extracted from Netherrack and Nether Quartz using a Mortar and Pestle";
			
		}
		
		public static class Fuses {
			
			public static final String ENTRY = "Fuses";
			
			public static final String PAGE0 = "Fuses and Match Cords are used in explosives and firearm construction, respectively; $(br)$(br)"
					+ "Fuses are obtained by using a Match Cord on a Cauldron of Liquid Niter.";
			
			public static final String PAGE1 = "Bark Strands are used in the production of Match Cords, and are obtained by stripping a log "
					+ "with shears, similar to stripping a log with an axe.";
		}
		
		public static class Recipes {
			
			public static final String ENTRY = "Recipes";
			
		}
	}
	
}
