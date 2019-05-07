package com.gaming.prs.Players;

public class PlayerFactory{

    public static Player getComputer(){
        return new Computer();
    }

    public static Player getHuman(String name){
        return new Human(name);
    }
}