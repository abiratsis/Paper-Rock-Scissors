package com.gaming.prs.Players;

/**
 * Class Human. Represents the living player!
 */
public class Human extends Player {

    /**
     * Constructor
     *
     * @param name The name of the player
     */
    Human(String name){
        super(name);
    }

    /**
     * Sets the move chosen by human.
     *
     * @param move
     */
    public void setLastMove(SymbolType move){
        this.lastMove = move;
    }
}
