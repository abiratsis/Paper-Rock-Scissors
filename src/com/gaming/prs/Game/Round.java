package com.gaming.prs.Game;

import com.gaming.prs.Players.Player;
import com.gaming.prs.Players.SymbolType;
import javafx.util.Pair;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class Round. Decides for the winner and handles the results of the round.
 */
public class Round {
    private static int currentItems = 0;

    private Player player1;
    private Player player2;
    private RoundResult result;
    private int id;

    //all the rules for the Paper-Rock-Scissors game
    private final Map<Pair<SymbolType, SymbolType>, RoundResultType> rules =
            Collections.unmodifiableMap(Stream.of(
                    new SimpleEntry<>(new Pair<>(SymbolType.Paper, SymbolType.Rock), RoundResultType.Player1Wins),
                    new SimpleEntry<>(new Pair<>(SymbolType.Paper, SymbolType.Scissors), RoundResultType.Player2Wins),
                    new SimpleEntry<>(new Pair<>(SymbolType.Paper, SymbolType.Paper), RoundResultType.Draw),
                    new SimpleEntry<>(new Pair<>(SymbolType.Rock, SymbolType.Paper), RoundResultType.Player2Wins),
                    new SimpleEntry<>(new Pair<>(SymbolType.Rock, SymbolType.Rock), RoundResultType.Draw),
                    new SimpleEntry<>(new Pair<>(SymbolType.Rock, SymbolType.Scissors), RoundResultType.Player1Wins),
                    new SimpleEntry<>(new Pair<>(SymbolType.Scissors, SymbolType.Paper), RoundResultType.Player1Wins),
                    new SimpleEntry<>(new Pair<>(SymbolType.Scissors, SymbolType.Rock), RoundResultType.Player2Wins),
                    new SimpleEntry<>(new Pair<>(SymbolType.Scissors, SymbolType.Scissors), RoundResultType.Draw))
                    .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

    /**
     * Constructor
     *
     * @param player1
     * @param player2
     */
    public Round(Player player1, Player player2){
        if(player1 == null || player2 == null)
            throw new IllegalArgumentException("Please specify a valid non empty player.");

        this.player1 = player1;
        this.player2 = player2;

        currentItems++;
        this.id = currentItems;
    }

    public int getId(){ return this.id;}

    public RoundResult getResult(){ return this.result; }

    public Player getPlayer2(){ return this.player2;}

    /**
     * Plays the current round.
     *
     * @return The results of the round.
     */
    public RoundResult play(){
        return play(null, null);
    }

    /**
     * Plays the current round. The result is based on the following rules:
     * • Paper beats (wraps) rock
     * • Rock beats (blunts) scissors
     * • Scissors beats (cuts) paper
     *
     * @param player1Move The move for the 1st player.
     * @param player2Move The move for the 2nd player.
     * @return The results of the round.
     */
    public RoundResult play(SymbolType player1Move, SymbolType player2Move){
        if(player1Move == null)
            player1Move = this.player1.getLastMove();

        if(player2Move == null)
            player2Move = this.player2.getLastMove();

        if(player1Move == null || player2Move == null)
            throw new IllegalArgumentException("Both moves for player1 and player2 should be specified.");

        RoundResultType resultType = rules.get(new Pair<>(player1Move, player2Move));

        switch (resultType){
            case Player1Wins:
                this.player1.addWin();
                this.player2.addLoss();
                this.result = new RoundResult(this.player1, resultType, player1Move, player2Move);
                break;
            case Player2Wins:
                this.player1.addLoss();
                this.player2.addWin();
                this.result = new RoundResult(this.player2, resultType, player1Move, player2Move);
                break;
            case Draw:
                this.result = new RoundResult(null, resultType, player1Move, player2Move);
                break;
        }
        return this.result;
    }
}
