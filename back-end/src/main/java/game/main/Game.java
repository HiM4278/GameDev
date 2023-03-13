package game.main;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Game {
    private List<Match> matches = new ArrayList<>();
    public static final Configuration configuration = Configuration.instance(Paths.get("Configuration.txt"));

    public boolean CreateMatch(String hostName, String roomName, String password, int maxPlayer){
        Player host  = new Player(hostName);

        for (Match m : matches){
            if(m.getRoomName() == roomName) {
                return false;
            }
        }
        matches.add(new Match(roomName,password,host,maxPlayer));
        return true;
    }

    public void updateMatches(){
        matches.removeIf(Match::isEnd);
    }

}
