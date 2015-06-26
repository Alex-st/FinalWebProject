package dao.models;

import java.util.HashMap;
import java.util.Map;

/**
 * /**
 * * <h1>Result</h1>
 * Result class is responsible for single entity in result table
 * Created by alex on 6/18/15.
 */
public class Result {
    private Student student;
    private Map<Topic, Integer> results;

    public Result() {
        results = new HashMap<Topic, Integer>();
    }

    public Result(Student student) {
        this();
        this.student = student;
    }

    public void addResult(Topic topic, int res) {
        results.put(topic, res);
    }

    public Student getStudent() {
        return student;
    }

    public Map<String, Integer> getStringResult() {
        Map<String, Integer> tmp = new HashMap<>();

        for(Map.Entry<Topic, Integer> i:results.entrySet()) {
            tmp.put(i.getKey().getTopicName(), i.getValue());
        }
        return tmp;
    }
}
