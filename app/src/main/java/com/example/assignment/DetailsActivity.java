package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.assignment.adapter.UserAdapter;
import com.example.assignment.adapter.UserDetailsAdapter;
import com.example.assignment.databinding.ActivityDetailsBinding;
import com.example.assignment.databinding.UserDetailsBinding;
import com.example.assignment.model.Followers;
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

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    String id, login;
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
        userDetailsBinding = DataBindingUtil.setContentView(this, R.layout.user_details);
        Intent details = getIntent();
        id = details.getStringExtra("id");
        login = details.getStringExtra("login");
        initViews();
        Retrofit retrofit = RetrofitClient.getInstance();
        service = retrofit.create(Service.class);
        load();
        userDetailsBinding.followers.setOnClickListener(this);
        userDetailsBinding.following.setOnClickListener(this);
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

                        if (userDetails.getName() == null) {
                            userDetailsBinding.name.setText("No name provided");
                        } else {
                            userDetailsBinding.name.setText(userDetails.getName());
                        }
                        if (userDetails.getCompany() == null) {
                            userDetailsBinding.company.setText("No company provided");
                        } else {
                            userDetailsBinding.company.setText(userDetails.getCompany());
                        }

                        if (userDetails.getLocation() == null) {
                            userDetailsBinding.name.setText("No location provided");
                        } else {
                            userDetailsBinding.location.setText(userDetails.getLocation());
                        }
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

    @Override
    public void onClick(View view) {
        if (view == userDetailsBinding.followers) {
            if (Integer.parseInt(userDetailsBinding.followers.getText().toString()) == 0) {
                Toast.makeText(this, "No Followers to Display", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(DetailsActivity.this, FollowersActivity.class);
                intent.putExtra("login", login);
                startActivity(intent);
            }

        }
        if (view == userDetailsBinding.following) {
            if (Integer.parseInt(userDetailsBinding.following.getText().toString()) == 0) {
                Toast.makeText(this, "No Following to Display", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(DetailsActivity.this, FollowingActivity.class);
                intent.putExtra("login", login);
                startActivity(intent);
            }

        }

    }
}
