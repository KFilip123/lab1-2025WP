package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping()
    public String getDishesPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("dishes", dishService.listDishes());
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "listDishes";
    }

    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime, @RequestParam(required = false) Long chefId)
    {
        Dish newDish = dishService.create(dishId, name, cuisine, preparationTime, chefId);

        if (chefId != null) {
            chefService.addDishToChef(chefId, newDish.getDishId());
        }

        return "redirect:/dishes";
    }

    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id, @RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime, @RequestParam(required = false) Long chefId)
    {
        Dish updatedDish = dishService.update(id, dishId, name, cuisine, preparationTime, chefId);

        if (chefId != null) {
            chefService.addDishToChef(chefId, updatedDish.getDishId());
        }

        return "redirect:/dishes";
    }

    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id)
    {
        dishService.delete(id);
        return "redirect:/dishes";
    }

    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        Dish dish = dishService.findById(id).orElseThrow(RuntimeException::new);
        model.addAttribute("dish", dish);
        model.addAttribute("chefs", chefService.listChefs());
        model.addAttribute("isEdit", true);
        return "dish-form";
    }

    @GetMapping("/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("chefs", chefService.listChefs());
        model.addAttribute("isEdit", false);
        return "dish-form";
    }

    @PostMapping("/add-to-chef")
    public String addDishToChef(@RequestParam Long chefId,
                                @RequestParam String dishId,
                                @RequestParam String name,
                                @RequestParam String cuisine,
                                @RequestParam int preparationTime) {
        Dish created = dishService.create(dishId, name, cuisine, preparationTime, chefId);
        chefService.addDishToChef(chefId, created.getDishId());
        return "redirect:/dishes";
    }
}
