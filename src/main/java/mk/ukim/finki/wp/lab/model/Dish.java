package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Chef chef;

    public Dish() {}

    public Dish(String dishId, String name, String cuisine, int preparationTime) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }

    public Dish(String dishId, String name, String cuisine, int preparationTime, Chef chef) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
        this.chef = chef;
    }

    public Long getId() {return id; }
    public void setId(Long id) { this.id = id; }

    public String getDishId() { return dishId; }
    public void setDishId(String dishId) { this.dishId = dishId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCuisine() { return cuisine; }
    public void setCuisine(String cuisine) { this.cuisine = cuisine; }

    public int getPreparationTime() { return preparationTime; }
    public void setPreparationTime(int preparationTime) { this.preparationTime = preparationTime; }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }
}
