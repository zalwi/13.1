package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Competition {
    private String description;
    private List<Player> players;

    public Competition(String description) {
        this.description = description;
        this.players = new ArrayList<Player>();
    }

    public void addPlayerResult(String firstName, String lastName, Integer points){
        players.add(new Player(firstName,lastName,points));
    }

    public String printListOfPlayersInCsvFormat(boolean reverseList){
        String content = "FIRSTNAME;LASTNAME;POINTS\n";
        Collections.sort(players);
        if(reverseList) Collections.reverse(players);
        for(Player p: players){
            content += p + "\n";
        }
        return content;
    }
}
