package com.homeless.jackrututorialretrofit.remote;

import retrofit2.Retrofit;

public class ApiUtils {




    public static final String BASE_URL = "http://10.10.3.158:8000/dfd";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);

    }
}
