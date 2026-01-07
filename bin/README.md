# GridQuest RPG

## Description
GridQuest RPG is a JavaFX-based mini RPG game played on a 10×10 grid dungeon.  
The player navigates using keyboard input (WASD), explores a maze-like map, fights enemies,
collects treasures, manages health and healing potions, and reaches the exit to save
their score to a MySQL leaderboard.

This project is designed to demonstrate Object-Oriented Programming (OOP) concepts,
JavaFX UI development, and basic database integration.

---

## Gameplay Overview
- The player starts inside a dungeon surrounded by walls.
- The outer layer of the map is fully blocked by walls, preventing escape.
- Walls are interconnected, forming a maze-like layout.
- Enemies must be defeated through turn-based combat.
- Defeating enemies increases score
- Player can exit the dungeon without defeating all enemies
- Treasures increase score and have a 50% chance to drop a healing potion.
- Healing potions can be used later to restore player health.
- Reaching the exit ends the game and saves the score.
- If the player’s HP reaches zero, the game ends.

---

## Controls
| Key | Action            |
|-----|-------------------|
| W   | Move Up           |
| A   | Move Left         |
| S   | Move Down         |
| D   | Move Right        |
| H   | Use Healing Potion|


---

## Features
- Keyboard-controlled movement (WASD)
- Maze-style dungeon with interconnected walls
- Turn-based combat system
- Multiple enemy types with different stats
- Treasure collection and score system
- Healing potion system
- JavaFX UI with CSS styling
- MySQL-based leaderboard

---

## Enemy Types
- **Goblin**: Lower HP and defense, moderate damage
- **Orc**: Higher HP, defense, and damage

---

## Player Classes
- **Warrior**: Higher HP and physical damage
- **Mage**: Higher attack power with lower HP

---

## How to Run

### Requirements
- Java JDK 17+
- JavaFX SDK 17+
- Eclipse IDE
- XAMPP (for MySQL)

---

### Running the Game
1. Open the project in Eclipse
2. Navigate to `MainApp.java`
3. Click **Run**

---

### JavaFX VM Arguments Setup
If JavaFX errors occur:

1. Click the arrow next to the Run button
2. Select **Run Configurations...**
3. Choose **MainApp** under Java Application
4. Open the **Arguments** tab
5. Add the VM arguments below
6. Replace the path with your JavaFX `lib` directory
7. Click **Apply** → **Run**

--module-path "PATH_TO_JAVAFX/lib" --add-modules javafx.controls,javafx.graphics

Example:
--module-path "G:\GridQuestRPG\src\openjfx-17.0.7_windows-x64_bin-sdk\javafx-sdk-17.0.7\lib" --add-modules javafx.controls,javafx.graphics

---

## Database Setup (XAMPP + MySQL)

### Step-by-Step:
1. Open **XAMPP Control Panel**
2. Start **Apache** and **MySQL**
3. Click **Admin** on MySQL (phpMyAdmin)
4. Open the **SQL** tab
5. Paste and run the query below

### SQL Script:
CREATE DATABASE GridQuestRPG;
USE GridQuestRPG;

CREATE TABLE scores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    score INT
);

## List of Class
### Core Package
- **GameMap**: Responsible for map generation, procedural maze walls, and placing enemies/treasures.
- **BattleSystem**: Contains the turn-based combat logic and outcome calculations.
- **TileType**: Defines the properties for map tiles (e.g., Wall, Floor, Door).

### Player Package
- **Player**: The base class for all player characters; handles shared logic and attributes.
- **Warrior**: A specialized player class focused on high HP and melee combat.
- **Mage**: A specialized player class focused on high magic damage.

### Enemy Package
- **Enemy**: The base class for all NPCs; handles shared AI and stat logic.
- **Goblin**: A specific weak enemy type for early-game encounters.
- **Orc**: A specific strong enemy type for late-game challenges.

### UI Package
- **MainMenu**: Manages the initial user interface and navigation flow.
- **GameScreen**: The main rendering engine for the active gameplay.
- **LeaderboardScreen**: Displays high scores pulled from the database.

### Data Package
- **ScoreDAO**: Data Access Object used to perform CRUD operations on the MySQL database.
- **Score**: The data model representing a single score entry.

## OOP Concepts Used
### Encapsulation
- Player stats, enemy stats, and map data are encapsulated within classes.
### Inheritance
- Player → Warrior, Mage
- Enemy → Goblin, Orc
### Polymorphism
- Different attack logic for each class and enemy type.
### Abstraction
- Abstract base classes define shared behavior for players and enemies.