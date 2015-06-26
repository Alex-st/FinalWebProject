package dao.data;

import dao.models.Question;
import dao.pool.MyDBPool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * <h1>QuestionsDao</h1>
 * QuestionsDao class is responsible for Data Access Object in DAO pattern
 * for Question table. Logic for processing data from table "questions" is
 * implemented here
 *
 */
public class QuestionsDao {
    private List<Question> tutorsList;
    private MyDBPool pool;
    private String propFileName = "resources/myconfig.properties";
    private int numberOfQuestionsInTest;
    private static final String findByLogin = "SELECT questions.qText, topics.topicName" +
            " FROM questions INNER JOIN topics ON topics.idtopics = questions.qTopicId WHERE qTutorId =" +
            "(SELECT idtutors FROM tutors WHERE tLogin = ?)";

    public QuestionsDao() {

        tutorsList = new ArrayList<>();
        Properties prop = new Properties();

        try {
            InputStream in = this.getClass()
                    .getClassLoader()
                    .getResourceAsStream(propFileName);
            prop.load(in);
        } catch (IOException ex) {
            System.out.println("config file not found");
            Logger.getLogger(QuestionsDao.class.getName()).log(Level.ERROR, null, ex);
        }

        // get the property value and use it for dbpool
        String db = prop.getProperty("db");
        String dbuser = prop.getProperty("dbuser");
        String dbpass = prop.getProperty("dbpass");

        pool = new MyDBPool(db, dbuser, dbpass);

        numberOfQuestionsInTest = Integer.valueOf(prop.getProperty("numberOfQuestionsInTest"));
    }

    public void insertQuestionsToDB(Question... questions) throws SQLException {

        Connection conn = pool.getConnection();
        try {
            PreparedStatement statement=
                    conn.prepareStatement(
                            "INSERT INTO questions (qText, qTopicId, qCorrectAnswer, qAnswer2, qAnswer3, qAnswer4, qAnswer5, qTutorId, qLanguage) " +
                                    "VALUES (?,?,?,?,?,?,?,?,?)");
            for (Question i: questions) {
               // statement.setInt(1, i.getIdQ());
                statement.setString(1, i.getqText());
                statement.setInt(2, i.getqTopic());
                statement.setString(3, i.getqCorrectAnswer());
                statement.setString(4, i.getqAnswer2());
                statement.setString(5, i.getqAnswer3());
                statement.setString(6, i.getqAnswer4());
                statement.setString(7, i.getqAnswer5());
                statement.setInt(8, i.getqTutorId());

                statement.setString(9, i.getqLanguage());
            //    statement.setString(9, i.getqLanguage());
                statement.addBatch();
            }
            int [] updateCounts = statement.executeBatch();

        } catch (BatchUpdateException ex) {

            Logger lg = Logger.getLogger(QuestionsDao.class.getName());

            Logger.getLogger(QuestionsDao.class.getName()).log(Level.ERROR, null, ex);
        }

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

    public Map<String, String> getAllTutorQuestions(String login) {
        Map<String, String> questions = new HashMap<>();
        Connection conn = pool.getConnection();

        try {
            PreparedStatement st = conn.prepareStatement(findByLogin);
            st.setString(1, login);

            ResultSet rs = st.executeQuery();
            // rs.next(); //at the begining iterator placed before first element

            while (rs.next()) {

                questions.put(rs.getString(1), rs.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuestionsDao.class.getName()).log(Level.ERROR, null, ex);
        } finally {
            pool.releaseConnection(conn);
        }

        return questions;
    }

    // returns list with size equals to number of questions in test (such parameter is assigned in config file)
    public List<Question> getQuestionsByTopic(String topic) throws SQLException {
        List<Question> tmp = new ArrayList<>();

        Connection conn = pool.getConnection();

        PreparedStatement st = conn.prepareStatement("SELECT * FROM questions WHERE qTopicId =" +
                " (SELECT idtopics FROM topics WHERE topicName=?)");
        st.setString(1, topic);

        ResultSet rs = st.executeQuery();

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

        while (tmp.size() > numberOfQuestionsInTest) {
            int i = (int)(Math.random()*tmp.size());
            tmp.remove(i);
        }

        pool.releaseConnection(conn);
        return tmp;
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

    //main method for testing
    public static void main(String[] args) {
        QuestionsDao qd = new QuestionsDao();
        Question q = new Question();

        q.setqText("Тестовый вопрос");
        q.setqCorrectAnswer("Бывает как обычно");
        q.setqAnswer2("by the way");
        q.setqAnswer3("нет ничего");
        q.setqAnswer4("Show must go on");
        q.setqAnswer5(null);
        q.setqLanguage("ru");
        q.setqTutorId(1);
        q.setqTopic(2);

        try {
            qd.insertQuestionsToDB(q);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
