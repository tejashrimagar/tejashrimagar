package com.example.canwilllogin.RoomDatabase;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "LoginDetails")
public class LoginTable {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int id;

    @ColumnInfo(name = "UserName")
    private String userName;

    @ColumnInfo(name = "Password")
    private String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String mUserName) {
        userName = mUserName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String mPassword) {
        password = mPassword;
    }
}

