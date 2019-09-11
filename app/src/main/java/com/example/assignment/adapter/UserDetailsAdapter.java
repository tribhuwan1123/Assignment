package com.example.assignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.DetailsActivity;
import com.example.assignment.R;
import com.example.assignment.databinding.UserDetailsBinding;
import com.example.assignment.databinding.UsersListBinding;
import com.example.assignment.model.User;
import com.example.assignment.model.UserDetails;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsAdapter.ViewHolder> {

    private List<UserDetails> userList;
    Context context;

    public UserDetailsAdapter(Context context, List<UserDetails> userList) {
        this.userList = userList;
        this.context = context;
    }

    public UserDetailsAdapter() {

    }

    @NonNull
    @Override
    public UserDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserDetailsBinding userDetailsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.user_details, parent, false);
        return new ViewHolder(userDetailsBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull UserDetailsAdapter.ViewHolder holder, int position) {
        UserDetails user = userList.get(position);
        holder.userDetailsBinding.setUserDetails(user);


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        UserDetailsBinding userDetailsBinding;

        public ViewHolder(@NonNull UserDetailsBinding itemView) {
            super(itemView.getRoot());
            userDetailsBinding = itemView;

        }


    }
}
