package com.example.assignment.model;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetails {

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("avatar_url")
    @Expose
    public  String avatarUrl;

    @SerializedName("location")
    @Expose
    public String location;

    @SerializedName("company")
    @Expose
    public String company;

    @SerializedName("public_repos")
    @Expose
    public String public_repos;

    @SerializedName("followers")
    @Expose
    public String followers;

    @SerializedName("following")
    @Expose
    public String following;

    public UserDetails() {
    }

    public UserDetails(String name,String avatarUrl, String location, String company, String public_repos, String followers, String following) {
        this.name = name;
        this.location = location;
        this.company = company;
        this.avatarUrl = avatarUrl;
        this.public_repos = public_repos;
        this.followers = followers;
        this.following = following;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(String public_repos) {
        this.public_repos = public_repos;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }
    @BindingAdapter("android:displayImage")
    public static void loadImage(View view, String imageUrl) {
        ImageView imageView= (ImageView) view;
        Glide.with(view.getContext()).load(imageUrl).into(imageView);
    }
}
