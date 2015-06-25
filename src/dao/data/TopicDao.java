package dao.data;

import dao.models.Topic;
import dao.pool.MyDBPool;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * Created by alex on 6/15/15.
 */
public class TopicDao {
    private List<Topic> topicsList;
    private MyDBPool pool;
    private String propFileName = "resources/myconfig.properties";
    private static final String byName = "SELECT idtopics FROM topics WHERE topicName=?";

    public TopicDao() {
        topicsList = new ArrayList<>();
        Properties prop = new Properties();

        try {
            InputStream in = this.getClass()
                    .getClassLoader()
                    .getResourceAsStream(propFileName);
            prop.load(in);
        } catch (IOException e) {
            System.out.println("config file not found");
        }

        // get the property value and use it for dbpool
        String db = prop.getProperty("db");
        String dbuser = prop.getProperty("dbuser");
        String dbpass = prop.getProperty("dbpass");

        pool = new MyDBPool(db, dbuser, dbpass);
    }

    public int getIdbyName(String tname) {
        Connection conn = pool.getConnection();
        int tmp=0;

        try {
            PreparedStatement st = conn.prepareStatement(byName);
            st.setString(1, tname);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                tmp = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TopicDao.class.getName()).log(Level.INFO, null, ex);
        }
        return tmp;
    }

    public void insertTopicsToDB(Topic... topics) throws SQLException{
        Connection conn = pool.getConnection();
        try {
            PreparedStatement statement=
                    conn.prepareStatement(
                            "INSERT INTO topics VALUES (?,?,?,?)");
            for (Topic i: topics) {
                statement.setInt(1, i.getIdtopics());
                statement.setString(2, i.getTopicName());
                statement.setString(3, i.getTopicDesc());
                statement.setString(4, i.getTopicLanguage());
                statement.addBatch();
            }
            int [] updateCounts = statement.executeBatch();

        } catch (BatchUpdateException e) {
            Logger.getLogger(TopicDao.class.getName()).log(Level.INFO, null, e);}

        pool.releaseConnection(conn);

    }

    public void removeTopicFromDB(Topic topic) throws SQLException{
        Connection conn = pool.getConnection();

        PreparedStatement statement=
                conn.prepareStatement(
                        "DELETE FROM topics WHERE idtopics=? AND topicName =?");

        statement.setInt(1, topic.getIdtopics());
        statement.setString(2, topic.getTopicName());
        statement.executeUpdate();

        pool.releaseConnection(conn);

    }

    public List<Topic> getAllTopicsFromDB() throws SQLException{
        List<Topic> tmp = new ArrayList<>();

        Connection conn = pool.getConnection();

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM topics");
//        ResultSetMetaData rsmd = rs.getMetaData();
//
//        int columns = rsmd.getColumnCount();

        while (rs.next()) {

            Topic currTopic = new Topic();
            currTopic.setIdtopics(rs.getInt(1));
            currTopic.setTopicName(rs.getString(2));
            currTopic.setTopicDesc(rs.getString(3));
            currTopic.setTopicLanguage(rs.getString(4));

            tmp.add(currTopic);
        }

        pool.releaseConnection(conn);
        return tmp;
    }

    public static void main(String[] args) {
        TopicDao test = new TopicDao();
        System.out.println(test.getIdbyName(new String("Алгебра")));
        System.out.println(test.getIdbyName("Math"));

        URL resource = Thread.currentThread().getContextClassLoader()
                .getResource("log4j.properties");
        System.out.println("resource = " + resource);

        final Logger logger = Logger.getLogger(TopicDao.class.getName());

        logger.info("Hello world");

        try {
            test.getAllTopicsFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
