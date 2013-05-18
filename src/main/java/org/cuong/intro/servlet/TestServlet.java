package org.cuong.intro.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cuong.intro.controller.TestBusinessFacade;

@WebServlet(urlPatterns = "/TestServlet")
public class TestServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -6178400368679711823L;

    @EJB
    private TestBusinessFacade testFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MemberRegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + testFacade.getHello() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.flush();
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

}
