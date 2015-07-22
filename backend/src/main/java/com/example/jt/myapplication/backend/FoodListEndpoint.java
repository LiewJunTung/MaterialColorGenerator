package com.example.jt.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "foodListApi",
        version = "v1",
        resource = "foodList",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.ANDROID_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE},
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.jt.example.com",
                ownerName = "backend.myapplication.jt.example.com",
                packagePath = ""
        )
)
public class FoodListEndpoint {

    private static final Logger logger = Logger.getLogger(FoodListEndpoint.class.getName());
    private ArrayList<Food> foods = new ArrayList<>();

    @ApiMethod(name = "getAllFood")
    public ArrayList<Food> getAllFood(){
        return foods;
    }

    @ApiMethod(name = "getFood")
    public Food getFood(@Named("id") int id){
        Food result = null;
        for(Food food: foods){
            if (food.getId() == id){
                result = food;
                break;
            }
        }
        return result;
    }

    @ApiMethod(name = "newFood", httpMethod = "POST")
    public Food postFood(@Named("name") String name, @Named("picture") String picture){
        int id = foods.size() + 1;
        Food food = new Food(id, name, picture);
        foods.add(food);
        return food;
    }

    @ApiMethod(name = "updateFood", httpMethod = "PUT")
    public Food updateFood(@Named("id") int id, @Named("name") String name, @Named("picture") String picture){
        Food food = getFood(id);
        food.setName(name);
        food.setPicture(picture);
        return food;
    }

    @ApiMethod(name = "deleteFood", httpMethod = "DELETE")
    public Food deleteFood(@com.google.api.server.spi.config.Named("id") int id){
        Food food = getFood(id);
        foods.remove(food);
        return food;
    }
}