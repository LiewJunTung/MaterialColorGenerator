/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.jt.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

import java.util.ArrayList;

/**
 * An endpoint class we are exposing
 */
@Api(name = "userList", version = "v1",
        namespace = @ApiNamespace(ownerDomain = "backend.myapplication.jt.example.com",
        ownerName = "backend.myapplication.jt.example.com", packagePath = ""),
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.ANDROID_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE})

public class UserList {
    ArrayList<User> users = new ArrayList<>();

    @ApiMethod(name = "getAllUser")
    public ArrayList<User> getAllUser() {
        return users;
    }

    @ApiMethod(name = "getUser")
    public User getUser(@Named("id") int id) {
        User result = null;
        for (User user : users) {
            if (user.getId() == id) {
                result = user;
                break;
            }
        }
        return result;
    }

    @ApiMethod(name = "newUser", httpMethod = "POST")
    public User postUser(@Named("name") String name) {
        int id = users.size() + 1;
        User user = new User(id, name);
        users.add(user);
        return user;
    }

    @ApiMethod(name = "updateUser", httpMethod = "PUT")
    public User updateUser(@Named("id") int id, @Named("name") String name) {
        User user = getUser(id);
        user.setName(name);
        return user;
    }

    @ApiMethod(name = "deleteUser", httpMethod = "DELETE")
    public User deleteUser(@Named("id") int id) {
        User user = getUser(id);
        users.remove(user);
        return user;
    }

    @ApiMethod(name = "authUser", path = "authUser/authed")
    public User authUser(com.google.appengine.api.users.User user) {
        int id = users.size() + 1;
        User user1 = new User(id, user.getEmail());
        users.add(user1);
        return user1;
    }

}
