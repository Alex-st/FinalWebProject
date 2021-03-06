package dao.data;

import dao.models.Tutor;
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
 * <h1>TutorsDao</h1>
 * TutorsDao class is responsible for Data Access Object in DAO pattern
 * for tutors table. Logic for processing data from table "tutors" is
 * implemented here ()
 *
 */
public class TutorsDao {
    private List<Tutor> tutorsList;
    private MyDBPool pool;
    private String propFileName = "resources/myconfig.properties";
    final static String find = "SELECT COUNT(*) FROM tutors WHERE tLogin = ? AND tPassword = ?";
    final static String findByLogin = "SELECT tName, tSurname FROM tutors WHERE tLogin = ?";
    final static String findIdByLogin = "SELECT idtutors FROM tutors WHERE tLogin = ?";

    public TutorsDao() {

        tutorsList = new ArrayList<>();
        Properties prop = new Properties();

        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(propFileName);
            prop.load(in);
            //prop.load(new FileInputStream(propFileName));
        } catch (IOException e) {
            Logger.getLogger(TutorsDao.class.getName()).log(Level.INFO, null, e);
            Logger.getLogger(TutorsDao.class.getName()).info("config file not found");
            System.out.println("config file not found");
        }

        // get the property value and use it for dbpool
        String db = prop.getProperty("db");
        String dbuser = prop.getProperty("dbuser");
        String dbpass = prop.getProperty("dbpass");

        pool = new MyDBPool(db, dbuser, dbpass);
    }

    public void insertTutorsToDB(Tutor... tutors) throws SQLException {

        Connection conn = pool.getConnection();
        try {
            PreparedStatement statement=
                    conn.prepareStatement(
                            "INSERT INTO tutors (tName, tSurname, tLogin, tPassword, email) VALUES (?,?,?,?,?)");
            for (Tutor i: tutors) {
              //  statement.setInt(1, i.getIdtutors());
                statement.setString(1, i.gettName());
                statement.setString(2, i.gettSurname());
                statement.setString(3, i.gettLogin());
                statement.setString(4, i.gettPassword());
                statement.setString(5, i.getEmail());
                statement.addBatch();
            }
            int [] updateCounts = statement.executeBatch();

        } catch (BatchUpdateException e) {
            Logger.getLogger(TutorsDao.class.getName()).log(Level.INFO, null, e);}

        pool.releaseConnection(conn);

    }

    public void removeTutorFromDB(Tutor tutor) throws SQLException{
        Connection conn = pool.getConnection();

        PreparedStatement statement=
                conn.prepareStatement(
                        "DELETE FROM tutors WHERE idtutors=? AND tName=?");

        statement.setInt(1, tutor.getIdtutors());
        statement.setString(2, tutor.gettName());
        statement.executeUpdate();

        pool.releaseConnection(conn);

    }

    public boolean findTutor(Tutor tutor) {
        Connection conn = pool.getConnection();

        try {
            PreparedStatement st = conn.prepareStatement(find);
            st.setString(1, tutor.gettLogin());
            st.setString(2, tutor.gettPassword());

            ResultSet rs = st.executeQuery();
            rs.next(); //at the begining iterator placed before first element

            if (rs.getInt(1) > 0 )
                return true;
        } catch (SQLException e) {
            Logger.getLogger(TutorsDao.class.getName()).log(Level.INFO, null, e);
        } finally {
            pool.releaseConnection(conn);
        }

        return false;
    }

    public int getIdByLogin(String login) {
        Connection conn = pool.getConnection();
        int tmp=0;

        try {
            PreparedStatement st = conn.prepareStatement(findIdByLogin);
            st.setString(1, login);

            ResultSet rs = st.executeQuery();
            // rs.next(); //at the begining iterator placed before first element

            while (rs.next()) {

                tmp = rs.getInt(1);
            }

        } catch (SQLException e) {
            Logger.getLogger(TutorsDao.class.getName()).log(Level.INFO, null, e);
        } finally {
            pool.releaseConnection(conn);
        }
        return tmp;
    }

    public String findTutorByLogin(Tutor tutor) {
        Connection conn = pool.getConnection();
        String tmp="";

        try {
            PreparedStatement st = conn.prepareStatement(findByLogin);
            st.setString(1, tutor.gettLogin());

            ResultSet rs = st.executeQuery();
            // rs.next(); //at the begining iterator placed before first element

            while (rs.next()) {

                tmp = rs.getString(1)+" "+rs.getString(2);
            }

        } catch (SQLException e) {
            Logger.getLogger(TutorsDao.class.getName()).log(Level.INFO, null, e);
        } finally {
            pool.releaseConnection(conn);
        }
        return tmp;
    }

    public List<Tutor> getAllTutorsFromDB() throws SQLException{
        List<Tutor> tmp = new ArrayList<>();

        Connection conn = pool.getConnection();

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM tutors");
//        ResultSetMetaData rsmd = rs.getMetaData();
//
//        int columns = rsmd.getColumnCount();

        while (rs.next()) {
            Tutor currTutor = new Tutor();
            currTutor.setIdtutors(rs.getInt(1));
            currTutor.settName(rs.getString(2));
            currTutor.settSurname(rs.getString(3));
            currTutor.settLogin(rs.getString(4));
            currTutor.settPassword(rs.getString(5));
            currTutor.setEmail(rs.getString(6));

            tmp.add(currTutor);
        }

        pool.releaseConnection(conn);
        return tmp;
    }
}
