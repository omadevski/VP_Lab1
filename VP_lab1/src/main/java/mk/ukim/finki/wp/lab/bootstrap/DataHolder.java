package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.User;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();
    public static List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        dishes.add(new Dish("1", "Pasta Carbonara", "Italian", 25));
        dishes.add(new Dish("2", "Beef Wellington", "British", 45));
        dishes.add(new Dish("3", "Chicken Tikka Masala", "Indian", 35));
        dishes.add(new Dish("4", "Sushi Platter", "Japanese", 50));
        dishes.add(new Dish("5", "Moussaka", "Greek", 40));

        chefs.add(new Chef(1L, "Gordon", "Ramsay", "World-renowned chef with multiple Michelin stars."));
        chefs.add(new Chef(2L, "Massimo", "Bottura", "Italian chef famous for modern cuisine."));
        chefs.add(new Chef(3L, "Heston", "Blumenthal", "British chef known for molecular gastronomy."));
        chefs.add(new Chef(4L, "Vesna", "Kukovska", "Local chef passionate about seasonal ingredients."));
        chefs.add(new Chef(5L, "Toma", "Nikolov", "Grill master and sauce whisperer."));

        users.add(new User("Student","One","student","student"));
        users.add(new User("Test","User","test","test"));
    }
}
