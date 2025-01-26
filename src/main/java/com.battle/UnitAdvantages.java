package com.battle;

import java.util.List;
import java.util.Map;

public class UnitAdvantages {
    private static final UnitAdvantages INSTANCE = new UnitAdvantages();
    private final Map<String, List<String>> advantages;

    private UnitAdvantages() {
        advantages = Map.of(
                "Militia", List.of("Spearmen", "LightCavalry"),
                "Spearmen", List.of("LightCavalry", "HeavyCavalry"),
                "LightCavalry", List.of("FootArcher", "CavalryArcher"),
                "HeavyCavalry", List.of("Militia", "FootArcher", "LightCavalry"),
                "CavalryArcher", List.of("Spearmen", "HeavyCavalry"),
                "FootArcher", List.of("Militia", "CavalryArcher")
        );
    }

    public static UnitAdvantages getInstance() {
        return INSTANCE;
    }

    public Map<String, List<String>> getAdvantages() {
        return advantages; // Returning an immutable map is preferable
    }
}