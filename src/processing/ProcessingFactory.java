package processing;

import registration.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 6/18/15.
 */
public class ProcessingFactory {
    static Map<String, ProcessingCommand> commands = new HashMap<>();
    static {
        commands.put("newquestion", new CreateQCommand());
    //    commands.put("modifypersonal", new ModifyPersonalCommand());
        commands.put("exit", new ExitCommand());
        commands.put("exam", new ExamCommand());
    }

    public static ProcessingCommand getCommand(HttpServletRequest request) {
        String value = request.getParameter("send");

        return commands.get(value);

    }
}
