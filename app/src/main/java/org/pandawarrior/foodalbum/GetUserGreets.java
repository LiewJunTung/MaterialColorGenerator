package org.pandawarrior.foodalbum;

import android.os.AsyncTask;

import com.example.jt.myapplication.backend.userList.UserList;
import com.example.jt.myapplication.backend.userList.model.User;

import java.io.IOException;

import de.greenrobot.event.EventBus;

/**
 * Created by jt on 4/28/15.
 */
public class GetUserGreets extends AsyncTask<Void, Void, String> {
    @Override
    protected String doInBackground(Void... params) {
        UserList userListApi = UserEndpointApi.getUserListApi();
        try {
            User user = userListApi.authUser().execute();
            return user.getName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        EventBus.getDefault().post(new Event(s));
    }

    public class Event {
        public String email;
        public Event(String email) {
            this.email = email;
        }
        public String getEmail() {
            return email;
        }
    }
}
