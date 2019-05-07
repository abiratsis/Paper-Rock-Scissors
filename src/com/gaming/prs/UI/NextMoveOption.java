package com.gaming.prs.UI;

import com.gaming.prs.Players.SymbolType;

public enum NextMoveOption {
    Paper,
    Rock,
    Scissors,
    PrevMenu,
    Invalid;

    public static NextMoveOption fromInt(int option){
        if(option > 4 || option < 1)
            return NextMoveOption.Invalid;

        return NextMoveOption.values()[option - 1];
    }

    public SymbolType toSymbolType() throws Exception {
        int type = this.ordinal();

        if(type > 2 || type < 0)
            throw new Exception("Invalid SymbolType value.");

        return SymbolType.values()[type];
    }
}

