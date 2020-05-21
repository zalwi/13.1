package logic;

import data.Competition;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;

public class CompetitionOperator {
    private final static String FINISH_COMPETITIONS = "STOP";

    public static void run(Competition competition) {
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Podaj wynik kolejnego gracza (lub stop):");

            String playerInfo = scanner.nextLine();
            if(playerInfo.toUpperCase().equals(FINISH_COMPETITIONS)) {
                loop = false;
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
