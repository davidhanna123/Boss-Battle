RPG (Game) By: David Hanna and Ryan Liyanage

--------------------------------------
To evaluate, open and run "Game.java".
--------------------------------------

Table of Contents:
I.   CODE EVALUATION NOTES
II.  HOW TO PLAY
III. MECHANICS OF THE GAME
IV.  GAMEPLAY (HOW TO WIN)
V.   IMAGE CREDITS.

I. CODE EVALUATION NOTES:

The project is comprised of 4 files.

-------------
Expectations:
-------------
Arrays: Everywhere

Sorting: Bubble sort - see BubbleSortGameover.java

Search Function: Linear searches are used to read movelist.txt, enemyinfo.txt.

2-D Arrays: Used for the cooldown mechanic, and character data.

Data files: character.txt, movelist.txt, enemyinfo.txt, death.txt

Methods: Everywhere

Advanced Error Traps: Cooldown mechanic and endeavour situation. 

String Manipulation: There is a block of string manipulation for reading non-damaging moves. Find the word "substring" using the IDE to locate. It is located in the method "executestatchange".

Swing programming.

---------------------------------
Tested Machine System Properties:
---------------------------------
DrJava Version drjava-20160913-225446
java.runtime.name = Java(TM) SE Runtime Environment
java.runtime.version = 1.8.0_211-b12
os.arch = amd64
os.name = Windows 10
java.version = 1.8.0_211
Number of processors/cores: 12


II. HOW TO PLAY: 

--------------
Map Controls: 
--------------
When you enter the map, your character will be represented by a BLACK dot. Press and hold the arrow keys to move your character. To fight enemies move towards the RED dots.

There are 4 areas in total, each area has a boss. To proceed to the next area you must defeat the current area's boss. To encounter the boss, you must defeat 3 enemies.

When the boss appears, all other enemies will disappear. The boss will spawn in the middle of the map represented by a YELLOW dot.

----------------
Battle Controls: 
----------------
When you a enter a battle, select your move right under the enemy picture. There are 4 moves that you can have. 

When you select a move, information about the selected move will appear on the bottom left. When are happy with your selection, press the attack button - located above the flee button.

You can press flee to run away from the battle. 

Your name is Weenie. Your stats are located on the top left panel. You can view your level, health (the green bar), attack, defence, dexterity and the amount of expereince you have until you level up. 

The log panel is on the top right corner, it displays all the events that happen in the battle. If you die confused and get revived, check the log to see what the enemy did.

----------------
Death:
----------------
You die when your HP reaches 0. The game will revive you back, but it will not count your win towards reaching/defeating the boss.


III. MECHANICS:

Damage is calculated through the POWER of the move you selected, your ATK, and the opposing side's DEF.

DEX influences which side attacks first.

Depending on the move, players or enemies can either heal, buff stats, or debilitate the opposing side's stats.

Boss's have special moves that will execute only once per battle. Boss's will do a LARGE heal only once per battle. These are executed when the boss reaches below a certain health threshold.

Each move has a cool down. If you can't use any moves there will be an option to endeavor.

When you defeat an enemy you have a chance to learn one of their moves.

Leveling up increases your stats.



IV. GAMEPLAY (HOW TO WIN)

The game is nerfed for evaluation purposes.

You win after you beat the final boss located in area 4. 

Even though you can, do NOT replace any of your moves if you want to win fast. Your starting moves given are extremely powerful and normally unobtainable without grinding and until area 4. These moves will rush you to the final boss. However, feel free to replace these moves if more powerful ones are presented. 

General tactic to win every single battle: Keep debuffing the enemy's attack while buffing yours. Do not forget to heal. When the enemy starts doing negligible damage, spam Cloudkill. **If you keep debuffing a boss's ATK past a certain threshold, the boss will buff it's ATK until it is above that threshold.  

--------
Features
--------
There are 13 different enemies to battle, with over 58 different moves to learn.

Enjoy and good luck.


V. IMAGE CREDITS.

------------------------------------------------------
https://grognard.booru.org/index.php?page=post&s=list:
------------------------------------------------------
These images are uploaded to this website and have no tagged artists.

Ancient Warrior
Cult Leader
Imperator
Lower Demon
Mercenary
Panthera Soldier
Reaper
Soldier
Tank
Undead Space Marine
Water Oracle
Wizard

------------------------------
Buriedbornes(R) texture assets
------------------------------
Dragon Knight
Mystic Elk
Lord of The Flies
The Demiurge

-----------------
Final Fantasy VII 
-----------------
Battletheme.wav (unused) can be found at https://www.youtube.com/watch?v=QY1Vetd7OCs

