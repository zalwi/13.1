package data;

public class Player implements Comparable<Player>{
    private String firstName;
    private String lastName;
    private Integer points;

    public Player(String firstName, String lastName, Integer points) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
    }

    @Override
    public int compareTo(Player player) {
        //w zależności od znaku czyli +1 albo -1 będzie sortowanie rosnąco lub malejąco
        if (this.points > player.points)
            return -1;
        else if (this.points < player.points)
            return 1;
        int lastNameCompare = this.lastName.compareTo(player.lastName);
        if (lastNameCompare != 0)
            return lastNameCompare;
        return this.firstName.compareTo(player.firstName);
    }

    @Override
    public String toString() {
        return firstName + ';' + lastName + ';' + points;
    }
}
