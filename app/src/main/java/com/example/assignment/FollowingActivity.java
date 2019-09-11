package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.assignment.adapter.FollowersAdapter;
import com.example.assignment.adapter.FollowingAdapter;
import com.example.assignment.databinding.ActivityFollowingBinding;
import com.example.assignment.model.Followers;
import com.example.assignment.model.Following;
import com.example.assignment.routes.RetrofitClient;
import com.example.assignment.routes.Service;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class FollowingActivity extends AppCompatActivity {

    private ActivityFollowingBinding followingBinding;
    ProgressDialog progressDialog;
    FollowingAdapter followingAdapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Service service;
    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        followingBinding = DataBindingUtil.setContentView(this, R.layout.activity_following);
        Intent intent = getIntent();
        login = intent.getStringExtra("login");
        initViews();
        Retrofit retrofit = RetrofitClient.getInstance();
        service = retrofit.create(Service.class);
        load();
    }

    private void initViews() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting Followings");
        progressDialog.setCancelable(false);
        progressDialog.show();
        followingBinding.rvFollowing.setLayoutManager(new LinearLayoutManager(this));
        followingBinding.rvFollowing.smoothScrollToPosition(0);
    }

    private void load() {
        compositeDisposable.add(service.getFollowing(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Following>>() {
                    @Override
                    public void accept(List<Following> followings) throws Exception {
                        displayFollowing(followings);
                    }
                }));

    }

    private void displayFollowing(List<Following> followings) {
        followingAdapter = new FollowingAdapter(getApplicationContext(), followings);
        followingBinding.rvFollowing.setAdapter(followingAdapter);
        followingBinding.rvFollowing.smoothScrollToPosition(0);
        progressDialog.dismiss();
    }

}
