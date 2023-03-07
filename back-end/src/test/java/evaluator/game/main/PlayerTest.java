package evaluator.game.object;

import game.main.Player;
import game.main.Region;
import game.main.Territory;
import game.object.Land;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    @Test
    public void playerMethods_Test () throws IOException {
        Territory tt = new Territory(10, 10);
        Region region = new Region(1,1);
        Player player = new Player("Anda", tt);
        Land land = new Land(2000, player , region);


        for (int x = 0; x < 10; x++) {
            Region region2 = new Region(x, x+1);
            player.CreateLand(region2);
        }

        // Test if the lands that player creates, are their
        List<Land> allLand = player.getAllLand();
        for (Land l : allLand) {
            assertTrue(allLand.contains(l));
        }

        //Test if the first city of player is actually the cityCenter
        assertTrue(player.getCityCenter().isCityCenters());
        assertTrue(land.isCityCenters());
    }
}
