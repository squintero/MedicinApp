package com.squintero.medicinapp.data.roomDao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.squintero.medicinapp.data.model.MedicineData;
import com.squintero.medicinapp.data.model.PersonData;

import java.util.List;

@Dao
public interface MedicineDao {

    @Query("SELECT * FROM medicines")
    List<MedicineData> getAll();

    @Query("SELECT * FROM medicines WHERE id IN (:personDataIds)")
    List<MedicineData> loadAllByIds(int[] personDataIds);

    @Query("SELECT * FROM medicines WHERE id = :idToFind LIMIT 1")
    MedicineData findOneById(int idToFind);

    @Insert
    void insertAll(MedicineData... medicineDatas);

    @Delete
    void delete(MedicineData medicineData);
}
