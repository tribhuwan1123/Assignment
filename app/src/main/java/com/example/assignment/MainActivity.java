package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.adapter.UserAdapter;
import com.example.assignment.model.User;
import com.example.assignment.routes.Client;
import com.example.assignment.routes.Service;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvUsers;
    TextView internet;
    ProgressDialog progressDialog;
    SwipeRefreshLayout refreshLayout;
    List<User> userList;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        refreshLayout=(SwipeRefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        internet=(TextView)findViewById(R.id.internet);
        load();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
                Toast.makeText(MainActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void load() {
        try {
            Client client = new Client();
            Service service = client.getClient().create(Service.class);
            Call<List<User>> call = service.getUsers();
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    List<User> users = response.body();
                    rvUsers.setAdapter(new UserAdapter(getApplicationContext(), users));
                    rvUsers.smoothScrollToPosition(0);
                    refreshLayout.setRefreshing(false);
                    progressDialog.hide();
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {

                }
            });
        }catch (Exception e){
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void initViews() {
        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Getting Users");
        progressDialog.setCancelable(false);
        progressDialog.show();
        rvUsers=(RecyclerView)findViewById(R.id.rvUsers);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.smoothScrollToPosition(0);
    }
}
