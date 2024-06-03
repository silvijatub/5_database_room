package com.example.a5_database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDAO {

    @Insert
    void insert (Person person);
    @Query("DELETE FROM Person")
    void deleteAll();
    @Query("SELECT * from Person ORDER BY first_name ASC")
    List<Person> getAllPersons();
    @Query("DELETE FROM Person WHERE id = (SELECT MAX(id) FROM Person)")
    void deleteLastRecord();


}
