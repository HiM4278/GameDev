package com.example.game.main;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class Configuration {
    private static Configuration instance;
    private long M,N,timeForFirstPlan,timeForRevision,initBudget,initCenterDeposit,max_dep;
    private int rev_cost,interest_pct;
    private final HashMap<String, Integer> map;

    private Configuration(Path p) {
        map = readFromFile(p);
        this.setup();
    }

    public static Configuration instance(Path path){
        if(instance == null){
            instance = new Configuration(path);
        }
        return instance;
    }

    private HashMap<String,Integer> readFromFile(Path path) {
        try (Scanner scanner = new Scanner(path)){
            HashMap<String, Integer> map = new HashMap<>();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String noSpacesLine = line.replaceAll("\\s*=\\s*", "=");
                String[] tokens = noSpacesLine.split("=");
                if(tokens.length==2) {
                    String key = tokens[0].toLowerCase();
                    int value = Integer.parseInt(tokens[1]);
                    map.put(key, value);
                }
            }
            scanner.close();
            return map;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void setup() {
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
