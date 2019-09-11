package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.assignment.adapter.FollowersAdapter;
import com.example.assignment.adapter.UserDetailsAdapter;
import com.example.assignment.databinding.ActivityFollowersBinding;
import com.example.assignment.model.Followers;
import com.example.assignment.model.UserDetails;
import com.example.assignment.routes.RetrofitClient;
import com.example.assignment.routes.Service;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class FollowersActivity extends AppCompatActivity {

    private ActivityFollowersBinding followersBinding;
    ProgressDialog progressDialog;
    FollowersAdapter followersAdapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        followersBinding= DataBindingUtil.setContentView(this,R.layout.activity_followers);
        initViews();
        Retrofit retrofit = RetrofitClient.getInstance();
        service = retrofit.create(Service.class);

        load();

    }
    private void initViews() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting Followers");
        progressDialog.setCancelable(false);
        progressDialog.show();
        followersBinding.rvFollowers.setLayoutManager(new LinearLayoutManager(this));
        followersBinding.rvFollowers.smoothScrollToPosition(0);
    }

    private void load() {
        compositeDisposable.add(service.getFollowers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Followers>>() {
                    @Override
                    public void accept(List<Followers> followers) throws Exception {
                        displayFolloers(followers);
                    }
                }));

    }

    private void displayFolloers(List<Followers> followers) {
        followersAdapter = new FollowersAdapter(getApplicationContext(), followers);
        followersBinding.rvFollowers.setAdapter(followersAdapter);
        followersBinding.rvFollowers.smoothScrollToPosition(0);
        progressDialog.dismiss();
    }
}
