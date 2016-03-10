import java.sql.*;
import java.util.Scanner;


public class trainingSession {

    private static Connection myCon;
    private static Statement myStmt;
    private static Scanner scr = new Scanner(System.in);

    public static void main(String[] args) {
    }

    public void newTrainingSession() throws SQLException {
        myCon = DriverManager.getConnection("jdbc:mysql://localhost/treningsdagbok", "root", "");
        myStmt = myCon.createStatement();

        ResultSet sessionIDs = myStmt.executeQuery("SELECT MAX(sessionID) AS HighestID FROM trainingSession");
        int maxSessionID = 0;
        while(sessionIDs.next()){
            maxSessionID = sessionIDs.getInt("HighestID");
        }
        maxSessionID += 1;
        System.out.println("Creating new training session");

        System.out.print("What day is it? (int): ");
        String day = scr.nextLine();
        System.out.print("What month is it? (int): ");
        String month = scr.nextLine();
        System.out.print("What year is it? (int): ");
        String year = scr.nextLine();
        while (day.length() < 2){
            day = "0" + day;
        }
        while (month.length() < 2){
            month = "0" + month;
        }
        while (year.length() < 4){
            year = "0" + year;
        }
        String sessionDate = day + month + year;

        System.out.print("What time is it? (HHSS): ");
        String sessionTime = scr.nextLine();

        System.out.print("How long will you work out for? (mins): ");
        String sessionDuration = scr.nextLine();

        System.out.print("How is your personal form right now? (0-9): ");
        String personalForm = scr.nextLine();

        System.out.print("What is the intent of the training session? (any comment): ");
        String sessionIntent = scr.nextLine();

        String sql = "insert into trainingSession  "
                + " (sessionID, sessionDate, sessionTime, sessionDuration, personalForm, sessionIntent)"
                + " values (" + "'" + maxSessionID + "'" + ", " + "'" + sessionDate + "'" + ", "
                + "'" + sessionTime + "'" + ", " + "'" + sessionDuration + "'" + ", " + "'" + personalForm + "'" + ", "
                + "'" + sessionIntent + "'" + ")";
        myStmt.executeUpdate(sql);
    }

    public void getResults(){

    }

    public void updateTrainingSession(){

    }
}

