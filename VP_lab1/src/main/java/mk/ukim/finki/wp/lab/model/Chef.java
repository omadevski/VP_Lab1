package mk.ukim.finki.wp.lab.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Chef {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private List<Dish> dishes = new ArrayList<>();

    public Chef() {}

    public Chef(Long id, String firstName, String lastName, String bio) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public List<Dish> getDishes() { return dishes; }
    public void setDishes(List<Dish> dishes) { this.dishes = dishes; }
    public String getFullName() { return firstName + " " + lastName; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chef chef = (Chef) o;
        return Objects.equals(id, chef.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
