package com.gaming.prs.Tests.Game;

import com.gaming.prs.Game.Round;
import com.gaming.prs.Game.RoundResult;
import com.gaming.prs.Game.RoundResultType;
import com.gaming.prs.Players.Human;
import com.gaming.prs.Players.PlayerFactory;
import com.gaming.prs.Players.SymbolType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoundTest {
    //• Paper beats (wraps) rock
    //• Rock beats (blunts) scissors
    //• Scissors beats (cuts) paper.

    @Test
    @DisplayName("Paper beats (wraps) rock.")
    public void play_Succeeds_For_paperBeatsRock_Test(){
        Human p1 = (Human) PlayerFactory.getHuman("p1");
        Human p2 = (Human)PlayerFactory.getHuman("p2");

        p1.setLastMove(SymbolType.Paper);
        p2.setLastMove(SymbolType.Rock);

        Round round1 = new Round(p1, p2);
        RoundResult result = round1.play();

        assertEquals(result.getResultType(), RoundResultType.Player1Wins);
        assertTrue(result.getWinner().equals(p1));
        assertEquals(1, p1.getWins());
        assertEquals(1, p2.getLoses());
    }

    @Test
    @DisplayName("Rock beats (blunts) scissors.")
    public void play_Succeeds_For_rockBeatsScissors_Test(){
        Human p1 = (Human)PlayerFactory.getHuman("p1");
        Human p2 = (Human)PlayerFactory.getHuman("p2");

        p2.setLastMove(SymbolType.Rock);
        p1.setLastMove(SymbolType.Scissors);

        Round round1 = new Round(p1, p2);
        RoundResult result = round1.play();

        assertEquals(result.getResultType(), RoundResultType.Player2Wins);
        assertTrue(result.getWinner().equals(p2));
        assertEquals(1, p2.getWins());
        assertEquals(1, p1.getLoses());
    }

    @Test
    @DisplayName("Scissors beats (cuts) paper.")
    public void play_Succeeds_For_scissorsBeatsPaper_Test(){
        Human p1 = (Human)PlayerFactory.getHuman("p1");
        Human p2 = (Human)PlayerFactory.getHuman("p2");

        p1.setLastMove(SymbolType.Scissors);
        p2.setLastMove(SymbolType.Paper);

        Round round1 = new Round(p1, p2);
        RoundResult result = round1.play();

        assertEquals(result.getResultType(), RoundResultType.Player1Wins);
        assertTrue(result.getWinner().equals(p1));
        assertEquals(1, p1.getWins());
        assertEquals(1, p2.getLoses());
    }

    @Test
    @DisplayName("Test draw.")
    public void play_Succeeds_When_Draw_Test(){
        Human p1 = (Human)PlayerFactory.getHuman("p1");
        Human p2 = (Human)PlayerFactory.getHuman("p2");

        p1.setLastMove(SymbolType.Scissors);
        p2.setLastMove(SymbolType.Scissors);

        Round round1 = new Round(p1, p2);

        assertTrue(round1.getId() > 0);

        RoundResult result = round1.play();

        assertEquals(result.getResultType(), RoundResultType.Draw);
        assertTrue(round1.getResult().getWinner() == null);
        assertEquals(0, p1.getWins());
        assertEquals(0, p2.getLoses());

        Round round2 = new Round(p1, p2);
        RoundResult result2 = round2.play();

        assertEquals(result2.getResultType(), RoundResultType.Draw);
        assertTrue(result2.getWinner() == null);
        assertEquals(0, p1.getWins());
        assertEquals(0, p2.getLoses());
    }

    @Test
    public void constructor_Throws_Exception_When_EmptyPlayer_Test(){
        Human p1 = (Human)PlayerFactory.getHuman("p1");
        Human p2 = null;

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Round(p1, p2)
        );

        assertEquals("Please specify a valid non empty player.", exception.getMessage());
    }

    @Test
    public void play_Throws_Exception_When_EmptyMove_Test(){
        Human p1 = (Human)PlayerFactory.getHuman("p1");
        Human p2 = (Human)PlayerFactory.getHuman("p2");

        p1.setLastMove(SymbolType.Scissors);

        Round round1 = new Round(p1, p2);

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> round1.play()
        );

        assertEquals("Both moves for player1 and player2 should be specified.", exception.getMessage());
    }
}
