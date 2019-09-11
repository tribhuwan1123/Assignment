package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.assignment.adapter.UserAdapter;
import com.example.assignment.adapter.UserDetailsAdapter;
import com.example.assignment.databinding.ActivityDetailsBinding;
import com.example.assignment.databinding.UserDetailsBinding;
import com.example.assignment.model.User;
import com.example.assignment.model.UserDetails;
import com.example.assignment.routes.RetrofitClient;
import com.example.assignment.routes.Service;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class DetailsActivity extends AppCompatActivity {
    String id;
    Service service;
    ProgressDialog progressDialog;
    ActivityDetailsBinding detailsBinding;
    UserDetailsBinding userDetailsBinding;
    UserDetailsAdapter userAdapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        userDetailsBinding=DataBindingUtil.setContentView(this,R.layout.user_details);
        Intent details = getIntent();
        id = details.getStringExtra("id");
        initViews();
        Retrofit retrofit = RetrofitClient.getInstance();
        service = retrofit.create(Service.class);
        load();


    }

    private void initViews() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting Users Details");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void load() {
        compositeDisposable.add(service.getUserDetail(Integer.parseInt(id))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<UserDetails>() {
            @Override
            public void accept(UserDetails userDetails) throws Exception {
                userDetailsBinding.name.setText(userDetails.getName());
                userDetailsBinding.location.setText(userDetails.getLocation());
                userDetailsBinding.company.setText(userDetails.getCompany());
                userDetailsBinding.followers.setText(userDetails.getFollowers());
                userDetailsBinding.following.setText(userDetails.getFollowing());
                userDetailsBinding.publicRepos.setText(userDetails.getPublic_repos());
                Glide.with(getApplicationContext()).load(userDetails.getAvatarUrl()).into(userDetailsBinding.userImage);
                Toast.makeText(DetailsActivity.this, id, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }));
    }



    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}
