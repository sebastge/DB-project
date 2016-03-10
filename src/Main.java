import java.sql.*;
import java.util.Scanner;

/**
 * Created by allih_000 on 10/03/2016.
 */
public class Main {
    public static void main(String args[]) throws SQLException {

        Scanner scr = new Scanner(System.in);
        trainingSession trnssn = new trainingSession();
        System.out.println("Skriv et tall mellom 1-3. 0 for å avslutte.");
        System.out.println("1: Ny treningsøkt");
        System.out.println("2: Resultater");
        System.out.println("3: Oppdater gammel økt");
        System.out.println("4: 'Hidden' developer option. Fill the database with starting entries.");
        int query = Integer.parseInt(scr.nextLine());
        while (query != 0){
            if (query == 1){
                trnssn.newTrainingSession();
            }
            if (query == 2){
                trnssn.getResults();
            }
            if (query == 3){
                trnssn.updateTrainingSession();
            }
            if (query == 4){
                trnssn.initializeDatabase();
            }

            System.out.println("Skriv et tall mellom 1-3. 0 for å avslutte.");
            System.out.println("1: Ny treningsøkt");
            System.out.println("2: Resultater");
            System.out.println("3: Oppdater gammel økt");
            query = Integer.parseInt(scr.nextLine());
        }
    }
}
