package com.gaming.prs.Tests.Game;

import com.gaming.prs.Game.PaperRockScissorsGame;
import com.gaming.prs.Game.RoundResult;
import com.gaming.prs.Game.RoundResultType;
import com.gaming.prs.Players.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaperRockScissorsGameTest {

    //• Paper beats (wraps) rock
    //• Rock beats (blunts) scissors
    //• Scissors beats (cuts) paper.

    @Test
    public void playNextRound_Returns_ExpectedResults_When_HumanPlaysPaperResults_Test(){
        Computer pc = (Computer) PlayerFactory.getComputer();
        Human human = (Human)PlayerFactory.getHuman("Mike");
        int rounds = 3;

        PaperRockScissorsGame game = new PaperRockScissorsGame(rounds, pc, human);

        SymbolType m1 = SymbolType.Paper;
        RoundResult r1 = game.playNextRound(m1);

        //check move was propagated correctly to human
        assertTrue(r1.getPlayer2Move() == m1);

        //check result based on computer move
        SymbolType pcMove1 = r1.getPlayer1Move();
        if(pcMove1 == SymbolType.Paper) {
            assertTrue(r1.getResultType() == RoundResultType.Draw);
            assertEquals(r1.getWinner(), null);
        }
        else if(pcMove1 == SymbolType.Rock){
            assertTrue(r1.getResultType() == RoundResultType.Player2Wins);
            assertEquals(r1.getWinner(), human);
        }
        else{
            assertTrue(r1.getResultType() == RoundResultType.Player1Wins);
            assertEquals(r1.getWinner(), pc);
        }
    }

    @Test
    public void playNextRound_Returns_ExpectedResults_When_HumanPlaysRockResults_Test(){
        Computer pc = (Computer)PlayerFactory.getComputer();
        Human human = (Human)PlayerFactory.getHuman("Mike");
        int rounds = 3;

        PaperRockScissorsGame game = new PaperRockScissorsGame(rounds, pc, human);

        //check rounds was properly get
        assertEquals(rounds, game.getRounds().size());

        SymbolType m1 = SymbolType.Rock;
        RoundResult r1 = game.playNextRound(m1);

        //check move was propagated correctly to human
        assertTrue(r1.getPlayer2Move() == m1);

        //check result based on computer move
        SymbolType pcMove1 = r1.getPlayer1Move();
        if(pcMove1 == SymbolType.Paper) {
            assertTrue(r1.getResultType() == RoundResultType.Player1Wins);
            assertEquals(r1.getWinner(), pc);
        }
        else if(pcMove1 == SymbolType.Rock){
            assertTrue(r1.getResultType() == RoundResultType.Draw);
            assertEquals(r1.getWinner(), null);
        }
        else{
            assertTrue(r1.getResultType() == RoundResultType.Player2Wins);
            assertEquals(r1.getWinner(), human);
        }
    }

    @Test
    public void playNextRound_Returns_ExpectedResults_When_HumanPlaysScissorsResults_Test(){
        Computer pc = (Computer)PlayerFactory.getComputer();
        Human human = (Human)PlayerFactory.getHuman("Mike");
        int rounds = 3;

        PaperRockScissorsGame game = new PaperRockScissorsGame(rounds, pc, human);

        //check rounds was properly get
        assertEquals(rounds, game.getRounds().size());

        SymbolType m1 = SymbolType.Scissors;
        RoundResult r1 = game.playNextRound(m1);

        //check move was propagated correctly to human
        assertTrue(r1.getPlayer2Move() == m1);

        //check result based on computer move
        SymbolType pcMove1 = r1.getPlayer1Move();
        if(pcMove1 == SymbolType.Paper) {
            assertTrue(r1.getResultType() == RoundResultType.Player2Wins);
            assertEquals(1, game.getCurrentScore().humanWins);
            assertEquals(r1.getWinner(), human);
        }
        else if(pcMove1 == SymbolType.Rock){
            assertTrue(r1.getResultType() == RoundResultType.Player1Wins);
            assertEquals(0, game.getCurrentScore().humanWins);
            assertEquals(r1.getWinner(), pc);
        }
        else{
            assertTrue(r1.getResultType() == RoundResultType.Draw);
            assertEquals(0, game.getCurrentScore().computerWins);
            assertEquals(r1.getWinner(), pc);
        }
    }

    @Test
    public void constructor_Throws_Exception_When_InvalidRoundsNumGiven_Test(){
        Player p2 = PlayerFactory.getHuman("p1");
        Player p1 = PlayerFactory.getComputer();

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new PaperRockScissorsGame(0, (Computer) p1, (Human) p2)
        );

        assertEquals("Please specify a valid round number >= 1", exception.getMessage());
    }

    @Test
    public void constructor_Throws_Exception_When_NullPlayerGiven_Test(){
        Player p1 = PlayerFactory.getComputer();

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new PaperRockScissorsGame(1, (Computer) p1, null)
        );

        assertEquals("Please provide both valid computer and human players.", exception.getMessage());
    }

    @Test
    public void playNextRound_ThrowsException_When_RoundsAreExchausted_Test(){
        Player p1 = PlayerFactory.getComputer();
        Player p2 = PlayerFactory.getHuman("Tom");
        int rounds = 3;

        PaperRockScissorsGame game = new PaperRockScissorsGame(rounds, (Computer) p1, (Human) p2);

        for (int i=0; i<rounds; i++)
            game.playNextRound(SymbolType.Paper);

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> game.playNextRound(SymbolType.Paper)
        );

        assertEquals("There are no more available rounds for this game!", exception.getMessage());
    }

    @Test
    public void playNextRound_Succeeds_When_addMoreRoundsCalled_Test(){
        Player p1 = PlayerFactory.getComputer();
        Player p2 = PlayerFactory.getHuman("Tom");
        int rounds = 3;

        PaperRockScissorsGame game = new PaperRockScissorsGame(rounds, (Computer) p1, (Human) p2);

        for (int i=0; i<rounds; i++)
            game.playNextRound(SymbolType.Paper);

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> game.playNextRound(SymbolType.Paper)
        );

        assertEquals("There are no more available rounds for this game!", exception.getMessage());

        game.addMoreRounds(1);

        RoundResult res = game.playNextRound(SymbolType.Paper);

        assertTrue(game.getRounds().size() == 4);
    }
}
