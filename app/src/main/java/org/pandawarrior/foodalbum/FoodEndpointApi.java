package org.pandawarrior.foodalbum;

import com.example.jt.myapplication.backend.foodListApi.FoodListApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

/**
 * Created by jt on 4/26/15.
 */
public class FoodEndpointApi {
    private static FoodListApi foodListApi;

    public static FoodListApi getFoodListApi(){
        if(foodListApi == null) {  // Only do this once
            FoodListApi.Builder builder = new FoodListApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://solid-depot-92717.appspot.com/_ah/api/");
//                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });
            foodListApi = builder.build();
        }
        return foodListApi;
    }

}
