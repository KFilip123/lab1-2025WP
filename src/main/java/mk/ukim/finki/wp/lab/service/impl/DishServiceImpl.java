package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public DishServiceImpl(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return dishRepository.findByDishId(dishId);
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id);
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime, Long chefId) {

        Dish dish = dishRepository.save(new Dish(dishId, name, cuisine, preparationTime));
        if (chefId != null) {
            Chef chef = chefRepository.findById(chefId).orElse(null);
            dish.setChef(chef);
        }
        return dish;
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime, Long chefId) {
        Dish updDish = dishRepository.findById(id).orElseThrow(RuntimeException::new);
        updDish.setDishId(dishId);
        updDish.setName(name);
        updDish.setCuisine(cuisine);
        updDish.setPreparationTime(preparationTime);
        if (chefId != null) {
            Chef chef = chefRepository.findById(chefId).orElse(null);
            updDish.setChef(chef);
        }
        return dishRepository.save(updDish);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public List<Dish> findAllByChefId(Long chefId) {
        return dishRepository.findAllByChef_Id(chefId);
    }
}
