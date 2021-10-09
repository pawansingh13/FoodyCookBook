package com.app.foodycookbook.networking;

import com.app.foodycookbook.feature.meal.Meals;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/*This is the service interface in which all the method define which will used for data...*/
public interface ApiService {

    @GET("random.php")
    Observable<Meals> doGetMeals();


    @GET("search.php?")
    Observable<Meals> doSearch(@Query("s") String searchkey);

//    @POST("{endPoint}")
//    Observable<ApproveOrDeclineModel> doDeclineOrderDetails(@Path("endPoint") String endPoint, @Body ApproveOrDeclineModel.PostDeclineModel model);
}
