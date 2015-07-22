package org.pandawarrior.foodalbum;

import android.os.AsyncTask;

import com.example.jt.myapplication.backend.userList.UserList;

import java.io.IOException;

import de.greenrobot.event.EventBus;

/**
 * Created by jt on 4/25/15.
 */
public class NewUser extends AsyncTask<String, Void, Integer> {

    @Override
    protected Integer doInBackground(String... params) {
        UserList userListApi = UserEndpointApi.getUserListApi();
        int id = 0;
        try {
            id = userListApi.newUser(params[0]).execute().getId();
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
