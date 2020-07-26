# Assassin 2020

Play from home: The Game of Assassin redesigned for those in quarantine.

[Author: Bill Zhao](https://www.iambillzhao.com/)

## Usage and Dependency

Please use Java to compile this project, the client may need to turn off an 
option to make sure to the program runs successfully.

Please try `-Xlint:unchecked` in compile options if intial execution fails.

## Game Rules

The game of assassin is played as follows. Players start out with a group 
of people who want to play the game. For example, let's say that we have 
five people playing whose names are Joe, Sally, Jim, Carol and Chris. 
A circular chain of assassination targets is established (what is called 
the “kill ring” in the sample log of execution). For example, we might 
decide Joe should stalk Sally, Sally should stalk Jim, Jim should stalk 
Carol, Carol should stalk Chris and Chris should stalk Joe.

```
Joe --> Sally --> Jim --> Carol --> Chris
 ^                                    |
 |                                    V
 +--------<--------<---------<--------+
```
When someone is assassinated, the chain needs to be relinked by “skipping” 
that person. For example, suppose that Jim is assassinated first (obviously 
this would have been by Sally). Sally needs a new target, so we give her 
Jim's target: Carol. Thus, the chain becomes:

```
          +-------->--------+
          ^                 |
          |                 V
Joe --> Sally     Jim     Carol --> Chris
 ^                                    |
 |                                    V
 +--------<--------<---------<--------+
```

[Learn more about this game on wikiHow](https://www.wikihow.com/Play-Assassin)

## Class Functions

- `AssassinManager(List<String> names)`:
 Adds the names from the list into the kill ring in the same order in which 
 they appear in the list. Throws an `IllegalArgumentException` if the list is 
 empty.

- `void printKillRing()`:
 Prints the names of the people in the kill ring, one per line, indented four 
 spaces, with output of the form `<name> is stalking <name>`.

- `void printGraveyard()`:
Prints the names of the people in the graveyard, one per line, indented four 
spaces, in reverse kill order, with output of the form 
`<name> was killed by <name>`.

- `boolean killRingContains(String name)`:
Returns true if the given name is in the current kill ring and returns false 
otherwise. It ignores case in comparing names.

- `boolean graveyardContains(String name)`:
Returns true if the given name is in the current graveyard and returns false 
otherwise. It ignores case in comparing names.

- `boolean gameOver()`:
Returns true if the game is over (i.e., if the kill ring has just one person 
in it) and returns false otherwise.

- `String winner()`:
Returns the name of the winner of the game, returns `NULL` if the game 
is not over.

- `void kill(String name)`:
Records the killing of the person with the given name, transferring the 
person from the kill ring to the graveyard.  This operation does not change 
the kill ring order of printKillRing. Throws an IllegalArgumentException 
if the given name is not part of the current kill ring and it should throw 
an `IllegalStateException` if the game is over. Ignores case in comparing names.

### Execution Log

```
Welcome to the Assassin Manager

What name file do you want to use this time? names3.txt
Do you want the names shuffled? (y/n)? n

Current kill ring:
    Athos is stalking Porthos
    Porthos is stalking Aramis
    Aramis is stalking Athos
Current graveyard:

next victim? Aramis

Current kill ring:
    Athos is stalking Porthos
    Porthos is stalking Athos
Current graveyard:
    Aramis was killed by Porthos

next victim? Athos

Game was won by Porthos
Final graveyard is as follows:
    Athos was killed by Porthos
    Aramis was killed by Porthos
```