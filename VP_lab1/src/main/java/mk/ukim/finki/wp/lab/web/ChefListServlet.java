package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/listChefs")
public class ChefListServlet extends HttpServlet {

    @Autowired
    private ChefService chefService;

    @Override public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Chef> chefs = chefService.listChefs();
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><meta charset='utf-8'><title>Restaurant Chefs</title>"
                + "<style>body{width:800px;margin:auto;font-family:Arial,sans-serif;}h1{color:#333;}</style></head><body>");
        out.println("<header><h1>Welcome to Our Restaurant</h1></header>");
        out.println("<main><h2>Choose a chef:</h2>");
        out.println("<form action='/dish' method='POST'>");
        for (Chef c : chefs) {
            out.printf("<label><input type='radio' name='chefId' value='%d' required> Name: %s %s, Bio: %s</label><br/>",
                    c.getId(), c.getFirstName(), c.getLastName(), c.getBio());
        }
        out.println("<br/><input type='submit' value='Submit'></form>");
        out.println("<p><a href='/logout'>Logout</a></p>");
        out.println("</main></body></html>");
        out.flush();
    }
}
