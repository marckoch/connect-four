# Jetbrains Academy - Connect Four

My solutions for the Jetbrains Academy Problem 'Connect Four'

https://hyperskill.org/projects/202

The solution is build up step by step over several stages. 
Stage 1 is the first and simple one. The following stages 
build up on the previous stages and get more and more advanced.
There are five stages in total.

Because each stage is completely independent of the previous one,
IntelliJ might show some warnings about duplicated code between 
the stages.

## Stage 1

[click here to see description @ JetBrains Academy](https://hyperskill.org/projects/202/stages/1002/implement)

Just some basic information: Names of the two players and board dimensions

just execute this:

    gradle -PmainClass=stage1.MainKt run --console=plain

    Connect Four
    First player's name:
    A
    Second player's name:
    B
    Set the board dimensions (Rows x Columns)
    Press Enter for default (6 x 7)
    
    A VS B
    6 X 7 board

## Stage 2

[click here to see description @ JetBrains Academy](https://hyperskill.org/projects/202/stages/1003/implement)

Now we print the initial (empty) board.

just execute this:

    gradle -PmainClass=stage2.MainKt run --console=plain
    
    First player's name:
    A
    Second player's name:
    B
    Set the board dimensions (Rows x Columns)
    Press Enter for default (6 x 7)
    
    A VS B
    6 X 7 board
     1 2 3 4 5 6 7
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║ ║ ║ ║ ║ ║
    ╚═╩═╩═╩═╩═╩═╩═╝

## Stage 3

[click here to see description @ JetBrains Academy](https://hyperskill.org/projects/202/stages/1004/implement)

We add basic game logic.

just execute this:

    gradle -PmainClass=stage3.MainKt run --console=plain
    
    Connect Four
    First player's name:
    A
    Second player's name:
    B
    Set the board dimensions (Rows x Columns)
    Press Enter for default (6 x 7)
    
    A VS B
    6 X 7 board
     1 2 3 4 5 6 7
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║ ║ ║ ║ ║ ║
    ╚═╩═╩═╩═╩═╩═╩═╝
    A's turn:
    9
    The column number is out of range (1 - 7)
    A's turn:
    3
     1 2 3 4 5 6 7
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║ ║ ║ ║ ║ ║
    ║ ║ ║o║ ║ ║ ║ ║
    ╚═╩═╩═╩═╩═╩═╩═╝
    B's turn:
    end
    Game over!

## Stage 4

[click here to see description @ JetBrains Academy](https://hyperskill.org/projects/202/stages/1005/implement)

We add winning condition. Game will end if one player has connected four in a row.

just execute this:

    gradle -PmainClass=stage4.MainKt run --console=plain

## Stage 5

[click here to see description @ JetBrains Academy](https://hyperskill.org/projects/202/stages/1006/implement)

We add possibility to play multiple games in a row.

just execute this:

    gradle -PmainClass=stage5.MainKt run --console=plain
