package data;

public class Player {
    private String firstName;
    private String lastName;
    private Integer points;

    public Player(String firstName, String lastName, Integer points) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
    }

    @Override
    public String toString() {
        return firstName + ';' + lastName + ';' + points;
    }
}
