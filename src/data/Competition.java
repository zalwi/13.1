package data;

import java.util.ArrayList;
import java.util.Collections;

public class Competition {
    private String description;
    private ArrayList<Player> players;

    public Competition(String description) {
        this.description = description;
        players = new ArrayList<Player>();
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
