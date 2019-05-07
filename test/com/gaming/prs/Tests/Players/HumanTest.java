package com.gaming.prs.Tests.Players;

import com.gaming.prs.Players.Human;
import com.gaming.prs.Players.PlayerFactory;
import com.gaming.prs.Players.SymbolType;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HumanTest {

    @Test
    @DisplayName("throws IllegalArgumentException when null name provided.")
    public void constructorHandlesException(){
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> PlayerFactory.getHuman(null)
        );

        assertEquals("Please specify a valid player name", exception.getMessage());
    }

    @Test
    public void getNameTest() {
        Human p = (Human) PlayerFactory.getHuman("Alex");
        assertEquals(p.getName(), "Alex");
    }

    @Test
    public void getWinsTest() {
        Human p = (Human) PlayerFactory.getHuman("Alex");
        assertEquals(p.getWins(), 0);

        p.addWin();

        assertEquals(p.getWins(), 1);
    }

    @Test
    public void getLosesTest() {
        Human p = (Human) PlayerFactory.getHuman("Alex");

        assertEquals(p.getLoses(), 0);

        p.addLoss();

        assertEquals(p.getLoses(), 1);
    }

    @Test
    public void addWinTest() {
        Human p = (Human) PlayerFactory.getHuman("Alex");
        p.addWin();

        assertEquals(p.getWins(), 1);
    }

    @Test
    public void addLossTest() {
        Human p = (Human) PlayerFactory.getHuman("Alex");
        p.addLoss();

        assertEquals(p.getLoses(), 1);
    }

    @Test
    public void getLastMoveTest() {
        Human p = (Human) PlayerFactory.getHuman("Alex");
        p.setLastMove(SymbolType.Paper);

        assertEquals(p.getLastMove(), SymbolType.Paper);
    }

    @Test
    public void setLastMoveTest(){
        Human p = (Human) PlayerFactory.getHuman("Alex");

        assertEquals(p.getLastMove(), null);

        p.setLastMove(SymbolType.Paper);

        assertEquals(p.getLastMove(), SymbolType.Paper);
    }
}