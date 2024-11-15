import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcMultipleConn{
    public static void main(String[] args) {
        Connection conn1 = null;
        Connection conn2 = null;
        Connection conn3 = null;

        try{
            //Connection no #1
            String url1 = "jdbc:mysql://localhost:3306/jdbcdb"; 
            String user = "root";
            String password = "ramesh";
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {           
                System.out.println("Connected to the database jdbcdb");
                    }
                String url2 = "jdbc:mysql://localhost:3306/jdbcdb?user=root&password=ramesh";
            conn2 = DriverManager.getConnection(url2);
            if (conn2 != null) {
            System.out.println("Connected to the database jdbcdb");
        }

            String url3 = "jdbc:mysql://localhost:3306/jdbcdb";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "ramesh");
            conn3 = DriverManager.getConnection(url3, info);
                if (conn3 != null) {
                System.out.println("Connected to the database jdbcdb");
        }
        
        }

        catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
                }
        }
    }                
