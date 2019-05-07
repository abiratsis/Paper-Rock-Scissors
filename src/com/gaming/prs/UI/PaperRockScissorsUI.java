package com.gaming.prs.UI;

import com.gaming.prs.Game.PaperRockScissorsGame;
import com.gaming.prs.Game.Round;
import com.gaming.prs.Game.RoundResultType;
import com.gaming.prs.Players.Computer;
import com.gaming.prs.Players.Human;
import com.gaming.prs.Players.PlayerFactory;
import com.gaming.prs.Players.SymbolType;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PaperRockScissorsUI {

    public void play() throws Exception {
        MainMenuOption option = readMainMenuOption();
        Map<LocalDateTime, PaperRockScissorsGame> history = new HashMap<>();

        while (option != MainMenuOption.Exit) {

            if (option == MainMenuOption.NewGame) {
                PaperRockScissorsGame cgame = handleNewGame();
                history.put(LocalDateTime.now(), cgame);
            }
            else if (option == MainMenuOption.ViewHistory){
                showHistory(history);
            }
            option = readMainMenuOption();
        }

        System.out.println("Exiting PaperRockScissors game ....");
        Thread.sleep(1000);
    }

    private PaperRockScissorsGame handleNewGame() throws Exception {
        String name = readHumanName();
        int rounds = readRoundsNumber();

        Human human = (Human) PlayerFactory.getHuman(name);
        Computer pc = (Computer) PlayerFactory.getComputer();
        PaperRockScissorsGame game = new PaperRockScissorsGame(rounds, pc, human);

        for (Round round : game.getRounds()) {
            NextMoveOption nextMoveOption = readNextMove();

            if (nextMoveOption == NextMoveOption.PrevMenu)
                return game;

            SymbolType nextMove = nextMoveOption.toSymbolType();

            game.playNextRound(nextMove);
            showRoundResults(round);
        }

        showGameResults(game);

        return game;
    }

    private void showHistory(Map<LocalDateTime, PaperRockScissorsGame> history){
        System.out.println("\n\n************************Game history****************************");

        for(Map.Entry<LocalDateTime, PaperRockScissorsGame> gameInfo : history.entrySet()) {
            PaperRockScissorsGame cgame = gameInfo.getValue();
            int wins = 0;
            int losses = 0;
            int rounds = cgame.getRounds().size();

            if(cgame.getWinner() == "Computer") {
                wins = cgame.getCurrentScore().computerWins;
                losses =  rounds - wins;
            }
            else if(cgame.getWinner() == cgame.getHuman().getName()){
                wins = cgame.getCurrentScore().humanWins;
                losses = rounds - wins;
            }
            else
                wins = losses = (rounds == 0 ? 0 : rounds / 2);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
            if(cgame.getWinner() != "Draw")
                System.out.println(String.format("\n%s - Winner: %s - Wins: %d - Losses: %d",
                        gameInfo.getKey().format(formatter),
                        cgame.getWinner(),
                        wins,
                        losses
                ));
            else
                System.out.println(String.format("\n%s - Draw", gameInfo.getKey().format(formatter)));
        }

        System.out.println("\n****************************************************************");
        System.out.println("Press ENTER to continue...");
        System.console().readLine();
        System.console().flush();
    }

    private void showRoundResults(Round round) {
        if(round.getResult().getResultType() == RoundResultType.Player1Wins)
            System.out.println("\nWinner: computer");
        else if(round.getResult().getResultType() == RoundResultType.Player2Wins)
            System.out.println(String.format("\nWinner: %s", round.getPlayer2().getName()));
        else
            System.out.println("\nDraw...");

        System.out.println(String.format("Computer played:%s", round.getResult().getPlayer1Move()));
        System.out.println(String.format("%s played:%s",round.getPlayer2().getName(), round.getResult().getPlayer2Move()));
    }

    private void showGameResults(PaperRockScissorsGame game) throws IOException {
        int pcWins = game.getCurrentScore().computerWins;
        int humanWins = game.getCurrentScore().humanWins;

        System.out.println("\n********************Game results**************************");
        System.out.println(String.format("\nComputer wins:%d", pcWins));
        System.out.println(String.format("%s wins:%d", game.getHuman().getName(), humanWins));

        if(game.getWinner() != "Draw")
            System.out.println(String.format("\n%s Won!!!", game.getWinner()));
        else
            System.out.println("\nNobody Won. Is a Draw!!!\n");
        System.out.println("**********************************************************\n");

        System.out.println("Press ENTER to continue...");
        System.console().readLine();
        System.console().flush();
    }

    private void showMainMenu(boolean clr){
        if(clr)
            clrscr();

        System.out.println("\n1. New game");
        System.out.println("2. View history");
        System.out.println("3. Exit");
    }

    private void showNextMoveMenu(boolean clr){
        if(clr)
            clrscr();

        System.out.println("\nPlease select one of the following.\n");
        System.out.println("1. Paper");
        System.out.println("2. Rock");
        System.out.println("3. Scissors");
        System.out.println("4. Previous menu.");
    }

    private MainMenuOption readMainMenuOption() throws IOException {
        showMainMenu(true);
        MainMenuOption option = MainMenuOption.fromInt(readIntFromInput("Option"));

        while(option == MainMenuOption.Invalid){
            clrscr();
            System.out.println(String.format("\n%d is invalid option!\n", option.ordinal()));
            showMainMenu(false);
            option = MainMenuOption.fromInt(readIntFromInput("Option"));
        }

        return option;
    }

    private String readHumanName(){
        clrscr();
        System.out.print("\nPlease specify your name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner. nextLine();

        while(name == null || name.isEmpty()){
            clrscr();
            System.out.print("Please specify your name:");
            name = scanner. nextLine();
        }

        return name;
    }

    private NextMoveOption readNextMove() throws IOException {
        showNextMoveMenu(false);
        NextMoveOption option = NextMoveOption.fromInt(readIntFromInput("Option"));

        while(option == NextMoveOption.Invalid){
            System.out.println(String.format("\n%d is invalid option!", option.ordinal()));
            showNextMoveMenu(false);
            option = NextMoveOption.fromInt(readIntFromInput("Option"));
        }

        return option;
    }

    private int readRoundsNumber(){
        clrscr();
        System.out.print("\nPlease specify how many rounds you want to play:");
        return readIntFromInput(null);
    }

    private int readIntFromInput(String label){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                if(label != null)
                    System.out.print("\n" + label + ":");

                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.print("Invalid integer. Please insert a valid integer:");
            }
        }
    }

    /**
     * borrowed from: https://stackoverflow.com/questions/2979383/java-clear-the-console
     */
    public static void clrscr(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
}
