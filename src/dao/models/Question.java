package dao.models;

/**
 * Created by alex on 6/11/15.
 */

public class Question {
    private int idQ;
    private String qText;
    private int qTopic;
    private String qCorrectAnswer;
    private String qAnswer2;
    private String qAnswer3;
    private String qAnswer4;
    private String qAnswer5;
    private int qTutorId;
    private String qLanguage;


    public int getIdQ() {
        return idQ;
    }

    public void setIdQ(int idQ) {
        this.idQ = idQ;
    }

    public String getqText() {
        return qText;
    }

    public void setqText(String qText) {
        this.qText = qText;
    }

    public int getqTopic() {
        return qTopic;
    }

    public void setqTopic(int qTopic) {
        this.qTopic = qTopic;
    }

    public String getqCorrectAnswer() {
        return qCorrectAnswer;
    }

    public void setqCorrectAnswer(String qCorrectAnswer) {
        this.qCorrectAnswer = qCorrectAnswer;
    }

    public String getqAnswer2() {
        return qAnswer2;
    }

    public void setqAnswer2(String qAnswer2) {
        this.qAnswer2 = qAnswer2;
    }

    public String getqAnswer3() {
        return qAnswer3;
    }

    public void setqAnswer3(String qAnswer3) {
        this.qAnswer3 = qAnswer3;
    }

    public String getqAnswer4() {
        return qAnswer4;
    }

    public void setqAnswer4(String qAnswer4) {
        this.qAnswer4 = qAnswer4;
    }

    public String getqAnswer5() {
        return qAnswer5;
    }

    public void setqAnswer5(String qAnswer5) {
        this.qAnswer5 = qAnswer5;
    }

    public int getqTutorId() {
        return qTutorId;
    }

    public void setqTutorId(int qTutorId) {
        this.qTutorId = qTutorId;
    }

    public String getqLanguage() {
        return qLanguage;
    }

    public void setqLanguage(String qLanguage) {
        this.qLanguage = qLanguage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (idQ != question.idQ) return false;
        if (qText != null ? !qText.equals(question.qText) : question.qText != null) return false;
        if (qCorrectAnswer != null ? !qCorrectAnswer.equals(question.qCorrectAnswer) : question.qCorrectAnswer != null)
            return false;
        if (qAnswer2 != null ? !qAnswer2.equals(question.qAnswer2) : question.qAnswer2 != null) return false;
        if (qAnswer3 != null ? !qAnswer3.equals(question.qAnswer3) : question.qAnswer3 != null) return false;
        if (qAnswer4 != null ? !qAnswer4.equals(question.qAnswer4) : question.qAnswer4 != null) return false;
        if (qAnswer5 != null ? !qAnswer5.equals(question.qAnswer5) : question.qAnswer5 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idQ;
        result = 31 * result + (qText != null ? qText.hashCode() : 0);
        result = 31 * result + (qCorrectAnswer != null ? qCorrectAnswer.hashCode() : 0);
        result = 31 * result + (qAnswer2 != null ? qAnswer2.hashCode() : 0);
        result = 31 * result + (qAnswer3 != null ? qAnswer3.hashCode() : 0);
        result = 31 * result + (qAnswer4 != null ? qAnswer4.hashCode() : 0);
        result = 31 * result + (qAnswer5 != null ? qAnswer5.hashCode() : 0);
        return result;
    }
}
