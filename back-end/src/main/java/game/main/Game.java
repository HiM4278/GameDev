package game.main;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public class Game {
    private List<Match> matches;
    private Configuration configuration;

    public boolean CreateMatch(Configuration configuration , String name, String password,Player host,int MaxPlayer){
        boolean check = false;
        for (Match m : matches){
            if(m.getName() == name) {
                check = false;
            } else {
                check = true;
            }
        }
        if (check){
            matches.add(new Match(configuration,name,password,host,MaxPlayer));
        }
        return check;
    }
    public void updateMatches(){
        for (Match m : matches){
            if (m.isEnd() == true){
                matches.remove(m);
            }
        }
    }

}
