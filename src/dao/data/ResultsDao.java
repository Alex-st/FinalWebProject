package dao.data;

import dao.models.Result;
import dao.pool.MyDBPool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * <h1>ResultsDao</h1>
 * ResultsDao class is responsible for Data Access Object in DAO pattern
 * for "results" table. Logic for processing data from table "results" is
 * implemented here ()
 *
 */
public class ResultsDao {
    private MyDBPool pool;
    private String propFileName = "resources/myconfig.properties";

    private static final String findResulByLogin =
            "SELECT topics.topicName, results.Mark FROM results INNER JOIN topics ON topics.idtopics = results.TopicId" +
                    " WHERE StudId = (SELECT idStudents FROM students WHERE studLogin = ?)";

    private static final String addResult = "INSERT INTO results (StudId, TopicId, Mark) VALUES" +
            "((SELECT idstudents FROM students WHERE studLogin = ?), (SELECT idtopics FROM topics WHERE topicName=?), ?)";

    public ResultsDao() {
        Properties prop = new Properties();

        try {
            InputStream in = this.getClass()
                    .getClassLoader()
                    .getResourceAsStream(propFileName);
            prop.load(in);
            //prop.load(new FileInputStream(propFileName));
        } catch (IOException ex) {
            System.out.println("config file not found");
            Logger.getLogger(ResultsDao.class.getName()).log(Level.ERROR, null, ex);
            Logger.getLogger(ResultsDao.class.getName()).info("config file not found");
        }

        // get the property value and use it for dbpool
        String db = prop.getProperty("db");
        String dbuser = prop.getProperty("dbuser");
        String dbpass = prop.getProperty("dbpass");

        pool = new MyDBPool(db, dbuser, dbpass);
    }

    public Map<String, Integer> getStudentResultByLogin(String stLogin) {
        Map<String, Integer> tmp = new HashMap<>();
        Connection conn = pool.getConnection();

        try {
            PreparedStatement st = conn.prepareStatement(findResulByLogin);
            st.setString(1, stLogin);

            ResultSet rs = st.executeQuery();
            // rs.next(); //at the begining iterator placed before first element

            while (rs.next()) {
                tmp.put(rs.getString(1), rs.getInt(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ResultsDao.class.getName()).log(Level.ERROR, null, ex);
        } finally {
            pool.releaseConnection(conn);
        }

        return tmp;
    }

    public void addResultToDb(String login, String topic, int res) {
        Connection conn = pool.getConnection();

        try {
            PreparedStatement st = conn.prepareStatement(addResult);
            st.setString(1, login);
            st.setString(2, topic);
            st.setFloat(3, res);

            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ResultsDao.class.getName()).log(Level.ERROR, null, ex);
        } finally {
            pool.releaseConnection(conn);
        }
    }
    //main method for testing
    public static void main(String[] args) {
        ResultsDao test = new ResultsDao();

        test.addResultToDb("james", "Алгебра", 3);

        Map<String, Integer> map = new HashMap<>();
        map = test.getStudentResultByLogin("james");

        for (Map.Entry<String, Integer> i: map.entrySet()) {
            System.out.println(i.getKey()+":"+i.getValue());
        }
    }
}
