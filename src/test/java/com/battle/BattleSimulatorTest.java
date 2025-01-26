package com.battle;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class BattleSimulatorTest {

   private final Map<String, List<String>> advantages =UnitAdvantages.getInstance().getAdvantages();

    @ParameterizedTest
    @CsvSource({
            // Own Platoons, Opponent Platoons, Expected Outcome (true for win, false for no win)
            "Militia#30;Spearmen#10;LightCavalry#100;HeavyCavalry#120;FootArcher#20;Spearmen#10;Militia#10;FootArcher#100;CavalryArcher#100;LightCavalry#20,true",
            "Militia#10;Spearmen#10;LightCavalry#10;HeavyCavalry#10;FootArcher#10;Militia#100;Spearmen#100;LightCavalry#100;HeavyCavalry#100;FootArcher#100,false",
            "Militia#100;Spearmen#100;LightCavalry#100;HeavyCavalry#100;FootArcher#100;Militia#10;Spearmen#10;LightCavalry#10;HeavyCavalry#10;FootArcher#10,true"
    })
    void testFindWinningArrangement(String inputCsv,  String expectedOutcome) {
        BattleSimulator simulator = new BattleSimulator(new Battle(advantages));

        String[] parts = inputCsv.split(";");
        String[] ownPlatoonsCsv = Arrays.copyOfRange(parts, 0, 5);
        String[] opponentPlatoonsCsv = Arrays.copyOfRange(parts, 5, parts.length);
        List<Platoon> own = parsePlatoons(ownPlatoonsCsv);
        List<Platoon> opponent = parsePlatoons(opponentPlatoonsCsv);
        boolean expected = Boolean.parseBoolean(expectedOutcome);


        List<Platoon> arrangement = simulator.findWinningArrangement(own, opponent);
        if (expected) {
            assertNotNull(arrangement);
            assertEquals(5, arrangement.size());
        } else {
            assertNull(arrangement);
        }
    }


    private List<Platoon> parsePlatoons(String[] platoonsCsv) {
        return Arrays.stream(platoonsCsv)
                .map(s -> {
                    String[] parts = s.split("#");
                    return new Platoon(parts[0], Integer.parseInt(parts[1]));
                })
                .collect(Collectors.toList());
    }


    @Test
    void testFindWinningArrangement_UnequalSizes(){
        BattleSimulator simulator = new BattleSimulator(new Battle(advantages));
        List<Platoon> own = Arrays.asList(new Platoon("Militia",10));
        List<Platoon> opponent = Arrays.asList(new Platoon("Militia",20), new Platoon("Spearmen",10));
        assertThrows(IllegalArgumentException.class, () -> simulator.findWinningArrangement(own,opponent));
    }
}
