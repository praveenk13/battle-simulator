package com.battle;
public class Platoon {
    String type;
    int soldiers;

    public Platoon(String type, int soldiers) {
        this.type = type;
        this.soldiers = soldiers;
    }

    public int getStrength() {
        return soldiers;
    }

    @Override
    public String toString() {
        return type + "#" + soldiers;
    }
}
