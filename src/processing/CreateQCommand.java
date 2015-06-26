package processing;

import dao.data.QuestionsDao;
import dao.data.TopicDao;
import dao.data.TutorsDao;
import dao.models.Question;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * <h1>CreateQCommand</h1>
 * CreateQCommand implements logic for creating new question and adding it to DB
 * Created by alex on 6/18/15.
 */
public class CreateQCommand implements ProcessingCommand {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        request.setCharacterEncoding("UTF-8");

        String qtext = new String (request.getParameter("qtext").getBytes ("iso-8859-1"), "UTF-8");
        String correctanswer = new String (request.getParameter("correctanswer").getBytes ("iso-8859-1"), "UTF-8");
        String q2 = new String (request.getParameter("q2").getBytes ("iso-8859-1"), "UTF-8");
        String q3 = new String (request.getParameter("q3").getBytes ("iso-8859-1"), "UTF-8");
        String q4 = new String (request.getParameter("q4").getBytes ("iso-8859-1"), "UTF-8");
        String q5 = new String (request.getParameter("q5").getBytes ("iso-8859-1"), "UTF-8");
        String qtopicString = new String (request.getParameter("qtopic").getBytes ("iso-8859-1"), "UTF-8");
//        String qtext = request.getParameter("qtext");
//        System.out.println(qtext);

        TopicDao tp = new TopicDao();
        Integer qtopic = tp.getIdbyName(qtopicString);

        TutorsDao td = new TutorsDao();
        Integer tid = td.getIdByLogin((String)request.getSession().getAttribute("login"));

        QuestionsDao qd = new QuestionsDao();

        Question curQuestion = new Question();
        curQuestion.setqText(qtext);
        curQuestion.setqCorrectAnswer(correctanswer);
        curQuestion.setqAnswer2(q2);
        curQuestion.setqAnswer3(q3);
        curQuestion.setqAnswer4(q4);
        curQuestion.setqAnswer5(q5);

//        curQuestion.setqText(request.getParameter("qtext"));
//        curQuestion.setqCorrectAnswer(request.getParameter("correctanswer"));
//        curQuestion.setqAnswer2(request.getParameter("q2"));
//        curQuestion.setqAnswer3(request.getParameter("q3"));
//        curQuestion.setqAnswer4(request.getParameter("q4"));
//        curQuestion.setqAnswer5(request.getParameter("q5"));

        curQuestion.setqTopic(qtopic);
        curQuestion.setqTutorId(tid);
        curQuestion.setqLanguage((String)request.getSession().getAttribute("locale"));

        qd.insertQuestionsToDB(curQuestion);

        request.setAttribute("result", "<center>Question was successfully added</center>");

        QuestionsDao questionsDao = new QuestionsDao();
        Map<String, String> questions = questionsDao.getAllTutorQuestions((String)request.getSession().getAttribute("login"));
        request.setAttribute("questions", questions);

        RequestDispatcher rd = request.getRequestDispatcher("/restricted/tutorlogin.jsp");

        rd.forward(request, response);
    }
    public String toString() {
        return "create new question";
    }
}
