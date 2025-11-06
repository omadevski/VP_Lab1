package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {

    @Autowired private ChefService chefService;

    @Override public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String chefIdStr = req.getParameter("chefId");
        String dishId = req.getParameter("dishId");
        Long chefId = chefIdStr != null ? Long.parseLong(chefIdStr) : null;
        Chef chef = chefId != null ? chefService.addDishToChef(chefId, dishId) : null;

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><meta charset='utf-8'><title>Chef Details</title>"
                + "<style>body{width:800px;margin:auto;font-family:Arial,sans-serif;}h1{color:#333;}ul{list-style-type:none;padding:0;}li{padding:5px;margin:5px 0;background-color:#f5f5f5;border-radius:3px;}</style></head><body>");
        if (chef == null) {
            out.println("<header><h1>Chef not found</h1></header></body></html>");
            out.flush();
            return;
        }
        out.printf("<header><h1>Chef: %s</h1></header>", chef.getFullName());
        out.printf("<section><h2>Bio: %s</h2>", chef.getBio());
        out.println("<h2>Dishes prepared by this chef:</h2><ul>");
        for (Dish d : chef.getDishes()) {
            out.printf("<li>%s (%s, %d min)</li>", d.getName(), d.getCuisine(), d.getPreparationTime());
        }
        out.println("</ul></section><p><a href='/listChefs'>Back</a></p></body></html>");
        out.flush();
    }
}
