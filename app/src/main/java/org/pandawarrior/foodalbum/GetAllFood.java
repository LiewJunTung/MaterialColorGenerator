package org.pandawarrior.foodalbum;

import android.os.AsyncTask;

import com.example.jt.myapplication.backend.foodListApi.FoodListApi;
import com.example.jt.myapplication.backend.foodListApi.model.Food;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by jt on 4/25/15.
 */
public class GetAllFood extends AsyncTask<Void, Void, List<Food>> {

    @Override
    protected List<Food> doInBackground(Void... params) {
        FoodListApi foodListApi = FoodEndpointApi.getFoodListApi();
        List<Food> foods = new ArrayList<>();
        try {
            foods = foodListApi.getAllFood().execute().getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foods;
    }

    @Override
    protected void onPostExecute(List<Food> foods) {
        EventBus.getDefault().post(new Event(foods));
    }

    public class Event {
        private List<Food> foods;
        public Event(List<Food> foods) {
            this.foods = foods;
        }
        public List<Food> getFoods() {
            return foods;
        }
    }
}
