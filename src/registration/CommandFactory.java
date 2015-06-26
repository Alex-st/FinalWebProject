package registration;



import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>CommandFactory</h1>
 * Factory for calling proper command handler due to received parameter
 * in registration/authentication forms
 * Created by alex on 6/15/15.
 */
public class CommandFactory {
    static Map<String, Command> commands = new HashMap<>();
    static {
        commands.put("signAsStudent", new StudAuthCommand());
        commands.put("signAsTutor", new TutorAuthCommand());
        commands.put("register", new RegistrationCommand());
    }

    public static Command getCommand(HttpServletRequest request) {
        String value = request.getParameter("send");

        return commands.get(value);

    }
}
