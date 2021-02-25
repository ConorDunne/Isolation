# Isolation

Isolation is a single-player, 2D platformer created for the module COMP30540 Game Development. The game is adapted from
a 2D topdown shooter created by Dr Abraham Campbell for the module using Java AWT and Java Swing.

## Storyline

The idea of Isolation came from the African proverb, "If you want to go fast, go alone. If you want to go far, go
together", along with the social isolation that has been brought on by Covid-19.

The game depicts a single man, the player, going out into the darkness with only his oil lamp for light. However, with
the limited fuel in his lantern, it dims quickly. He needs to gather oil on his journey to keep the light alive. Without
it, the darkness will envelop him.

## Playing the game

### Controlling the Character

The controls for the game use the keys, a, d, and the space bar. The a key moves the player left. The d key moves the
player right, and the space bar lets the character jump.

## Assets Used

### Software

Game Engine created by Dr Abraham Campbell  
Game Soundtrack created on [Musescore 3.0](https://musescore.org)  
Game Maps assembled on [Tiled Map Editor](https://www.mapeditor.org/)  
Image Manipulation done on [Adobe Photoshop](https://www.adobe.com/products/photoshop.html#)

### Visual Assets

Background created by [David Maarah - Aethrall](https://aethrall.itch.io/demon-woods-parallax-background)  
Map Tiles created by [Bayat Games](https://bayat.itch.io/platform-game-assets)  
Menu Buttons created by [Mikiz](https://mikiz.itch.io/buttons-pack-46-buttons)  
Character Sprite created by [rvros](https;//rvros.itch.io/animated-pixel-hero)  
Oil Refill Image created by [Raven Tale](https://raventale.itch.io/daily-doodles-pixelart-asset-pack)  
Level Maps assembled by [Conor Dunne](https://github.io/ConorDunne)  
Menu Star Background created by [Conor Dunne](https://github.io/ConorDunne)  
Smokey Darkness created by [Conor Dunne](https://github.io/ConorDunne)

### Audio Assets

Game Soundtrack created by [Conor Dunne](https://github.io/ConorDunne)  
Death Sound Effect created by [Conor Dunne](https://github.io/ConorDunne)  
Jump Sound Effect created by [JDWasabi](https://jdwasabi.itch.io/8-bit-sound-effects-pack)  
Oil Lamp Refill Sound Effect created by [JDWasabi](https://jdwasabi.itch.io/8-bit-sound-effects-pack)

## Main Feature

The feature that makes this game unique is the shrinking viewport caused by the oil lamp running out of fuel. This adds
a time limit to the levels that push the player forward which creates a sense of urgency. The refuelling lamps are
spaced out near the edge of the player's vision giving them just enough time to collect the oil and to continue going.

## Work Diary

When I downloaded the initial game, I first deleted all enemy logic as I knew I would not need them in my game, and I
wanted to keep the code as clean and simple as I could. I then created an initial level using Tiled to work on basic
collisions. Once the initial map was created, I made a Level interface and object so I could add extra levels easier.

As I was making a platformer and not a top-down shooter, I needed the player to be able to move left and right beyond
the scope of the screen. The easiest way I found to do this is to fix the player in the centre of the screen and to move
the background instead.

To do this, I created an XPos variable in the Level object and used this to locate the player instead of using the
players Centre Point. I then changed the levels XPos depending if the player moved left or right and used this XPos to
draw the map from different points, making it look like the player was moving.

To work on player collision with the ground, I first added gravity to the player which would move them down a set amount
every game loop. In the Level object, I created a grounded method which would tests if a player is touching the ground.
This worked by testing if the players Y coordinate is at a certain height given its X coordinate. By setting different Y
coordinates to be grounded when between different points, you can create different ground levels. If the player is
grounded, I moved the player down a distance of 0.

I wanted to let the player jump as well, instead of only falling. If the player presses the space bar when the character
is grounded, I moved the character up the height I wanted to jump. However, this was very sudden, so to add some
smoothness and realism, I added a fall speed to move the player along the Y axis. Every tick, I subtracted 0.1 from this
to give a gravity of 0.1. This causes the players fall speed to increase as they fall. When the player is grounded, I
set this to 0 so they would not fall through the floor, and if the player presses space, I set this to 4 to give the
character an initial upward velocity.

After this was working, I had to stop the player from clipping through the walls of the map. For this, I copied the
grounded function but gave it a second input for the players X coordinate. If the player would be in the wall, I stopped
the player from moving any further. This X coordinate was needed as if I used the same one from the grounded function,
it would only activate when the player was in the wall which caused them to get stuck.

I then needed to work on my main feature of the game. The oil lamp and viewport. To do this, created a simplistic smoky
image on photoshop and added a pixilation filter to make it fit more with the aesthetic of the game. I then created a
rectangle the size of the screen and used the shape subtract function to cut out a circle where the player is. I used
this shape to clip the smoky image to cover the screen except around the player. To make it more fitting to the game, I
drew the image a second time to cover the entire screen, even over the viewport. I then change the transparency of both
images and had it change with the size of the circle, indicated by the variable fuel.

The next task to be done was to allow the player to refuel their lamp. This was done by putting bottles of oil on the
map. I created a list of these bottles and if the player came close enough to the bottle, it would be removed from the
list and the fuel variable will be reset to 100%. I then set it to end the game if the fuel reached 0 as this means the
player’s failed the level.

The game then tested if the player reached the end of the level, and if so, would load the next one. However, if it was
on the last level, it then played some credits, which doubled as a reference sheet for the assets used in this module.
After cleaning up the game, the menu, and adding music and sound effects, the game was then complete.

## Future Work

If continuing this project, I would like to improve on the maps of this game by drawing the tiles individually as they
enter the screen. This is because now, the map cannot be made too big or Java will fail to draw it after a point. By
drawing the tiles individually, this would save memory and allow bigger map sizes. With the application Tiled, you can
export the maps as Json files which contains arrays for what blocks are where on the map.

I would also like to improve the character animation. The character pack I used contains images for running and jumping
as well as other animations. I would also like to flip the image depending on which way the player is moving.

The next thing I would like to do is to add extra levels along with different types of obstacles and events that can
occur. This would flesh out the game and make it into a proper adventure.

Finally, I would like to create a beginning and ending scene for the game and the levels to show the games story line.
This would include the player leaving a village and other villagers to travel alone, only to return with less people
there. The ending would require the player to return to the start to meet up with the other villagers to travel with
them as it would not be possible to complete the game alone as this is the only way to beat Isolation.  
