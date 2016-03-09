import java.sql.*;


public class trainingSession {


    public static void main(String[] args) {

        try {
            // 1. Get connection to database
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
            // 2. Create a statement
            Statement myStmt = myCon.createStatement();
            // 3. Execute SQL query
            String sql = "insert into trainingSession  "
                    + " (sessionID, sessionDate, sessionTime, sessionDuration, personalForm, sessionIntent)"
                    + " values ('1999', '10032016', '1230', '120', '7', 'Better stamina')";
            myStmt.executeUpdate(sql);
            System.out.println("Insert complete");
        } catch (Exception e) {
            e.printStackTrace();
    }
    }
}

