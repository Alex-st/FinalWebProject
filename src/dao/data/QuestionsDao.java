package dao.data;

import dao.models.Question;
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
public class QuestionsDao {
    private List<Question> tutorsList;
    private MyDBPool pool;
    private String propFileName = "src/myconfig.properties";

    public QuestionsDao() {

        tutorsList = new ArrayList<>();
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

    public void insertQuestionsToDB(Question... questions) throws SQLException {

        Connection conn = pool.getConnection();
        try {
            PreparedStatement statement=
                    conn.prepareStatement(
                            "INSERT INTO questions VALUES (?,?,?,?,?,?,?,?,?,?)");
            for (Question i: questions) {
                statement.setInt(1, i.getIdQ());
                statement.setString(2, i.getqText());
                statement.setInt(3, i.getqTopic());
                statement.setString(4, i.getqCorrectAnswer());
                statement.setString(5, i.getqAnswer2());
                statement.setString(6, i.getqAnswer3());
                statement.setString(7, i.getqAnswer4());
                statement.setString(8, i.getqAnswer5());
                statement.setInt(9, i.getqTutorId());
                statement.setString(10, i.getqLanguage());
                statement.addBatch();
            }
            int [] updateCounts = statement.executeBatch();

        } catch (BatchUpdateException e) {
            e.printStackTrace();}

        pool.releaseConnection(conn);

    }

    public void removeQuestionFromDB(Question question) throws SQLException{
        Connection conn = pool.getConnection();

        PreparedStatement statement=
                conn.prepareStatement(
                        "DELETE FROM questions WHERE idQ=?");

        statement.setInt(1, question.getIdQ());
        statement.executeUpdate();

        pool.releaseConnection(conn);

    }

    public List<Question> getAllQuestionsFromDB() throws SQLException{
        List<Question> tmp = new ArrayList<>();

        Connection conn = pool.getConnection();

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM questions");
//        ResultSetMetaData rsmd = rs.getMetaData();
//
//        int columns = rsmd.getColumnCount();

        while (rs.next()) {
            Question currQuestion = new Question();
            currQuestion.setIdQ(rs.getInt(1));
            currQuestion.setqText(rs.getString(2));
            currQuestion.setqTopic(rs.getInt(3));
            currQuestion.setqCorrectAnswer(rs.getString(4));
            currQuestion.setqAnswer2(rs.getString(5));
            currQuestion.setqAnswer3(rs.getString(6));
            currQuestion.setqAnswer4(rs.getString(7));
            currQuestion.setqAnswer5(rs.getString(8));
            currQuestion.setqTutorId(rs.getInt(9));
            currQuestion.setqLanguage(rs.getString(10));

            tmp.add(currQuestion);
        }

        pool.releaseConnection(conn);
        return tmp;
    }

}
