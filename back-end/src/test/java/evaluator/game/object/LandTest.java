package evaluator.game.object;

import game.main.Configuration;
import game.main.Player;
import game.main.Region;
import game.main.Territory;
import game.object.Land;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LandTest {
//    Path p1 = Paths.get("Z:\\OOP\\Project_UPBEAT\\back-end\\src\\main\\java\\game\\main\\Configuration.txt");
    Configuration config = new Configuration(Path.of("Z:\\OOP\\Project_UPBEAT\\back-end\\src\\main\\java\\game\\main\\Configuration.txt"));


    public LandTest() throws IOException {
    }

    @Test
    public void readConfigTest () {
        assertEquals(10000, config.getInitBudget());
        assertEquals(1000000, config.getMax_dep());
        assertEquals(5, config.getInterest_pct());
        assertEquals(100, config.getRev_cost());
        assertEquals(20, config.getM());
        assertEquals(15, config.getN());
        assertEquals(1000, config.getInitCenterDeposit());
        assertEquals(300, config.getTimeForFirstPlan());
        assertEquals(1800, config.getTimeForRevision());
    }

    @Test
    public void landMethods_Test () throws IOException {
        Territory tt = new Territory(10, 10);
        Region  region = new Region(0, 0);
        Player player = new Player("Unda", tt);
        Land land = new Land( 2000, player, region);

        assertEquals(2000, land.getDeposit());
        assertEquals(region, land.getPosition());
        assertEquals(player, land.getOwner());

        land.updateDeposit((double) config.getInterest_pct());
        long Deposit = land.getDeposit();
        assertEquals( 2100, Deposit);
    }

}
