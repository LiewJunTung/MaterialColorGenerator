package com.example.jt.myapplication.backend;

import java.util.ArrayList;

/**
 * Created by jt on 4/26/15.
 */
public class FoodList {
    private ArrayList<Food> foods;

    public FoodList(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public FoodList() {
        this.foods = new ArrayList<>();
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }
}
