package com.homeless.jackrututorialretrofit.remote;


import com.homeless.jackrututorialretrofit.model.ResObj;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface  UserService {

    @GET("/Meterial/{username}/{password}  ")
    Call<ResObj> login(@Path("username") String username,@Path("password") String password);
}
