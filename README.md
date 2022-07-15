# Angels-and-Devils-Game
Java program to play the Angels and Devils game in terminal.

## Description
The game contains an angel and a demon. Each player plays in turn.
* At first, the angel is placed in the middle of the chessboard.
* The angel has a constant power K, which describes the furthest square it can jump to. Firstly, the angel moves to an empty position, which resides in a distance smaller than K. More specifically, if an angel is on a position (x,y) it can move to a posistion (a,b) if and only if |a-x| <= K and |b-y| <= K.
* After an angel move, the demon blocks a square of the chessboard. Blocked positions are not available for movement to the angel.
* The angel can move to its destination regardless of whether the squares between the starting and final positions are blocked or not.
* Angel's goal is to reach a square at the edge of the chessboard, whereas devil's goal is to block the angel in a square  which is not at the edge of the chessboard. Devil wins if it surrounds the angel with a square with a size of at least K blocked positions.

## Game Modes
* In User vs. User mode, one player plays as the angel, and the other plays as the devil. In this mode, the users will be prompted to choose the X and Y coordinates to move their pieces.

* In Automated mode, the computer plays against itself, controlling both the angel and the devil.


## Game Display
* Angel piece is marked with A
* Devil's blocked positions are marked with B
* Empty squares are marked with +

~~~
     1   2   3   4   5   6   7   8   9   10      
1   +   B   B   B   +   +   +   +   +   +   1   
2   +   +   +   +   +   +   +   +   +   +   2   
3   +   +   A   +   +   +   +   +   +   +   3   
4   +   +   +   +   +   +   +   +   +   +   4   
5   +   +   +   +   +   +   +   +   +   +   5   
6   +   +   +   +   +   +   +   +   +   +   6   
7   +   +   +   +   +   +   +   +   +   +   7   
8   +   +   +   +   +   +   +   +   +   +   8   
9   +   +   +   +   +   +   +   +   +   +   9   
10  +   +   +   +   +   +   +   +   +   +   10  
    1   2   3   4   5   6   7   8   9   10      
~~~
