package com.gaming.prs.Tests.Players;

import com.gaming.prs.Players.Computer;
import com.gaming.prs.Players.Player;
import com.gaming.prs.Players.PlayerFactory;
import com.gaming.prs.Players.SymbolType;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class ComputerTest {

    @Test
    public void computeNewMove_Succeeds_Test(){
        Computer computer = (Computer)PlayerFactory.getComputer();

        SymbolType newMove = computer.computeNewMove();
        SymbolType lastMove = computer.getLastMove();

        assertEquals(newMove, lastMove);
        assertTrue( Arrays.stream(SymbolType.values()).anyMatch( st -> lastMove == st)  );
    }

    @Test
    public void getName_Succeeds_Test() {
        Player p = PlayerFactory.getComputer();

        assertEquals(p.getName(), "Computer");
    }

    @Test
    public void getWins_Succeeds_Test() {
        Player p = PlayerFactory.getComputer();

        assertEquals(p.getWins(), 0);

        p.addWin();

        assertEquals(p.getWins(), 1);
    }

    @Test
    public void getLoses_Succeeds_Test() {
        Player p = PlayerFactory.getComputer();

        assertEquals(p.getLoses(), 0);

        p.addLoss();

        assertEquals(p.getLoses(), 1);
    }

    @Test
    public void addWin_Succeeds_Test() {
        Player p = PlayerFactory.getComputer();
        p.addWin();

        assertEquals(p.getWins(), 1);
    }

    @Test
    public void addLoss_Succeeds_Test() {
        Player p = PlayerFactory.getComputer();
        p.addLoss();

        assertEquals(p.getLoses(), 1);
    }
}