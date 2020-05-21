package data;

import java.util.ArrayList;

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
}
