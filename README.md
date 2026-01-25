
Never Mod
=
Currently in-development and not in a playable state

A self-contained mod, that (hopefully) has a few mobs, current plans is a few piglin variants and two bosses, one structure or a few rooms added to vainilla structures, Gems(Items) with abilities to reward your bravery, and a Dimension 


You can acquire Gems by overcoming dungeons or Bosses, once you have all the Gems(thanos style) and Gold that pigs fears, you can build a contraption that combines them into a key to the final Dimension, where you fight the big Boss, which is conveniently the only way unlock the key's final ability (funny how life works :3)

NeoForge version: '2.0.139'
current version: 0.0.9
versioning: x.y.z(.ab.cc)
x: 'Extra stuff'>1>'stuff still needs to be done'
y: major feature milestone(all Mobs are functional)
z: minor feature milesstone(a mob is functional)
a: development signficant part of a feature(minor or major)
b: development minor part of a feature or base implmentation of a deature
c: development bug fixes

##### usable features
features that are currently
all of these features work as intended, (most are only accessible in creative mode)
- TeleportGem
- LinkedGem

##### functional features
These features dont work as intended but there core functionality work, when a feature relies on another that is either nonfunctional or yet to be implemented
- DimensionGem

##### non functional
these features are currently being implemented, but doesnt function or game breaking.
- AbsorptionGem
- Never_Key
- Ancient_Gold
- NeverDoor

##### not yet implemented
- Mobs
- Structures
- Never(Dimension)
- Forge

###### Other Mod Loaders:
I would like to port the mod to fabric and Quilt eventually, but have no idea when/if that will happen

Installation information
=======
to use only
a jar will be provided in the future but for now you need to build it your self

to use only:
1. Download to project
	- via git 
		1. in a terminal run `git clone https://github.com/BlasterRiv/NeverMod`
	- via zip
		1. click the green Code button
		2. click Download ZIP
		3. extract to directory of your choosing
2. in a terminal change to project directory 
	- `cd /*REPLACE-WITH-PATH-TO-PROJECT*/NeverMod` 
3. to build the JAR, run the following in the terminal
	1. ./gradlew build 
	2. Jar can now be found at the build\libs directory
	
	- (Alternatively you can run the following to just run it)./gradlew runClient
4. put the jar in your usual .minecraft mod directory
5. find bugs!

to edit and use:
1. Download to project
	- via git 
		1. in a terminal run `git clone https://github.com/BlasterRiv/NeverMod`
	- via zip
		1. click the green Code button
		2. click Download ZIP
		3. extract to directory of your choosing
2. in a terminal change to project directory 
	- `cd /*REPLACE-WITH-PATH-TO-PROJECT*/NeverMod` 
3. run the following in the terminal
	- run `./gradlew task` To see available gradle Tasks
	- modify desired code
	- finally run `./gradlew runClient` To see available gradle Tasks``
4. find bugs!


> **Note**: For Eclipse, use tasks in `Launch Group` instead of ones founds in `Java Application`. A preparation task must run before launching the game. NeoGradle uses launch groups to do these subsequently.


> Note: If at any point you are missing libraries in your IDE, or you've run into problems you can run `./gradlew --refresh-dependencies` to refresh the local cache. `./gradlew clean` to reset everything {this does not affect your code} and then start the process again.

HEY!! WHAT ARE YOU DOING! DON'T WATCH THAT, IT HAS SPOILERS!:
======
this is a just demo of some of the abilities so far, and they are expected to change especially the Dimension_Gem, you will not be able to use it like this in the future
![[DEMO.mp4]]
![[image]]



Contributions
bug fixes: you can submit a pull request for an existing issue, or  create one first if it doesn't exist, that exist, guidelines:
- a pull request should address one issue
- keep fixes as small as possible
- fixes shouldn't change the structure of the project, unless absolutely necessary
adding features() 
please open a feature request before working on it, I have a vision of what the mod should look like, but i would love to incorporate other ideas into the mod, so opening the request before hand gives us the change to try and integrate the ideas in to the mod without changing its identity   



___
#### Additional Resources: 
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


