package registration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by alex on 6/17/15.
 */
public interface Command {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException;
    public String toString();
}
