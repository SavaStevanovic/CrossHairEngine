package com.crosshairengine.firstgame.engine;

/**
 * Created by CrossHairEngine team on 5/16/2017.
 */

public enum Direction {
    UP("up"), DOWN("down"), LEFT("left"), RIGHT("right"), CENTER("center");

    private final String name;

    private Direction(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
