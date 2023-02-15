import evaluator.node.Plan;
import evaluator.parser.PlanParser;
import exeption.SyntaxErrorException;

public class Main {
    public static void main(String[] args) throws SyntaxErrorException {
        PlanParser exprParser = new PlanParser();
        Plan plan = exprParser.parse("""
                t = t + 1  # keeping track of the turn number
                m = 0  # number of random moves
                while (deposit) { # still our region
                  if (deposit - 100)
                  then collect (deposit / 4)  # collect 1/4 of available deposit
                  else if (budget - 25) then invest 25
                  else {}
                  if (budget - 100) then {} else done  # too poor to do anything else
                  opponentLoc = opponent
                  if (opponentLoc / 10 - 1)
                  then  # opponent afar
                    if (opponentLoc % 10 - 5) then move downleft
                    else if (opponentLoc % 10 - 4) then move down
                    else if (opponentLoc % 10 - 3) then move downright
                    else if (opponentLoc % 10 - 2) then move upright
                    else if (opponentLoc % 10 - 1) then move upright
                    else move up
                  else if (opponentLoc)
                  then  # opponent adjacent to city crew
                    if (opponentLoc % 10 - 5) then {
                      cost = 10 ^ (opponent % 100 + 1) #21
                      if (budget - cost) then shoot upleft cost else {}
                    }
                    else if (opponentLoc % 10 - 4) then {
                      cost = 10 ^ (nearby downleft % 100 + 1)
                      if (budget - cost) then shoot downleft cost else {}
                    }
                    else if (opponentLoc % 10 - 3) then {
                      cost = 10 ^ (nearby down % 100 + 1)
                      if (budget - cost) then shoot down cost else {}
                    }
                    else if (opponentLoc % 10 - 2) then {
                      cost = 10 ^ (nearby downright % 100 + 1) # 33
                      if (budget - cost) then shoot downright cost else {}
                    }
                    else if (opponentLoc % 10 - 1) then {
                      cost = 10 ^ (nearby upright % 100 + 1)
                      if (budget - cost) then shoot upright cost else {}
                    }
                    else { # 40
                      cost = 10 ^ (nearby up % 100 + 1)
                      if (budget - cost) then shoot up cost else {}
                    }
                  else {  # no visible opponent; move in a random direction
                    dir = random % 6
                    if (dir - 4) then move upleft
                    else if (dir - 3) then move downleft
                    else if (dir - 2) then move down
                    else if (dir - 1) then move downright
                    else if (up) then move upright # 50
                    else move up
                    m = m + 1
                  }
                }  # end while
                # city crew on a region belonging to nobody, so claim it
                if (budget - 1) then invest 1 else {}
                """);
        StringBuilder s = new StringBuilder();
        plan.prettyPrint(s, 0);
        System.out.println(s);
    }
}