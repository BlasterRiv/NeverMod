Never Mod
==
Currently in-development and not in a playable state

A self-contained mod, that (hopefully) has; a few Enemies, some Bosses, Dungeons/rooms added to vanilla structures, a few Items with unique abilities, and a new Dimension. 

You can acquire Gems by overcoming dungeons or Bosses, theses Gems have abilities to reward your bravery, once you have all the Gems(thanos style) and *the Gold that pigs fears*, you can build a contraption that combines them into a key to *the place that never was*dimension, where you fight the Big Boss, which is conveniently the only way unlock the key's final ability (funny how life works :3).

Plans
====
### Current Plans
#### Mobs
- a few piglin variants
- 2 or 3 bosses, 
#### Items 
- Gems with unique abilities
- Key that leads to dimension and has abilities
#### Places
- Two dungeons or one dungeon with rooms added to vanilla structures, 
- a new Dimension. 

### Status
##### Version
Current version: 0.0.9 <br>
NeoForge version: '2.0.139'
###### Versioning: x.y.z(.ab.cc):
x: 'Extra stuff' > 1 > 'stuff still needs to be done'<br>
y: major feature milestone(ex: all Mobs are functional)<br>
z: minor feature milestone(ex: a mob is functional)<br>
a: development significant part of a feature(minor or major)<br>
b: development minor part of a feature or base implementation of a feature<br>
c: development bug fixes

##### Features
###### Usable features
features that are currently work as intended, (most are only accessible in creative mode).
- TeleportGem
- LinkedGem

###### Functional features
These features don't work as intended but there core functionality work, when a feature relies on another that is either nonfunctional or yet to be implemented.
- DimensionGem

###### Non-functional
these features are currently being implemented, but doesn't function or are game breaking.
- AbsorptionGem
- Never_Key
- Ancient_Gold
- NeverDoor

###### Not yet implemented
- Mobs
- Structures
- Never(Dimension)
- Forge Contraption


>Other Mod Loaders: I would like to port the mod to fabric and Quilt eventually, but have no idea when/if that will happen

Installation information
=======
In the future a jar will be provided but for now you need to build it your self

To use only:
1. Download to project
	- via git 
		1. in a terminal run `git clone https://github.com/BlasterRiv/NeverMod`
	- via zip
		1. click the green Code button
		2. click Download ZIP
		3. extract to directory of your choosing
2. In a terminal change to project directory 
	- `cd /*REPLACE-WITH-PATH-TO-PROJECT*/NeverMod` 
3. Build the JAR, run the following in the terminal
	1. `./gradlew build`
	2. Jar can now be found at the build/libs directory
4. Put the jar in your usual .minecraft mod directory
5. Find bugs!

To edit and use:
1. Download to project
	- via git 
		1. in a terminal run `git clone https://github.com/BlasterRiv/NeverMod`
	- via zip
		1. click the green Code button
		2. click Download ZIP
		3. extract to directory of your choosing
2. In a terminal change to project directory 
	- `cd /*REPLACE-WITH-PATH-TO-PROJECT*/NeverMod` 
3. Run the following in the terminal
	- run `./gradlew task` To see available gradle Tasks
	- modify desired code
	- finally run `./gradlew runClient`
4. Find bugs!


> **Note**: For Eclipse, use tasks in `Launch Group` instead of ones founds in `Java Application`. A preparation task must run before launching the game. NeoGradle uses launch groups to do these subsequently.


> Note: If at any point you are missing libraries in your IDE, or you've run into problems you can run `./gradlew --refresh-dependencies` to refresh the local cache. `./gradlew clean` to reset everything {this does not affect your code} and then start the process again.

Contributions
======
##### Adding features
Please start a feature request discussions before working on anything, I would love to incorporate other ideas into the mod, but I have a vision i would like to preserve, opening the discussion before hand gives us the chance to talk about the idea, and try to integrate it into the mod without changing its identity.
##### Bug fixes: 
you can submit a pull request for an existing issue, or create one first if it doesn't exist
###### Guidelines:
- Changes shouldn't be bundled together, ideally a pull request should address one issue
- Changes should only be as big as the need to be
- Don't change the structure of the project, unless absolutely necessary
- Include a description of how the change fixes the issue, no need to get into details, just what caused the issue(if the causes was unknown beforehand), and how the change fixes it.

---
#### HEY!! WHAT ARE YOU DOING! DON'T WATCH THAT, IT HAS SPOILERS!:

This is a just demo of some of the abilities so far, and they are expected to changed especially the Dimension_Gem, which you will not be able to use it like this in the future
<video src="DEMO.mp4" width="320" height="240" controls></video>

___
Additional Resources: 
======
Community Documentation: https://docs.neoforged.net/  
NeoForged Discord: https://discord.neoforged.net/

[Template information](https://github.com/NeoForgeMDKs/MDK-1.20.6-NeoGradle)
this project used NeoGradle-1.20.6 at first, but now changed to use ModDevGradle-1.21.1

##### Mapping Names:

By default, the MDK is configured to use the official mapping names from Mojang for methods and fields 
in the Minecraft codebase. These names are covered by a specific license. All modders should be aware of this
license, if you do not agree with it you can change your mapping names to other crowdsourced names in your 
build.gradle. For the latest license text, refer to the mapping file itself, or the reference copy here:
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md


