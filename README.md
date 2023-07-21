# Virtual_World_Java
Simulation game written in java as a project to learn object oriented programming

## About
Virtual world is a simple turn-based game in which a human moves along all kinds of plants and animals.  Every organism on the map has its own parameters (like strength, initiative and age).  
Strength is used to compare the final score of organism collision and initiative is used to determine the order of turns.

### How the game works?
- For every turn, all organisms make a move on the map.
- Player makes a move for a human and all the organisms moves are randomized.
- When the player plays the turn, all organisms make a turn starting from the higher initiative (or age if it's the same)
- If two organisms collide with each other, then one can either kill another, or they can both breed (if are the same kind)
- Organism with higher strength will kill the weaker one unless it has a special ability preventing this
- Human can also be killed and if so happens, the game can continue (just without the player)

## Controls
- Arrows -> determine human movement (if alive) and play turn
- ### Buttons
  - "Play turn" -> plays a turn for all organisms (human movement is determined by last input)
  - "Save" and "Load" -> saves and loads the game (only 1 save can be stored at a time)
  - "Activate power" -> activates super power for human (if alive)
 
## Special Abilities
- description coming soon
