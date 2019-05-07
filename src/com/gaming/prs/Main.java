package com.gaming.prs;

import com.gaming.prs.UI.PaperRockScissorsUI;

public class Main {

    public static void main(String[] args) {
        try {
            PaperRockScissorsUI ui = new PaperRockScissorsUI();
            ui.play();
        }
        catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
