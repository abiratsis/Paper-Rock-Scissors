package com.gaming.prs.UI;

public enum MainMenuOption {
    NewGame,
    ViewHistory,
    Exit,
    Invalid;

    public static MainMenuOption fromInt(int option){
        if(option > 3 || option < 1)
            return MainMenuOption.Invalid;

        return MainMenuOption.values()[option - 1];
    }
}
