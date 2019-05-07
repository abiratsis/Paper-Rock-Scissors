package com.gaming.prs.Players;

/**
 * The computer player class
 */
public class Computer extends Player {
    /**
     * Constructor
     */
    Computer() {
        super("Computer");
    }

    /**
     * Computes the next random move of computer.
     *
     * @return The move, it should be on of the Paper, Rock, Scissors
     */
    public SymbolType computeNewMove(){
        this.lastMove = SymbolType.generateRandomMove();

        return this.lastMove;
    }
}
