package dao.data;

import dao.models.Topic;
import dao.pool.MyDBPool;

import java.io.FileInputStream;
import java.io.IOException;
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
    private String propFileName = "src/myconfig.properties";

    public TopicDao() {
        topicsList = new ArrayList<>();
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream(propFileName));
        } catch (IOException e) {
            System.out.println("config file not found");
        }

        // get the property value and use it for dbpool
        String db = prop.getProperty("db");
        String dbuser = prop.getProperty("dbuser");
        String dbpass = prop.getProperty("dbpass");

        pool = new MyDBPool(db, dbuser, dbpass);
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
            e.printStackTrace();}

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

}
