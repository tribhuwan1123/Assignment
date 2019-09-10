package com.example.assignment.routes;

import com.example.assignment.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("users")
    Observable<List<User>> getUsers();


}
