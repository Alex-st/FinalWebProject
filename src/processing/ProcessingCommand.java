package processing;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by alex on 6/18/15.
 */
public interface ProcessingCommand {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException;
    public String toString();
}
