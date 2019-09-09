package com.example.assignment.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.assignment.model.User;

public class UserViewModel extends ViewModel {
    String username,gitLink,imageUrl;

    public UserViewModel(User user) {
        this.username = username;
        this.gitLink = gitLink;
        this.imageUrl = imageUrl;
    }

    public UserViewModel() {
    }
}
