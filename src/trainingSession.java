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

    public void getResults() throws SQLException {
        myCon = DriverManager.getConnection("jdbc:mysql://localhost/treningsdagbok", "root", "");
        myStmt = myCon.createStatement();

        System.out.print("Do you want Cardio//Strength or Endurance? (C or E): ");
        String option = scr.nextLine();
        System.out.println(option);
        ResultSet myRes;
        if (option.toUpperCase().equals("C")){
            myRes = myStmt.executeQuery("SELECT * FROM cardiostrengthresult");
            System.out.println("Load, Reps, Sets");
            while (myRes.next()){
                System.out.println(myRes.getString("resultLoad") + ", " + myRes.getString("resultReps") + ", " +
                        myRes.getString("resultSets"));
            }
        }
        else if (option.toUpperCase().equals("E")){
            myRes = myStmt.executeQuery("SELECT * FROM enduranceresult");
            System.out.println("Duration, Length");
            while (myRes.next()){
                System.out.println(myRes.getString("resultDuration") + ", " + myRes.getString("resultLength"));
            }
        }
    }

    public void updateTrainingSession(){

    }

    public void initializeDatabase(){
        //Initialize the database
        try {
            // 1. Get connection to database
            myCon = DriverManager.getConnection("jdbc:mysql://localhost/treningsdagbok", "root", "");
            // 2. Create a statement
            myStmt = myCon.createStatement();
            // 3. Execute SQL query
            String sql = "insert into trainingSession  "
                    + " (sessionID, sessionDate, sessionTime, sessionDuration, personalForm, sessionIntent)"
                    + " values ('1991', '10032016', '1230', '120', '7', 'Better stamina')";
            myStmt.executeUpdate(sql);

            sql = "insert into trainingSession  "
                    + " (sessionID, sessionDate, sessionTime, sessionDuration, personalForm, sessionIntent)"
                    + " values ('1990', '10042016', '1230', '120', '7', 'Better stamina')";
            myStmt.executeUpdate(sql);


            sql = "insert into exercise" +
                    "(description, name, replacement, trainingSession_sessionID)" +
                    "values ('Builds core and leg muscles', 'Squats', 'None', '1991')";
            myStmt.executeUpdate(sql);

            sql = "insert into exercise" +
                    "(description, name, replacement, trainingSession_sessionID)" +
                    "values ('Builds core and leg muscles', 'Squats', 'None', '1990')";
            myStmt.executeUpdate(sql);

            sql = "insert into cardiostrength" +
                    "(exercise_name, exercise_trainingSession_sessionID, goalLoad, goalReps, goalSets)" +
                    "values('Squats', '1991', '65', '8', '3')";
            myStmt.executeUpdate(sql);

            sql = "insert into cardiostrength" +
                    "(exercise_name, exercise_trainingSession_sessionID, goalLoad, goalReps, goalSets)" +
                    "values('Squats', '1990', '65', '8', '3')";
            myStmt.executeUpdate(sql);

            sql = "insert into cardiostrengthresult" +
                    "(cardioStrength_exercise_name, cardioStrength_exercise_trainingSession_sessionID, historyID, resultLoad, resultReps, resultSets)" +
                    "values('Squats', '1991', '1', '65', '8', '3')";
            myStmt.executeUpdate(sql);

            sql = "insert into cardiostrengthresult" +
                    "(cardioStrength_exercise_name, cardioStrength_exercise_trainingSession_sessionID, historyID, resultLoad, resultReps, resultSets)" +
                    "values('Squats', '1990', '0', '65', '7', '3')";
            myStmt.executeUpdate(sql);

            //ResultSet res = myStmt.executeQuery("SELECT * FROM trainingSession");
            //while (res.next()){
            //    System.out.println(res.getString("sessionDate") + ", " + res.getString("sessionTime"));
            //}
            System.out.println("Insert complete");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

