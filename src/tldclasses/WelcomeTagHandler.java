package tldclasses;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * <h1>WelcomeTagHandler</h1>
 * Class is responsible new jstl tag "hello" with parameter but without body
 * Created by alex on 6/24/15.
 */
public class WelcomeTagHandler extends TagSupport {

    private String uname="";

    // Метод, вызываемый, чтобы начать обработку тега
    public int doStartTag() throws JspException {
        try{
                // получение объекта JspWriter для вывода содержимого
                JspWriter out = pageContext.getOut();
                String l = (String)pageContext.getSession().getAttribute("locale");
                if (l.equals("ru")) {
                    out.print("Уважаемый "+uname);
                } else {
                    if (l.equals("en")) {
                        out.print("Hello Dear "+uname);
                    }
                }
            }
        catch( IOException ioException ) {
                throw new JspException( ioException.getMessage() );
        }
        return SKIP_BODY; // игнорировать тело тега
    }

    public void setUname( String uname ){
        this.uname = uname;
    }
}
