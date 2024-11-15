import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTransactionExample {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            // Step 1: Establish database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "ramesh");

            // Step 2: Disable auto-commit mode
            conn.setAutoCommit(false);

            // Step 3: Prepare SQL statements
            String sql1 = "UPDATE bank_account SET balance = balance - ? WHERE account_id = ?";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setDouble(1, 1000); // deduct 1000 from account 1
            pstmt1.setInt(2, 1);

            String sql2 = "UPDATE bank_account SET balance = balance + ? WHERE account_id = ?";
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setDouble(1, 1000); // add 1000 to account 2
            pstmt2.setInt(2, 2);

            // Step 4: Execute SQL statements
            pstmt1.executeUpdate();
            pstmt2.executeUpdate();

            // Step 5: Commit the transaction if both updates succeed
            conn.commit();
            System.out.println("Transaction committed successfully");

        } catch (SQLException e) {
            // Step 6: Rollback the transaction in case of error
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transaction rolled back due to error");
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Step 7: Clean up resources
            try {
                if (pstmt1 != null) pstmt1.close();
                if (pstmt2 != null) pstmt2.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
