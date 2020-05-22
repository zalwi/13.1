package logic;

import data.Competition;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;

public class CompetitionOperator {
    private final static String FINISH_COMPETITIONS = "STOP";
    private final static String SORT_DESCENDING = "1";
    private final static String SORT_ASCENDING = "2";

    public static void run(Competition competition) {
        boolean reverseList = false;
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        do{
            System.out.println("Wybierz tryb sortowania wyników\n" +
                    "1. Malejąco\n" +
                    "2. Rosnąco");
            String sortOption = scanner.nextLine();

            if(sortOption.toUpperCase().equals(SORT_DESCENDING)) {
                System.out.println("Wybrano malejące sortowanie wyników");
                loop = false;
            }else if(sortOption.toUpperCase().equals(SORT_ASCENDING)){
                System.out.println("Wybrano rosnące sortowanie wyników");
                reverseList = true;
                loop = false;
            }else{
                System.out.println("Wybrano zły numer: " + sortOption);
            }
        } while (loop);

        loop = true; //dla następnej pętli
        do {
            System.out.println("Podaj wynik kolejnego gracza (lub stop):");

            String playerInfo = scanner.nextLine();
            if(playerInfo.toUpperCase().equals(FINISH_COMPETITIONS)) {
                loop = false;
                // ---- test block ----
                competition.addPlayerResult("Kamil", "Zalwert", 123);
                competition.addPlayerResult("Maciej", "Agrafka", 77);
                competition.addPlayerResult("Jan", "Kowalski", 56);
                competition.addPlayerResult("Paweł", "Pierwszy", 34);
                competition.addPlayerResult("Andrzej", "Drugi", 12);
                competition.addPlayerResult("Andrzej", "Agrafka", 77);
                // ---- end of test ----
                ReportCreator.generateReport("stats.csv", competition.printListOfPlayersInCsvFormat(reverseList));
                System.out.println("Dane posortowano i zapisano do pliku stats.csv.\n" +
                        "Zawartość pliku po zapisie:");
                System.out.println(competition.printListOfPlayersInCsvFormat(reverseList));
                return;
            }

            String[] playerValues = playerInfo.split(" ");
            if(validateInput(playerValues)){
                String  firstName   = playerValues[0],
                        lastName    = playerValues[1];
                Integer points      = NumberUtils.createInteger(playerValues[2]);

                competition.addPlayerResult(firstName,lastName,points);
            }
        } while (loop);
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
