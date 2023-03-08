package evaluator.game.object;

import extra.Direction;
import game.main.*;
import game.object.CityCrew;
import game.object.Land;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class CityCrewTest {
    Configuration config = new Configuration(Path.of("Z:\\OOP\\Project_UPBEAT\\back-end\\src\\main\\java\\game\\main\\Configuration.txt"));

    public CityCrewTest() throws IOException {
    }

    @Test
    public void cityCrewMethods_Test () throws IOException {
        Territory tt = new Territory(10, 10);
        Region region = tt.RandomRegion();
//        for (int i = 0 ; i < 10 ; i++){
//            tt.RandomRegion();
//        }
        Land land = new Land(config.getMax_dep(),null,region);
        int budget = (int)config.getInitBudget();
        CityCrew crew = new CityCrew(budget, land, region);
        System.out.println(budget);
//        System.out.println(crew.getPosition().getRealty());
//        crew.invest(100);
        assertTrue(crew.collect(100));
        assertEquals( 10099, crew.getBudget());

        assertFalse(crew.collect(10000000));
    }

    @Test
    public void moveTest () {
        Territory tt = new Territory(10, 10);
        Region region = tt.getRegions(3,3);
        Land land = new Land(config.getMax_dep(),null,region);
        int budget = (int)config.getInitBudget();
        CityCrew crew = new CityCrew(budget, land, region);

        System.out.print(crew.getPosition().getX()+ " "); System.out.println(crew.getPosition().getY());

        crew.move(Direction.UPLEFT);
        System.out.print(crew.getPosition().getX()+ " "); System.out.println(crew.getPosition().getY());
    }


    @Test
    public void settingTest () {
        Territory tt = new Territory(10, 10);
        Region region = tt.RandomRegion();
        Region region2 = tt.getRegions(1, 1);
        Land land = new Land(config.getMax_dep(),null,region);
        int budget = (int)config.getInitBudget();
        CityCrew crew = new CityCrew(budget, land, region);
        System.out.println(region2.getNeighbor(Direction.UPRIGHT));
        System.out.println(region2.getNeighbor(Direction.UPLEFT));
        System.out.println(region2.getNeighbor(Direction.UP));
        System.out.println(region2.getNeighbor(Direction.DOWNRIGHT));
        System.out.println(region2.getNeighbor(Direction.DOWNLEFT));
        System.out.println(region2.getNeighbor(Direction.DOWN));

//        tt.printRegion();
//        System.out.println(crew.getPosition().getRealty());
//        System.out.println(crew.getPosition().getUnit());
//        System.out.println(region2.getX() + ", " + region2.getY());
//        System.out.println(region2.getNeighbor(Direction.UPRIGHT).getX() + ", " + region2.getNeighbor(Direction.UPRIGHT).getY());
//        System.out.println(region2.getNeighbor(Direction.UPLEFT).getX() + ", " + region2.getNeighbor(Direction.UPLEFT).getY());
//        System.out.println(region2.getNeighbor(Direction.DOWN) );
//        System.out.println(region2.getNeighbor(Direction.UP).getX() + ", " + region2.getNeighbor(Direction.UP).getY());
//        System.out.println(region2.getNeighbor(Direction.DOWN).getX() + ", " + region2.getNeighbor(Direction.DOWN).getY());
//        System.out.println(region2.getNeighbor(Direction.DOWNLEFT).getX() + ", " + region2.getNeighbor(Direction.DOWNLEFT).getY());
//        System.out.println(region2.getNeighbor(Direction.DOWNRIGHT).getX() + ", " + region2.getNeighbor(Direction.DOWNRIGHT).getY());
    }
}
