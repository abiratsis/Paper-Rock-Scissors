package com.gaming.prs.Game;

import com.gaming.prs.Players.Computer;
import com.gaming.prs.Players.Human;
import com.gaming.prs.Players.SymbolType;

import java.util.ArrayList;
import java.util.List;

/**
 * Class PaperRockScissorsGame. The class handles the rounds for the PaperRockScissors game.
 */
public class PaperRockScissorsGame {
    private int roundsNum;
    private Computer computer;
    private Human human;

    private List<Round> rounds;
    private int currentRoundIndex;
    private GameScore score;

    /**
     * Constructor
     *
     * @param rounds The number of rounds to create for this game
     * @param computer The instance of the computer player
     * @param human The instance of human player
     */
    public PaperRockScissorsGame(int rounds, Computer computer, Human human){
        if(rounds <= 0)
            throw new IllegalArgumentException("Please specify a valid round number >= 1");

        if(computer == null || human == null)
            throw new IllegalArgumentException("Please provide both valid computer and human players.");

        this.computer = computer;
        this.human = human;
        this.roundsNum = rounds;
        this.currentRoundIndex = 0;
        this.rounds = new ArrayList<>(this.roundsNum);
        this.score = new GameScore();

        this.generateRounds(rounds);
    }

    public GameScore getCurrentScore(){ return this.score; }

    public List<Round> getRounds(){ return this.rounds; }

    public Human getHuman(){ return human; }

    public String getWinner(){
        if(score.computerWins > score.humanWins)
            return "Computer";
        else if(score.computerWins < score.humanWins)
            return human.getName();
        else
            return "Draw";
    }

    /**
     * Plays current round computer VS human.
     *
     * @param humanMove The move of the player
     * @return The results of the round.
     */
    public RoundResult playNextRound(SymbolType humanMove){
        if(currentRoundIndex == this.roundsNum)
            throw new IllegalArgumentException("There are no more available rounds for this game!");

        Round currentRound = this.rounds.get(currentRoundIndex++);
        SymbolType computerMove = this.computer.computeNewMove();

        RoundResult result = currentRound.play(computerMove, humanMove);

        if(result.getResultType() == RoundResultType.Player1Wins)
            this.score.computerWins++;
        else if(result.getResultType() == RoundResultType.Player2Wins)
            this.score.humanWins++;

        return result;
    }

    /**
     * Adds additional rounds.
     *
     * @param rounds Additional rounds.
     */
    public void addMoreRounds(int rounds){
        this.generateRounds(rounds);
        this.roundsNum += rounds;
    }

    /**
     * Creates rounds for this game.
     *
     * @param rounds
     */
    private void generateRounds(int rounds){
        for(int i = 0; i < rounds; i++){
            this.rounds.add(new Round(this.computer, this.human));
        }
    }

    public class GameScore{
        public int computerWins = 0;
        public int humanWins = 0;
    }
}
