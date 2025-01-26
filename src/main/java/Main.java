import com.battle.Battle;
import com.battle.BattleSimulator;
import com.battle.Platoon;
import com.battle.UnitAdvantages;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Platoon> ownPlatoons = parsePlatoons(scanner.nextLine());
        List<Platoon> opponentPlatoons = parsePlatoons(scanner.nextLine());
        scanner.close();

        Battle battle = new Battle(UnitAdvantages.getInstance().getAdvantages()); //Initialize battle with advantage map
        BattleSimulator simulator = new BattleSimulator(battle);
        List<Platoon> winningArrangement = simulator.findWinningArrangement(ownPlatoons, opponentPlatoons);


        if (winningArrangement == null) {
            System.out.println("There is no chance of winning");
        } else {
            System.out.println(winningArrangement.stream()
                    .map(Platoon::toString)
                    .collect(Collectors.joining(";")));
        }
    }

    private static List<Platoon> parsePlatoons(String line) {
        return Arrays.stream(line.split(";"))
                .map(s -> {
                    String[] parts = s.split("#");
                    return new Platoon(parts[0], Integer.parseInt(parts[1]));
                })
                .collect(Collectors.toList());
    }
}