package com.crosshairengine.firstgame.engine.Commands;

/**
 * Created by Sava on 5/16/2017.
 * I/O commands from mobile phone UI
 */

// these commands should probably be moved to game
//
public enum Command {
    MoveUP("up"),
    MoveDOWN("down"),
    MoveLEFT("left"),
    MoveRIGHT("right"),
    CENTER("center"),
    Fire("fire")
    ;

    private final String name;

    private Command(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
