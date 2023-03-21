package ETU1873.framework.servlet;

import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;


public class FrontServlet extends HttpServlet {
    protected void ProcessRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        out.println(request.getRequestURL());

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            ProcessRequest(request,response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            ProcessRequest(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
