package com.battle;
import java.util.List;
import java.util.Map;

public class Battle {
    private final Map<String, List<String>> advantages;

    public Battle(Map<String, List<String>> advantages) {
        this.advantages = advantages;
    }

    public int battleOutcome(Platoon attacker, Platoon defender) {
        int attackerStrength = attacker.soldiers;
        if (advantages.getOrDefault(attacker.type, List.of()).contains(defender.type)) {
            attackerStrength *= 2;
        }
        return attackerStrength - defender.soldiers;
    }
}
