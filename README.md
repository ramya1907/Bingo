# Bingo Game

Bingo Game is a simple console-based application that allows users to play the classic game of Bingo. The game supports creating custom bingo cards, marking numbers, and determining winners.

### Running the Game

1. Clone or download the repository.
2. Open the project in your Java IDE or compile and run the code using the command line.
3. Execute the `BingoRunner` class to start the game.

## Features

- **Play Bingo:** Enjoy a game of Bingo by marking off numbers as they are called.
- **Create Bingo Card:** Create custom bingo cards with specified rows and columns.
- **Set Number Separator:** Personalize the separator used between numbers on the bingo cards.
- **List Existing Cards:** View and print the details of existing bingo cards.
- **Set Bingo Card Size:** Adjust the size (rows and columns) of the bingo cards.

## Usage

1. Choose from the main menu options by entering the corresponding number.
2. Follow the on-screen instructions to play the game, create cards, and manage settings.
3. Have fun playing Bingo!

## Code Structure

The project is organized into several classes:

- `BingoCard`: Represents a Bingo card with methods for resetting, marking numbers, and checking for a winner.
- `BingoController`: Manages the game, including options like playing, creating cards, and setting game parameters.
- `BingoRunner`: The main class to execute to start the Bingo game.
- `Defaults`: Contains default values and settings for the game.
- `Toolkit`: Utility class for input/output operations.
