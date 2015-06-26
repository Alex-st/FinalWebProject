package dao.data;

import dao.models.Student;
import dao.pool.MyDBPool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * <h1>StudentsDao</h1>
 * StudentsDao class is responsible for Data Access Object in DAO pattern
 * for students table. Logic for processing data from table "students" is
 * implemented here ()
 *
 */
public class StudentsDao {
    private List<Student> studentsList;
    private MyDBPool pool;
    private String propFileName = "resources/myconfig.properties";
    final static String find = "SELECT COUNT(*) FROM students WHERE studLogin = ? AND studPassword = ?";
    final static String findByLogin = "SELECT studName, studSurname FROM students WHERE studLogin = ?";

    public StudentsDao() {

        studentsList = new ArrayList<>();
        Properties prop = new Properties();

        try {
            InputStream in = this.getClass()
                    .getClassLoader()
                    .getResourceAsStream(propFileName);
            prop.load(in);
            //prop.load(new FileInputStream(propFileName));
        } catch (IOException ex) {
            System.out.println("config file not found");
            Logger.getLogger(StudentsDao.class.getName()).log(Level.ERROR, null, ex);
        }

        // get the property value and use it for dbpool
        String db = prop.getProperty("db");
        String dbuser = prop.getProperty("dbuser");
        String dbpass = prop.getProperty("dbpass");

        pool = new MyDBPool(db, dbuser, dbpass);
    }

    public void insertStudentsToDB(Student... students) {

        Connection conn = pool.getConnection();

        try {
        PreparedStatement statement=
                conn.prepareStatement(
                        "INSERT INTO students (studName, studSurname, studLogin, studPassword, email) VALUES (?,?,?,?,?)");
            System.out.println(statement == null);
        for (Student i: students) {
            //statement.setInt(1, i.getIdstudents());
            statement.setString(1, i.getStudName());
            statement.setString(2, i.getStudSurname());
            statement.setString(3, i.getStudLogin());
            statement.setString(4, i.getStudPassword());
            statement.setString(5, i.getEmail());
            statement.addBatch();
        }
        int [] updateCounts = statement.executeBatch();

    } catch (BatchUpdateException ex) {
            Logger.getLogger(StudentsDao.class.getName()).log(Level.ERROR, null, ex);}
        catch (SQLException ee) {
            Logger.getLogger(StudentsDao.class.getName()).log(Level.ERROR, null, ee);
        }

        pool.releaseConnection(conn);

    }

    public void removeStudentFromDB(Student student) throws SQLException{
        Connection conn = pool.getConnection();

        PreparedStatement statement=
                    conn.prepareStatement(
                            "DELETE FROM students WHERE idstudents=? AND studName=?");

        statement.setInt(1, student.getIdstudents());
        statement.setString(2, student.getStudName());
        statement.executeUpdate();

        pool.releaseConnection(conn);

    }

    public boolean findStudent(Student student) {
        Connection conn = pool.getConnection();

        try {
            PreparedStatement st = conn.prepareStatement(find);
            st.setString(1, student.getStudLogin());
            st.setString(2, student.getStudPassword());

            ResultSet rs = st.executeQuery();
            rs.next(); //at the begining iterator placed before first element

            if (rs.getInt(1) > 0 )
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDao.class.getName()).log(Level.ERROR, null, ex);
        } finally {
            pool.releaseConnection(conn);
        }
        return false;
    }

    public String findStudentByLogin(Student student) {
        Connection conn = pool.getConnection();
        String tmp="";

        try {
            PreparedStatement st = conn.prepareStatement(findByLogin);
            st.setString(1, student.getStudLogin());

            ResultSet rs = st.executeQuery();
           // rs.next(); //at the begining iterator placed before first element

            while (rs.next()) {

                tmp = rs.getString(1)+" "+rs.getString(2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentsDao.class.getName()).log(Level.ERROR, null, ex);
        } finally {
            pool.releaseConnection(conn);
        }
        return tmp;
    }

    public List<Student> getAllStudentsFromDB() throws SQLException{
        List<Student> tmp = new ArrayList<>();

        Connection conn = pool.getConnection();

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM students");
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int columns = rsmd.getColumnCount();

        while (rs.next()) {
            Student currStudent = new Student();
            currStudent.setIdstudents(rs.getInt(1));
            currStudent.setStudName(rs.getString(2));
            currStudent.setStudSurname(rs.getString(3));
            currStudent.setStudLogin(rs.getString(4));
            currStudent.setStudPassword(rs.getString(5));
            currStudent.setEmail(rs.getString(6));

            tmp.add(currStudent);
        }

        pool.releaseConnection(conn);
        return tmp;
    }

    //main method for testing
    public static void main(String[] args) {
        StudentsDao test = new StudentsDao();
        Student testStudent = new Student(2, "максим", "перепилица", "perepel", "test", "mail");
        test.insertStudentsToDB(testStudent);


        try {
            test.studentsList = test.getAllStudentsFromDB();
        } catch (SQLException ex) {
            Logger.getLogger(StudentsDao.class.getName()).log(Level.ERROR, null, ex);
        }
        System.out.println(test.studentsList.size());
        for(Student i: test.studentsList) {
            System.out.println(i.getIdstudents()+" "+i.getStudName()+" "+i.getStudSurname());
        }
    }
}
