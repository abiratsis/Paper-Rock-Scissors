package com.gaming.prs.Game;

import com.gaming.prs.Players.Player;
import com.gaming.prs.Players.SymbolType;

/**
 * Class RoundResult. Keeps the result of the game rounds.
 */
public class RoundResult {
    private Player winner;
    private SymbolType player1Move;
    private SymbolType player2Move;
    private RoundResultType resultType;

    /**
     * Constructor
     * @param winner Winner of the round
     * @param resultType This can be one of the Player1Wins, Player2Wins, Draw
     * @param computerMove The move that computer played
     * @param humanMove The move that human played
     */
    RoundResult(Player winner, RoundResultType resultType, SymbolType computerMove, SymbolType humanMove){
        this.winner = winner;
        this.player1Move = computerMove;
        this.player2Move = humanMove;
        this.resultType = resultType;
    }

    public Player getWinner(){
        return this.winner;
    }

    public SymbolType getPlayer1Move(){
        return this.player1Move;
    }

    public SymbolType getPlayer2Move(){
        return this.player2Move;
    }

    public RoundResultType getResultType(){
        return this.resultType;
    }
}
