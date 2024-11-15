// File: UpdateEmployeeSalary.java
import java.sql.*;
import java.util.Scanner;

public class PreparedUpdateex {
    public static void main(String[] args) {
        // Database credentials
        String jdbcURL = "jdbc:mysql://localhost:3306/jdbcdb"; // Replace with your database URL

        //private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
   
        String username = "root"; // Replace with your MySQL username
        String password = "ramesh"; // Replace with your MySQL password

        // SQL query to update employee salary by 2% where dept_id matches
        String updateSalaryQuery = "UPDATE employee SET salary = salary * 1.02 WHERE dept_id = ?";
         String query="select * from employee";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateSalaryQuery);
             Scanner scanner = new Scanner(System.in)) {

            // Get the dept_id from the user
            System.out.print("Enter the department ID to update salaries: ");
            int deptId = scanner.nextInt();

            // Set the dept_id parameter in the query
            preparedStatement.setInt(1, deptId);

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();
            
            // Output the result
            System.out.println("Salaries updated for department ID: " + deptId);
            System.out.println("Number of rows affected: " + rowsAffected);

            Statement st=connection.createStatement();
            
            ResultSet resultSet = st.executeQuery(query);
             System.out.println("Employee Records:");
            while (resultSet.next()) {
                // Retrieve the record fields using ResultSet
                int empId = resultSet.getInt("emp_id");
                String empName = resultSet.getString("empname");
                Date dob = resultSet.getDate("dob");
                double salary = resultSet.getDouble("salary");
                int dept_Id = resultSet.getInt("dept_id");

                // Print out the retrieved values
                System.out.println("Employee ID: " + empId);
                System.out.println("Employee Name: " + empName);
                System.out.println("Date of Birth: " + dob);
                System.out.println("Salary: " + salary);
                System.out.println("Department ID: " + dept_Id);
                System.out.println("--------------");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
