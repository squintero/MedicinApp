package com.squintero.medicinapp.data.roomDao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.squintero.medicinapp.data.model.PersonData;

import java.util.List;

@Dao
public interface PersonDao {

    @Query("SELECT * FROM persons")
    List<PersonData> getAll();

    @Query("SELECT * FROM persons WHERE id IN (:personDataIds)")
    List<PersonData> loadAllByIds(int[] personDataIds);

    @Query("SELECT * FROM persons WHERE id = :idToFind LIMIT 1")
    PersonData findOneById(int idToFind);

    @Insert
    void insertAll(PersonData... personDatas);

    @Delete
    void delete(PersonData personData);
}
