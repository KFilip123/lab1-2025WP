package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public DataHolder(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }


    @PostConstruct
    public void init()
    {
        if(chefRepository.findAll().isEmpty()) {
            chefRepository.save(new Chef("Branko", "Brankovski", "Dobar kuvar", new ArrayList<>()));
            chefRepository.save(new Chef("Kiro", "Kirovski", "Soliden kuvar", new ArrayList<>()));
            chefRepository.save(new Chef("Stanko", "Stankovski", "Predobar kuvar", new ArrayList<>()));
            chefRepository.save(new Chef("Panko", "Pankovski", "Sreden kuvar", new ArrayList<>()));
            chefRepository.save(new Chef("Danko", "Dankovski", "Losh kuvar", new ArrayList<>()));
        }

        if(dishRepository.findAll().isEmpty()) {
            dishRepository.save(new Dish("1", "Grav", "Makedonska", 45));
            dishRepository.save(new Dish("2", "Supa", "Makedonska", 15));
            dishRepository.save(new Dish("3", "Zelnik", "Makedonska", 60));
            dishRepository.save(new Dish("4", "Pizza", "Italijanska", 20));
            dishRepository.save(new Dish("5", "Giro", "Grcka", 5));
        }

    }
}
