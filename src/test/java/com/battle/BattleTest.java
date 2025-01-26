package com.battle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {
    private final Map<String, List<String>> advantages = UnitAdvantages.getInstance().getAdvantages();
    private Battle battle;

    @BeforeEach
    public void setUp() {
        battle = new Battle(advantages);
    }

    @Test
    void shouldReturnPositiveResultWhenMoreHeavyCavalryFacesArcher() {
        Platoon attacker = new Platoon("HeavyCavalry", 130);
        Platoon defender = new Platoon("CavalryArcher", 100);
        assertTrue(battle.battleOutcome(attacker, defender) > 0); //HeavyCavalry wins
    }

    @Test
    void shouldReturnNegativeResultWhenLessSpearManFacesHeavyCavalry() {
        Platoon attacker = new Platoon("Spearmen", 10);
        Platoon defender = new Platoon("HeavyCavalry", 20);
        assertTrue(battle.battleOutcome(attacker, defender) < 0); //Spearmen loses
    }

    @Test
    void shouldReturnPositiveResultWhenMoreMilitiaFacesSpearmen() {
        Platoon attacker = new Platoon("Militia", 30);
        Platoon defender = new Platoon("Spearmen", 10);
        assertTrue(battle.battleOutcome(attacker, defender) > 0); // Militia wins
    }


}