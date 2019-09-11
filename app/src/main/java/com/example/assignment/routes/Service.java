package com.example.assignment.routes;

import com.example.assignment.model.Followers;
import com.example.assignment.model.User;
import com.example.assignment.model.UserDetails;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("users")
    Observable<List<User>> getUsers();

    @GET("users/{id}")
    Observable<UserDetails>getUserDetail(@Path("id") int userId);

    @GET("users/mojombo/followers")
    Observable<List<Followers>> getFollowers();

}
