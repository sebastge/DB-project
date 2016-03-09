import java.sql.*;


public class trainingSession {


    public static void main(String[] args) {

        try {
            // 1. Get connection to database
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost/treningsdagbok", "root", "");
            // 2. Create a statement
            Statement myStmt = myCon.createStatement();
            // 3. Execute SQL query
            //String sql = "SELECT navn FROM exercise " +
            //        "JOIN trainingSession ON trainingSession.sessionID = exercise.trainingSession_sessionID" +
            //        "JOIN "
            String sql = "insert into trainingSession  "
                    + " (sessionID, sessionDate, sessionTime, sessionDuration, personalForm, sessionIntent)"
                    + " values ('1990', '10032016', '1230', '120', '7', 'Better stamina')";
            myStmt.executeUpdate(sql);
            ResultSet res = myStmt.executeQuery("SELECT * FROM trainingSession");
            while (res.next()){
                System.out.println(res.getString("sessionDate") + ", " + res.getString("sessionTime"));
            }
            System.out.println("Insert complete");
        } catch (Exception e) {
            e.printStackTrace();
    }
    }
}

