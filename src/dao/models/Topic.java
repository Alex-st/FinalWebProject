package dao.models;


/**
 * Created by alex on 6/11/15.
 */

public class Topic {
    private int idtopics;
    private String topicName;
    private String topicDesc;
    private String topicLanguage;

    public Topic() {

    }

    public Topic(int id, String name, String desc, String lang) {
        idtopics = id;
        topicName = name;
        topicDesc = desc;
        topicLanguage = lang;
    }

    public int getIdtopics() {
        return idtopics;
    }

    public void setIdtopics(int idtopics) {
        this.idtopics = idtopics;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public String getTopicLanguage() {
        return topicLanguage;
    }

    public void setTopicLanguage(String topicLanguage) {
        this.topicLanguage = topicLanguage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic = (Topic) o;

        if (idtopics != topic.idtopics) return false;
        if (topicName != null ? !topicName.equals(topic.topicName) : topic.topicName != null) return false;
        if (topicDesc != null ? !topicDesc.equals(topic.topicDesc) : topic.topicDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idtopics;
        result = 31 * result + (topicName != null ? topicName.hashCode() : 0);
        result = 31 * result + (topicDesc != null ? topicDesc.hashCode() : 0);
        return result;
    }
}
