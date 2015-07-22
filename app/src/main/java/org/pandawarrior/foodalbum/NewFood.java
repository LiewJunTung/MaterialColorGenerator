package org.pandawarrior.foodalbum;

import android.os.AsyncTask;

import com.example.jt.myapplication.backend.foodListApi.FoodListApi;

import java.io.IOException;

import de.greenrobot.event.EventBus;

/**
 * Created by jt on 4/25/15.
 */
public class NewFood extends AsyncTask<String, Void, Integer> {

    @Override
    protected Integer doInBackground(String... params) {
        FoodListApi foodListApi = FoodEndpointApi.getFoodListApi();
        int id = 0;
        try {
            id = foodListApi.newFood(params[0], params[1]).execute().getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        EventBus.getDefault().post(new Event(integer));
    }

    public class Event {
        public Integer Id;
        public Event(Integer id) {
            Id = id;
        }
        public Integer getId() {
            return Id;
        }
    }
}
