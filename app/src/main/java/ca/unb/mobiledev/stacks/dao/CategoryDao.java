package ca.unb.mobiledev.stacks.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import ca.unb.mobiledev.stacks.entity.Category;

/**
 * This DAO object validates the SQL at compile-time and associates it with a method
 */
@Dao
public interface CategoryDao {
    @Query("SELECT * from category_table ORDER BY id ASC")
    LiveData<List<Category>> listAllCategories();

    @Query("SELECT * from category_table WHERE name = :nName ORDER BY CAST(id AS int)")
    List<Category> searchCategories(String nName);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRecord(Category category);
}
