package com.example.assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.databinding.FollowersListBinding;
import com.example.assignment.databinding.UserDetailsBinding;
import com.example.assignment.model.Followers;

import java.util.List;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.ViewHolder> {

    private List<Followers> followersList;
    Context context;

    public FollowersAdapter(Context context, List<Followers> followersList) {
        this.followersList = followersList;
        this.context = context;
    }

    public FollowersAdapter() {

    }

    @NonNull
    @Override
    public FollowersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FollowersListBinding followersListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.followers_list, parent, false);
        return new ViewHolder(followersListBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull FollowersAdapter.ViewHolder holder, int position) {
        Followers followers = followersList.get(position);
        holder.followersListBinding.setFollowers(followers);


    }

    @Override
    public int getItemCount() {
        return followersList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        FollowersListBinding followersListBinding;

        public ViewHolder(@NonNull FollowersListBinding itemView) {
            super(itemView.getRoot());
            followersListBinding = itemView;

        }


    }
}
