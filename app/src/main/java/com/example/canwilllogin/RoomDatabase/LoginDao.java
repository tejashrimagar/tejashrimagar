package com.example.canwilllogin.RoomDatabase;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LoginDao {

    @Insert
    void insertDetails(LoginTable data);

    @Query("select * from LoginDetails")
    LiveData<List<LoginTable>> getDetails();

}
