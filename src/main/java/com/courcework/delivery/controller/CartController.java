package com.courcework.delivery.controller;

import com.courcework.delivery.exception.CartNotFoundException;
import com.courcework.delivery.exception.DishNotFoundException;
import com.courcework.delivery.exception.RestaurantNotFoundException;
import com.courcework.delivery.model.Cart;
import com.courcework.delivery.model.Dish;
import com.courcework.delivery.model.Restaurant;
import com.courcework.delivery.model.User;
import com.courcework.delivery.service.CartService;
import com.courcework.delivery.service.DishService;
import com.courcework.delivery.service.RestaurantService;
import com.courcework.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private DishService dishService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/carts")
    public List<Cart> getAllCarts(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            return cartService.getCartsByUserId(userId);
        } else {
            return cartService.getAllCarts();
        }
    }

    @PostMapping(value = "/cart/{userId}/{dishId}/{restaurantId}")
    public Cart addCart(@RequestBody Cart newCart,
                        @PathVariable Long userId,
                        @PathVariable Long dishId,
                        @PathVariable Long restaurantId) throws DishNotFoundException, RestaurantNotFoundException {
        User user = userService.getUserById(userId);
        Dish dish = dishService.getDishById(dishId);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        newCart.setUser(user);
        newCart.setDish(dish);
        newCart.setRestaurant(restaurant);
        return cartService.addCart(newCart);
    }



    @DeleteMapping("/cart/{id}")
    public String deleteCart(@PathVariable Long id) throws CartNotFoundException {
        return cartService.deleteCart(id);
    }

    @GetMapping("/carts/user/{userId}")
    public List<Cart> getCartsByUserId(@PathVariable Long userId) {
        return cartService.getCartsByUserId(userId);
    }

    @PutMapping("/cart/{id}")
    public Cart updateCart(@RequestBody Cart newCart, @PathVariable Long id) throws CartNotFoundException {
        return cartService.update(newCart, id);
    }
}
