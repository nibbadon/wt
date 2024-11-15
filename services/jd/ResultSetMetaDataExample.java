// File: ResultSetMetaDataExample.java
import java.sql.*;

public class ResultSetMetaDataExample {
    public static void main(String[] args) throws ClassNotFoundException {
        // Database credentials
        String jdbcURL = "jdbc:mysql://localhost:3306/jdbcdb"; // Replace with your database URL
         //String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
   
        String username = "root"; // Replace with your MySQL username
        String password = "ramesh"; // Replace with your MySQL password

        // SQL query to select from a specific table
        String query = "SELECT * FROM employee"; // Replace with your table name
//Class.forName("oracle.jdbc.driver.OracleDriver");

        // JDBC connection and query execution
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Get the metadata of the result set
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Number of columns
            int columnCount = metaData.getColumnCount();

            // Print column names
            System.out.println("Table and Column names:");
            for (int i = 1; i <= columnCount; i++) {
                String tableName = metaData.getTableName(i);
                String columnName = metaData.getColumnName(i);
                System.out.print(columnName + " (" + tableName + ") | ");
            }
            System.out.println(); // Newline after printing column names

            // Print rows data
            System.out.println("\nRow data:");
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    Object columnValue = resultSet.getObject(i); // Fetch value based on column index
                    System.out.print(columnValue + " | ");
                }
                System.out.println(); // Newline after printing each row
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
