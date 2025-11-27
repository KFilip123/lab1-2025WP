package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;

import java.io.IOException;

@WebServlet(name = "delete-dish-servlet", urlPatterns = "/chefDetails")
public class DeleteDishServlet extends HttpServlet {

    private final DishService dishService;
    private final ChefService chefService;

    public DeleteDishServlet(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dishId = req.getParameter("dishId");
        Long chefId = Long.valueOf(req.getParameter("chefId"));
        Chef chef = chefService.findById(chefId);
        Dish dish = dishService.findByDishId(dishId);
        chef.getDishes().remove(dish);
        resp.sendRedirect("/chefDetails?chefId=" + chef.getId());
    }
}