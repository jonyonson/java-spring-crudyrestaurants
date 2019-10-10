package com.lambdaschool.crudyrestaurants.controllers;

import com.lambdaschool.crudyrestaurants.models.Restaurant;
import com.lambdaschool.crudyrestaurants.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController
{
    @Autowired
    private RestaurantService restaurantService;

    // GET http://localhost:2019/restaurants/restaurants
    @GetMapping(value = "/restaurants",
        produces = {"application/json"})
    public ResponseEntity<?> listAllRestaurants()
    {
        List<Restaurant> myList = restaurantService.findall();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    // GET http://localhost:2019/restaurants/restaurant/7
    @GetMapping(value = "/restaurant/{restaurantid}",
        produces = {"application/json"})
    public ResponseEntity<?> findById(@PathVariable long restaurantid)
    {
        Restaurant myRest = restaurantService.findById(restaurantid);
        return new ResponseEntity<>(myRest, HttpStatus.OK);
    }


    // POST http://localhost:2019/restaurants/restaurant
    @PostMapping(value = "/restaurant",
        consumes = {"application/json"})
    public ResponseEntity<?> addNewRestaurant(@Valid
                                              @RequestBody Restaurant newRestaurant)
    {
        restaurantService.save(newRestaurant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/restaurants/restaurant/7
    @PutMapping(value = "/restaurant/{restaurantid}",
        consumes = {"application/json"})
    public ResponseEntity<?> updateARestaurant(@RequestBody Restaurant restaurant,
                                               @PathVariable long restaurantid)
    {
        restaurantService.update(restaurant, restaurantid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:2019/restaurants/restaurant/7
    @DeleteMapping(value = "/restaurants/{restaurantid}")
    public ResponseEntity<?> deleteRestaurantById(@PathVariable long restaurantid)
    {
        restaurantService.delete(restaurantid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
