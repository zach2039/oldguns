#### v1.16.5-3.0.4-alpha

- Port for Minecraft 1.16.5
- Fix patchouli template not processing issue
- Edit mixins java version required
- Relocate patchouli pages for 1.16.5 version
- Fix data generation and add functionality
- Port all except entity, jei, and patchouli
- Remove artillery Port types
- Refactor file locations for 1.16.5 standard
- Add updateJson to mods.toml

#### v1.18.1-3.0.3-alpha
    
- Fix recipe overlap with iron gear set and medium iron musket ball
- Add lang for bombard entity and item (WIP)
- Add bombard (WIP)
- Add bombard render (WIP)
- Fix brewing recipe not appearing in JEI
- Add guide book content

#### v1.18.1-3.0.2-alpha

- Move common configs to server
- Guide book and config additions
- Add patchouli recipes, entires, and multiblock pages
- Edit mods.toml for license and depends
- Add entity.minecraft.chicken to refuseLow animals
- Add patchouli pages
- Add missing recipes for matchlock and wheellock mechanism
- Add more crafting items

#### v1.18.1-3.0.1-alpha

- Reimplement matchlock firearms
- Reimplement wheellock firearms
- Add black powder creation, medium and high grade
- Add piston and obsidian interaction to product wet high grade powder cakes
- Add nitrated soil, niter, and niter bedding to produce niter
- Add design notes and craft gating via notes
- Add loot spawn to design notes
- Add patchouli support (WIP)
- Add config options for loot and crafting

#### v1.18.1-3.0.0

- Rewrite for Minecraft version 1.18.1 
- Reimplement flintlock weapons
- Reimplement gunsmiths bench
- Reimplement recipes for weapons and misc
- Reimplement JEI support
- Add mixin for crafting support
- Reimplement configs
- Add exotic firearms with recipes disabled by default
- Add exotic firearms to dungeon loot for balance

#### v1.12.2-2.0.5

- Fixed https://github.com/grilled-salmon/oldguns/issues/1
- Added melter for bullet creation (not finished)
- Added more config options for weapons

#### v1.12.2-2.0.4

- Fixed more packet errors
- Fixed and re-balanced firearm effective ranges and accuracy values
- Added caplock firearms, parts, and recipes
- Reconfigured config file and defaults (A DELETION OF THE CURRENT CONFIG, IF PRESENT, IS REQUIRED)

#### v1.5.2

- Fixed recipe error with flintlock mechanism.

#### v1.5.1

- Fixed overlapped sulfur/fine gunpowder recipe.

#### v1.5.0

- Added iron nuggets and subsequent recipes for bullet creation.
- Changed amount of iron ingots/nuggets needed for each bullet bit recipe by one less than original (this fixes overriding of iron trap door recipe, which I have no idea how i missed that up until now).
- Added config options to allow customization of bullet bit/percussion cap crafting and crafting returns/yield.
- Made bullet bit recipes shapeless
- Added needlefire rifle (caplock based breechloader).
- Fixed cannon fire shot not lighting things on fire when hitting the side of a block
- Changed stack size of bullet bits (small/16,medium/12,large/8). 
- Changed placement of artillery pieces to the block the player is looking at, along with their rotation, rather than just appearing on their head.
- Rebalanced damages a tad. The general rule is weapons that have larger magazines have slightly less damage. But higher tiers of guns will always have just as good or better stats than then the previous tier (matchlocks\<flintlocks\<caplocks).
- General cleaning of code, to make additions of other weapons a bit easier.
- Added fire lance, an ancient chinese weapon that utilized a small explosive charge to spit fire and projectiles out of a barrel fixed to the tip of a spear.
- Removed the semi-realistic damage option from 1.5.0_exp (not yet released) for beta forge for mc 1.8.8 (Forge for mc 1.8.8 changed how potions were done, and I figure that I can redo the system once I figure out how to do bleeding effects the new way).
- Most crafting recipes play nice with ore dictionary now, so certain items can be crafted with similar parts in other mods (crafting breechloaders does not play nice with ore dict yet).
- No longer releasing seperate x16 and x32 textured mods. Now you can add the "resourcepacks" folder to your .minecraft folder and select the pack for x32 textures.

#### v1.4.0

-Added 12 pound cannon - A larger cannon with a longer range
-Added 6 pound mortar - An artillery piece used to lob shells or cannonballs at a high arc or over walls.
-Added mortar shells - Shells explode into shrapnel when they hit the ground. Good for getting people in trenches.
-Added incendiary ammo types to cannons, mortars - Spread fire with style. Burn things down!
-Added breech-loading weapons - Load with holding down right-click. No crafting needed.
-Added hacksaw - Used to convert guns to breech-loaders.
-Added gunpowder recipe with craftable sulfur - Can be disabled.
-Added config file - Options to tailor your experience. Beta realistic damage, alternative gunpowder recipe, etc. . -generates on load.
-Changed textures to be more vanilla stick like.

#### v1.3.0

-Added 6 Pound Cannon
-Added cannon tools
-Added artillery guide book
-Added caplock revolving rifle
-Added custom death messages
-Changed item format to use nbt instead of seperate items

#### v1.3.0

-Added 6 Pound Cannon
-Added cannon tools
-Added artillery guide book
-Added caplock revolving rifle
-Added custom death messages
-Changed item format to use nbt instead of seperate items

#### v1.2.0

-Fixed multishot weapon bug.
-Fixed Shears recipe overwrite (Derp on my part).
-Fixed Entities rendering black on shoot bug.
-Changed iron bits recipes.
-Added Caplock Revolver and subsequent parts (Revolver needs cylinder; load cylinder crafting grid to refill ammo or reload revolver one cartridge at a time with the cylinder in).
-Added first paper cartridges (Will help with loading other guns, now just needed to load revolver).
-Fixed single shot weapons jumping slots on fire.

#### v1.1.0

- Fixed Bowl dupe bug.
- Changed fine gunpowder recipe.
- Added flintlock pepperbox.
- Added flintlock blunderbuss.
- Added flintlock doublebarrel blunderbuss.

#### v1.0.0

- Release

