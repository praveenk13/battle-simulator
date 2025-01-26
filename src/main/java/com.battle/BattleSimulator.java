package com.battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BattleSimulator {
    private final Battle battle;

    public BattleSimulator(Battle battle) {

        this.battle = battle;
    }

    public List<Platoon> findWinningArrangement(List<Platoon> ownPlatoons, List<Platoon> opponentPlatoons) {

        if (ownPlatoons.size() != opponentPlatoons.size() || ownPlatoons.size() != 5) {
            throw new IllegalArgumentException("Must have 5 platoons each");
        }

        List<List<Platoon>> permutations = new ArrayList<>();
        permute(ownPlatoons, 0, permutations);

        for (List<Platoon> arrangement : permutations) {
            int wins = 0;
            for (int i = 0; i < ownPlatoons.size(); i++) {
                if (battle.battleOutcome(arrangement.get(i), opponentPlatoons.get(i)) > 0) {
                    wins++;
                }
            }
            if (wins >= 3) {
                return arrangement;
            }
        }
        return null; // No winning arrangement found
    }

    private void permute(List<Platoon> platoons, int index, List<List<Platoon>> permutations) {
        if (index == platoons.size()) {
            permutations.add(new ArrayList<>(platoons));
            return;
        }
        for (int i = index; i < platoons.size(); i++) {
            Collections.swap(platoons, index, i);
            permute(platoons, index + 1, permutations);
            Collections.swap(platoons, index, i); // backtrack
        }
    }
}