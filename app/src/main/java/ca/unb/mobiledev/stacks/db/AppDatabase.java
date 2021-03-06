package ca.unb.mobiledev.stacks.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ca.unb.mobiledev.stacks.entity.Category;
import ca.unb.mobiledev.stacks.entity.Item;
import ca.unb.mobiledev.stacks.dao.ItemDao;
import ca.unb.mobiledev.stacks.dao.CategoryDao;

/**
 * Database layer in top of the SQLite database
 */
@Database(entities = {Item.class, Category.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public abstract ItemDao itemDao();
    public abstract CategoryDao categoryDao();

    // Define an ExecutorService with a fixed thread pool which is used to run database operations asynchronously on a background thread
    public static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Singleton access to the database
    public static AppDatabase getDatabase(final Context context) {
        if (null == INSTANCE) {
            synchronized (AppDatabase.class) {
                if (null == INSTANCE) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }

}

