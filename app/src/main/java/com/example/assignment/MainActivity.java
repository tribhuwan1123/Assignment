package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.adapter.UserAdapter;
import com.example.assignment.model.User;
import com.example.assignment.routes.Client;
import com.example.assignment.routes.RetrofitClient;
import com.example.assignment.routes.Service;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvUsers;
    TextView internet;
    ProgressDialog progressDialog;
    SwipeRefreshLayout refreshLayout;
    UserAdapter userAdapter;
    Service service;
    CompositeDisposable compositeDisposable= new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit= RetrofitClient.getInstance();
        service= retrofit.create(Service.class);
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
       compositeDisposable.add(service.getUsers()
       .subscribeOn(Schedulers.io())
       .observeOn(AndroidSchedulers.mainThread())
       .subscribe(new Consumer<List<User>>() {
           @Override
           public void accept(List<User> userList) throws Exception {
               displayData(userList);
           }
       }));



//        try {
//            Client client = new Client();
//            Service service = client.getClient().create(Service.class);
//            Call<List<User>> call = service.getUsers();
//            call.enqueue(new Callback<List<User>>() {
//                @Override
//                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                    List<User> users = response.body();
//                    userAdapter=new UserAdapter(getApplicationContext(), users);
//                    rvUsers.setAdapter(userAdapter);
//                    rvUsers.smoothScrollToPosition(0);
//                    refreshLayout.setRefreshing(false);
//                    progressDialog.hide();
//                }
//
//                @Override
//                public void onFailure(Call<List<User>> call, Throwable t) {
//
//                }
//            });
//        }catch (Exception e){
//            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//        }

    }

    private void displayData(List<User> userList) {
        userAdapter=new UserAdapter(getApplicationContext(), userList);
                    rvUsers.setAdapter(userAdapter);
                    rvUsers.smoothScrollToPosition(0);
                    refreshLayout.setRefreshing(false);
                    progressDialog.hide();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.searchAction);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}
