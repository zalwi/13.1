package logic;

import data.Competition;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;

public class CompetitionOperator {
    private final static String FINISH_COMPETITIONS = "STOP";
    private final static String TEST_PROGRAM = "TEST";
    private final static String SORT_DESCENDING = "1";
    private final static String SORT_ASCENDING = "2";

    public static void run(Competition competition) {
        boolean reverseList = chooseSortOption();
        boolean loop = true;
        do{loop = readPlayers(competition);} while (loop);
        ReportCreator.generateReport("stats.csv", competition.printListOfPlayersInCsvFormat(reverseList));
        System.out.println( "Dane posortowano i zapisano do pliku stats.csv.\n" +
                            "Zawartość pliku po zapisie:");
        System.out.println(competition.printListOfPlayersInCsvFormat(reverseList));
    }

    private static boolean readPlayers(Competition competition){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj wynik kolejnego gracza (lub stop:koniec programu, test:dodaj dane testowe):");
        String playerInfo = scanner.nextLine();

        switch(playerInfo.toUpperCase()){
            case FINISH_COMPETITIONS -> {
                return false;
            }
            case TEST_PROGRAM -> {
                fillTestData(competition);
                System.out.println( "Wypełnionio listę zawodników testowymi danymi,\n" +
                                    "możesz zakończyć program wpisując STOP lub dodać własnych zawodników do listy");
                return true;
            }
            default -> {
                String[] playerValues = playerInfo.split(" ");
                if(validateInput(playerValues)){
                    String  firstName   = playerValues[0],
                            lastName    = playerValues[1];
                    Integer points      = NumberUtils.createInteger(playerValues[2]);
                    competition.addPlayerResult(firstName,lastName,points);
                }
                return true;
            }
        }
    }

    private static boolean chooseSortOption(){
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        boolean reverseList = false;
        do{
            System.out.println("Wybierz tryb sortowania wyników\n" +
                    "1. Malejąco\n" +
                    "2. Rosnąco");
            String sortOption = scanner.nextLine();
            switch(sortOption.toUpperCase()) {
                case SORT_DESCENDING -> {
                    System.out.println("Wybrano malejące sortowanie wyników");
                    loop = false;
                }
                case SORT_ASCENDING -> {
                    System.out.println("Wybrano rosnące sortowanie wyników");
                    reverseList = true;
                    loop = false;
                }
                default -> {
                    System.out.println("Wybrano zły numer: " + sortOption);
                }
            }
        } while (loop);
        return reverseList;
    }

    private static void fillTestData(Competition competition){
        competition.addPlayerResult("Kamil", "Zalwert", 123);
        competition.addPlayerResult("Maciej", "Agrafka", 77);
        competition.addPlayerResult("Jan", "Kowalski", 56);
        competition.addPlayerResult("Paweł", "Pierwszy", 34);
        competition.addPlayerResult("Andrzej", "Drugi", 12);
        competition.addPlayerResult("Andrzej", "Agrafka", 77);
    }

    private static boolean validateInput(String[] playerValues) {
        if(playerValues.length <3){
            System.out.println("Za mała ilość argumentów");
            return false;
        }
        String  firstName = playerValues[0],
                lastName = playerValues[1],
                points = playerValues[2];

        if(firstName.length() < 2 || lastName.length() <2){
            System.out.println("Imię lub nazwisko jest zbyt krótkie (min 2 znaki)");
            return false;
        }
        if (!NumberUtils.isParsable(points)) {
            System.out.println("Punkty nie są liczbą");
            return false;
        }
        int pointsValue = NumberUtils.createInteger(points);
        if(pointsValue < 0){
            System.out.println("Punkty nie mogą być ujemne");
            return false;
        }
        return true;
    }
}
