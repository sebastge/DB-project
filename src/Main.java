import java.sql.*;
import java.util.Scanner;

/**
 * Created by allih_000 on 10/03/2016.
 */
public class Main {
    public static void main(String args[]) throws SQLException {

        //Initialize the database
        try {
            // 1. Get connection to database
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost/treningsdagbok", "root", "");
            // 2. Create a statement
            Statement myStmt = myCon.createStatement();
            // 3. Execute SQL query
            String sql = "insert into trainingSession  "
                    + " (sessionID, sessionDate, sessionTime, sessionDuration, personalForm, sessionIntent)"
                    + " values ('1991', '10032016', '1230', '120', '7', 'Better stamina')";
            myStmt.executeUpdate(sql);
            ResultSet res = myStmt.executeQuery("SELECT * FROM trainingSession");
            while (res.next()){
                System.out.println(res.getString("sessionDate") + ", " + res.getString("sessionTime"));
            }
            System.out.println("Insert complete");
        } catch (Exception e) {
            e.printStackTrace();
        }


        Scanner scr = new Scanner(System.in);
        trainingSession trnssn = new trainingSession();
        System.out.println("Skriv et tall mellom 1-3. 0 for å avslutte.");
        System.out.println("1: Ny treningsøkt");
        System.out.println("2: Resultater");
        System.out.println("3: Oppdater gammel økt");
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

            System.out.println("Skriv et tall mellom 1-3. 0 for å avslutte.");
            System.out.println("1: Ny treningsøkt");
            System.out.println("2: Resultater");
            System.out.println("3: Oppdater gammel økt");
            query = Integer.parseInt(scr.nextLine());
        }
    }
}
