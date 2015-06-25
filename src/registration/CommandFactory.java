package registration;



import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 6/15/15.
 */
public class CommandFactory {
    static Map<String, Command> commands = new HashMap<>();
    static {
        commands.put("signAsStudent", new StudAuthCommand());
        commands.put("signAsTutor", new TutorAuthCommand());
        commands.put("register", new RegistrationCommand());
//        commands.put("StudentForm", new SignInAsStudentCommand());
//        commands.put("TutorForm", new SignInAsTutorCommand());
//        commands.put("registerForm", new RegistrFormCommand());

    }

    public static Command getCommand(HttpServletRequest request) {
        String value = request.getParameter("send");

        return commands.get(value);

    }
}
