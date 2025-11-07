package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init()
    {
        chefs.add(new Chef(1L, "Branko", "Brankovski", "Dobar kuvar", new ArrayList<>()));
        chefs.add(new Chef(2L, "Kiro", "Kirovski", "Soliden kuvar", new ArrayList<>()));
        chefs.add(new Chef(3L, "Stanko", "Stankovski", "Predobar kuvar", new ArrayList<>()));
        chefs.add(new Chef(4L, "Panko", "Pankovski", "Sreden kuvar", new ArrayList<>()));
        chefs.add(new Chef(5L, "Danko", "Dankovski", "Losh kuvar", new ArrayList<>()));

        dishes.add(new Dish("1", "Grav", "Makedonska", 45));
        dishes.add(new Dish("2", "Supa", "Makedonska", 15));
        dishes.add(new Dish("3", "Zelnik", "Makedonska", 60));
        dishes.add(new Dish("4", "Pizza", "Italijanska", 20));
        dishes.add(new Dish("5", "Giro", "Grcka", 5));

    }
}
