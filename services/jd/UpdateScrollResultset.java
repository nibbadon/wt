// File: ScrollableAndUpdatableResultSetExample.java
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateScrollResultset {
    public static void main(String[] args) throws ClassNotFoundException {
        // Database credentials
        //Class.forName("oracle.jdbc.driver.OracleDriver");
       //String jdbcURL = "jdbc:oracle:thin:@localhost:1521/XE";
        String jdbcURL = "jdbc:mysql://localhost:3306/jdbcdb"; // Replace with your database URL
        String username = "root"; // Replace with your MySQL username
        String password = "ramesh"; // Replace with your MySQL password

        // SQL query to select all rows from employee table
        String query = "SELECT emp_id, empname, dob,salary,dept_id FROM employee";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {

            // Create a scrollable and updatable ResultSet
            Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,      // Scrollable: Sensitive to changes made by others
                ResultSet.CONCUR_UPDATABLE            // Updatable: Can update through the ResultSet
            );

            // Execute the query to get the ResultSet
            ResultSet resultSet = statement.executeQuery(query);

            // Scroll to the last row and display the data
            if (resultSet.last()) {
                System.out.println("Last Employee:");
                printEmployee(resultSet);
            }

            // Scroll back to the first row and display the data
            if (resultSet.first()) {
                System.out.println("First Employee:");
                printEmployee(resultSet);
            }

            // Scroll to an absolute row (e.g., 3rd row) and display the data
            if (resultSet.absolute(3)) {
                System.out.println("Third Employee:");
                printEmployee(resultSet);
            }

            // Update a record's salary using the ResultSet
            resultSet.absolute(2); // Move to the second row
            double oldSalary = resultSet.getDouble("salary");
            double newSalary = oldSalary * 1.05; // Increase salary by 5%
            resultSet.updateDouble("salary", newSalary);
            

            resultSet.updateRow(); // Update the row in the database

            System.out.println("Updated Employee (2nd row) Salary: ");
            printEmployee(resultSet);

           // Insert a new row into the employee table using ResultSet
            resultSet.moveToInsertRow(); // Move to a special insert row
            resultSet.updateInt("emp_id",9); // Set emp_id for new employee
            resultSet.updateString("empname", "Karthik");
            resultSet.updateDate("dob", Date.valueOf("1990-01-01"));
            resultSet.updateDouble("salary", 500000);
            resultSet.updateInt("dept_id", 15);
            resultSet.insertRow(); // Insert the row in the database
            System.out.println("Inserted a new employee.");
    
            // Delete the newly inserted row
            if (resultSet.last()) {
                resultSet.deleteRow(); // Delete the last row (which was just inserted)
                System.out.println("Deleted the newly inserted employee.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Utility method to print employee details
    private static void printEmployee(ResultSet resultSet) throws SQLException {
        int empId = resultSet.getInt("emp_id");
        String empName = resultSet.getString("empname");
        Date dob = resultSet.getDate("dob");
        double salary = resultSet.getDouble("salary");
        int deptId = resultSet.getInt("dept_id");

        System.out.println("Employee ID: " + empId);
        System.out.println("Employee Name: " + empName);
       // System.out.println("Date of Birth: " + dob);
        System.out.println("Salary: " + salary);
        System.out.println("Department ID: " + deptId);
        System.out.println("------------------------");
    }
}
