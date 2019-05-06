package com.ihm.project.menumaker.models;

import android.content.ContentResolver;
import android.provider.ContactsContract;
import java.util.ArrayList;
import java.util.List;


public class UserModel {

    private static List<User> users = new ArrayList<>();

    public static void addUser(User user) {
        UserModel.users.add(user);
    }


    public class User {

    }
}
