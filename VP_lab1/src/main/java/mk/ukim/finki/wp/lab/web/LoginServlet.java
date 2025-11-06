package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mk.ukim.finki.wp.lab.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Autowired private AuthService authService;

    @Override public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        render(resp, null);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User u = authService.login(username, password);
            HttpSession session = req.getSession(true);
            session.setAttribute("user", u);
            resp.sendRedirect("/listChefs");
        } catch (InvalidUserCredentialsException e) {
            render(resp, "Invalid username or password");
        }
    }

    private void render(HttpServletResponse resp, String error) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><meta charset='utf-8'><title>Login</title></head><body>");
        out.println("<h1>Login</h1>");
        if (error != null) out.printf("<p style='color:red'>%s</p>", error);
        out.println("<form method='POST' action='/login'>");
        out.println("<label>Username:</label><br/><input type='text' name='username' required/><br/>");
        out.println("<label>Password:</label><br/><input type='password' name='password' required/><br/><br/>");
        out.println("<input type='submit' value='Submit'/>");
        out.println("</form></body></html>");
        out.flush();
    }
}
