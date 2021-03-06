package processing;

import dao.data.QuestionsDao;
import dao.data.ResultsDao;
import dao.data.TopicDao;
import dao.models.Question;
import dao.models.Topic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * <h1>ExamCommand</h1>
 * ExamCommand implements logic for student quiz. It also add to logfile information about
 * quiz beginning and finishing
 * Created by alex on 6/19/15.
 */
public class ExamCommand implements ProcessingCommand {

    /**
     * Method for quiz initialization. Reads topic name (from request parameter "test"),
     * initializes quiz result in session as 0, and put to session parameter "examQuestions"
     * list of appropriate topic questions from DB
     * Created by alex on 6/19/15.
     */
    public void testInitialization(HttpServletRequest request) throws IOException, SQLException{

        request.getSession().setAttribute("isTestRun", new String(request.getParameter("test").getBytes("iso-8859-1"), "UTF-8"));
        request.getSession().setAttribute("quizResult", 0);

        QuestionsDao qd = new QuestionsDao();

        List<Question> qlist = qd.getQuestionsByTopic(new String(request.getParameter("test").getBytes("iso-8859-1"), "UTF-8"));
        request.getSession().setAttribute("examQuestions", qlist);

        //log begining
        Logger.getLogger(QuestionsDao.class.getName()).info("test initialization: "+request.getSession().getAttribute("login"));
    }

    /**
     * Method checks whether user answer on question is correct or not and increases quizResult session attribute
     * in positive case
     * Created by alex on 6/19/15.
     */
    public void processPreviousAnswer(HttpServletRequest request) throws IOException {
        List<Question> temp = (List<Question>)request.getSession().getAttribute("examQuestions");
        Question previousQ = temp.remove(0);

       if (previousQ.getqCorrectAnswer().equals(request.getParameter("answer"))) {
            int res = (int)request.getSession().getAttribute("quizResult");
            request.getSession().setAttribute("quizResult", ++res);

        }

        request.getSession().setAttribute("examQuestions", temp);

    }

    /**
     * Main processing method. It prepares current question shuffling answers
     * and processes results if quiz has finished
     * Created by alex on 6/19/15.
     */
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        // Processing results of question
        if (request.getSession().getAttribute("isTestRun") != null) {
            processPreviousAnswer(request);
        }
        //----end of processing results-----------------

        // Quiz initialization module
        if (request.getSession().getAttribute("isTestRun") == null) {
            testInitialization(request);
        }
        //-------------

        List<Question> curList = (List<Question>)request.getSession().getAttribute("examQuestions");

        // ----- module for processing quiz results
        if (curList.size() == 0) {

            //log finishing
            Logger.getLogger(QuestionsDao.class.getName()).info("test finished: "+request.getSession().getAttribute("login"));

            int res = (int)request.getSession().getAttribute("quizResult");
            request.setAttribute("result", "Ваш результат за тест "+request.getSession().getAttribute("isTestRun")+": "+res);

            ResultsDao resultd = new ResultsDao();
            resultd.addResultToDb((String)request.getSession().getAttribute("login"), (String)request.getSession().getAttribute("isTestRun"), res);

            request.getSession().setAttribute("isTestRun", null);

            ResultsDao resultsDao = new ResultsDao();
            TopicDao topicDao = new TopicDao();

            Map<String, Integer> results = resultsDao.getStudentResultByLogin((String)request.getSession().getAttribute("login"));
            request.setAttribute("results", results);

            List<Topic> topics = topicDao.getAllTopicsFromDB();
            request.setAttribute("topics", topics);

            RequestDispatcher rd = request.getRequestDispatcher("/restricted/studlogin.jsp");
            rd.forward(request, response);
        }

        //System.out.println(curList.size());
        List<String> answers = new ArrayList<>();
        String text;

        text = curList.get(0).getqText();
        answers.add(curList.get(0).getqCorrectAnswer());
        if (curList.get(0).getqAnswer2() != null)
            answers.add(curList.get(0).getqAnswer2());
        if (curList.get(0).getqAnswer3() != null)
            answers.add(curList.get(0).getqAnswer3());
        if (curList.get(0).getqAnswer4() != null)
            answers.add(curList.get(0).getqAnswer4());
        if (curList.get(0).getqAnswer5() != null)
            answers.add(curList.get(0).getqAnswer5());

        Collections.shuffle(answers);

        request.setAttribute("curQText", text);
        request.setAttribute("answers", answers);

        RequestDispatcher rd = request.getRequestDispatcher("/restricted/exam.jsp");

        rd.forward(request, response);
    }
    public String toString() {
        return "exam";
    };
}
