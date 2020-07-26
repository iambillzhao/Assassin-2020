/**
 * AssassinManager will simulate the game of assassin,a violent game
 *          that makes people to kill each other:( AssassinManager 
 *          will keep track of the kill ring (current players) and 
 *          the graveyard (dead players and their killers). AssassinManager
 *          may let you kill others, check the kill ring, the graveyard, 
 *          the validity of inputs, and the status of the game. 
 *          Note that this class is not case-sensitive and may 
 *          return exceptions when illegal values are passed.
 * 
 * @author Bill Zhao
 * @version 07/17/2019
 */

import java.util.*;
public class AssassinManager {
    private AssassinNode killRingFront; // the front of the kill ring 
    private AssassinNode graveyardFront; // the front of the graveyard

    /**
     * Constructs a game manager with a kill ring of given names,
     *          specifying the order of the who is stalking whom.
     *          This method is not case-sensitive and assumes that
     *          the names are nonempty strings and that there are
     *          no duplicate names. Note that this method may throw
     *          an IllegalArgumentException if the given input is 
     *          empty.
     * @param List<String> names    the given input of names
     * 
     * no @return
     */
    public AssassinManager(List<String> names) {
        if (names == null || names.size() == 0) {
            throw new IllegalArgumentException();
        } 
        for (int i = 0; i < names.size(); i++) {
            AssassinNode assassin = new AssassinNode(names.get(i));
            if (killRingFront == null) {
                killRingFront = assassin;
            } else {
                AssassinNode current = killRingFront;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = assassin;
            }
        }
    }

    /**
     * Displays the names of people in the kill ring to console.
     *          If only one player is left in the kill ring, this
     *          method will display that he is stalking himself.
     * no @param 
     * 
     * no @return
     */
    public void printKillRing() {
        AssassinNode current = killRingFront;
        while (current != null) {
            if (current.next != null) {
                System.out.println("    " + current.name + " is stalking "
                                    + current.next.name);
            } else {
                System.out.println("    " + current.name + " is stalking " 
                                    + killRingFront.name); 
            }
            current = current.next;
        }
    }

    /**
     * Displays the names of people in the graveyard to console in
     *          reversed kill order. No output will be displayed if
     *          the graveyard is empty.
     * no @param 
     * 
     * no @return
     */
    public void printGraveyard() {
        AssassinNode current = graveyardFront;
        while (current != null) {
            System.out.println("    " + current.name + " was killed by " 
                                    + current.killer);
            current = current.next;
        }
    }

    /**
     * Checks if the given input is in the current kill ring.
     *          This method is not case-sensitive.
     * @param name      the given input to be checked if it is in 
     *                  the current kill ring
     * 
     * @return true if the given name is in the current kill ring 
     *         and false otherwise
     */
    public boolean killRingContains(String name) {
        AssassinNode current = killRingFront;
        while (current != null) {
            if (name.equalsIgnoreCase(current.name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Checks if the given input is in the current graveyard.
     *          This method is not case-sensitive.
     * @param name      the given input to be checked if it is in
     *                  the current graveyard
     * 
     * @return true if the given name is in the current graveyard 
     *         and false otherwise
     */
    public boolean graveyardContains(String name) {
        AssassinNode current = graveyardFront;
        while (current != null) {
            if (name.equalsIgnoreCase(current.name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Checks if the simulated game is over and cannot be preceded.
     * no @param 
     * 
     * @return true if the game is over and false otherwise
     */
    public boolean gameOver() {
        return killRingFront.next == null;
    }

    /**
     * Checks who the winner is if the game is over.
     * no @param 
     * 
     * @return the name of the winner and null if the game is not over yet
     */
    public String winner() {
        if (gameOver()) {
            return killRingFront.name;
        } else {
            return null;
        }
    }

    /**
     * Records the killing of the person with the given name,
     *          transfers the killed person from kill ring to 
     *          graveyard in the most-recent order. 
     *          An IllegalArgumentException will be thrown if
     *          the given name is not part of the current
     *           kill ring. An IllegalStateException
     *          will be thrown if the game is over.
     *          This method is not case-sensitive.
     * @param name      the person who will get killed
     * 
     * no @return
     */
    public void kill(String name) {
        if (!killRingContains(name)) {
            throw new IllegalArgumentException();
        } else if (gameOver()) {
            throw new IllegalStateException();
        } 
        AssassinNode current = killRingFront;
        AssassinNode killed = null;
        if (killRingFront.name.equalsIgnoreCase(name)) {
            while (current.next != null) {
                current = current.next;
            }
            killed = killRingFront;
            killed.killer = current.name;
            killRingFront = killRingFront.next;
        } else {
            while (!current.next.name.equalsIgnoreCase(name)) {
                current = current.next;
            }
            killed = current.next;
            killed.killer = current.name;
            current.next = current.next.next;
        }
        killed.next = graveyardFront;
        graveyardFront = killed;
    }
}