import data.Competition;
import logic.CompetitionOperator;

public class Main {
    public static void main(String[] args) {
        Competition competition = new Competition("Zawody strzeleckie");
        CompetitionOperator.run(competition);
    }
}

