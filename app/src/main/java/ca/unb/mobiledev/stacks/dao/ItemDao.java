package ca.unb.mobiledev.stacks.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ca.unb.mobiledev.stacks.entity.Item;

/**
 * This DAO object validates the SQL at compile-time and associates it with a method
 */
@Dao
public interface ItemDao {

    @Query("SELECT * from item_table ORDER BY id ASC")
    LiveData<List<Item>> listAllItems();

    @Query("SELECT * from item_table WHERE name LIKE :nName ORDER BY CAST(id AS int)")
    List<Item> searchItems(String nName);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item item);


}
