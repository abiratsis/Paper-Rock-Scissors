package com.gaming.prs.Players;

/**
 * Abstract class for both computer and human
 */
public abstract class Player {
    private String name;
    private int wins;
    private int loses;
    protected SymbolType lastMove;

    /**
     * Constructor
     *
     * @param name This is constant for computer
     */
    Player(String name){
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Please specify a valid player name");

        this.name = name;
        this.loses = 0;
        this.wins = 0;
        this.lastMove = null;
    }

    public String getName() {
        return this.name;
    }

    public int getWins() {
        return this.wins;
    }

    public int getLoses() {
        return this.loses;
    }

    public void addWin() {
        this.wins++;
    }

    public void addLoss() {
        this.loses++;
    }

    public SymbolType getLastMove(){
        return this.lastMove;
    }
}
