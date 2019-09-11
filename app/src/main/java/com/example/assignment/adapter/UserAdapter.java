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
import com.example.assignment.databinding.UsersListBinding;
import com.example.assignment.model.User;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> implements Filterable {

    private List<User> userList;
    private List<User> userListfull;
    Context context;
    public UserAdapter(Context context, List<User> userList){
        this.userList=userList;
        this.context=context;
        userListfull= new ArrayList<>(userList);
    }
    public UserAdapter(){

    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UsersListBinding usersListBinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),R.layout.users_list,parent,false);
        return new ViewHolder(usersListBinding);
//        View view= LayoutInflater.from(context).inflate(R.layout.users_list,parent,false);
//        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user= userList.get(position);
        holder.usersListBinding.setUser(user);

//        holder.username.setText(user.getLogin());
//        holder.gitLink.setText(user.getHtmlUrl());
//        Picasso.get().load(user.getAvatarUrl()).placeholder(R.mipmap.ic_launcher).into(holder.profileImage);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public Filter getFilter() {
        return userFilter;
    }
    private Filter userFilter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<User> filteredUser= new ArrayList<>();
            if (charSequence==null||charSequence.length()==0){
                filteredUser.addAll(userListfull);
            }else{
                String filterPattern=charSequence.toString().toLowerCase().trim();
                for (User item:userListfull){
                    if (item.getLogin().toLowerCase().contains(filterPattern)){
                        filteredUser.add(item);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredUser;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            userList.clear();
            userList.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder  {
        CircleImageView profileImage;
        TextView username,gitLink;
        UsersListBinding usersListBinding;

        public ViewHolder(@NonNull UsersListBinding itemView)  {
            super(itemView.getRoot());
            usersListBinding=itemView;
            usersListBinding.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION){
                        Intent intent= new Intent(context, DetailsActivity.class);
                        intent.putExtra("id",userList.get(position).getId());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(view.getContext(), "Clicked ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    }
}
