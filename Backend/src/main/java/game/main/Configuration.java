package game.main;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class Configuration {
    private long M,N,timeForFirstPlan,timeForRevision,initBudget,initCenterDeposit,max_dep;
    private int rev_cost,interest_pct;
    private HashMap<String, Integer> map;

    public Configuration(Path p) throws IOException {
        map = readFromFile(p);
    }
    private HashMap<String,Integer> readFromFile(Path path) throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        Scanner scanner = new Scanner(path);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String noSpacesLine = line.replaceAll("\\s*=\\s*", "=");
            String[] tokens = noSpacesLine.split("=");
            if (tokens.length == 2) {
                String key = tokens[0].toLowerCase();
                int value = Integer.parseInt(tokens[1]);
                map.put(key, value);
            }
        }
        scanner.close();
        return map;
    }
    public void setup() throws IOException {
        this.M = map.get("m");
        this.N = map.get("n");
        this.initBudget = map.get("init_budget");
        this.initCenterDeposit = map.get("init_center_dep");
        this.max_dep = map.get("max_dep");
        this.interest_pct = map.get("interest_pct");
        this.rev_cost = map.get("rev_cost");
        this.timeForFirstPlan = (map.get("init_plan_min")*60) + map.get("init_plan_sec");
        this.timeForRevision = (map.get("plan_rev_min")*60) + map.get("plan_rev_sec");
    }
    public long getM() {
        return M;
    }
    public long getN(){
        return N;
    }
    public long getTimeForFirstPlan(){
        return timeForFirstPlan;
    }
    public long getTimeForRevision(){
        return timeForRevision;
    }
    public long getInitBudget(){
        return initBudget;
    }
    public long getInitCenterDeposit(){
        return initCenterDeposit;
    }
    public long getMax_dep(){return max_dep;}
    public int getRev_cost(){return rev_cost;}
    public int getInterest_pct(){return interest_pct;}
}
