package game.main;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Game {
    private List<Match> matches = new ArrayList<>();
    private Configuration configuration;

    public boolean CreateMatch(Configuration configuration , String name, String password,Player host,int MaxPlayer){

        for (Match m : matches){
            if(m.getName() == name) {
                return false;
            }
        }
        matches.add(new Match(configuration,name,password,host,MaxPlayer));
        return true;
    }
    public void updateMatches(){
        for (Match m : matches){
            if (m.isEnd() == true){
                matches.remove(m);
            }
        }
    }

}
