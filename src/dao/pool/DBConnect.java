/**
 * * <h1>DBConnect</h1>
 * DBConnect class is test class for MYDBPool. It is not used in web project logic
 * Created by alex on 6/11/15.
 */
package dao.pool;

import java.sql.*;

public class DBConnect {
    MyDBPool pool;

    public DBConnect() {
        pool = new MyDBPool("mystudydb", "root", "st");
    }

    public static void main(String[] args) throws SQLException{

        DBConnect test = new DBConnect();

        Connection conn = test.pool.getConnection();

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM students");
        ResultSetMetaData rsmd = rs.getMetaData();

        int columns = rsmd.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columns; i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println(" ");
        }

        test.pool.releaseConnection(conn);
        Connection conn2 = test.pool.getConnection();

        ResultSet rs2 = st.executeQuery("SELECT * FROM questions");
        ResultSetMetaData rsmd2 = rs2.getMetaData();

        int columns2 = rsmd2.getColumnCount();

        while (rs2.next()) {
            for (int i = 1; i <= columns2; i++) {
                System.out.print(rs2.getString(i) + " ");
            }
            System.out.println(" ");
        }

        test.pool.releaseConnection(conn2);


    }
}
