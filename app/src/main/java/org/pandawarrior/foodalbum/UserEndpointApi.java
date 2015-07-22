package org.pandawarrior.foodalbum;

import com.example.jt.myapplication.backend.userList.UserList;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

/**
 * Created by jt on 4/26/15.
 */
public class UserEndpointApi {
    private static UserList userListApiService;

    public static UserList getUserListApi(){
        if(userListApiService == null) {  // Only do this once
            UserList.Builder builder = new UserList.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")
                    .setRootUrl("https://solid-depot-92717.appspot.com/_ah/api/");
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(false);
//                        }
//                    });
            userListApiService = builder.build();
        }
        return userListApiService;
    }

}
