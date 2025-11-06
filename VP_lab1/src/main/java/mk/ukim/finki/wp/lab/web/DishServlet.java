package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/dish")
public class DishServlet extends HttpServlet {

    @Autowired private DishService dishService;
    @Autowired private ChefService chefService;

    @Override public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String chefIdStr = req.getParameter("chefId");
        Long chefId = chefIdStr != null ? Long.parseLong(chefIdStr) : null;
        Chef chef = chefId != null ? chefService.findById(chefId) : null;
        List<Dish> dishes = dishService.listDishes();

        Chef popular = chefService.mostPopularChef();
        int popularCount = popular.getDishes() == null ? 0 : popular.getDishes().size();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><meta charset='utf-8'><title>Add Dish to Chef</title>"
                + "<style>body{width:800px;margin:auto;font-family:Arial,sans-serif;}table{width:100%;margin-top:20px;border-collapse:collapse;}table,td,th{border:1px solid black;padding:10px;}th{background-color:#4CAF50;color:white;}section{float:left;margin:0 1.5%;width:63%;}aside{float:right;margin:0 1.5%;width:30%;}</style></head><body>");
        out.println("<header><h1>Select the Dish to add to the Chef</h1></header>");
        out.println("<section><h2>Select dish:</h2>");
        out.println("<form action='/chefDetails' method='POST'>");
        for (Dish d : dishes) {
            out.printf("<label><input type='radio' name='dishId' value='%s' required> %s</label><br/>",
                    d.getDishId(), d.getName());
        }
        if (chefId != null) out.printf("<input type='hidden' name='chefId' value='%d'/>", chefId);
        out.println("<br/><input type='submit' value='Add dish'></form></section>");
        out.println("<aside><table>");
        out.println("<tr><td><b>Chef ID</b></td><td>" + (chef != null ? chef.getId() : "") + "</td></tr>");
        out.println("<tr><td><b>Chef Name</b></td><td>" + (chef != null ? chef.getFullName() : "") + "</td></tr>");

        out.println("<tr><td colspan='2'><br/></td></tr>");
        out.println("<tr><td colspan='2'>");
        out.println("<div style='border:1px solid #ccc;padding:10px;border-radius:4px;'>");
        out.println("<b>Most popular chef</b><br/>");
        out.println("Name: " + popular.getFirstName() + " " + popular.getLastName() + "<br/>");
        out.println("Dishes prepared: " + popularCount);
        out.println("</div>");
        out.println("</td></tr>");

        out.println("</table></aside></body></html>");
        out.flush();
    }
}
