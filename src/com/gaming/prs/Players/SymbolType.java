package com.gaming.prs.Players;

import java.util.Random;

public enum SymbolType {
    Paper,
    Rock,
    Scissors;

    public static SymbolType generateRandomMove(){
        Random r = new Random();
        int nextMove = r.ints(1, 3).findFirst().getAsInt();

        return SymbolType.values()[nextMove - 1];
    }
}
